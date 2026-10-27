package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class BlockFromToListener implements Listener {
	
	private Main plugin;
	public BlockFromToListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockFromTo(BlockFromToEvent e) {
		
		if(plugin.getConfig().getStringList("protectedblocks").contains(e.getToBlock().getLocation().toString())) {
			
			e.setCancelled(true);
			
		}
		
	}

}
