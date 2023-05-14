package net.krlite.rituality_lens.animation;

import it.unimi.dsi.fastutil.floats.FloatUnaryOperator;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.krlite.rituality_lens.RitualityLens;
import net.krlite.rituality_lens.base.CameraPack;
import net.krlite.rituality_lens.base.Processable;
import net.krlite.rituality_lens.base.Resetable;
import net.krlite.rituality_lens.mixin.CameraAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class StartRidingAnimation implements Resetable, Processable {
	private enum YawModifier {
		NONE(yaw -> yaw),
		MINUS_360(yaw -> yaw - 360),
		PLUS_360(yaw -> yaw + 360);

		private final FloatUnaryOperator operator;

		YawModifier(FloatUnaryOperator operator) {
			this.operator = operator;
		}

		public float apply(float yaw) {
			return operator.apply(yaw);
		}
	}

	private float progress = 0;
	private boolean checked = false;
	private YawModifier yawModifier = YawModifier.NONE;
	@Nullable
	private CameraPack cameraPack;

	{
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (Math.abs(1 - progress) <= RitualityLens.CONFIG.gap) progress = 1;
			else progress = (float) RitualityLens.lerp(progress, 1, RitualityLens.CONFIG.lerpSpeed);
		});
	}

	@Override
	public void reset(Camera camera) {
		progress = 0;
		checked = false;
		cameraPack = new CameraPack(camera);
	}

	public void checkYaw(double currentYaw) {
		if (checked || cameraPack == null) return;
		checked = true;

		double relativeYaw = (cameraPack.yaw() - currentYaw) % 360;

		if (relativeYaw > 180)
			yawModifier = YawModifier.MINUS_360;
		else if (relativeYaw < -180)
			yawModifier = YawModifier.PLUS_360;
		else
			yawModifier = YawModifier.NONE;
	}

	@Override
	public float relativeYaw(float currentYaw) {
		if (cameraPack == null) return 0;
		return (float) RitualityLens.lerp(yawModifier.apply((cameraPack.yaw() - currentYaw) % 360), 0, progress);
	}

	@Override
	public float relativePitch(float currentPitch) {
		if (cameraPack == null) return 0;
		return (float) RitualityLens.lerp(cameraPack.pitch() - currentPitch, 0, progress);
	}

	@Override
	public Vec3d relativePos(Vec3d currentPos) {
		if (cameraPack == null) return Vec3d.ZERO;

		double yaw = ((CameraAccessor) MinecraftClient.getInstance().gameRenderer.getCamera()).rawYaw();
		double radius = Math.sqrt(Math.pow(cameraPack.pos().x - currentPos.x, 2) + Math.pow(cameraPack.pos().z - currentPos.z, 2));
		double theta = RitualityLens.lerp((cameraPack.yaw() - yaw) % 360, yaw % 360, progress) / 180 * Math.PI + Math.PI;

		return new Vec3d(
				RitualityLens.lerp(cameraPack.pos().x - currentPos.x + radius * Math.cos(theta), 0, progress),
				RitualityLens.lerp(cameraPack.pos().y - currentPos.y, 0, progress),
				RitualityLens.lerp(cameraPack.pos().z - currentPos.z + radius * Math.sin(theta), 0, progress)
		);
	}
}
