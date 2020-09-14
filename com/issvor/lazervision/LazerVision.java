package com.issvor.lazervision;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getServer;

public class LazerVision extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[LazerVision]: plugin is enabled.");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[LazerVision]:  is disabled.");
    }

    int id;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) { //If console sends command
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("lazervision")) {
            if(args.length == 1) { // once the player type /lazervision <argument>, then execute the following code
                if (args[0].equalsIgnoreCase("enable")) { //if <argument> is enable, start explosions
                    player.sendMessage(ChatColor.GREEN + "Lazervision is enabled!");
                    id = getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                        @Override
                        public void run() {
                            int x = player.getTargetBlock(null, 30).getLocation().getBlockX();
                            int y = player.getTargetBlock(null, 30).getLocation().getBlockY();
                            int z = player.getTargetBlock(null, 30).getLocation().getBlockZ();
                            player.getWorld().createExplosion(x, y+1, z, 2.0f);
                            //using x/y+1/z instead of .getLocation because .getLocation causes the explosion to be
                            //inside the block you're looking at, and if it's stone, it won't cause surrounding blocks to explode
                        }
                    }, 0, 5);
                } else if (args[0].equalsIgnoreCase("disable")) { //if <argument>
                    getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                        @Override
                        public void run() {
                            player.sendMessage(ChatColor.RED + "Lazervision is disabled!");
                            Bukkit.getScheduler().cancelTask(id);
                        }
                    }, 0);
                }
            }
        }
        return true;
    }
}