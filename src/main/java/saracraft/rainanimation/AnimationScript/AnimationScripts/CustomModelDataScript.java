package saracraft.rainanimation.AnimationScript.AnimationScripts;

import org.bukkit.Bukkit;
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
        ItemMeta meta;
        if (item.hasItemMeta())
            meta = item.getItemMeta();
        else
            meta = Bukkit.getItemFactory().getItemMeta(item.getType());
        meta.setCustomModelData(Integer.parseInt(param[0]));
        item.setItemMeta(meta);
        task.setItem(item);
        return true;
    }

    @Override
    public String toString() {
        return "CustomModelDataScript";
    }
}
