package saracraft.rainanimation.AnimationScript.AnimationScripts;

import saracraft.rainanimation.AnimationScript.AnimationScript;
import saracraft.rainanimation.AnimationTask.AnimationTask;

public class DebugScript implements AnimationScript {
    @Override
    public String getIdentifier() {
        return "debug";
    }

    @Override
    public boolean run(AnimationTask task, String[] param) {
        return true;
    }
}
