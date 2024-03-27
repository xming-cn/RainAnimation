package saracraft.rainanimation.AnimationScript.AnimationScripts;

import org.bukkit.entity.LivingEntity;
import saracraft.rainanimation.AnimationScript.AnimationScript;
import saracraft.rainanimation.AnimationTask.AnimationTask;

public class RainScript implements AnimationScript {

    @Override
    public String getIdentifier() {
        return "rain";
    }

    @Override
    public boolean run(AnimationTask task, String[] param) {
        LivingEntity living = task.getTarget();
        living.remove();
        return true;
    }

    @Override
    public String toString() {
        return "RainScript";
    }
}
