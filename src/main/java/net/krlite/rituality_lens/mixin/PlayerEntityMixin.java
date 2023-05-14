package net.krlite.rituality_lens.mixin;

import net.krlite.rituality_lens.RitualityLens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
	@Inject(method = "tick", at = @At("HEAD"))
	private void tick(CallbackInfo ci) {
		if (((PlayerEntity) (Object) this).hasVehicle()) {
			RitualityLens.START_RIDING_ANIMATION.checkYaw(((PlayerEntity) (Object) this).getYaw());
		}
	}

	@Inject(method = "interact", at = @At("HEAD"))
	private void updateCameraPack(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
		if (((EntityInvoker) this).invokeCanStartRiding(entity)) {
			RitualityLens.START_RIDING_ANIMATION.reset(MinecraftClient.getInstance().gameRenderer.getCamera());
		}
	}

	/*
	@Inject(method = "tick", at = @At("HEAD"))
	private void checkYawBehavior(CallbackInfo ci) {
		if (((Entity) (Object) this).hasVehicle()) {
			float yaw = RitualityLens.cameraPack().yaw(), currentYaw = ((CameraAccessor) MinecraftClient.getInstance().gameRenderer.getCamera()).rawYaw(),
					relativeYaw = (yaw - currentYaw) % 360;
			RitualityLens.checkYawBehavior(yawBehavior(relativeYaw));
		}
	}

	private RitualityLens.YawBehavior yawBehavior(float relativeYaw) {
		return relativeYaw > 180
					   ? RitualityLens.YawBehavior.MINUS_360
					   : (relativeYaw < -180 ? RitualityLens.YawBehavior.PLUS_360 : RitualityLens.YawBehavior.NONE);
	}

	 */
}
