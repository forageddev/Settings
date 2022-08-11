package dev.foraged.settings.setting.impl

import dev.foraged.settings.setting.Setting
import dev.foraged.settings.setting.SettingService
import java.util.*

abstract class ToggleSetting(
    id: String,
    defaultValue: Boolean,
) : Setting<Boolean>(id, defaultValue) {

    override fun getSettingValue(identifier: UUID): Boolean {
        return SettingService.fetchSettingMap(this)[identifier] ?: defaultValue
    }

    override fun setSettingValue(identifier: UUID, value: Boolean) : Boolean {
        SettingService.fetchSettingMap(this)[identifier] = value
        return value
    }

    override fun previousSettingOption(identifier: UUID): Boolean = setSettingValue(identifier, !getSettingValue(identifier))
    override fun nextSettingOption(identifier: UUID): Boolean = setSettingValue(identifier, !getSettingValue(identifier))
}
