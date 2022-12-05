package saracraft.rainanimation.AnimationScript.AnimationScripts;

import saracraft.rainanimation.AnimationScript.AnimationScript;

public class MaterialScript implements AnimationScript {
    String[] param;

    @Override
    public String getIdentifier() {
        return "material";
    }

    @Override
    public boolean run() {
        return true;
    }
}
