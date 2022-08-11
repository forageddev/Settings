package dev.foraged.settings.setting

import dev.foraged.commons.annotations.custom.CustomAnnotationProcessors
import dev.foraged.commons.persist.PersistMap
import dev.foraged.commons.persist.PluginService
import dev.foraged.commons.persist.impl.BooleanPersistMap
import dev.foraged.commons.persist.impl.EnumerablePersistMap
import dev.foraged.settings.SettingsExtendedPlugin
import dev.foraged.settings.setting.annotation.AutoRegisterSetting
import dev.foraged.settings.setting.impl.AdvancedSetting
import dev.foraged.settings.setting.impl.ToggleSetting
import gg.scala.flavor.inject.Inject
import gg.scala.flavor.service.Configure
import gg.scala.flavor.service.Service

@Service
object SettingService : PluginService {
    @Inject lateinit var plugin: SettingsExtendedPlugin
    val settings = mutableListOf<Setting<*>>()
    val settingMaps = mutableMapOf<String, PersistMap<*>>()

    @Configure
    override fun configure() {
        CustomAnnotationProcessors.process<AutoRegisterSetting> {
            if (it is AdvancedSetting<*>) registerSetting(it)
            if (it is ToggleSetting) registerSetting(it)
        }
    }

    @JvmName("registerSimpleSetting")
    fun registerSetting(setting: Setting<Boolean>) {
        settings.add(setting)
        settingMaps[setting.id] = object : BooleanPersistMap("Setting${setting.id}", "Setting${setting.id}", false) {
            override var usedGlobally = true
        }
        plugin.peristableMaps.add(settingMaps[setting.id]!!)
    }

    @JvmName("registerEnumSetting")
    inline fun <reified T : Enum<T>> registerSetting(setting: Setting<T>) {
        settings.add(setting)
        settingMaps[setting.id] = object : EnumerablePersistMap<T>("Setting${setting.id}", "Setting${setting.id}", false) {
            override var usedGlobally = true

            override fun getKotlinObject(str: String?): T {
                return kotlin.runCatching {
                    T::class.objectInstance?.declaringClass?.enumConstants?.toMutableList()?.find {
                        it.name == str
                    } ?: setting.defaultValue
                }.getOrNull() ?: setting.defaultValue
            }
        }
        plugin.peristableMaps.add(settingMaps[setting.id]!!)
    }

    inline fun <reified T> fetchSettingMap(setting: Setting<T>) : PersistMap<T> {
        return settingMaps[setting.id]!! as PersistMap<T>
    }

    fun fetchSettingMap(id: String) : PersistMap<*> {
        return settingMaps[id]!!
    }
}