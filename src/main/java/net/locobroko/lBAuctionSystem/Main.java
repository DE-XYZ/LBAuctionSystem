package net.locobroko.lBAuctionSystem;

import lombok.Getter;
import net.locobroko.coresystem.CoreSystem;
import net.locobroko.coresystem.api.coins.CoinAPI;
import net.locobroko.coresystem.api.redis.RedisAPI;
import net.locobroko.lBAuctionSystem.commands.AuctionHouseCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;
    private CoinAPI coinAPI;
    private RedisManager redisManager;
    private static final Logger logger = LoggerFactory.getLogger(Main.class.getSimpleName());

    @Override
    public void onEnable() {
        instance = this;
        CoreSystem coreSystemPlugin = (CoreSystem) Bukkit.getServer().getPluginManager().getPlugin("CoreSystem");
        coinAPI = Bukkit.getServicesManager().load(CoinAPI.class);
        RedisAPI redisAPI = Bukkit.getServicesManager().load(RedisAPI.class);
        redisManager = new RedisManager(redisAPI);

        logger.info("  _      ____                    _   _              _____           _                 ");
        logger.info(" | |    |  _ \\   /\\             | | (_)            / ____|         | |                ");
        logger.info(" | |    | |_) | /  \\  _   _  ___| |_ _  ___  _ __ | (___  _   _ ___| |_ ___ _ __ ___  ");
        logger.info(" | |    |  _ < / /\\ \\| | | |/ __| __| |/ _ \\| '_ \\ \\___ \\| | | / __| __/ _ \\ '_ ` _ \\ ");
        logger.info(" | |____| |_) / ____ \\ |_| | (__| |_| | (_) | | | |____) | |_| \\__ \\ ||  __/ | | | | |");
        logger.info(" |______|____/_/    \\_\\__,_|\\___|\\__|_|\\___/|_| |_|_____/ \\__, |___/\\__\\___|_| |_| |_|");
        logger.info("                                                           __/ |                      ");
        logger.info("                                                          |___/                       ");
        logger.info(" ");

        if (coreSystemPlugin == null || !coreSystemPlugin.isEnabled()) {
            logger.info("CoreSystem is not loaded!");
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            logger.info("CoreSystem is loaded!");
            load();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Auction House Plugin deactivated!");
    }

    private void load() {
        this.getCommand("ah").setExecutor(new AuctionHouseCommand(coinAPI, redisManager));
    }
}