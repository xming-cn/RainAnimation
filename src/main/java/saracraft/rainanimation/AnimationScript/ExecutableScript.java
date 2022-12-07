package saracraft.rainanimation.AnimationScript;

import org.apache.commons.lang.ArrayUtils;
import saracraft.rainanimation.AnimationTask.AnimationTask;
import saracraft.rainanimation.RainAnimation;

public class ExecutableScript {
    private AnimationScript script;
    private String[] param;

    public ExecutableScript(AnimationScript script, String[] param) {
        this.script = script;
        this.param = param;
    }

    public AnimationScript getScript() {
        return script;
    }

    public void setScript(AnimationScript script) {
        this.script = script;
    }

    public String[] getParam() {
        return param;
    }

    public void setParam(String[] param) {
        this.param = param;
    }

    public boolean run(AnimationTask task) {
        return this.script.run(task, param);
    }

    static public ExecutableScript parseScript(String script) {
        String[] scriptLine = script.split(" ");
        String name = scriptLine[0];
        String[] param = new String[8];
        System.arraycopy(scriptLine, 1, param, 0, scriptLine.length - 1);
        AnimationScript scriptType = AnimationScriptManager.getInstance().getScript(name);
        if (scriptType == null) {
            RainAnimation.plugins.getLogger().warning("cant find animation: " + name);
        }
        return new ExecutableScript(scriptType, param);
    }

    @Override
    public String toString() {
        return "Script{" + script + "-" + ArrayUtils.toString(param) + '}';
    }
}
