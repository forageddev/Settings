package dev.foraged.settings

import dev.foraged.commons.ExtendedPaperPlugin
import dev.foraged.commons.annotations.container.ContainerEnable
import dev.foraged.commons.annotations.custom.CustomAnnotationProcessors
import dev.foraged.commons.config.annotations.ContainerConfig
import dev.foraged.settings.configuration.SettingsConfiguration
import me.lucko.helper.plugin.ap.Plugin
import me.lucko.helper.plugin.ap.PluginDependency

@Plugin(
    name = "Settings",
    version = "\${git.commit.id.abbrev}",
    depends = [
        PluginDependency("Commons")
    ]
)
@ContainerConfig(
    value = "config",
    model = SettingsConfiguration::class,
    crossSync = false
)
class SettingsExtendedPlugin : ExtendedPaperPlugin()
{
    companion object {
        lateinit var instance: SettingsExtendedPlugin
    }

    @ContainerEnable
    fun containerEnable()
    {
        instance = this
    }
}
