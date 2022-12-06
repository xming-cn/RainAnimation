package saracraft.rainanimation.AnimationScript.AnimationScripts;

import saracraft.rainanimation.AnimationScript.AnimationScript;

public class CustomModelDataScript implements AnimationScript {
    String[] param;

    @Override
    public String getIdentifier() {
        return "customModelData";
    }

    @Override
    public boolean run(String[] param) {
        return true;
    }
}
