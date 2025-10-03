package net.locobroko.lBAuctionSystem.commands.subcommands;

import net.locobroko.lBAuctionSystem.commands.SubCommand;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ViewCommand extends SubCommand {

    @Override
    public void execute(Player player, String[] args) {
        openAuctionHouseGUI(player);
    }

    private void openAuctionHouseGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 54, "§6§lAuktionshaus");

        // Inventar designen

        player.openInventory(gui);
    }
}