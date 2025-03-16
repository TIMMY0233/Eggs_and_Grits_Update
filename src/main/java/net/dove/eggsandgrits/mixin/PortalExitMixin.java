/*package net.dove.eggsandgrits.mixin;

import net.dove.eggsandgrits.EggsAndGrits;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class PortalExitMixin {

    // Add a flag to prevent recursive teleportation
    private boolean hasTeleported = false;

    // Handles teleportation when entering a dimension
    @Inject(method = "teleportTo", at = @At("HEAD"), cancellable = true)
    private void onTeleportEnter(TeleportTarget teleportTarget, CallbackInfoReturnable<Entity> cir) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

        // Prevent recursion by checking if the player has already teleported
        if (hasTeleported) {
            return;
        }

        // Check if teleporting to your custom dimension
        if (teleportTarget.world().getRegistryKey().getValue().equals(Identifier.of(EggsAndGrits.MOD_ID, "oceandim"))) {
            BlockPos fixedPos = new BlockPos(0, 100, 0); // Fixed teleport location
            player.teleport(teleportTarget.world(), fixedPos.getX(), fixedPos.getY(), fixedPos.getZ(), player.getYaw(), player.getPitch());

            // Mark that the player has teleported to prevent recursion
            hasTeleported = true;

            // Cancel the default teleportation logic
            cir.cancel();
        }
    }

    // Reset the teleportation flag when leaving the dimension
    @Inject(method = "worldChanged", at = @At("HEAD"))
    private void onTeleportLeave(ServerWorld destination, CallbackInfo ci) {
        // Reset the teleportation flag when leaving the dimension
        hasTeleported = false;
    }
}

 */



