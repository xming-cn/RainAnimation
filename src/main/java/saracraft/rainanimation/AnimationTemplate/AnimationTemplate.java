package saracraft.rainanimation.AnimationTemplate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
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
    }

    public AnimationTask generateTask(LivingEntity target, EquipmentSlot slot) {
        AnimationTask result = new AnimationTask(target, slot);
        result.setDefaultInterval(defaultInterval);
        result.setScriptProgress(AnimationScriptLinkedList.parseLinkedList(scriptProgress));
        result.setScriptEnd(AnimationScriptLinkedList.parseLinkedList(scriptEnd));
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
