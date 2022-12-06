package saracraft.rainanimation.AnimationScript.AnimationScripts;

import saracraft.rainanimation.AnimationScript.AnimationScript;

public class DebugScript implements AnimationScript {
    @Override
    public String getIdentifier() {
        return "debug";
    }

    @Override
    public boolean run(String[] param) {
        return true;
    }
}
