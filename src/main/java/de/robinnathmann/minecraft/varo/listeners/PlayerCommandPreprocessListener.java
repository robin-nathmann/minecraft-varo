package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerCommandPreprocessListener implements Listener {
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		
		String cmd = e.getMessage().toLowerCase();
		Player p = e.getPlayer();
		
		if(cmd.startsWith("/time")) {
			
			e.setCancelled(true);
			
			if(Main.onlinetimeatday.containsKey(p.getName())) {
				
				int realonlinetimeticks = 36000 - Main.onlinetimeatday.get(p.getName());
				int realonlinetimesecs = realonlinetimeticks / 20;
				int[] realonlinetimeall = getTime(realonlinetimesecs);
				String realonlinetimestring = "ERROR";
				
				//Wenn Stunden gez§hlt werden
				if(realonlinetimeall[2] != 0) {
					
					//Wenn Minute 1 entspricht
					if(realonlinetimeall[1] == 1) {
						
						//Wenn Sekunden 1 entspricht
						if(realonlinetimeall[0] == 1) {
							
							realonlinetimestring = realonlinetimeall[2] + " Stunde " + realonlinetimeall[1] + " Minute " + realonlinetimeall[0] + " Sekunde";
							
						} else {
						//Wenn Sekunde nicht 1 entspricht
							
							realonlinetimestring = realonlinetimeall[2] + " Stunde " + realonlinetimeall[1] + " Minute " + realonlinetimeall[0] + " Sekunden";
							
						}
						
					} else {
					//Wenn Minute nicht 1 entspricht
						
						//Wenn Sekunde 1 entspricht
						if(realonlinetimeall[0] == 1) {
							
							realonlinetimestring = realonlinetimeall[2] + " Stunde " + realonlinetimeall[1] + " Minuten " + realonlinetimeall[0] + " Sekunde";
							
						} else {
						//Wenn Sekunde nicht 1 entspricht
							
							realonlinetimestring = realonlinetimeall[2] + " Stunde " + realonlinetimeall[1] + " Minuten " + realonlinetimeall[0] + " Sekunden";
							
						}
						
					}
					
				} else {
				//Wenn Stunden nicht gez§hlt werden
					
					//Wenn Minuten gez§hlt werden
					if(realonlinetimeall[1] != 0) {
						
						//Wenn Minute 1 entspricht
						if(realonlinetimeall[1] == 1) {
							
							//Wenn Sekunden 1 entspricht
							if(realonlinetimeall[0] == 1) {
								
								realonlinetimestring = realonlinetimeall[1] + " Minute " + realonlinetimeall[0] + " Sekunde";
								
							} else {
							//Wenn Sekunde nicht 1 entspricht
								
								realonlinetimestring = realonlinetimeall[1] + " Minute " + realonlinetimeall[0] + " Sekunden";
								
							}
							
						} else {
						//Wenn Minute nicht 1 entspricht
							
							//Wenn Sekunde 1 entspricht
							if(realonlinetimeall[0] == 1) {
								
								realonlinetimestring = realonlinetimeall[1] + " Minuten " + realonlinetimeall[0] + " Sekunde";
								
							} else {
							//Wenn Sekunde nicht 1 entspricht
								
								realonlinetimestring = realonlinetimeall[1] + " Minuten " + realonlinetimeall[0] + " Sekunden";
								
							}
							
						}
						
					} else {
					//Wenn Minuten nicht gez§hlt werden
						
						//Wenn Sekunden 1 entspricht
						if(realonlinetimeall[0] == 1) {
							
							realonlinetimestring = realonlinetimeall[0] + " Sekunde";
							
						} else {
						//Wenn Sekunde nicht 1 entspricht
							
							realonlinetimestring = realonlinetimeall[0] + " Sekunden";
							
						}
						
					}
					
				}
				
				p.sendMessage("§3Du hast noch §e" + realonlinetimestring);
				
			}
			
		}
		
		if(!p.hasPermission("varo.admin")) {
			
			if(cmd.startsWith("/ping") || cmd.startsWith("/zeit")) {
				
			} else {
				
				p.sendMessage("§cUnbekannter Befehl!");
				e.setCancelled(true);
				
			}
			
			
		} else {
			
			if(cmd.startsWith("/op") | cmd.startsWith("/deop")) {
				
				p.sendMessage("§cUnbekannter Befehl!");
				e.setCancelled(true);
				
			}
			
		}
		
	}
	
    int[] getTime(int seconds) {
        int[] time = new int[3];
        int hours = (int) (Math.floor(seconds / 3600.0D));
        int rest = seconds - hours * 3600;
        int minutes = (int) (Math.floor(rest / 60.0D));
        rest = rest - minutes * 60;
        time[2] = hours;
        time[1] = minutes;
        time[0] = rest;
        return time;
     }

}
