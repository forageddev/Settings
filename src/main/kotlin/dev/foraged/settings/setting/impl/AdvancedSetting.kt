package dev.foraged.settings.setting.impl

import dev.foraged.commons.persist.PersistMap
import dev.foraged.settings.setting.Setting
import dev.foraged.settings.setting.SettingService
import java.util.*

abstract class AdvancedSetting<T : Enum<T>>(id: String, value: T) : Setting<T>(id, value) {
    override fun getSettingValue(identifier: UUID): T {
        return SettingService.fetchSettingMap(id)[identifier] as T? ?: defaultValue
    }

    override fun setSettingValue(identifier: UUID, value: T): T {
        (SettingService.fetchSettingMap(id) as PersistMap<T>)[identifier] = value
        return value
    }
}