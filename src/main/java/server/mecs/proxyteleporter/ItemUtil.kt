package server.mecs.proxyteleporter

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

object ItemUtil {

    val plugin: ProxyTeleporter? = null

    fun Teleporter(): ItemStack? {
        val item = ItemStack(Material.COMPASS)
        val itemmeta = item.itemMeta
        itemmeta.displayName = "§aServer Menu"
        itemmeta.lore = listOf("§b右クリックでサーバー選択画面が開きます。")
        item.itemMeta = itemmeta
        return item
    }

    fun LobbyServer(): ItemStack?{
        val item = ItemStack(Material.GRASS)
        val itemmeta = item.itemMeta
        itemmeta.displayName = "§aMain Lobby"
        itemmeta.lore = listOf(
                "§7メインロビーに帰還します。",
                "§7ロビーから様々なサーバーを選択できます。",
                "",
                "§7現在${plugin?.proxych?.getPlayerCount("lobby")}人のプレイヤーがいます。"
        )
        item.itemMeta = itemmeta
        return item
    }

    fun MinigameServer(): ItemStack?{
        val item = ItemStack(Material.DIAMOND_SWORD)
        val itemmeta = item.itemMeta
        itemmeta.displayName = "§aMini Game"
        itemmeta.lore = listOf(
                "§7FFAなどのPVPやその他ミニゲームが遊べます。",
                "",
                "§7現在${plugin?.proxych?.getPlayerCount("minigame")}人のプレイヤーがいます。"
        )
        itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        item.itemMeta = itemmeta
        return item
    }

    fun RPGServer(): ItemStack?{
        val item = ItemStack(Material.BARRIER)
        val itemmeta = item.itemMeta
        itemmeta.displayName = "§aRole Playing Game"
        itemmeta.lore = listOf(
                "§c現在開発中です。",
                "",
                "§7現在${plugin?.proxych?.getPlayerCount("rpg")}人のプレイヤーがいます。"
        )
        item.itemMeta = itemmeta
        return item
    }

    fun createInventory(name: String,size: Int,itemList: Map<Int, ItemStack?>): Inventory? {
        val inventory = Bukkit.createInventory(null,size,name)
        itemList.forEach { (index,element) ->
            inventory.setItem(index, element)
        }
        return inventory
    }
}