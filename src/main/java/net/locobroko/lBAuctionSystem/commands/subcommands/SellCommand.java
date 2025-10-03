package net.locobroko.lBAuctionSystem.commands.subcommands;

import net.locobroko.coresystem.api.coins.CoinAPI;
import net.locobroko.coresystem.chat.ChatManager;
import net.locobroko.lBAuctionSystem.RedisManager;
import net.locobroko.lBAuctionSystem.commands.SubCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class SellCommand extends SubCommand {

    private final CoinAPI coinAPI;
    private final RedisManager redisManager;

    public SellCommand(CoinAPI coinAPI, RedisManager redisManager) {
        this.coinAPI = coinAPI;
        this.redisManager = redisManager;
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args.length < 2) {
            ChatManager.sendTranslationMessage(
                    p,
                    "auction-system.commands.wrong-arguments"
            );
            return;
        }

        try {
            long price = Long.parseLong(args[0]);
            int time = parseTime(args[1]);

            if (time == -1) {
                p.sendMessage("§cBitte gib eine gültige Zeit an! (1, 3, 6, 12, 24 oder 1h, 3h, 6h, 12h, 24h)");
                return;
            }

            sellItemInHand(p, price, time);
            System.out.println("Try: args[0]: " + args[0] + ", time: " + time);

        } catch (NumberFormatException e) {
            p.sendMessage("§cBitte gib einen gültigen Preis an!");
            System.out.println("Catch: args[0]: " + args[0]);
        }
    }

    private void sellItemInHand(Player p, long price, int time) {
        ItemStack item = p.getInventory().getItemInMainHand();
        UUID uuid = p.getUniqueId();

        if (item == null || item.getType().isAir()) {
            p.sendMessage("§cDu musst ein Item in der Hand haben!");
            return;
        }

        // Hier die Logik zum Verkaufen des Items
        // Item ins Auktionshaus einstellen, aus Inventar entfernen etc.

        System.out.println(redisManager.getPlayerOption(p.getUniqueId(), "auction_selling"));

        p.getInventory().setItemInMainHand(null);

        p.sendMessage("§aItem für §6" + price + "§a ins Auktionshaus eingestellt! (Dauer: " + time + "h)");
    }

    private int parseTime(String timeStr) {
        String normalized = timeStr.toLowerCase().replaceAll("h$", "");

        try {
            int time = Integer.parseInt(normalized);

            if (time == 1 || time == 3 || time == 6 || time == 12 || time == 24) {
                return time;
            }
        } catch (NumberFormatException e) {
            return -1;
        }

        return -1;
    }
}