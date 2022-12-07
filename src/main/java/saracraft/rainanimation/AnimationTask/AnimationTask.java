package saracraft.rainanimation.AnimationTask;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import saracraft.rainanimation.AnimationScript.AnimationScriptLinkedList;

import java.util.Objects;

public class AnimationTask {
    LivingEntity target;
    EquipmentSlot slot;


    Integer waiting = 0;

    Integer defaultInterval;

    AnimationScriptLinkedList scriptProgress;
    AnimationScriptLinkedList scriptEnd;

    public AnimationTask(LivingEntity target, EquipmentSlot slot) {
        this.target = target;
        this.slot = slot;
    }

    public void tick() {
        this.waiting += 1;
        if (this.waiting <= defaultInterval) {
            step();
            this.waiting = 0;
        }
    }

    public void step() {
        if (Objects.nonNull(scriptProgress)) {
            stepProgress();
        } else {
            stepEnd();
        }
    }

    public void stepProgress() {
        scriptProgress.step().run(this);
    }

    public void stepEnd() {
        scriptEnd.step().run(this);
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public void setSlot(EquipmentSlot slot) {
        this.slot = slot;
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
        equipment.setItem(slot, item);
    }

    public ItemStack getItem() {
        EntityEquipment equipment = target.getEquipment();
        if (Objects.isNull(equipment)) return new ItemStack(Material.AIR);
        return equipment.getItem(slot);
    }
//    {
//        UUID uuid = new UUID();
//        if (Bukkit.getServer().getEntity(uuid) instanceof ArmorStand armorStand) {
//            armorStand.getItem(EquipmentSlot.CHEST)
//        }
//    }
}
