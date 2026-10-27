package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerEditBookListener implements Listener {
	
	private Main plugin;
	public PlayerEditBookListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerEditBook(PlayerEditBookEvent e) {
		
		if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setCancelled(true);
			return;
			
		}
		
		if(Main.startprotection.containsKey(e.getPlayer())) {
			
			e.setCancelled(true);
			
		}
		
	}

}
