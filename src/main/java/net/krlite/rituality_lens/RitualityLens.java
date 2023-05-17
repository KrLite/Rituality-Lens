package net.krlite.rituality_lens;

import net.fabricmc.api.ModInitializer;
import net.krlite.rituality_lens.animation.StartRidingAnimation;
import net.krlite.rituality_lens.base.Processable;
import net.krlite.rituality_lens.base.Resetable;
import net.krlite.rituality_lens.config.RitualityLensConfig;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;

public class RitualityLens implements ModInitializer {
	public static final String NAME = "Rituality Lens", ID = "rituality-lens";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);
	public static final RitualityLensConfig CONFIG = new RitualityLensConfig();

	// Animations
	public static final StartRidingAnimation START_RIDING_ANIMATION = new StartRidingAnimation();

	@Override
	public void onInitialize() {
		CONFIG.load();
		CONFIG.save();
	}

	public static double lerp(double a, double b, double t) {
		return a + (b - a) * t;
	}

	public static float relativeYaw(float currentYaw) {
		float yaw = 0;

		yaw += START_RIDING_ANIMATION.relativeYaw(currentYaw);

		return yaw;
	}

	public static float relativePitch(float currentPitch) {
		float pitch = 0;

		pitch += START_RIDING_ANIMATION.relativePitch(currentPitch);

		return pitch;
	}

	public static Vec3d relativePos(Vec3d currentPos) {
		Vec3d pos = Vec3d.ZERO;

		pos = pos.add(START_RIDING_ANIMATION.relativePos(currentPos));

		return pos;
	}
}
