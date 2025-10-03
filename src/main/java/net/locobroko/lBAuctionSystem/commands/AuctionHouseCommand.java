package net.locobroko.lBAuctionSystem.commands;

import net.locobroko.coresystem.api.coins.CoinAPI;
import net.locobroko.coresystem.chat.ChatManager;
import net.locobroko.lBAuctionSystem.RedisManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.locobroko.lBAuctionSystem.commands.subcommands.*;

public class AuctionHouseCommand implements CommandExecutor {

    private final ViewCommand viewCommand;
    private final DropboxCommand dropboxCommand;
    private final SellCommand sellCommand;

    private final CoinAPI coinAPI;
    private final RedisManager redisManager;

    public AuctionHouseCommand(CoinAPI coinAPI, RedisManager redisManager) {
        this.coinAPI = coinAPI;
        this.redisManager = redisManager;
        this.viewCommand = new ViewCommand();
        this.dropboxCommand = new DropboxCommand();
        this.sellCommand = new SellCommand(coinAPI, redisManager);
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) {
            ChatManager.sendTranslationMessage(
                    s,
                    "command.only-player"
            );
            return true;
        }

        Player p = (Player) s;

        if (args.length == 0) {
            ChatManager.sendTranslationMessage(
                    p,
                    "auction-system.commands.wrong-arguments"
            );
            return true;
        }

        String subCommand = args[0].toLowerCase();
        String[] subArgs = new String[args.length - 1];
        if (args.length > 1) {
            System.arraycopy(args, 1, subArgs, 0, args.length - 1);
        }


        switch (subCommand) {
            case "view":
            case "v":
                viewCommand.execute(p, subArgs);
                break;

            case "dropbox":
            case "d":
                dropboxCommand.execute(p, subArgs);
                break;

            case "sell":
            case "s":
                sellCommand.execute(p, subArgs);
                break;

            default:
                ChatManager.sendTranslationMessage(
                        p,
                        "auction-system.commands.wrong-arguments"
                );
                break;
        }

        return true;
    }
}
