package dev.foraged.settings.command

import dev.foraged.commons.acf.ConditionFailedException
import dev.foraged.commons.acf.annotation.CommandAlias
import dev.foraged.commons.acf.annotation.Description
import dev.foraged.commons.annotations.commands.AutoRegister
import dev.foraged.commons.command.GoodCommand
import dev.foraged.settings.SettingsExtendedPlugin
import dev.foraged.settings.configuration.SettingsConfiguration
import dev.foraged.settings.menu.SettingsMenu
import org.bukkit.entity.Player

@AutoRegister
class SettingsCommand : GoodCommand() {

    @CommandAlias("settings|options")
    @Description("Update your settings on the server")
    fun settings(player: Player) {
        if (!SettingsExtendedPlugin.instance.config<SettingsConfiguration>().enableSettingsMenu) throw ConditionFailedException("You cannot modify your settings on this server.")
        SettingsMenu().openMenu(player)
    }
}