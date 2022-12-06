package saracraft.rainanimation.AnimationTemplate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import saracraft.rainanimation.AnimationScript.AnimationScriptLinkedList;

import java.io.File;

public class AnimationTemplate {
    String identifier;

    String path;
    FileConfiguration config;

    Integer defaultInterval;

    AnimationScriptLinkedList scriptProgress;
    AnimationScriptLinkedList scriptEnd;

    public AnimationTemplate(String identifier, String path) {
        this.identifier = identifier;
        this.path = path;
        reload();
    }

    public void reload() {
        File configFile = new File(this.path);
        this.config = YamlConfiguration.loadConfiguration(configFile);
        this.defaultInterval = this.config.getInt("Interval");
        this.scriptProgress = AnimationScriptLinkedList.parseLinkedList(this.config.getStringList("Progress"));
        this.scriptEnd = AnimationScriptLinkedList.parseLinkedList(this.config.getStringList("Progress"));
    }

    @Override
    public String toString() {
        return "AnimationTemplate{" +
                "identifier='" + identifier + '\'' +
                ", path='" + path + '\'' +
                ", config=" + config +
                ", defaultInterval=" + defaultInterval +
                ", scriptProgress=" + scriptProgress +
                ", scriptEnd=" + scriptEnd +
                '}';
    }
}
