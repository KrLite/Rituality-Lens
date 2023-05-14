package net.krlite.rituality_lens.mixin;

import net.krlite.rituality_lens.RitualityLens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	/*
	@ModifyArg(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionf;)V", ordinal = 2))
	private Quaternionf modifyPitch(Quaternionf quaternion) {
		return quaternion.rotateX(RitualityLens.relativePitch(((CameraAccessor) MinecraftClient.getInstance().gameRenderer.getCamera()).rawPitch()));
	}

	@ModifyArg(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionf;)V", ordinal = 3))
	private Quaternionf modifyYaw(Quaternionf quaternion) {
		return quaternion.rotateY(RitualityLens.relativeYaw(((CameraAccessor) MinecraftClient.getInstance().gameRenderer.getCamera()).rawYaw()));
	}

	@ModifyArg(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;setupFrustum(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Vec3d;Lorg/joml/Matrix4f;)V"), index = 1)
	private Vec3d modifyPosFrustrum(Vec3d pos) {
		return pos.add(RitualityLens.relativePos(((CameraAccessor) MinecraftClient.getInstance().gameRenderer.getCamera()).rawPos()));
	}

	@ModifyArg(
			method = "renderWorld",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/render/WorldRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lorg/joml/Matrix4f;)V"
			), index = 4
	)
	private Camera modifyCamera(Camera camera) {
		((CameraInvoker) camera).invokeSetRotation(camera.getPitch() + RitualityLens.relativePitch(((CameraAccessor) MinecraftClient.getInstance().gameRenderer.getCamera()).rawPitch()), camera.getYaw() + RitualityLens.relativeYaw(((CameraAccessor) MinecraftClient.getInstance().gameRenderer.getCamera()).rawYaw()));
		((CameraInvoker) camera).invokeSetPos(camera.getPos().add(RitualityLens.relativePos(((CameraAccessor) MinecraftClient.getInstance().gameRenderer.getCamera()).rawPos())));
		return camera;
	}

	 */
}
