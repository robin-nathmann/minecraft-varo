package de.robinnathmann.minecraft.varo.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class EntityExplodeListener implements Listener {
	
	private Main plugin;
	public EntityExplodeListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		
	    List<Block> blockListCopy = new ArrayList<Block>();
	    blockListCopy.addAll(e.blockList());
	    for (Block b : blockListCopy) {
	    	
	      if (plugin.getConfig().getStringList("protectedblocks").contains(b.getLocation().toString()) | Main.savedchest.containsKey(b.getWorld().getName().toString() + "," + b.getX() + "," + b.getY() + "," + b.getZ())) {
	        e.blockList().remove(b);
	      }
	    }
		
	}

}
