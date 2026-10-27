package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AddTeam implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("addteam") && sender.hasPermission("varo.admin")) {
			
			if(args.length == 2) {
				
				if(args[0].length() < 17) {
					
					if(Bukkit.getScoreboardManager().getMainScoreboard().getTeam(args[0]) == null) {
						
						Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(args[0]);
						Bukkit.getScoreboardManager().getMainScoreboard().getTeam(args[0]).addEntry(args[1]);
						
						sender.sendMessage("§aDu hast das Team §e" + args[0] + " §amit den Erstenspieler §e" + args[1] + " §ahinzugef§gt");
						
					} else {
						
						sender.sendMessage("§cDieses Team gibt es schon!");
						
					}
					
				} else {
					
					sender.sendMessage("§cTeamname kann maximal 16 Zeichen lang sein!");
					
				}
				
			} else {
				
				sender.sendMessage("§cBitte nutze: §e/addteam <Teamname> <Erster-Spieler>");
				
			}
			
		}
		
		return true;
	}

}
