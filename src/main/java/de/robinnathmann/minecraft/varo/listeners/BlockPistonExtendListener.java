package de.robinnathmann.minecraft.varo.listeners;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class BlockPistonExtendListener implements Listener {
	
	private Main plugin;
	public BlockPistonExtendListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPistonExtend(BlockPistonExtendEvent e) {
		
		if(plugin.getConfig().getStringList("protectedblocks").contains(e.getBlock().getLocation().add(e.getDirection().getModX(), e.getDirection().getModY(), e.getDirection().getModZ()).toString())) {
			
			e.setCancelled(true);
			return;
			
		}
		
		List<Block> b = e.getBlocks();
		
		for(Block blocks : b) {
			
			if(plugin.getConfig().getStringList("protectedblocks").contains(blocks.getLocation().add(e.getDirection().getModX(), e.getDirection().getModY(), e.getDirection().getModZ()).toString())) {
				
				e.setCancelled(true);
				
			}
			
		}
		
	}

}
