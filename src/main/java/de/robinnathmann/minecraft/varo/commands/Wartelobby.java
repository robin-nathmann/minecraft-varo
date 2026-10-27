package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.robinnathmann.minecraft.varo.main.Main;

public class Wartelobby implements CommandExecutor {
	
	private Main plugin;
	public Wartelobby(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("wartelobby") && sender.hasPermission("varo.admin")) {
			
			if(sender instanceof Player) {
				
				Player p = (Player) sender;
				
				plugin.getConfig().set("wartelobby.World", p.getLocation().getWorld().getName());
				plugin.getConfig().set("wartelobby.X", p.getLocation().getX());
				plugin.getConfig().set("wartelobby.Y", p.getLocation().getY());
				plugin.getConfig().set("wartelobby.Z", p.getLocation().getZ());
				plugin.getConfig().set("wartelobby.Yaw", p.getLocation().getYaw());
				plugin.getConfig().set("wartelobby.Pitch", p.getLocation().getPitch());
				plugin.saveConfig();
				p.sendMessage("§aDu hast die Wartelobby gesetzt");
				
			}
			
		}
		
		return true;
	}

}
