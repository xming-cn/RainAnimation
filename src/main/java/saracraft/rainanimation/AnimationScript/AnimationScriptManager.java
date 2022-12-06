package saracraft.rainanimation.AnimationScript;

import saracraft.rainanimation.AnimationScript.AnimationScripts.CustomModelDataScript;
import saracraft.rainanimation.AnimationScript.AnimationScripts.DebugScript;
import saracraft.rainanimation.AnimationScript.AnimationScripts.MaterialScript;

import java.util.ArrayList;

public class AnimationScriptManager {
    private static final AnimationScriptManager inst = new AnimationScriptManager();
    private AnimationScriptManager() {}

    public static AnimationScriptManager getInstance() {
        return inst;
    }
    ArrayList<AnimationScript> scripts = new ArrayList<>(); {
        registerBuiltInScript();
    }

    private void registerBuiltInScript() {
        registerScript(new DebugScript());
        registerScript(new MaterialScript());
        registerScript(new CustomModelDataScript());
    }

    public AnimationScript getScript(String identifier) {
        for (AnimationScript script : scripts) {
            if (script.getIdentifier().equalsIgnoreCase(identifier)) {
                return script;
            }
        }
        return null;
    }

    private void registerScript(AnimationScript script) {
        scripts.add(script);
    }

    private void unregisterScript(String identifier) {
        scripts.removeIf(script -> script.getIdentifier().equalsIgnoreCase(identifier));
    }
}
