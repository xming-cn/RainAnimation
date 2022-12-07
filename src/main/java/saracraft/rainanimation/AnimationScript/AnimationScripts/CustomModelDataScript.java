package saracraft.rainanimation.AnimationScript.AnimationScripts;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import saracraft.rainanimation.AnimationScript.AnimationScript;
import saracraft.rainanimation.AnimationTask.AnimationTask;

public class CustomModelDataScript implements AnimationScript {
    @Override
    public String getIdentifier() {
        return "customModelData";
    }

    @Override
    public boolean run(AnimationTask task, String[] param) {
        ItemStack item = task.getItem();
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(Integer.parseInt(param[0]));
        item.setItemMeta(meta);
        task.setItem(item);
        return true;
    }
}
