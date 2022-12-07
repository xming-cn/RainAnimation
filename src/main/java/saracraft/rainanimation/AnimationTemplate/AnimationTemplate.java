package saracraft.rainanimation.AnimationTemplate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import saracraft.rainanimation.AnimationScript.AnimationScriptLinkedList;
import saracraft.rainanimation.AnimationTask.AnimationTask;

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

    public AnimationTask generateTask(LivingEntity target, EquipmentSlot slot) {
        AnimationTask result = new AnimationTask(target, slot);
        result.setDefaultInterval(defaultInterval);
        result.setScriptProgress(scriptProgress);
        result.setScriptEnd(scriptEnd);
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
