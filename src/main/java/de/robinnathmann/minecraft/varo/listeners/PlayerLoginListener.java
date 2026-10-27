package de.robinnathmann.minecraft.varo.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.Server;

public class PlayerLoginListener implements Listener {
	
	private Main plugin;
	public PlayerLoginListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		
		if(plugin.getConfig().getBoolean("setup") == true & (!e.getPlayer().hasPermission("varo.admin")) & (!e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid")))) {
			
			e.disallow(null, "§4Der Server ist momentan nur f§r Projektleiter zug§nglich");
			
		} else {
				
				if(plugin.getConfig().getConfigurationSection("gebannt") != null && plugin.getConfig().getConfigurationSection("gebannt").contains(e.getPlayer().getUniqueId().toString())) {
					
					List<String> bannreason = plugin.getConfig().getStringList("gebannt." + e.getPlayer().getUniqueId().toString());

					e.disallow(null, "§cDu wurdest vom Projekt §4AUSGESCHLOSSEN\n\n§e" + bannreason.get(0).replace("_", " ") + "\n\n§3Regelverstoߧ7:\n§c" + bannreason.get(1).replace("_", " ") + "\n\n§3Regeln§7:\n§c" + plugin.getConfig().getString("Regeln"));
					
				} else {
					
					if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
						
						if(Main.specallow == false) {
							
							e.disallow(null, "§4Um einen als Spectator Registrierten Account zu nutzen\nmuss dieser von den Projektleitern freigeschalten werden");
							
						} else {
							
							Main.specallow = false;
							
							for(Player players : Bukkit.getOnlinePlayers()) {
								
								if(players.hasPermission("varo.admin")) {
									
									players.sendMessage("§cDer Spectator Join wurde ausgeschaltet da ein Spectator Account gejoint ist");
									
								}
								
							}
							
							e.getPlayer().setGameMode(GameMode.SPECTATOR);
							
						}
						
						
						
					} else {
						
						if(plugin.getConfig().getBoolean("alreadystarted") == true && !plugin.getConfig().getStringList("member").contains(e.getPlayer().getUniqueId().toString())) {
							e.disallow(null, "§cDu bist nicht im Projekt!");
						} else {
			
							if((!Server.isServerOpen()) && plugin.getConfig().getBoolean("setup") == false && plugin.getConfig().getBoolean("alreadystarted") == true) {
								
								e.disallow(null, "§4Der Server ist nur von 16 Uhr bis 21 Uhr betretbar");
								
							} else {
								
								if(plugin.getConfig().getStringList("tote").contains(e.getPlayer().getUniqueId().toString())) {
									
									e.disallow(null, "§4Du bist gestorben. §cDamit bist du aus §eVaro §causgeschieden");

								} else {
									
									if(Main.onlinetimeatday.containsKey(e.getPlayer().getName()) && Main.onlinetimeatday.get(e.getPlayer().getName()) > 35999) {
										
										e.disallow(null, "§4Du hast die Maximale Onlinezeit von §e30 §4Minuten §berschritten\n\n§aDu kannst Morgen weiterspielen");
										
									}
									
								}
								
							}
							
						}
							
							
						

						
					}
					
				}
				

			

		}
		
	}

}
