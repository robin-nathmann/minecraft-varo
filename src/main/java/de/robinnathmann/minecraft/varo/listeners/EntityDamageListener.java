package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class EntityDamageListener implements Listener {
	
	private Main plugin;
	public EntityDamageListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		
		if(plugin.getConfig().getBoolean("beforegame") == true) {
			
			e.setCancelled(true);
			
		}
		
		if(e.getEntity() instanceof Player) {
			
			if(Main.startprotection.containsKey((Player) e.getEntity())) {
				
				e.setCancelled(true);
				
			}
			
			if(e.getEntity().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
				
				e.setCancelled(true);
				
			}
			
		}
		
	}

}
