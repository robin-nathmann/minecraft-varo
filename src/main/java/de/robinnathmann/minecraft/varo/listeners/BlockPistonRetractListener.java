package de.robinnathmann.minecraft.varo.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonRetractEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class BlockPistonRetractListener implements Listener {
	
	private Main plugin;
	public BlockPistonRetractListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPistonRetract(BlockPistonRetractEvent e) {
		
		if(e.getBlock().getType() == Material.PISTON_MOVING_PIECE) {
			
			
			List<Block> b = e.getBlocks();
			
		    for(Block blocks : b) {
		    	
		    	if(plugin.getConfig().getStringList("protectedblocks").contains(blocks.getLocation().add(e.getDirection().getModX(), e.getDirection().getModY(), e.getDirection().getModZ()).toString())) {
					
					e.setCancelled(true);
					
				} else {
					
					if(plugin.getConfig().getStringList("protectedblocks").contains(blocks.getLocation().toString())) {
						
						e.setCancelled(true);
						
					}
					
				}
		    	
		    }
						
		}
		
	}

}
