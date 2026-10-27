package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.robinnathmann.minecraft.varo.main.Main;

public class Finish implements CommandExecutor {
	
	private Main plugin;
	public Finish(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("Finish") && sender.hasPermission("varo.admin")) {
			
			if(plugin.getConfig().getBoolean("setup") == false) {
				
				plugin.getConfig().set("setup", true);
				plugin.saveConfig();
				sender.sendMessage("§cDer Server ist jetzt nur noch f§r Projektleiter zug§nglich");
				
			} else {
				
				plugin.getConfig().set("setup", false);
				plugin.saveConfig();
				sender.sendMessage("§aDer Server ist jetzt f§r alle zug§nglich");
				
			}
			
		}
		
		return true;
	}

}
