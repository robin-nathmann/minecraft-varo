package de.robinnathmann.minecraft.varo.listeners;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.Discord;

public class PlayerDeathListener implements Listener {
	
	private Main plugin;
	public PlayerDeathListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		
		if(plugin.getConfig().getBoolean("beforegame") == false) {
			
			Integer number = 1;
			while (plugin.getConfig().getConfigurationSection("inventorybackup") != null && plugin.getConfig().getConfigurationSection("inventorybackup").contains(e.getEntity().getName() + number)) {
				
				number++;
				
			}
				
			plugin.getConfig().set("inventorybackup." + e.getEntity().getName() + number + ".inventory", e.getEntity().getInventory().getContents());
			plugin.getConfig().set("inventorybackup." + e.getEntity().getName() + number + ".armor", e.getEntity().getInventory().getArmorContents());
			plugin.getConfig().set("inventorybackup." + e.getEntity().getName() + number + ".xplvl", e.getEntity().getLevel());
			plugin.getConfig().set("inventorybackup." + e.getEntity().getName() + number + ".exp", e.getEntity().getExp());
			
			List<String> totgrund = plugin.getConfig().getStringList("totgrund");
			totgrund.add(e.getDeathMessage() + "X:" + e.getEntity().getLocation().getBlockX() + "Y:" + e.getEntity().getLocation().getBlockY() + "Z:" + e.getEntity().getLocation().getBlockZ());
			plugin.getConfig().set("totgrund", totgrund);
			
			if(e.getEntity().getKiller() == null) {
				
			    e.setDeathMessage(null);
			    Bukkit.broadcastMessage("§e" + e.getEntity().getName() + " §7ist gestorben");
			    Discord.sendBroadcast(e.getEntity().getName() + " ist gestorben");
				
			} else {
				
				e.setDeathMessage(null);
				Bukkit.broadcastMessage("§e" + e.getEntity().getName() + " §7wurde von §e" + e.getEntity().getKiller().getName() + " §7get§tet");
				Discord.sendBroadcast(e.getEntity().getName() + " wurde von " + e.getEntity().getKiller().getName() + " get§tet");
				
			}
			
			List<String> tote = plugin.getConfig().getStringList("tote");
			tote.add(e.getEntity().getUniqueId().toString());
			plugin.getConfig().set("tote", tote);
			
			plugin.saveConfig();
			
//			double formel = (((double)Main.defaultworldborder/(double)plugin.getConfig().getStringList("member").size())*((double)plugin.getConfig().getStringList("member").size()-(double)plugin.getConfig().getStringList("tote").size()));
//			Integer gerundet = (int) Math.round(formel);
//			Integer ungrade;
			
//			if (gerundet%2 == 0) {
//				ungrade = gerundet + 1;
//			} else {
//				ungrade = gerundet;
//			}
			
			if(plugin.getConfig().getBoolean("end") == false) {
				
				if(Main.currentbordervalue == null) {
					
					Main.currentbordervalue = (int) (Bukkit.getWorlds().get(0).getWorldBorder().getSize() - plugin.getConfig().getInt("worldborderdecrease"));
					
					double vorher = (Bukkit.getWorlds().get(0).getWorldBorder().getSize() - 1)/2;
					double nacher = (Main.currentbordervalue - 1)/2;
					double unterschied = vorher - nacher;
					Integer unterschiedint = (int) unterschied;
					
					Bukkit.broadcastMessage("§3Die Weltgrenze wird durch den Tod von §e" + e.getEntity().getName() + " §3um §e" + unterschiedint + " §3Bl§cke innerhalb von §e10 §3Minuten verkleinert");
					Discord.sendBroadcast("Die Weltgrenze wird durch den Tod von " + e.getEntity().getName() + " um " + unterschiedint + " Bl§cke innerhalb von 10 Minuten verkleinert");
					
					for(World worlds : Bukkit.getWorlds()) {
						
						if(worlds.getName() != Bukkit.getWorld(plugin.getConfig().getString("wartelobby.World")).getName()) {
							
							worlds.getWorldBorder().setSize(Main.currentbordervalue, 600);
							
						}
						
					}
					
				} else {
					
					if(Main.worldborderqueue.isEmpty()) {
						
						Integer wbl = Main.currentbordervalue - plugin.getConfig().getInt("worldborderdecrease");
						
						Main.worldborderqueue.add(wbl);
						Main.worldborderqueueplayer.put(wbl, e.getEntity().getName());
						
					} else {
						
						Integer wbl = Collections.min(Main.worldborderqueue) - plugin.getConfig().getInt("worldborderdecrease");
						
						Main.worldborderqueue.add(wbl);
						Main.worldborderqueueplayer.put(wbl, e.getEntity().getName());
						
					}
					
				}
				
			}
			
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

				@SuppressWarnings("deprecation")
				public void run() {
					
					if(e.getEntity().getScoreboard().getPlayerTeam(e.getEntity()) != null) {
						
						e.getEntity().getScoreboard().getPlayerTeam(e.getEntity()).removePlayer(e.getEntity());
						
					}
					
					if(e.getEntity().isOnline()) {	
						e.getEntity().kickPlayer("§4Du bist gestorben. §cDamit bist du aus §eVaro §causgeschieden");
					}
					
				}
				
			}, 300);
			
		}
		
	}

}
