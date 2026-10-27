package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class EntityDamageByEntityListener implements Listener {
	
	private Main plugin;
	public EntityDamageByEntityListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		
		if (e.getDamager() instanceof FishHook) {			
	        FishHook fishhook = (FishHook)e.getDamager();
	      
	        if(fishhook.getShooter() instanceof Player) {
	        	
	    	    e.setCancelled(true);
	    	    Player p = (Player) fishhook.getShooter();
	    	    p.sendMessage("§cDu darfst mit einer Angel kein Schaden hinzuf§gen");
	    	    
	        }
	    }
		
		if(e.getDamager() instanceof Player) {
			
			Player dmger = (Player) e.getDamager();
			
			if(dmger.getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
				
				e.setCancelled(true);
				
			}
			
			if(Main.startprotection.containsKey(dmger)) {
				
				e.setCancelled(true);
				
			}
			
		}
		
		if(plugin.getConfig().getBoolean("schutzzeit") == true) {
			
			if(e.getEntity() instanceof Player & e.getDamager() instanceof Player) {
				e.setCancelled(true);
			}
			
			if (((e.getDamager() instanceof Arrow)) && ((e.getEntity() instanceof Player))) {			
		        Arrow arrow = (Arrow)e.getDamager();
		      
		        if(arrow.getShooter() instanceof Player) {
		        	
		    	    e.setCancelled(true);
		    	  
		        }
		    }
			
		} else {
			
			if(e.getEntity() instanceof Player & e.getDamager() instanceof Player) {
				
				Player schadenbekommener = (Player) e.getEntity();
				Player schadenausteilender = (Player) e.getDamager();
				
				if(schadenbekommener.getScoreboard().getPlayerTeam(schadenbekommener) != null && schadenausteilender.getScoreboard().getPlayerTeam(schadenausteilender) != null) {
					
					if(schadenbekommener.getScoreboard().getPlayerTeam(schadenbekommener).getName() == schadenausteilender.getScoreboard().getPlayerTeam(schadenausteilender).getName()) {
						
						e.setCancelled(true);
						
					}
					
				}
				
			}
			
			if (((e.getDamager() instanceof Arrow)) && ((e.getEntity() instanceof Player))) {			
		        Arrow arrow = (Arrow)e.getDamager();
		      
		        if(arrow.getShooter() instanceof Player) {
		        	
		        	Player schadenbekommener = (Player) e.getEntity();
		    	    Player schadenausteilender = (Player) arrow.getShooter();
		    	    
		    	    if(schadenbekommener == schadenausteilender) {
		    	    	
		    	    	return;
		    	    	
		    	    }
		    	    
					if(schadenbekommener.getScoreboard().getPlayerTeam(schadenbekommener) != null && schadenausteilender.getScoreboard().getPlayerTeam(schadenausteilender) != null) {
						
						if(schadenbekommener.getScoreboard().getPlayerTeam(schadenbekommener).getName()  == schadenausteilender.getScoreboard().getPlayerTeam(schadenausteilender).getName() ) {
							
							e.setCancelled(true);
							
						}
						
					}
		    	  
		        }
		    }
			
		}
		
		
		
	}

}
