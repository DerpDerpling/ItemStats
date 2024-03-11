package derp.itemstats.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerKillEntityCallback {
    Event<PlayerKillEntityCallback> EVENT = EventFactory.createArrayBacked(PlayerKillEntityCallback.class, (listeners) -> {
        return (player, killedEntity) -> {
            PlayerKillEntityCallback[] var3 = listeners;
            int var4 = listeners.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                PlayerKillEntityCallback listener = var3[var5];
                listener.killEntity(player, killedEntity);
            }

        };
    });

    void killEntity(ServerPlayerEntity var1, Entity var2);
}
