package saracraft.rainanimation.AnimationScript;

import saracraft.rainanimation.AnimationTask.AnimationTask;

import java.util.ArrayList;

public class AnimationSegment {
    private final ArrayList<ExecutableScript> scripts = new ArrayList<>();
    public boolean run(AnimationTask task) {
        for (ExecutableScript script : scripts) {
            if (!script.run(task)) {
                return false;
            }
        }
        return true;
    }

    public void addScript(ExecutableScript e) {
        scripts.add(e);
    }

    public static AnimationSegment parseSegment(String scripts) {
        AnimationSegment result = new AnimationSegment();
        String[] scriptLine = scripts.split("--");
        for (String s : scriptLine) {
            result.addScript(ExecutableScript.parseScript(s));
        }
        return result;
    }

    @Override
    public String toString() {
        return "AnimationSegment{" +
                "scripts=" + scripts +
                '}';
    }
}
