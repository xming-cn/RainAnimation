package saracraft.rainanimation.AnimationTemplate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class AnimationTemplate {
    String identifier;

    String path;
    FileConfiguration config;

    public AnimationTemplate(String identifier, String path) {
        this.identifier = identifier;
        this.path = path;
        reload();
    }

    public void reload() {
        File configFile = new File(this.path);
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }
}
