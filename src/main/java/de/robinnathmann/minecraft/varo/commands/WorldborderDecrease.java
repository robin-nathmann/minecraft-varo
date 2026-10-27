package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.robinnathmann.minecraft.varo.main.Main;

public class WorldborderDecrease implements CommandExecutor {
	
	private Main plugin;
	public WorldborderDecrease(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("worldborderdecrease") && sender.hasPermission("varo.admin")) {
			
			if(args.length == 1) {
				
				if(plugin.getConfig().getBoolean("alreadystarted") == false) {
					
					int i = 0;
					try {
						
						i = Integer.parseInt(args[0]);
						if (i > 0 && i < 2147483647) {
							
							plugin.getConfig().set("worldborderdecrease", i);
							plugin.saveConfig();
							
							sender.sendMessage("§aDu hast die Worldborder Verkleinerung auf §e" + i + " §agestellt");	
							
						} else {
							sender.sendMessage("§cBitte nutze: §e/worldborderdecrease <Bl§cke>");
						}
						
					} catch (NumberFormatException nfe) {
						sender.sendMessage("§cBitte nutze: §e/worldborderdecrease <Bl§cke>");
					}

					
				} else {					
					sender.sendMessage("§cVaro ist bereits gestartet");					
				}
				
			} else {
				
				sender.sendMessage("§cBitte nutze: §e/worldborderdecrease <Bl§cke>");
				
			}
			
		}
		
		return true;
	}
	
	

}
