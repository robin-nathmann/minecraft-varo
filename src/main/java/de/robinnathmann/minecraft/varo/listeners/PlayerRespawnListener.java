package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerRespawnListener implements Listener {
	
	private Main plugin;
	public PlayerRespawnListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		
		if(plugin.getConfig().getStringList("tote").contains(e.getPlayer().getUniqueId().toString())) {
			
			e.getPlayer().kickPlayer("§4Du bist gestorben. §cDamit bist du aus §eVaro §causgeschieden");
			
			
			if(e.getPlayer().getScoreboard().getPlayerTeam(e.getPlayer()) != null) {
				
				e.getPlayer().getScoreboard().getPlayerTeam(e.getPlayer()).removePlayer(e.getPlayer());
				
			}
			
			

		}
		
	}

}
