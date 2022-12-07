package saracraft.rainanimation.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import saracraft.rainanimation.AnimationTask.AnimationTaskPool;
import saracraft.rainanimation.AnimationTemplate.AnimationTemplateManager;
import saracraft.rainanimation.RainAnimation;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            printHelp(sender);
            return true;
        }
        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "start" -> {
                if (args.length < 4) {
                    printHelp(sender);
                    return true;
                }
                startSubCommand(sender, args);
            }
            case "end" -> {
                if (args.length < 3) {
                    printHelp(sender);
                    return true;
                }
                endSubCommand(sender, args);
            }
        }

        return true;
    }

    public void startSubCommand(CommandSender sender, String[] args) {
        Entity target = RainAnimation.plugins.getServer().getEntity(UUID.fromString(args[1]));
        LivingEntity living;
        if (target instanceof LivingEntity) {
            living = (LivingEntity) target;
        } else {
            sender.sendMessage("§c  target is not a LivingEntity!");
            return;
        }
        EquipmentSlot slot = EquipmentSlot.valueOf(args[2].toUpperCase(Locale.ROOT));
        AnimationTaskPool.getInstance().createTask(args[3], living, slot);
    }

    public void endSubCommand(CommandSender sender, String[] args) {
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return switch (args.length) {
            case 1 -> List.of("start", "stop");
            case 2 -> {
                Entity target = ((Player) sender).getTargetEntity(16);
                if (Objects.nonNull(target)) {
                    yield List.of(target.getUniqueId().toString());
                }
                yield List.of();
            }
            case 3 -> List.of("HEAD", "HAND");
            case 4 -> AnimationTemplateManager.getInst().getTemplates().keySet().stream().toList();
            default -> List.of();
        };
    }

    public void printHelp(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage("§dRain Animation Help");
        sender.sendMessage("");
        sender.sendMessage("  §f/ra start <盔甲架UUID> <HEAD/HAND> <动画ID>");
        sender.sendMessage("  §7为指定盔甲架开始一段动画");
        sender.sendMessage("");
//        sender.sendMessage("  §f/ra stop <盔甲架UUID> <HEAD/HAND>");
//        sender.sendMessage("  §7使指定盔甲架停止动画");
//        sender.sendMessage("");
    }
}
