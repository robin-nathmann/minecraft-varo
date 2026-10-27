package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerShearEntityListener implements Listener {
	
	private Main plugin;
	public PlayerShearEntityListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerShearEntity(PlayerShearEntityEvent e) {
		
		if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setCancelled(true);
			return;
			
		}
		
		if(Main.startprotection.containsKey(e.getPlayer())) {
			
			e.setCancelled(true);
			
		}
		
	}

}
