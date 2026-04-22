package com.mitchej123.hodgepodge.mixins.late.thaumcraft;

import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import thaumcraft.common.lib.world.dim.TeleporterThaumcraft;

@Mixin(value = TeleporterThaumcraft.class, remap = false)
public class MixinTeleporterThaumcraft_PortalFix {

    @Shadow(remap = false)
    private WorldServer worldServerInstance;

    @Inject(method = "placeInPortal", at = @At("HEAD"))
    private void hodgepodge$unblockForPortalSearch(Entity entity, double x, double y, double z, float yaw,
            CallbackInfo ci) {
        worldServerInstance.findingSpawnPoint = true;
    }

    @Inject(method = "placeInPortal", at = @At("RETURN"))
    private void hodgepodge$reblockAfterPortalSearch(Entity entity, double x, double y, double z, float yaw,
            CallbackInfo ci) {
        worldServerInstance.findingSpawnPoint = false;
    }
}
