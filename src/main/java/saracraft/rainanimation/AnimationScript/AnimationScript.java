package saracraft.rainanimation.AnimationScript;

import saracraft.rainanimation.AnimationTask.AnimationTask;

public interface AnimationScript {
    String getIdentifier();

    boolean run(AnimationTask task, String[] param);
}
