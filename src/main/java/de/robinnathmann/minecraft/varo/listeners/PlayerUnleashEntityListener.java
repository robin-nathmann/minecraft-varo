package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerUnleashEntityEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerUnleashEntityListener implements Listener {
	
	private Main plugin;
	public PlayerUnleashEntityListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerUnleashEntity(PlayerUnleashEntityEvent e) {
		
		if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setCancelled(true);
			return;
			
		}
		
		if(Main.startprotection.containsKey(e.getPlayer())) {
			
			e.setCancelled(true);
			
		}
		
	}

}
