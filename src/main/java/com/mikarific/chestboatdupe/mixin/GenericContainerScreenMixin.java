package com.mikarific.chestboatdupe.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.mikarific.chestboatdupe.SharedVariables.*;

@Mixin(GenericContainerScreen.class)
public abstract class GenericContainerScreenMixin extends HandledScreen<GenericContainerScreenHandler> {
	public GenericContainerScreenMixin(GenericContainerScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		assert MinecraftClient.getInstance().player != null;
		if (this.getTitle().getString().equals(Text.translatable("entity.minecraft.chest_boat").getString())) {
            this.textRenderer.drawWithShadow(matrices, "Chest Boat Dupe Mod by Mikarific.", (float) this.width/2-90, (float) this.height/2+90, 16777215);
			if (MinecraftClient.getInstance().player.getVehicle() instanceof ChestBoatEntity) {
				if (!ready) {
					this.addDrawableChild(new ButtonWidget(this.width/2-90, this.height/2+35-145, 50, 20, Text.of("Ready!"), (button) -> {
						if (shouldDupe) shouldDupe = false;
						if (shouldDupeAll) shouldDupeAll = false;
						ready = true;
					}));
				} else {
					this.textRenderer.drawWithShadow(matrices, "Duping on the next opportunity...", (float) this.width/2-90, (float) this.height/2+35-160, 16777215);
				}
			} else {
				if (!shouldDupe) {
					this.addDrawableChild(new ButtonWidget(this.width/2-90, this.height/2+35-145, 50, 20, Text.of("Dupe"), (button) -> {
						if (ready) ready = false;
						if (shouldDupeAll) shouldDupeAll = false;
						shouldDupe = true;
					}));
				} else {
					this.textRenderer.drawWithShadow(matrices, "Duping on the next opportunity...", (float) this.width/2-90, (float) this.height/2+35-160, 16777215);
				}
				if (!shouldDupeAll) {
					this.addDrawableChild(new ButtonWidget(this.width/2+40, this.height/2+35-145, 50, 20, Text.of("Dupe All"), (button) -> {
						if (ready) ready = false;
						if (shouldDupe) shouldDupe = false;
						shouldDupeAll = true;
					}));
				} else {
					this.textRenderer.drawWithShadow(matrices, "Duping on the next opportunity...", (float) this.width/2-90, (float) this.height/2+35-160, 16777215);
				}
			}
		}
	}
}
