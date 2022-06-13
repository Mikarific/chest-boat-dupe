package com.mikarific.chestboatdupe.mixin;

import com.mikarific.chestboatdupe.Main;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientPlayerEntityMixin {
    @Inject(at = @At("TAIL"), method = "tick")
    public void tick(CallbackInfo ci) {
        Main.tick();
    }
}