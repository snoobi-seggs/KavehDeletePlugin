package snoobi.grasscutters.KavehDeletePlugin;

import emu.grasscutter.plugin.Plugin;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.entity.EntityDamageEvent;

public final class KavehDeletePlugin extends Plugin {
    private static KavehDeletePlugin instance;
    public static KavehDeletePlugin getInstance() {
        return instance;
    }
    @Override public void onLoad() {
        // Set the plugin instance.
        instance = this;
    }
    @Override public void onEnable() {
        new EventHandler<>(EntityDamageEvent.class)
                .priority(HandlerPriority.NORMAL)
                .listener(EventListener::EntityDamageEvent)
                .register(this);

        this.getHandle().registerCommand(new snoobi.grasscutters.KavehDeletePlugin.commands.KavehDeleteCommand());

        // Log a plugin status message.
        this.getLogger().info("[KAVEH_DELETE] The plugin has been enabled.");
    }

    @Override public void onDisable() {
        // Log a plugin status message.
        this.getLogger().info("[KAVEH_DELETE] The plugin has been disabled");
    }
}