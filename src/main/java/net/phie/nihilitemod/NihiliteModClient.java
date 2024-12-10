package net.phie.nihilitemod;

import net.fabricmc.api.ClientModInitializer;
import net.phie.nihilitemod.event.TooltipHandler;

public class NihiliteModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TooltipHandler.register();
    }
}
