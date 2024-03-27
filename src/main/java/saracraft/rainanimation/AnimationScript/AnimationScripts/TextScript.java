package saracraft.rainanimation.AnimationScript.AnimationScripts;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TextDisplay;
import saracraft.rainanimation.AnimationScript.AnimationScript;
import saracraft.rainanimation.AnimationTask.AnimationTask;

public class TextScript implements AnimationScript {

    @Override
    public String getIdentifier() {
        return "text";
    }

    @Override
    public boolean run(AnimationTask task, String[] param) {
        LivingEntity living = task.getTarget();
        if (living instanceof TextDisplay display) {
            display.text(Component.text(param[0]));
        }
        return true;
    }

    @Override
    public String toString() {
        return "TextScript";
    }
}
