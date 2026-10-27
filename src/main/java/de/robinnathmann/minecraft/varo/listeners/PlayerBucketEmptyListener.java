package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerBucketEmptyListener implements Listener {
	
	private Main plugin;
	public PlayerBucketEmptyListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e) {
				
        Location clicked = e.getBlockClicked().getLocation();
        BlockFace f = e.getBlockFace();
        Location loc = new Location(clicked.getWorld(), clicked.getX() + f.getModX(), clicked.getY() + f.getModY(), clicked.getZ() + f.getModZ());
        
        if(plugin.getConfig().getStringList("protectedblocks").contains(loc.toString())) {
        	
        	e.setCancelled(true);
         	e.getPlayer().sendMessage("§cDu kannst an dieser Stelle nicht bauen");
        	
        }
		
	}

}
