package net.krlite.rituality_lens.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface EntityInvoker {
	@Invoker("canStartRiding")
	boolean invokeCanStartRiding(Entity entity);
}
