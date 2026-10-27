package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.UUIDFetcher;

public class Unban implements CommandExecutor {
	
	private Main plugin;
	public Unban(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("unban") && sender.hasPermission("varo.admin")) {
			
			if(args.length == 1) {


				String uuid;
				try {
					uuid = UUIDFetcher.getUUIDOf(args[0]).toString();
					
					if(plugin.getConfig().getConfigurationSection("gebannt") != null && plugin.getConfig().getConfigurationSection("gebannt").contains(uuid)) {
						
						plugin.getConfig().set("gebannt." + uuid, null);
						plugin.saveConfig();
						
						for(Player players : Bukkit.getOnlinePlayers()) {
							
							if(players.hasPermission("varo.admin")) {
								
								players.sendMessage("§e" + args[0] + " §cwurde von §e" + sender.getName() + " §centbannt");
								
							}
							
						}
						
					} else {
						
						sender.sendMessage("§cDieser Spieler ist nicht gebannt");
						
					}
					
				} catch (Exception e) {
					sender.sendMessage("§cDieser Spieler ist nicht gebannt");
				}
				
			} else {
				
				sender.sendMessage("§cBitte nutze: §e/unban <Spieler>");
				
			}
			
		}
		
		return true;
	}
	
	

}
