package derp.itemstats;

import derp.itemstats.event.PlayerKillEntityCallback;
import derp.itemstats.events.EntityKilled;
import derp.itemstats.utils.NBTUtils;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

public class ItemStats implements ModInitializer {
	public ItemStats() {
	}

	public void onInitialize() {
		EntityKilled entityKilled = new EntityKilled();
		PlayerKillEntityCallback.EVENT.register(entityKilled);
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
			ItemStack hand = player.getMainHandStack();
			if (hand != null && hand.getItem() instanceof PickaxeItem) {
				NbtCompound tag = hand.getOrCreateNbt();
				long total = 0L;
				long ancient_debris = tag.getLong("ancient_debris");
				long diamond = tag.getLong("diamond");
				long gold = tag.getLong("gold");
				long iron = tag.getLong("iron");
				long copper = tag.getLong("copper");
				long coal = tag.getLong("coal");
				long redstone = tag.getLong("redstone");
				long lapis = tag.getLong("lapis");
				long nether_quartz = tag.getLong("nether_quartz");
				long emerald = tag.getLong("emerald");
				long nether_gold = tag.getLong("nether_gold");

				// Check for mined blocks and update counts
				if (state.getBlock() == Blocks.ANCIENT_DEBRIS) {
					ancient_debris++;
				} else if (state.getBlock() == Blocks.DIAMOND_ORE || state.getBlock() == Blocks.DEEPSLATE_DIAMOND_ORE) {
					diamond++;
				} else if (state.getBlock() == Blocks.GOLD_ORE || state.getBlock() == Blocks.DEEPSLATE_GOLD_ORE) {
					gold++;
				} else if (state.getBlock() == Blocks.IRON_ORE || state.getBlock() == Blocks.DEEPSLATE_IRON_ORE) {
					iron++;
				} else if (state.getBlock() == Blocks.COPPER_ORE || state.getBlock() == Blocks.DEEPSLATE_COPPER_ORE) {
					copper++;
				} else if (state.getBlock() == Blocks.COAL_ORE || state.getBlock() == Blocks.DEEPSLATE_COAL_ORE) {
					coal++;
				} else if (state.getBlock() == Blocks.REDSTONE_ORE || state.getBlock() == Blocks.DEEPSLATE_REDSTONE_ORE) {
					redstone++;
				} else if (state.getBlock() == Blocks.LAPIS_ORE || state.getBlock() == Blocks.DEEPSLATE_LAPIS_ORE) {
					lapis++;
				} else if (state.getBlock() == Blocks.NETHER_QUARTZ_ORE) {
					nether_quartz++;
				} else if (state.getBlock() == Blocks.EMERALD_ORE) {
					emerald++;
				} else if (state.getBlock() == Blocks.NETHER_GOLD_ORE) {
					nether_gold++;
				}

				// Update total count
				total = ancient_debris + diamond + gold + iron + copper + coal + redstone + lapis + nether_quartz + emerald + nether_gold;

				// Update NBT
				tag.putLong("ancient_debris", ancient_debris);
				tag.putLong("diamond", diamond);
				tag.putLong("gold", gold);
				tag.putLong("iron", iron);
				tag.putLong("copper", copper);
				tag.putLong("coal", coal);
				tag.putLong("redstone", redstone);
				tag.putLong("lapis", lapis);
				tag.putLong("nether_quartz", nether_quartz);
				tag.putLong("emerald", emerald);
				tag.putLong("nether_gold", nether_gold);

				// Create lore
				NbtList list = new NbtList();
				list.add(0, NbtString.of("{\"text\":\"\",\"bold\":false,\"italic\":false,\"color\":\"#7A5200\"}"));
				list.add(1, NbtString.of("{\"text\":\"Ancient Debris: " + ancient_debris + "\",\"bold\":false,\"italic\":false,\"color\":\"#7A5200\"}"));
				list.add(2, NbtString.of("{\"text\":\"Nether Quartz: " + nether_quartz + "\",\"bold\":false,\"italic\":false,\"color\":\"#FFFFFF\"}"));
				list.add(3, NbtString.of("{\"text\":\"Nether Gold: " + nether_gold + "\",\"bold\":false,\"italic\":false,\"color\":\"#FFD700\"}"));
				list.add(4, NbtString.of("{\"text\":\"Diamond: " + diamond + "\",\"bold\":false,\"italic\":false,\"color\":\"#00EEFF\"}"));
				list.add(5, NbtString.of("{\"text\":\"Emerald: " + emerald + "\",\"bold\":false,\"italic\":false,\"color\":\"#00FF00\"}"));
				list.add(6, NbtString.of("{\"text\":\"Gold: " + gold + "\",\"bold\":false,\"italic\":false,\"color\":\"#F8D71B\"}"));
				list.add(7, NbtString.of("{\"text\":\"Redstone: " + redstone + "\",\"bold\":false,\"italic\":false,\"color\":\"#DE1B40\"}"));
				list.add(8, NbtString.of("{\"text\":\"Iron: " + iron + "\",\"bold\":false,\"italic\":false,\"color\":\"#B2AEB2\"}"));
				list.add(9, NbtString.of("{\"text\":\"Copper: " + copper + "\",\"bold\":false,\"italic\":false,\"color\":\"#E5621E\"}"));
				list.add(10, NbtString.of("{\"text\":\"Coal: " + coal + "\",\"bold\":false,\"italic\":false,\"color\":\"#373737\"}"));
				list.add(11, NbtString.of("{\"text\":\"Lapis: " + lapis + "\",\"bold\":false,\"italic\":false,\"color\":\"#070FD4\"}"));
				NBTUtils.setLore(hand, list);
			}
		});
	}
}
