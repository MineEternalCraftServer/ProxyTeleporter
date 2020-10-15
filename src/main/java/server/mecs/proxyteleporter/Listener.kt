package server.mecs.proxyteleporter

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class Listener(private val plugin: ProxyTeleporter): Listener {
    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    fun onLogin(e: PlayerJoinEvent){
        
    }
}