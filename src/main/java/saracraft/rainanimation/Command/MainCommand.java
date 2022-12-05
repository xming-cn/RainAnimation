package saracraft.rainanimation.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import saracraft.rainanimation.AnimationTemplate.AnimationTemplateManager;

import java.util.List;
import java.util.Objects;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
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
            case 3 -> List.of("HAT", "HAND");
            case 4 -> AnimationTemplateManager.getInst().getTemplates().keySet().stream().toList();
            default -> List.of();
        };
    }

    public void printHelp(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage("§dRain Animation Help");
        sender.sendMessage("");
        sender.sendMessage("  §f/ra start <盔甲架UUID> <HAT/HAND> <动画ID>");
        sender.sendMessage("  §7为指定盔甲架开始一段动画");
        sender.sendMessage("");
        sender.sendMessage("  §f/ra stop <盔甲架UUID> <HAT/HAND>");
        sender.sendMessage("  §7使指定盔甲架停止动画");
        sender.sendMessage("");
    }
}
