package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class EntityChangeBlockListener implements Listener {
	
	private Main plugin;
	public EntityChangeBlockListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent e) {
		
		if(e.getEntityType() == EntityType.WITHER | e.getEntityType() == EntityType.ENDER_DRAGON) {
			
			if (plugin.getConfig().getStringList("protectedblocks").contains(e.getBlock().getLocation().toString()) | Main.savedchest.containsKey(e.getBlock().getWorld().getName().toString() + "," + e.getBlock().getX() + "," + e.getBlock().getY() + "," + e.getBlock().getZ())) {
				
				e.setCancelled(true);
				
			}	
				
		} else {
			
			if(e.getEntityType() == EntityType.FALLING_BLOCK) {
				
				if(plugin.getConfig().getStringList("protectedblocks").contains(e.getBlock().getLocation().toString())) {
					
					e.setCancelled(true);
					
				}
				
			}
			
		}
		
	}

}
