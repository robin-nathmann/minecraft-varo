package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryMoveItemListener implements Listener {
	
	@EventHandler
	public void onInventoryMoveItem(InventoryMoveItemEvent e) {
		
		if(e.getSource().getType().equals(InventoryType.CHEST) | e.getInitiator().getType().equals(InventoryType.CHEST) | e.getDestination().getType().equals(InventoryType.CHEST)) {
			
			e.setCancelled(true);
			
		}
		
	}

}
