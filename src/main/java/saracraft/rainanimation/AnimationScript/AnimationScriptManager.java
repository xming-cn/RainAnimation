package saracraft.rainanimation.AnimationScript;

import saracraft.rainanimation.AnimationScript.AnimationScripts.CustomModelDataScript;
import saracraft.rainanimation.AnimationScript.AnimationScripts.DebugScript;
import saracraft.rainanimation.AnimationScript.AnimationScripts.MaterialScript;
import saracraft.rainanimation.AnimationScript.AnimationScripts.WaitScript;

import java.util.HashMap;
import java.util.Locale;

public class AnimationScriptManager {
    private static final AnimationScriptManager inst = new AnimationScriptManager();
    private AnimationScriptManager() {}

    public static AnimationScriptManager getInstance() {
        return inst;
    }
    HashMap<String, AnimationScript> scripts = new HashMap<>(); {
        registerBuiltInScript();
    }

    private void registerBuiltInScript() {
        registerScript(new DebugScript());
        registerScript(new MaterialScript());
        registerScript(new CustomModelDataScript());
        registerScript(new WaitScript());
    }

    public AnimationScript getScript(String identifier) {
        return scripts.getOrDefault(identifier.toLowerCase(Locale.ROOT), null);
    }

    private void registerScript(AnimationScript script) {
        scripts.put(script.getIdentifier().toLowerCase(Locale.ROOT), script);
    }

    private void unregisterScript(String identifier) {
        scripts.remove(identifier.toLowerCase(Locale.ROOT));
    }
}
