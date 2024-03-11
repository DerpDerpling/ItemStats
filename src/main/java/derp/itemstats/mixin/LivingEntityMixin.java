package derp.itemstats.mixin;

import derp.itemstats.event.PlayerKillEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	public LivingEntityMixin() {
	}

	@Inject(
			at = {@At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/LivingEntity;onKilledBy(Lnet/minecraft/entity/LivingEntity;)V"
			)},
			method = "onDeath",
			locals = LocalCapture.CAPTURE_FAILSOFT
	)
	private void onKilledByEntity(DamageSource source, CallbackInfo ci) {
		if (source.getAttacker() instanceof ServerPlayerEntity player) {
			PlayerKillEntityCallback.EVENT.invoker().killEntity(player, (LivingEntity) (Object) this);
		}
	}
}
