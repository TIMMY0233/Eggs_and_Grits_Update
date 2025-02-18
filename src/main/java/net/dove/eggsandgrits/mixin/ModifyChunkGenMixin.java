/*
package net.dove.eggsandgrits.mixin;


import net.minecraft.world.chunk.ChunkGenerationStep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ChunkGenerationStep.Builder.class) // Target the correct class
public class ModifyChunkGenMixin {



	@ModifyArg(
			method = "method_60535", // Ensure this method exists or find the correct one via Yarn mappings
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/chunk/ChunkGenerationStep$Builder;dependsOn(Lnet/minecraft/world/chunk/ChunkStatus;I)Lnet/minecraft/world/chunk/ChunkGenerationStep$Builder;",
					ordinal = 0 // Targeting the first occurrence
			),
			index = 1 // Modify the second argument (which is an integer)
	)
	private static int modifyStructureAccessRadius(int level) {
		return 17; // Change the max structure radius
	}
}
*/