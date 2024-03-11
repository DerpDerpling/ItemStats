package derp.itemstats.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class NBTUtils {
    public NBTUtils() {
    }

    public static NbtList getLore(ItemStack item) {
        NbtCompound tag = item.getOrCreateNbt();
        if (tag != null) {
            NbtCompound display = tag.getCompound("display");
            if (display != null) {
                return display.getList("Lore", 9);
            }
        }

        return new NbtList();
    }

    public static void setLore(ItemStack item, NbtList list) {
        NbtCompound tag = item.getOrCreateNbt();
        NbtCompound display = new NbtCompound();
        display.put("Lore", list);
        tag.put("display", display);
        item.setNbt(tag);
    }
}
