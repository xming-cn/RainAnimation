package saracraft.rainanimation.AnimationTask;

import org.bukkit.entity.LivingEntity;
import saracraft.rainanimation.AnimationTemplate.AnimationTemplate;
import saracraft.rainanimation.AnimationTemplate.AnimationTemplateManager;
import saracraft.rainanimation.RainAnimation;

import java.util.ArrayList;
import java.util.Objects;

public class AnimationTaskPool {
    static private final AnimationTaskPool inst = new AnimationTaskPool();
    private AnimationTaskPool() {}

    ArrayList<AnimationTask> tasks = new ArrayList<>();

    public static AnimationTaskPool getInstance() {
        return inst;
    }

    public void createTask(String id, LivingEntity target) {
        AnimationTemplate template = AnimationTemplateManager.getInst().getTemplate(id);
        if (Objects.isNull(template)) {
            RainAnimation.plugins.getLogger().warning("template does not exist: " + id);
            return;
        }
        AnimationTask task = template.generateTask(target);
        tasks.add(task);
    }

    public AnimationTask findTask(LivingEntity target) {
        for (AnimationTask task : tasks) {
            if (!task.getTarget().equals(target)) continue;
            return task;
        }
        return null;
    }

    public void removeTask(AnimationTask task) {
        tasks.remove(task);
    }

    public void tick() {
        if (tasks.isEmpty()) {
            return;
        }
        for (AnimationTask task : tasks) {
            task.tick();
        }
        tasks.removeIf( (AnimationTask task) -> Objects.isNull(task.getScriptProgress().getFirst()) && Objects.isNull(task.getScriptEnd().getFirst()));
    }
}
