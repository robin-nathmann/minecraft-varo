package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class FoodLevelChangeListener implements Listener {
	
	private Main plugin;
	public FoodLevelChangeListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		
		if(e.getEntity().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setCancelled(true);
			return;
			
		}
		
		if(Main.startprotection.containsKey(e.getEntity())) {
			
			e.setCancelled(true);
			
		}
		
	}

}
