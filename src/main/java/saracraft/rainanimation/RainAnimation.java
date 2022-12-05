package saracraft.rainanimation;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import saracraft.rainanimation.AnimationTemplate.AnimationTemplateManager;
import saracraft.rainanimation.Command.MainCommand;

import java.util.Objects;

public final class RainAnimation extends JavaPlugin {
    public static Plugin plugins;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin Enable!");
        plugins = this;

        AnimationTemplateManager.getInst().registerAll(RainAnimation.plugins.getDataFolder().getPath()  + "/animations");

        Objects.requireNonNull(Bukkit.getPluginCommand("RainAnimation")).setExecutor(new MainCommand());

//        getConfig().options().copyDefaults();
//        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin Disable!");
    }
}
