package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class EntityTargetListener implements Listener {
	
	private Main plugin;
	public EntityTargetListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityTarget(EntityTargetEvent e) {
		
		if(e.getTarget() == null) {
			
			return;
		}
		
		if(e.getTarget().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setTarget(null);
			return;
			
		}
		
		if(Main.startprotection.containsKey(e.getTarget())) {
			
			e.setTarget(null);
			
		}
		
	}

}
