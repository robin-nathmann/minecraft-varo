package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class BlockPhysicsListener implements Listener {
	
	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent e) { 
		  
		if (e.getBlock().getType().equals(Material.PORTAL)) {			
			e.setCancelled(true);
		}
		  
	}

}
