package derp.itemstats.events;


import derp.itemstats.event.PlayerKillEntityCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;

public class EntityKilled implements PlayerKillEntityCallback {
    public EntityKilled() {
    }

    public void killEntity(ServerPlayerEntity player, Entity killedEntity) {
        ItemStack stack = player.getMainHandStack();
        if (stack != null && (stack.getItem() instanceof SwordItem || stack.getItem() instanceof BowItem || stack.getItem() instanceof CrossbowItem || stack.getItem() instanceof TridentItem)) {
            long kill_players = 0L;
            long kill_mobs = 0L;
            NbtCompound tag = stack.getOrCreateNbt();
            if (killedEntity instanceof PlayerEntity) {
                if (tag.get("kill_players") != null) {
                    kill_players += tag.getLong("kill_players");
                    ++kill_players;
                    tag.putLong("kill_players", kill_players);
                } else {
                    ++kill_players;
                    tag.putLong("kill_players", kill_players);
                }
            } else if (tag.get("kills_mobs") != null) {
                kill_mobs += tag.getLong("kills_mobs");
                ++kill_mobs;
                tag.putLong("kills_mobs", kill_mobs);
            } else {
                ++kill_mobs;
                tag.putLong("kills_mobs", kill_mobs);
            }

            NbtList lore = new NbtList();
            lore.add(0, NbtString.of("{\"text\":\"\",\"bold\":false,\"italic\":false,\"color\":\"#8cff8f\"}"));
            lore.add(1, NbtString.of("{\"text\":\"Mobs killed: " + kill_mobs + "\",\"bold\":false,\"italic\":false,\"color\":\"#8cff8f\"}"));
            lore.add(2, NbtString.of("{\"text\":\"Players Killed: " + kill_players + "\",\"bold\":false,\"italic\":false,\"color\":\"#ff5454\"}"));
            NbtCompound display = new NbtCompound();
            display.put("Lore", lore);
            tag.put("display", display);
            stack.setNbt(tag);
        }

    }
}
