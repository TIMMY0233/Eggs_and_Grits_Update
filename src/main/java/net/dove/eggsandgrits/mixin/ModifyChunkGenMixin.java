package net.dove.eggsandgrits.mixin;

import net.minecraft.world.chunk.ChunkGenerationSteps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ChunkGenerationSteps.class)
public class ModifyChunkGenMixin {

        @ModifyArg(
                method = "method_60535",
                at = @At(
                        value = "INVOKE",
                        target = "Lnet/minecraft/world/chunk/ChunkGenerationStep$Builder;dependsOn(Lnet/minecraft/world/chunk/ChunkStatus;I)Lnet/minecraft/world/chunk/ChunkGenerationStep$Builder;", ordinal = 0),
                index = 1,
                remap = false
        )
    private static int modifyStructureAccessRadius(int level) {
        return 17;
    }
}
