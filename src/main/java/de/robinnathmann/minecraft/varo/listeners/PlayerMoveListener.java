package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerMoveListener implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		
		if(Main.startprotection.containsKey(e.getPlayer())) {
			
			if(e.getFrom().getX() != e.getTo().getX()) {
				
				e.getPlayer().teleport(e.getFrom());
				
			}
			
			if(e.getFrom().getY() != e.getTo().getY()) {
				
				e.getPlayer().teleport(e.getFrom());
				
			}
			
			if(e.getFrom().getZ() != e.getTo().getZ()) {
				
				e.getPlayer().teleport(e.getFrom());
		    } 
			
		}
		
	}

}
