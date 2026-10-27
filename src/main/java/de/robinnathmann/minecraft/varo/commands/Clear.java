package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.Discord;

public class Clear implements CommandExecutor {
	
	private Main plugin;
	public Clear(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("inventoryclear") && sender.hasPermission("varo.admin")) {
			
			if(args.length == 3) {
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null) {
					
					sender.sendMessage("§c" + args[0] + " ist aktuell nicht online");
					
				} else {
					
					Integer number = 1;
					while (plugin.getConfig().getConfigurationSection("inventorybackup") != null && plugin.getConfig().getConfigurationSection("inventorybackup").contains(target.getName() + number)) {
						
						number++;
						
					}
						
					plugin.getConfig().set("inventorybackup." + target.getName() + number + ".inventory", target.getInventory().getContents());
					plugin.getConfig().set("inventorybackup." + target.getName() + number + ".armor", target.getInventory().getArmorContents());
					plugin.getConfig().set("inventorybackup." + target.getName() + number + ".xplvl", target.getLevel());
					plugin.getConfig().set("inventorybackup." + target.getName() + number + ".exp", target.getExp());
					plugin.saveConfig();
					
					target.getInventory().clear();
					target.getInventory().setArmorContents(null);
					target.setLevel(0);
					target.setExp(0);
					target.kickPlayer("§cDein Inventar wurde §4GELEERT\n\n§e" + args[1].replace("_", " ") + "\n\n§3Regelverstoߧ7:\n§c" + args[2].replace("_", " ") + "\n\n§3Regeln§7:\n§c" + plugin.getConfig().getString("Regeln"));
					Bukkit.broadcastMessage("§cDas Inventar von §e" + target.getName() + " §cwurde aufgrund von Regelverst§§en §4GELEERT");
					Discord.sendBroadcast("Das Inventar von " + target.getName() + " wurde aufgrund von Regelverst§§en GELEERT");
					
				    for(Player players : Bukkit.getOnlinePlayers()) {
				    	
				    	if(players.hasPermission("varo.admin")) {
				    		
				    		players.sendMessage("§cDas Inventar von §e" + target.getName() + " §cwurde von §e" + sender.getName() + " §4GELEERT §3Paragraph§7:§c " + args[1].replace("_", " ") + " §3Regelverstoߧ7:§c " + args[2].replace("_", " ") + "");
				    		
				    	}
				    	
				    }
					
				}
				
			} else {
				
				sender.sendMessage("§cBitte nutze: §e/inventoryclear <Spieler> <Paragraph> <Grund>");
				sender.sendMessage("§cMehrere W§rter mit Unterstrich trennen!");
				
			}
			
		}

		return true;
	}		

}
