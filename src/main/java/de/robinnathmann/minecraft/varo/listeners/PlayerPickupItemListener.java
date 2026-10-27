package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerPickupItemListener implements Listener {
	
	private Main plugin;
	public PlayerPickupItemListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
		
		if(e.getItem().getItemStack().getType() == Material.BED || e.getItem().getItemStack().getType() == Material.SADDLE || e.getItem().getItemStack().getType() == Material.ENDER_CHEST || (e.getItem().getItemStack().getType() == Material.GOLDEN_APPLE & e.getItem().getItemStack().getDurability() == 1) || e.getItem().getItemStack().getType() == Material.POTION & !(e.getItem().getItemStack().getDurability() == 8261 | e.getItem().getItemStack().getDurability() == 8229 | e.getItem().getItemStack().getDurability() == 16 | e.getItem().getItemStack().getDurability() == 0 | e.getItem().getItemStack().getDurability() == 8197 | e.getItem().getItemStack().getDurability() == 8232 | e.getItem().getItemStack().getDurability() == 8264 | e.getItem().getItemStack().getDurability() == 16424 | e.getItem().getItemStack().getDurability() == 16456 | e.getItem().getItemStack().getDurability() == 8200 | e.getItem().getItemStack().getDurability() == 16392)) {
		
			e.setCancelled(true);
			
			if(e.getItem().getItemStack().getDurability() == 0) {
				
				e.getPlayer().sendMessage("§cDu darfst das Item §e" + e.getItem().getItemStack().getType() + " §cnicht benutzen");
				
			} else {
				
				e.getPlayer().sendMessage("§cDu darfst das Item §e" + e.getItem().getItemStack().getType() + ":" + e.getItem().getItemStack().getDurability() + " §cnicht benutzen");
				
			}
			
			e.getItem().remove();
			
		} else {
			
			if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
				
				e.setCancelled(true);
				return;
				
			}
			
			if(Main.startprotection.containsKey(e.getPlayer())) {
				
				e.setCancelled(true);
				
			}
			
		}
			
	}
		
}

