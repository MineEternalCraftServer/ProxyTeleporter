package server.mecs.proxyteleporter

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import server.mecs.proxyteleporter.ItemUtil.LobbyServer
import server.mecs.proxyteleporter.ItemUtil.MinigameServer
import server.mecs.proxyteleporter.ItemUtil.RPGServer
import server.mecs.proxyteleporter.ItemUtil.Teleporter
import server.mecs.proxyteleporter.ItemUtil.createInventory

class Listener(private val plugin: ProxyTeleporter): Listener {
    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    fun onLogin(e: PlayerJoinEvent){
        e.player.inventory.setItem(0, Teleporter())
    }

    @EventHandler
    fun onLeave(e: PlayerQuitEvent){
        e.player.inventory.clear()
    }

    @EventHandler
    fun onOpen(e: PlayerInteractEvent){
        if (e.item?.isSimilar(Teleporter()) != true)return
        if ((e.action == Action.RIGHT_CLICK_AIR) || (e.action == Action.RIGHT_CLICK_BLOCK)) {
            val menu = createInventory("Server Menu", 54,
                    mapOf(13 to LobbyServer(), 29 to MinigameServer(), 33 to RPGServer())
            )
            e.player.openInventory(menu)
        }
    }

    @EventHandler
    fun onSelect(e: InventoryClickEvent){
        val player: Player? = e.whoClicked as? Player

        if (player?.openInventory?.title == "Server Menu"){

            e.isCancelled = true

            when(e.currentItem?.itemMeta?.displayName){

                LobbyServer()?.itemMeta?.displayName -> {
                    plugin.proxych.connect(player, "lobby")
                }

                MinigameServer()?.itemMeta?.displayName -> {
                    plugin.proxych.connect(player, "minigame")
                }

                RPGServer()?.itemMeta?.displayName -> {
                    plugin.proxych.connect(player, "rpg")
                }
            }
        }
    }
}