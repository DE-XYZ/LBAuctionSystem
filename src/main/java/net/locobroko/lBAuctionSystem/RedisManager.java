package net.locobroko.lBAuctionSystem;

import net.locobroko.coresystem.api.redis.RedisAPI;

import java.util.Map;
import java.util.UUID;

public class RedisManager {

    private final RedisAPI redisAPI;

    public RedisManager(RedisAPI redisAPI) {
        this.redisAPI = redisAPI;
    }

    public void setPlayerOption(UUID uuid, String key, String value) {
        redisAPI.setPlayerOption(uuid, key, value);
    }

    public String getPlayerOption(UUID uuid, String key) {
        return redisAPI.getPlayerOption(uuid, key);
    }

    public Map<String, String> getPlayerOptions(UUID uuid) {
        return redisAPI.getPlayerOptions(uuid);
    }

    public void deletePlayerOption(UUID uuid, String key) {
        redisAPI.deletePlayerOption(uuid, key);
    }
}