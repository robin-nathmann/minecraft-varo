package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class EntityRegainHealthListener implements Listener {
	
	private Main plugin;
	public EntityRegainHealthListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent e) {
		
		if(e.getEntity().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setCancelled(true);
			return;
			
		}
		
		if(Main.startprotection.containsKey(e.getEntity())) {
			
			e.setCancelled(true);
			
		}
		
	}

}
