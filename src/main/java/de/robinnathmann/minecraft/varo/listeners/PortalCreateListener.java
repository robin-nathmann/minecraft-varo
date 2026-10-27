package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class PortalCreateListener implements Listener {
	
	private Main plugin;
	public PortalCreateListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPortalCreate(PortalCreateEvent e) {
		
		if(plugin.getConfig().getBoolean("beforegame") == false) {
			
			e.setCancelled(true);
			
		}
		
	}

}
