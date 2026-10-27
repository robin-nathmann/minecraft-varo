package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.material.Dispenser;

import de.robinnathmann.minecraft.varo.main.Main;

public class BlockDispenseListener implements Listener {
	
	private Main plugin;
	public BlockDispenseListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockDispense(BlockDispenseEvent e) {
		
		if(e.getItem().getType() == Material.LAVA_BUCKET | e.getItem().getType() == Material.WATER_BUCKET | e.getItem().getType() == Material.FLINT_AND_STEEL) {
			
			Dispenser dis = (Dispenser) e.getBlock().getState().getData();
			
			if(plugin.getConfig().getStringList("protectedblocks").contains(e.getBlock().getLocation().add(dis.getFacing().getModX(), dis.getFacing().getModY(), dis.getFacing().getModZ()).toString())) {
				
				e.setCancelled(true);
				
			}
			
		}
		
	}

}
