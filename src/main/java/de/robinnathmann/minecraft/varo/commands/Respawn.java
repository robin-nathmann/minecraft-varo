package de.robinnathmann.minecraft.varo.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.UUIDFetcher;

public class Respawn implements CommandExecutor {
	
	private Main plugin;
	public Respawn(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("respawn") && sender.hasPermission("varo.admin")) {
			
			
			if(args.length == 1) {
				
				try {
					
					String uuid = UUIDFetcher.getUUIDOf(args[0]).toString();
					
					if(plugin.getConfig().getStringList("tote").contains(uuid)) {
						
						List<String> tote = plugin.getConfig().getStringList("tote");
						tote.remove(uuid);
						plugin.getConfig().set("tote", tote);
						
						plugin.saveConfig();
						
						for(Player players : Bukkit.getOnlinePlayers()) {
							
							if(players.hasPermission("varo.admin")) {
								
								players.sendMessage("§e" + args[0] + " §cwurde von §e" + sender.getName() + " §cwiederbelebt");
								
							}
							
						}
						
					} else {
						
						sender.sendMessage("§cDieser Spieler ist nicht tot");
						
					}
					
				} catch (Exception e) {
					sender.sendMessage("§cDieser Spieler ist nicht tot");
				}
				
			} else {
				
				sender.sendMessage("§cBitte nutze: §e/respawn <Spieler>");
				
			}
			
			
		}
		
		return true;
	}

}
