package de.robinnathmann.minecraft.varo.commands;

import java.util.Collections;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.Discord;

public class Debug implements CommandExecutor {
	
	private Main plugin;
	public Debug(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("debug") && sender.hasPermission("varo.admin")) {
			
			if(args.length == 1) {
				
				if(args[0].equalsIgnoreCase("onlinetime")) {
					
					sender.sendMessage("§4DEBUG§7:\n§e" + Main.onlinetimeatday.entrySet().toString());
					
				} else {
					
					if(args[0].equalsIgnoreCase("fakeworldborder")) {
						
						if(Main.currentbordervalue == null) {
							
							Main.currentbordervalue = (int) (Bukkit.getWorlds().get(0).getWorldBorder().getSize() - plugin.getConfig().getInt("worldborderdecrease"));
							
							double vorher = (Bukkit.getWorlds().get(0).getWorldBorder().getSize() - 1)/2;
							double nacher = (Main.currentbordervalue - 1)/2;
							double unterschied = vorher - nacher;
							Integer unterschiedint = (int) unterschied;
							
							Bukkit.broadcastMessage("§3Die Weltgrenze wird durch den Tod von §e" + "DEBUG" + " §3um §e" + unterschiedint + " §3Bl§cke innerhalb von §e10 §3Minuten verkleinert");
							Discord.sendBroadcast("Die Weltgrenze wird durch den Tod von " + "DEBUG" + " um " + unterschiedint + " Bl§cke innerhalb von 10 Minuten verkleinert");
							
							for(World worlds : Bukkit.getWorlds()) {
								
								if(worlds.getName() != Bukkit.getWorld(plugin.getConfig().getString("wartelobby.World")).getName()) {
									
									worlds.getWorldBorder().setSize(Main.currentbordervalue, 600);
									
								}
								
							}
							
						} else {
							
							if(Main.worldborderqueue.isEmpty()) {
								
								Integer wbl = Main.currentbordervalue - plugin.getConfig().getInt("worldborderdecrease");
								
								Main.worldborderqueue.add(wbl);
								Main.worldborderqueueplayer.put(wbl, "DEBUG");
								
							} else {
								
								Integer wbl = Collections.min(Main.worldborderqueue) - plugin.getConfig().getInt("worldborderdecrease");
								
								Main.worldborderqueue.add(wbl);
								Main.worldborderqueueplayer.put(wbl, "DEBUG");
								
							}
							
						}
						
					} else {
						
						if(args[0].equalsIgnoreCase("currentbordervalue")) {
							
							if(Main.currentbordervalue != null) {
								
								sender.sendMessage("§4DEBUG§7:\n§e" + Main.currentbordervalue.toString());
								
							} else {
								
								sender.sendMessage("§4DEBUG§7:\n§e" + "NULL");
								
							}							
							
						} else {
							
							if(args[0].equalsIgnoreCase("worldborderqueue")) {
								
								if(Main.worldborderqueue != null) {
									
									sender.sendMessage("§4DEBUG§7:\n§e" + Main.worldborderqueue.toString());
									
								} else {
									
									sender.sendMessage("§4DEBUG§7:\n§e" + "NULL");
									
								}
															
							} else {
								
								if(args[0].equalsIgnoreCase("variable")) {
									
									String sdf2 = Main.sdf.format(new Date());
									int hour = (Integer.parseInt(sdf2));
									System.out.println(hour);
									
								} else {
									
									if(args[0].equalsIgnoreCase("nokick")) {
										
										sender.sendMessage(Main.nokick.toString());
										
									} else {
										
										if(args[0].equalsIgnoreCase("discord")) {
											
											Discord.sendBroadcast("DIES IST EIN TEST");
											
										}
										
									}
									
								}
								
							}
							
						}
						
					}
					
				}

			} else {
				
				if(args.length == 2) {
					
					if(args[0].equalsIgnoreCase("changeonlinetime")) {
						
						if(sender instanceof Player) {
							
							Player p = (Player) sender;
							
							int i = 0;
							try {
								
								i = Integer.parseInt(args[1]);
								if (i > 0 && i < 2147483647) {
									
									if(Main.onlinetimeatday.containsKey(p.getName())) {
										
										Main.onlinetimeatday.remove(p);
										Main.onlinetimeatday.put(p.getName(), i);
										
									}	
									
								}
								
							} catch (NumberFormatException nfe) {
								sender.sendMessage("§cBitte nutze: §e/worldborderdecrease <Bl§cke>");
							}
							

							
						}
						
					}
					
				} else {
					
					sender.sendMessage("§cBitte nutze: §e/debug <OnlineTime/FakeWorldBorder/CurrentBorderValue/WorldBorderQueue/Variable/ChangeOnlinetime/NoKick/Discord>");
					
				}
				
				
			}
			
		}
		
		return true;
	}

}
