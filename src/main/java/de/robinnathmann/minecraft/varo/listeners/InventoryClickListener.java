package de.robinnathmann.minecraft.varo.listeners;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import de.robinnathmann.minecraft.varo.main.Main;

public class InventoryClickListener implements Listener {
	
	private Main plugin;
	public InventoryClickListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if(e.getCurrentItem() != null) {
			
			if(e.getCurrentItem().getType() == Material.BED || e.getCurrentItem().getType() == Material.SADDLE || e.getCurrentItem().getType() == Material.ENDER_CHEST || (e.getCurrentItem().getType() == Material.GOLDEN_APPLE & e.getCurrentItem().getDurability() == 1) || e.getCurrentItem().getType() == Material.POTION & !(e.getCurrentItem().getDurability() == 8261 | e.getCurrentItem().getDurability() == 8229 | e.getCurrentItem().getDurability() == 16 | e.getCurrentItem().getDurability() == 0 | e.getCurrentItem().getDurability() == 8197 | e.getCurrentItem().getDurability() == 8232 | e.getCurrentItem().getDurability() == 8264 | e.getCurrentItem().getDurability() == 16424 | e.getCurrentItem().getDurability() == 16456 | e.getCurrentItem().getDurability() == 8200 | e.getCurrentItem().getDurability() == 16392)) {
				
				e.setCancelled(true);
				
				if(e.getCurrentItem().getDurability() == 0) {
					
					e.getWhoClicked().sendMessage("§cDu darfst das Item §e" + e.getCurrentItem().getType() + " §cnicht benutzen");
					
				} else {
					
					e.getWhoClicked().sendMessage("§cDu darfst das Item §e" + e.getCurrentItem().getType() + ":" + e.getCurrentItem().getDurability() + " §cnicht benutzen");
					
				}
				
				ItemStack is = new ItemStack(e.getCurrentItem().getType());
				is.setDurability(e.getCurrentItem().getDurability());
				
				e.getInventory().remove(is);
				e.getWhoClicked().getInventory().remove(is);
				
			} else {
				
				 
					
					if(p.getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
						
						if(e.getInventory().getType() != InventoryType.PLAYER || e.getInventory().getType() == InventoryType.PLAYER & e.getInventory().getHolder() != p) {
							
							e.setCancelled(true);
							
						}
						
					} else {
						
						if(Main.startprotection.containsKey(p)) {
							
							e.setCancelled(true);
							
						}
						
					}
				}
				
			}
			
		
		
		if(e.getInventory().getName().equals("§6M§chtest du die Kiste sichern?")) {
			
			e.setCancelled(true);
			
			if(e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.REDSTONE_BLOCK && e.getCurrentItem().getItemMeta().getDisplayName().equals("§cNein")) {
								
				Main.openplayer.remove(p);
				p.closeInventory();
				p.sendMessage("§cDu hast diese Kiste nicht gesichert");
				
			} else {
				
				if(e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.EMERALD_BLOCK && e.getCurrentItem().getItemMeta().getDisplayName().equals("§aJa")) {
					
					if(p.getScoreboard().getPlayerTeam(p) != null) {
						
						if(Main.savedchest.containsValue(p.getScoreboard().getPlayerTeam(p).getName().toString())) {
							
							for(String all : getKeysByValue(Main.savedchest, p.getScoreboard().getPlayerTeam(p).getName().toString())) {
								
								Main.savedchest.remove(all);
								
								plugin.getConfig().set("lockchest." + all, null);	
							    plugin.saveConfig();
								
							}
							
							p.sendMessage("§4Deine alte Teamkiste wurde entsichert");
							p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
							
						}
						
						Main.savedchest.put(Main.openplayer.get(p).getWorld().getName().toString() + "," + Main.openplayer.get(p).getBlockX() + "," + Main.openplayer.get(p).getBlockY() + "," + Main.openplayer.get(p).getBlockZ(), p.getScoreboard().getPlayerTeam(p).getName().toString());
						Main.openplayer.remove(p);
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
						p.closeInventory();		
						p.sendMessage("§aDu hast deine Teamkiste gesetzt");
						
					    for (Entry<String, String> entry : Main.savedchest.entrySet()) {
					    	plugin.getConfig().set("lockchest." + entry.getKey(), entry.getValue());
					    }
						
					    plugin.saveConfig();
						
					} else {
						
						Main.openplayer.remove(p);
						p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
						p.closeInventory();
						p.sendMessage("§cDu bist in keinen Team");
						
					}
					
				}
				
			}
			
		}
		
	}
	
	public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
	    Set<T> keys = new HashSet<T>();
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            keys.add(entry.getKey());
	        }
	    }
	    return keys;
	}

}


