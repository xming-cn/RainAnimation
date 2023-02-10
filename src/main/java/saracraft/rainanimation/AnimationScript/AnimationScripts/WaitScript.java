package saracraft.rainanimation.AnimationScript.AnimationScripts;

import saracraft.rainanimation.AnimationScript.AnimationScript;
import saracraft.rainanimation.AnimationTask.AnimationTask;

public class WaitScript  implements AnimationScript {
    @Override
    public String getIdentifier() {
        return "material";
    }

    @Override
    public boolean run(AnimationTask task, String[] param) {
        task.delay(Integer.parseInt(param[0]));
        return true;
    }
    @Override
    public String toString() {
        return "MaterialScript";
    }
}
