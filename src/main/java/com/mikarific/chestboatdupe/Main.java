package com.mikarific.chestboatdupe;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.screen.GenericContainerScreenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mikarific.chestboatdupe.SharedVariables.*;
import static com.mikarific.chestboatdupe.Util.*;

public class Main implements ModInitializer {
	@Override
	public void onInitialize() {
	}

	public static void tick() {
		if (String.valueOf(CLIENT.world != null ? CLIENT.world.getTime() : 0).endsWith("00")) {
			assert CLIENT.player != null;
			boolean b1 = (CLIENT.player.currentScreenHandler instanceof GenericContainerScreenHandler);
			if (!b1) {
				ready = false;
				shouldDupe = false;
				shouldDupeAll = false;
			}
			if (shouldDupe) {
				quickMoveItem(0);
				shouldDupe = false;
			}
			if (shouldDupeAll) {
				quickMoveAllItems();
				shouldDupeAll = false;
			}
			if (ready) {
				CLIENT.world.disconnect();
				CLIENT.disconnect();
				CLIENT.setScreen(new MultiplayerScreen(new TitleScreen()));
				ready = false;
			}
		}
	}
}
