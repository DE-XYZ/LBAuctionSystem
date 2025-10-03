package net.locobroko.lBAuctionSystem.commands.subcommands;

import net.locobroko.lBAuctionSystem.commands.SubCommand;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class DropboxCommand extends SubCommand {

    @Override
    public void execute(Player player, String[] args) {
        openDropboxGUI(player);
    }

    private void openDropboxGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "§3§lDropbox");

        // Hier verkaufte Items / Erlös des Spielers laden
        // Beispiel: gui.setItem(slot, soldItem);

        player.openInventory(gui);
        player.sendMessage("§aDropbox geöffnet! Hier findest du deine verkauften Items.");
    }
}
