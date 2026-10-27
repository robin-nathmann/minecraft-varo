package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.robinnathmann.minecraft.varo.main.Main;

public class AllowSpectatorJoin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("allowspectatorjoin") && sender.hasPermission("varo.admin")) {
			
			if(Main.specallow == false) {
				
				Main.specallow = true;
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					
					if(players.hasPermission("varo.admin")) {
						
						players.sendMessage("§aDer Spectator Join wurde von §e" + sender.getName() + " §aeingeschaltet, ein Spectator Account kann nun joinen");
						
					}
					
				}
				
			} else {
				
				Main.specallow = false;
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					
					if(players.hasPermission("varo.admin")) {
						
						players.sendMessage("§cDer Spectator Join wurde von §e" + sender.getName() + " §causgeschaltet, ein Spectator Account kann nun nicht mehr joinen");
						
					}
					
				}
				
			}
			
		}
		
		return true;
	}

}
