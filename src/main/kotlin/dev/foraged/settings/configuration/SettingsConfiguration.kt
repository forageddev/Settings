package dev.foraged.settings.configuration

import xyz.mkotb.configapi.comment.HeaderComment

@HeaderComment("Enable features with the cosmetics plugin")
class SettingsConfiguration
{
    var enableSettingsMenu: Boolean = true
}