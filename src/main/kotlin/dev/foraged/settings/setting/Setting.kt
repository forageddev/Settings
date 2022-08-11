package dev.foraged.settings.setting

import net.evilblock.cubed.serializers.impl.AbstractTypeSerializable
import net.evilblock.cubed.util.bukkit.ItemBuilder
import java.util.UUID

abstract class Setting<T>(var id: String, val defaultValue: T) : AbstractTypeSerializable
{
    abstract fun getSettingValue(identifier: UUID) : T
    abstract fun setSettingValue(identifier: UUID, value: T) : T
    abstract fun getButtonBuilder(identifier: UUID) : ItemBuilder

    abstract fun previousSettingOption(identifier: UUID) : T
    abstract fun nextSettingOption(identifier: UUID) : T
}