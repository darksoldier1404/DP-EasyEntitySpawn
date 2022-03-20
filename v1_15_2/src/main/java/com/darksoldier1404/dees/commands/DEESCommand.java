package com.darksoldier1404.dees.commands;

import com.darksoldier1404.dees.EasyEntitySpawn;
import com.darksoldier1404.dees.functions.DEESFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DEESCommand implements CommandExecutor, TabCompleter {
    private final String prefix = EasyEntitySpawn.prefix;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!sender.isOp()) {
            sender.sendMessage(prefix + "관리자 전용 명령어 입니다.");
            return false;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(prefix + "플레이어만 사용 가능합니다.");
            return false;
        }
        Player p = (Player) sender;
        if(args.length == 0) {
            DEESFunction.openGUI(p);
            return false;
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return null;
    }
}
