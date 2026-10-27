package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.Discord;
import de.robinnathmann.minecraft.varo.methods.Tablist;
import de.robinnathmann.minecraft.varo.methods.WorldBorderMethods;

public class PlayerJoinListener implements Listener {
	
	private Main plugin;
	public PlayerJoinListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		Tablist.sendHeaderAndFooter(p, "§3Minecraft Varo §8>> §6by our Gaming-Community\n§3Projektleiter §8>> §6" + plugin.getConfig().getString("Projektleiter"), "§3TeamSpeak§ §8>> §6" + plugin.getConfig().getString("TS3") + "\n§3Discord §8>> §6" + plugin.getConfig().getString("Discord") + "\n§3Regeln §8>> §6" + plugin.getConfig().getString("Regeln"));
		
		if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.getPlayer().setGameMode(GameMode.SPECTATOR);
			e.setJoinMessage(null);
			e.getPlayer().sendMessage("§aDu bist nun als Spectator online, niemand kann dich sehen");
			
		} else {
			
			if(plugin.getConfig().getBoolean("beforegame") == true) {
				
				e.setJoinMessage("§e" + e.getPlayer().getName() + " §3hat den Server betreten");
				
			}		
			
		}
			
		if(plugin.getConfig().getBoolean("beforegame") == true) {
			
			e.getPlayer().sendMessage("§aWir bitten dich die Regeln ausf§hrlich durchzulesen§7: §e" + plugin.getConfig().getString("Regeln"));
			e.getPlayer().sendMessage("§aDu kannst dir jederzeit mithilfe des Befehls §e/zeit §adeine verbleibende Spielzeit anzeigen lassen");
			
			if(!e.getPlayer().hasPlayedBefore()) {
				
			    World World = Bukkit.getWorld(plugin.getConfig().getString("wartelobby.World"));
			    double X = plugin.getConfig().getDouble("wartelobby.X");
			    double Y = plugin.getConfig().getDouble("wartelobby.Y");
			    double Z = plugin.getConfig().getDouble("wartelobby.Z");
			    float Yaw = (float) plugin.getConfig().getDouble("wartelobby.Yaw");
			    float Pitch = (float) plugin.getConfig().getDouble("wartelobby.Pitch");
			    e.getPlayer().teleport(new Location(World, X, Y, Z, Yaw, Pitch));
			    
			    for (Player player : Bukkit.getOnlinePlayers()) {
			    	
			    	player.hidePlayer(p);
			    	player.showPlayer(p);
			    	
			    }
				
			}
				
		} else {
			
			if(!(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid")))) {
				
				Main.startprotection.put(p, 0);
				Main.oxygen.put(p, p.getRemainingAir());
				Main.statuseffects.put(p, p.getActivePotionEffects());
				Main.fireticks.put(p, p.getFireTicks());
				
				e.setJoinMessage(null);
				Bukkit.broadcastMessage("§e" + e.getPlayer().getName() + " §3hat den Server betreten und ist in §e10 §3Sekunden angreifbar");
				Discord.sendBroadcast(e.getPlayer().getName() + " hat den Server betreten");
				
			}
			
		}
		
		if(!(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid")))) {						
			
			if(WorldBorderMethods.isOutsideOfBorder(p)) {
				 
			    World World = Bukkit.getWorld(plugin.getConfig().getString("respawn.World"));
			    double X = plugin.getConfig().getDouble("respawn.X");
			    double Y = plugin.getConfig().getDouble("respawn.Y");
			    double Z = plugin.getConfig().getDouble("respawn.Z");
			    e.getPlayer().teleport(new Location(World, X, Y, Z, 0, 0));
				
				Bukkit.broadcastMessage("§e" + p.getName() + " §3wurde durch die Weltgrenze zum Spawn teleportiert");
				Discord.sendBroadcast(p.getName() + " wurde durch die Weltgrenze zum Spawn teleportiert");
				
			}
			
		}
		
	}

}
