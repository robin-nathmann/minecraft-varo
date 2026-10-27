package de.robinnathmann.minecraft.varo.methods;

import java.util.Collections;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerNearNoKick {
	
	@SuppressWarnings("deprecation")
	public static void shouldPlayerBeKicked(Player players, Plugin plugin) {
		
		if(players.getScoreboard().getPlayerTeam(players) != null) {
			
			HashMap<Double, Player> abstand = new HashMap<Double, Player>();
			
			for(Player allp : Bukkit.getOnlinePlayers()) {
				
				if(!allp.getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
					
					if(allp.getScoreboard().getPlayerTeam(allp) != null) {
						
						if(players != allp) {
							
							if(players.getWorld() == allp.getWorld()) {
								
								if(players.getScoreboard().getPlayerTeam(players).getName() != allp.getScoreboard().getPlayerTeam(allp).getName()) {
									
									abstand.put(allp.getLocation().distanceSquared(players.getLocation()), allp);
									
								}
								
							}

						}
						
					} else {
						
						allp.sendMessage("§cEs ist ein Fehler aufgetreten.");
						allp.sendMessage("§cBitte sage Dennis oder Robin: varo.methods.playernearnokick.allp.noteam");
						
					}
					
				}
				
			}
			
			if(abstand.isEmpty()) {
				if(Main.nokick.contains(players)) {
					Main.nokick.remove(players);
					ActionBar.sendActionBar(players, "");
				}
				return;									
			}
			
			Double kw = Collections.min(abstand.keySet());
			Player ks = abstand.get(kw);
			
			if(players.getLocation().distance(ks.getLocation()) <= 20) {
				
				if(!Main.nokick.contains(players)) {
					Main.nokick.add(players);
					
				}
				
			} else {
				
				if(Main.nokick.contains(players)) {
					Main.nokick.remove(players);
					ActionBar.sendActionBar(players, "");
				}								
				
			}
			
		} else {
			
			players.sendMessage("§cEs ist ein Fehler aufgetreten.");
			players.sendMessage("§cBitte sage Dennis oder Robin: varo.methods.playernearnokick.players.noteam");
			
		}
		
	}

}
