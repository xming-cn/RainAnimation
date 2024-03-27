package saracraft.rainanimation.AnimationTask;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import saracraft.rainanimation.AnimationScript.AnimationScriptLinkedList;
import saracraft.rainanimation.AnimationScript.AnimationSegment;
import saracraft.rainanimation.AnimationTemplate.AnimationTemplate;

import java.util.Objects;

public class AnimationTask {
    private LivingEntity target;

    private Integer waiting = 0;

    private Integer defaultInterval;

    private AnimationScriptLinkedList scriptProgress;
    private AnimationScriptLinkedList scriptEnd;
    private AnimationTemplate.AnimationType type;

    public AnimationTask(LivingEntity target) {
        this.target = target;
    }

    public void end() {
        scriptProgress.end();
    }

    public void tick() {
        this.waiting += 1;
        if (this.waiting >= defaultInterval) {
            this.waiting = 0;
            step();
        }
    }

    public void step() {
        if (Objects.nonNull(scriptProgress.getFirst())) {
            stepProgress();
        } else if (Objects.nonNull(scriptEnd.getFirst())) {
            stepEnd();
        }
    }

    public void stepProgress() {
        AnimationSegment segment = scriptProgress.step();
        if (Objects.nonNull(segment)) segment.run(this);
    }

    public void stepEnd() {
        AnimationSegment segment = scriptEnd.step();
        if (Objects.nonNull(segment)) segment.run(this);
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public Integer getDefaultInterval() {
        return defaultInterval;
    }

    public void setDefaultInterval(Integer defaultInterval) {
        this.defaultInterval = defaultInterval;
    }

    public AnimationScriptLinkedList getScriptProgress() {
        return scriptProgress;
    }

    public void setScriptProgress(AnimationScriptLinkedList scriptProgress) {
        this.scriptProgress = scriptProgress;
    }

    public AnimationScriptLinkedList getScriptEnd() {
        return scriptEnd;
    }

    public void setScriptEnd(AnimationScriptLinkedList scriptEnd) {
        this.scriptEnd = scriptEnd;
    }

    public void setItem(ItemStack item) {
        EntityEquipment equipment = target.getEquipment();
        if (Objects.isNull(equipment)) return;
        switch (type) {
            case HEAD -> equipment.setHelmet(item);
            case MAIN_HAND -> equipment.setItem(EquipmentSlot.HAND, item);
        }
    }

    public ItemStack getItem() {
        EntityEquipment equipment = target.getEquipment();
        if (Objects.isNull(equipment)) return new ItemStack(Material.AIR);
        return switch (type) {
            case HEAD -> equipment.getHelmet();
            case MAIN_HAND -> equipment.getItem(EquipmentSlot.HAND);
            default -> new ItemStack(Material.AIR);
        };
    }

    @Override
    public String toString() {
        return "AnimationTask{" +
                "target=" + target +
                ", type=" + type +
                ", waiting=" + waiting +
                ", defaultInterval=" + defaultInterval +
                ", scriptProgress=" + scriptProgress +
                ", scriptEnd=" + scriptEnd +
                '}';
    }

    public Integer getWaiting() {
        return waiting;
    }

    public void delay(Integer delay) {
        this.waiting -= delay;
    }

    public void setWaiting(Integer waiting) {
        this.waiting = waiting;
    }

    public void setType(AnimationTemplate.AnimationType type) {
        this.type = type;
    }
}
