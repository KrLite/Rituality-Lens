package net.krlite.rituality_lens.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Camera.class)
public interface CameraInvoker {
	@Invoker("setRotation")
	void invokeSetRotation(float yaw, float pitch);

	@Invoker("setPos")
	void invokeSetPos(Vec3d pos);
}
