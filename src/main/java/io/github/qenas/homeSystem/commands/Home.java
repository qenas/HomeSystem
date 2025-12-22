package io.github.qenas.homeSystem.commands;

import io.github.qenas.homeSystem.manager.CooldownManager;
import io.github.qenas.homeSystem.manager.HomeManager;
import io.github.qenas.homeSystem.tasks.TeleportTask;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class Home implements CommandExecutor {

    private HomeManager homeManager;
    private CooldownManager cooldownManager;
    private final JavaPlugin plugin;

    public Home (HomeManager homeManager, CooldownManager cooldownManager, JavaPlugin plugin) {
        this.homeManager = homeManager;
        this.cooldownManager = cooldownManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(commandSender instanceof Player)) {
            return true;
        }

        Player player = (Player) commandSender;

        if(homeManager.playerHasHome(player)) {
            if(!cooldownManager.hasCooldown(player)) {
                Location playerHome = homeManager.getHome(player);
                teleportToHome(player, playerHome);
            } else {
                player.sendMessage(ChatColor.RED + "You can not use this command right now. You must wait + " + cooldownManager.getCooldown(player) + " seconds.");
            }
        } else {
            player.sendMessage("» You do not have a home");
        }


        return true;
    }


    private void teleportToHome(Player player, Location home) {
        Block startLocation = player.getLocation().getBlock();
        player.sendMessage(ChatColor.GREEN + "» Teleporting to your home in 10 seconds. Do not move...");

       new BukkitRunnable(){
           int timer = 10;
           @Override
           public void run() {
               timer--;
               player.sendMessage(ChatColor.GREEN + "» Teleporting in " + timer +  " seconds...");
               if(!player.isOnline() || !player.getLocation().getBlock().equals(startLocation)) {
                   player.sendMessage(ChatColor.RED + "» The teleport was cancelled because you move, try again.");
                    cancel();
                    return;
               }
               if(timer <= 0) {
                   player.teleport(home);
                   cooldownManager.setCooldown(player, 60);
                   player.sendMessage(ChatColor.GREEN + "» You have been teleported to your house.");
                   cancel();
               }
           }
       }.runTaskTimer(plugin, 0, 20L); // delay: 20 ticks = 1 second

        /*Bukkit.getScheduler().runTaskLater(plugin, () -> {

        }, 20L * 10); // 20 ticks = 1 second -> 10 seconds = 20 ticks * 10*/
    }
}
