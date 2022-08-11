package dev.foraged.settings.menu

import dev.foraged.settings.setting.SettingService
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import org.bukkit.entity.Player

class SettingsMenu : PaginatedMenu() {

    init {
        updateAfterClick = true
    }

    override fun getPrePaginatedTitle(player: Player) = "Settings Menu"

    override fun getAllPagesButtons(player: Player): Map<Int, Button> {
        return mutableMapOf<Int, Button>().also { buttons ->
            SettingService.settings.forEach {
                buttons[buttons.size] = it.getButtonBuilder(player.uniqueId).toButton { player, clickType ->
                    player ?: return@toButton
                    clickType ?: return@toButton

                    if (clickType.isLeftClick) {
                        it.nextSettingOption(player.uniqueId)
                    } else if (clickType.isRightClick) {
                        it.previousSettingOption(player.uniqueId)
                    }
                }
            }
        }
    }
}