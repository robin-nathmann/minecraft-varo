package de.robinnathmann.minecraft.varo.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.UUIDFetcher;

public class MemberException implements CommandExecutor {
	
	private Main plugin;
	public MemberException(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("memberexception") && sender.hasPermission("varo.admin")) {
			
			if(args.length == 2) {
				
				if(plugin.getConfig().getBoolean("alreadystarted") == false) {
					
					if(args[0].equalsIgnoreCase("add")) {
									
						try {
							List<String> member = plugin.getConfig().getStringList("member");
							member.add(UUIDFetcher.getUUIDOf(args[1]).toString());
							plugin.getConfig().set("member", member);
							plugin.saveConfig();
							
							sender.sendMessage("§aDu hast den Spieler §e" + args[1] + " §aals Ausnahme hinzugef§gt");					
						} catch (Exception e) {
							sender.sendMessage("§cDiesen Minecraft Account gibt es nicht");
						}
						
					} else {
						
						if(args[0].equalsIgnoreCase("remove")) {
							
							try {
								
								String uuid = UUIDFetcher.getUUIDOf(args[1]).toString();
								
								List<String> member = plugin.getConfig().getStringList("member");
								
								if(member.contains(uuid)) {
									
									member.remove(uuid);
									plugin.getConfig().set("member", member);
									plugin.saveConfig();
									
									sender.sendMessage("§aDu hast den Spieler §e" + args[1] + " §avon der Member Liste entfernt");
									
								} else {
									
									sender.sendMessage("§cDieser Spieler ist nicht auf der Member Liste");
									
								}
								
							} catch (Exception e) {
								sender.sendMessage("§cDiesen Minecraft Account gibt es nicht");
							}
							
						} else {
							
							sender.sendMessage("§cBitte nutze: §e/memberexception <Add/Remove> <Spieler>");
							
						}
						
					}
					
				} else {					
					sender.sendMessage("§cVaro ist bereits gestartet");
				}
				
			} else {				
				
				sender.sendMessage("§cBitte nutze: §e/memberexception <Add/Remove> <Spieler>");
				
			}
			
		}
		
		return true;
	}

}
