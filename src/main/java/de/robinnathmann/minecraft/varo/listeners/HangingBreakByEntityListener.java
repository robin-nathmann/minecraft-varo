package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class HangingBreakByEntityListener implements Listener {
	
	private Main plugin;
	public HangingBreakByEntityListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
		
		if(e.getRemover().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setCancelled(true);
			return;
			
		}
		
		if(Main.startprotection.containsKey(e.getRemover())) {
			
			e.setCancelled(true);
			
		}
		
	}

}
