package de.robinnathmann.minecraft.varo.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.robinnathmann.minecraft.varo.main.Main;

public class Ban implements CommandExecutor {
	
	private Main plugin;
	public Ban(Main plugin) {
	    this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("ban") && sender.hasPermission("varo.admin")) {
			
			if (args.length == 3) {
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target == null) {
					
					sender.sendMessage("§c" + args[0] + " ist aktuell nicht online");
					
				} else {
					    
					    List<String> bannreason = new ArrayList<String>();
					    bannreason.add(args[1]);
					    bannreason.add(args[2]);
					    plugin.getConfig().set("gebannt." + target.getUniqueId().toString(), bannreason);
					    
					    plugin.saveConfig();
					    
						if(target.getScoreboard().getPlayerTeam(target) != null) {
							
							target.getScoreboard().getPlayerTeam(target).removePlayer(target);
							
						}
					    
					    target.kickPlayer("§cDu wurdest vom Projekt §4AUSGESCHLOSSEN\n\n§e" + args[1].replace("_", " ") + "\n\n§3Regelverstoߧ7:\n§c" + args[2].replace("_", " ") + "\n\n§3Regeln§7:\n§c" + plugin.getConfig().getString("Regeln"));
					    
					    for(Player players : Bukkit.getOnlinePlayers()) {
					    	
					    	if(players.hasPermission("varo.admin")) {
					    		
					    		players.sendMessage("§e" + target.getName() + " §cwurde von §e" + sender.getName() + " §cvom Projekt §4AUSGESCHLOSSEN §3Paragraph§7:§c " + args[1].replace("_", " ") + " §3Regelverstoߧ7:§c " + args[2].replace("_", " ") + "");
					    		
					    	}
					    	
					    }
						
				}

			} else {
				sender.sendMessage("§cBitte nutze: §e/ban <Spieler> <Paragraph> <Grund>");
				sender.sendMessage("§cMehrere W§rter mit Unterstrich trennen!");
			}
			
		}
		
		return true;
	}

}
