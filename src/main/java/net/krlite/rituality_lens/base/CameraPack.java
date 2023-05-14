package net.krlite.rituality_lens.base;

import net.krlite.rituality_lens.mixin.CameraAccessor;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;

public class CameraPack {
	public CameraPack(Vec3d pos, float yaw, float pitch) {
		this.pos = pos;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public CameraPack(Camera camera) {
		this(((CameraAccessor) camera).rawPos(), ((CameraAccessor) camera).rawYaw(), ((CameraAccessor) camera).rawPitch());
	}

	private final Vec3d pos;
	private final float yaw, pitch;

	public Vec3d pos() {
		return pos;
	}

	public float yaw() {
		return yaw;
	}

	public float pitch() {
		return pitch;
	}
}
