package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.Discord;

public class PlayerQuitListener implements Listener {
	
	private Main plugin;
	public PlayerQuitListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		if(Main.openplayer.containsKey(e.getPlayer())) {
			Main.openplayer.remove(e.getPlayer());
		}
		
		if(Main.startprotection.containsKey(e.getPlayer())) {
			Main.startprotection.remove(e.getPlayer());
		}
		
		if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setQuitMessage(null);
			
		} else {
			
			if(Main.onlinetimeatday.containsKey(e.getPlayer().getName()) && Main.onlinetimeatday.get(e.getPlayer().getName()) > 107999) {
				
				e.setQuitMessage(null);
				Bukkit.broadcastMessage("§e" + e.getPlayer().getName() + " §7wurde vom Server gekickt");
				Discord.sendBroadcast(e.getPlayer().getName() + " wurde vom Server gekickt");
				
			} else {
				
				if(Main.onlinetimeatday.containsKey(e.getPlayer().getName()) && Main.onlinetimeatday.get(e.getPlayer().getName()) == 54000) {
					
					e.setQuitMessage(null);
					Bukkit.broadcastMessage("§e" + e.getPlayer().getName() + " §7wurde zur Halbzeit vom Server gekickt");
					Discord.sendBroadcast(e.getPlayer().getName() + " wurde zur Halbzeit vom Server gekickt");
					
				} else {
					
					if(plugin.getConfig().getConfigurationSection("gebannt") != null && plugin.getConfig().getConfigurationSection("gebannt").contains(e.getPlayer().getUniqueId().toString())) {
						
						e.setQuitMessage(null);
						
					} else {
						
						if(plugin.getConfig().getStringList("tote").contains(e.getPlayer().getUniqueId().toString())) {
							
							e.setQuitMessage(null);
							
						} else {
							
							e.setQuitMessage(null);
							Bukkit.broadcastMessage("§e" + e.getPlayer().getName() + " §3hat den Server verlassen");
							
							if(plugin.getConfig().getBoolean("beforegame") == false) {
								
								Discord.sendBroadcast(e.getPlayer().getName() + " hat den Server verlassen");
								
							}
							
						}
						
					}
					
				}	
				
			}

		}
		
		Main.logoutloc.put(e.getPlayer().getUniqueId().toString(), e.getPlayer().getLocation());
		
		if(Main.coordstrike.contains(e.getPlayer().getName())) {
			
			Bukkit.broadcastMessage("§3Koordinaten von §e" + e.getPlayer().getName());
			Bukkit.broadcastMessage("§3X§7: §e" + e.getPlayer().getLocation().getBlockX());
			Bukkit.broadcastMessage("§3Y§7: §e" + e.getPlayer().getLocation().getBlockY());
			Bukkit.broadcastMessage("§3Z§7: §e" + e.getPlayer().getLocation().getBlockZ());
			
			Discord.sendBroadcast("Koordinaten von " + e.getPlayer().getName() + "\nX: " + e.getPlayer().getLocation().getBlockX() + "\nY: " + e.getPlayer().getLocation().getBlockY() + "\nZ: " + e.getPlayer().getLocation().getBlockZ());
			
		}
		
	}

}
