package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("kick") && sender.hasPermission("varo.admin")) {
			
			if(args.length > 1) {
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target == null) {
					
					sender.sendMessage("§c" + args[0] + " ist aktuell nicht online");
					
				} else {
					
						String message = "";
						for (int i = 1; i < args.length; i++) {						
							message = message + args[i] + " ";
						}
					
					    target.kickPlayer("§cDu wurdest vom Server §4GEKICKT\n\n" + "§3Grund§7:§c " + message);
					    
					    for(Player players : Bukkit.getOnlinePlayers()) {
					    	
					    	if(players.hasPermission("varo.admin")) {
					    		
					    		players.sendMessage("§e" + target.getName() + " §cwurde von §e" + sender.getName() + " §cvom Server §4GEKICKT §3Grund§7:§c " + message);
					    		
					    	}
					    	
					    }
						
				}

			} else {
				sender.sendMessage("§cBitte nutze: §e/kick <Spieler> <Grund>");
			}
			
		}
		
		return true;
	}

}
