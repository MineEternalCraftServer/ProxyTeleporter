package server.mecs.proxyteleporter

import org.bukkit.plugin.java.JavaPlugin

class ProxyTeleporter : JavaPlugin() {

    lateinit var proxych: ProxyChannel

    override fun onEnable() {
        // Plugin startup logic
        proxych = ProxyChannel(this)
        Listener(this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}