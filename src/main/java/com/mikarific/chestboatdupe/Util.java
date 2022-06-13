package com.mikarific.chestboatdupe;

import net.minecraft.client.MinecraftClient;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.slot.SlotActionType;

import java.util.Objects;

public class Util {
    public static final MinecraftClient CLIENT = MinecraftClient.getInstance();

    public static void quickMoveAllItems() {
        for (int i = 0; i < 27; i++) {
            quickMoveItem(i);
        }
    }

    public static void quickMoveItem(int slot) {
        assert CLIENT.player != null;
        if (CLIENT.player.currentScreenHandler instanceof GenericContainerScreenHandler screenHandler) {
            Int2ObjectArrayMap<ItemStack> stack = new Int2ObjectArrayMap<>();
            stack.put(slot, screenHandler.getSlot(slot).getStack());
            Objects.requireNonNull(CLIENT.getNetworkHandler()).sendPacket(new ClickSlotC2SPacket(screenHandler.syncId, 0, slot, 0, SlotActionType.QUICK_MOVE, screenHandler.getSlot(0).getStack(), stack));
        }
    }
}
