package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerLeashEntityListener implements Listener {
	
	private Main plugin;
	public PlayerLeashEntityListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerLeashEntity(PlayerLeashEntityEvent e) {
		
		if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setCancelled(true);
			e.getPlayer().updateInventory();
			return;
			
		}
		
		if(Main.startprotection.containsKey(e.getPlayer())) {
			
			e.setCancelled(true);
			e.getPlayer().updateInventory();
			
		}
		
	}

}
