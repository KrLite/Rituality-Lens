package net.krlite.rituality_lens.base;

import net.minecraft.util.math.Vec3d;

public interface Processable {
	float relativeYaw(float currentYaw);
	float relativePitch(float currentPitch);
	Vec3d relativePos(Vec3d currentPos);
}
