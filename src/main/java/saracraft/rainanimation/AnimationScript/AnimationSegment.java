package saracraft.rainanimation.AnimationScript;

import java.util.ArrayList;

public class AnimationSegment {
    ArrayList<AnimationScript> scripts;
    public boolean run() {
        for (AnimationScript script : scripts) {
            if (!script.run()) {
                return false;
            }
        }
        return true;
    }
}
