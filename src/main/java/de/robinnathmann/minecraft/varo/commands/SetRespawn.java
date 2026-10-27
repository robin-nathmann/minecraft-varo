package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.robinnathmann.minecraft.varo.main.Main;

public class SetRespawn implements CommandExecutor {
	
	private Main plugin;
	public SetRespawn(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("setrespawn") && sender.hasPermission("varo.admin")) {
			
			if(args.length == 0) {
				
				if(sender instanceof Player) {
					
					Player p = (Player) sender;
					
					plugin.getConfig().set("respawn.World", p.getLocation().getWorld().getName());
					plugin.getConfig().set("respawn.X", p.getLocation().getX());
					plugin.getConfig().set("respawn.Y", p.getLocation().getY());
					plugin.getConfig().set("respawn.Z", p.getLocation().getZ());
					plugin.saveConfig();
					p.sendMessage("§aDu hast den Respawn gesetzt");
					
				}
				
			} else {
				
				sender.sendMessage("§cBitte nutze: §e/setrespawn>");
				
			}
			
		}
		
		return true;
	}
	
}
