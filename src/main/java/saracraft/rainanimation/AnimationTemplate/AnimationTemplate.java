package saracraft.rainanimation.AnimationTemplate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import saracraft.rainanimation.AnimationScript.AnimationScriptLinkedList;
import saracraft.rainanimation.AnimationTask.AnimationTask;

import java.io.File;
import java.util.List;

public class AnimationTemplate {
    String identifier;

    String path;
    FileConfiguration config;

    Integer defaultInterval;

    List<String> scriptProgress;
    List<String> scriptEnd;
    AnimationType type;
    public enum AnimationType {
        MAIN_HAND, HEAD, TEXT_DISPLAY
    }

    public AnimationTemplate(String identifier, String path) {
        this.identifier = identifier;
        this.path = path;
        reload();
    }

    public void reload() {
        File configFile = new File(this.path);
        this.config = YamlConfiguration.loadConfiguration(configFile);
        this.defaultInterval = this.config.getInt("Interval");
        this.scriptProgress = this.config.getStringList("Progress");
        this.scriptEnd = this.config.getStringList("End");
        this.type = AnimationType.valueOf(this.config.getString("Type", "MAIN_HAND").toUpperCase());
    }

    public AnimationTask generateTask(LivingEntity target) {
        AnimationTask result = new AnimationTask(target);
        result.setDefaultInterval(defaultInterval);
        result.setScriptProgress(AnimationScriptLinkedList.parseLinkedList(scriptProgress));
        result.setScriptEnd(AnimationScriptLinkedList.parseLinkedList(scriptEnd));
        result.setType(type);
        return result;
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
