package net.krlite.rituality_lens.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Camera.class)
public interface CameraAccessor {
	@Accessor("pos")
	Vec3d rawPos();

	@Accessor("yaw")
	float rawYaw();

	@Accessor("pitch")
	float rawPitch();
}
