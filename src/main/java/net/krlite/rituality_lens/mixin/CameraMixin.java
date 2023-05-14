package net.krlite.rituality_lens.mixin;

import net.krlite.rituality_lens.RitualityLens;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public abstract class CameraMixin {
	@Inject(method = "getPos", at = @At("RETURN"), cancellable = true)
	private void getPos(CallbackInfoReturnable<Vec3d> cir) {
		Vec3d pos = cir.getReturnValue();
		cir.setReturnValue(pos.add(RitualityLens.relativePos(pos)));
		/*
		if (getFocusedEntity() == MinecraftClient.getInstance().getCameraEntity() && RitualityLens.cameraPack() != null) {
			Vec3d pos = RitualityLens.cameraPack().pos(), currentPos = cir.getReturnValue();
			float
					yaw = RitualityLens.cameraPack().yaw(),
					currentYaw = ((CameraAccessor) MinecraftClient.getInstance().gameRenderer.getCamera()).rawYaw();
			double
					radius = Math.sqrt(Math.pow(pos.x - currentPos.x, 2) + Math.pow(pos.y - currentPos.y, 2)),
					theta = RitualityLens.applyYaw((yaw - currentYaw) % 360) / 180 * Math.PI;
			pos.add(Math.cos(theta) * radius, 0 , Math.sin(theta) * radius);
			cir.setReturnValue(new Vec3d(
					RitualityLens.lerp(pos.x, currentPos.x, RitualityLens.progress()),
					RitualityLens.lerp(pos.y, currentPos.y, RitualityLens.progress()),
					RitualityLens.lerp(pos.z, currentPos.z, RitualityLens.progress())
			));
		}

		 */
	}

	@Inject(method = "getPitch", at = @At(value = "RETURN"), cancellable = true)
	private void getPitch(CallbackInfoReturnable<Float> cir) {
		float pitch = cir.getReturnValue();
		cir.setReturnValue(pitch + RitualityLens.relativePitch(pitch));
		/*
		if (getFocusedEntity() == MinecraftClient.getInstance().getCameraEntity() && RitualityLens.cameraPack() != null) {
			cir.setReturnValue((float) RitualityLens.lerp(RitualityLens.cameraPack().pitch(), cir.getReturnValue(), RitualityLens.progress()));
		}

		 */
	}

	@Inject(method = "getYaw", at = @At(value = "RETURN"), cancellable = true)
	private void getYaw(CallbackInfoReturnable<Float> cir) {
		float yaw = cir.getReturnValue();
		cir.setReturnValue(yaw + RitualityLens.relativeYaw(yaw));
		/*
		if (getFocusedEntity() == MinecraftClient.getInstance().getCameraEntity() && RitualityLens.cameraPack() != null) {
			float yaw = RitualityLens.cameraPack().yaw(), currentYaw = cir.getReturnValue(), relativeYaw = (yaw - currentYaw) % 360;
			cir.setReturnValue(currentYaw + (float) RitualityLens.lerp(RitualityLens.applyYaw(relativeYaw), 0, RitualityLens.progress()));
		}

		 */
	}
}
