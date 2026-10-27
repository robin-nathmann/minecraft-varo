package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDamageEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class VehicleDamageListener implements Listener {
	
	private Main plugin;
	public VehicleDamageListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onVehicleDamage(VehicleDamageEvent e) {
		
		if(e.getAttacker().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setCancelled(true);
			return;
			
		}
		
		if(Main.startprotection.containsKey(e.getAttacker())) {
			
			e.setCancelled(true);
			
		}
		
	}

}
