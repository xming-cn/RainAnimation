package saracraft.rainanimation.AnimationScript.AnimationScripts;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import saracraft.rainanimation.AnimationScript.AnimationScript;
import saracraft.rainanimation.AnimationTask.AnimationTask;

public class MaterialScript implements AnimationScript {
    @Override
    public String getIdentifier() {
        return "material";
    }

    @Override
    public boolean run(AnimationTask task, String[] param) {
        ItemStack item = task.getItem();
        item.setType(Material.valueOf(param[0]));
        task.setItem(item);
        return true;
    }
}
