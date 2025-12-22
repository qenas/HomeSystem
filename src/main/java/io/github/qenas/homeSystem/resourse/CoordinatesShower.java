package io.github.qenas.homeSystem.resourse;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface CoordinatesShower {

    public static void showHome(Location home, Player player) {
        player.sendMessage(ChatColor.GREEN + "» You have a home, the coordinates are: ");
        displayCoordinates(home, player);
    }

    public static void displayCoordinates(Location location, Player player) {
        player.sendMessage(ChatColor.GOLD + "X: " + ChatColor.WHITE + (int) location.getX() + " §6| " +
                              ChatColor.GOLD + "Y: " + ChatColor.WHITE + (int) location.getY() + " §6| " +
                              ChatColor.GOLD + "Z: " + ChatColor.WHITE + (int) location.getZ());
    }
}
