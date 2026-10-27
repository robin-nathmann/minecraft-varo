package de.robinnathmann.minecraft.varo.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.robinnathmann.minecraft.varo.main.Main;

public class BlockPlaceListener implements Listener {
	
	private Main plugin;
	public BlockPlaceListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		
		Player p = e.getPlayer();
		
		if(plugin.getConfig().getStringList("protectedblocks").contains(e.getBlock().getLocation().toString())) {
			
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cDu kannst an dieser Stelle nicht bauen");
			return;
			
		}
			
		if(e.getBlock().getType() == Material.CHEST) {
			
			if(p.getScoreboard().getPlayerTeam(p) != null) {
				
				if(
						e.getBlock().getLocation().add(1, 0, 0).getBlock().getType() == Material.CHEST |
						e.getBlock().getLocation().add(-1, 0, 0).getBlock().getType() == Material.CHEST |
						e.getBlock().getLocation().add(0, 0, 1).getBlock().getType() == Material.CHEST |
						e.getBlock().getLocation().add(0, 0, -1).getBlock().getType() == Material.CHEST
						) {
							
							int xmax = e.getBlock().getX() + 1;
							int xmin = e.getBlock().getX() - 1;
							int zmax = e.getBlock().getZ() + 1;
							int zmin = e.getBlock().getZ() - 1;
							
							String locxadd = e.getBlock().getWorld().getName().toString() + "," + xmax + "," + e.getBlock().getY() + "," + e.getBlock().getZ();
							String locxremove = e.getBlock().getWorld().getName().toString() + "," + xmin + "," + e.getBlock().getY() + "," + e.getBlock().getZ();
							String loczadd = e.getBlock().getWorld().getName().toString() + "," + e.getBlock().getX() + "," + e.getBlock().getY() + "," + zmax;
							String loczremove = e.getBlock().getWorld().getName().toString() + "," + e.getBlock().getX() + "," + e.getBlock().getY() + "," + zmin;
									
							if(
							Main.savedchest.containsKey(locxadd) |
							Main.savedchest.containsKey(locxremove) |
							Main.savedchest.containsKey(loczadd) |
							Main.savedchest.containsKey(loczremove)
							) {
									
									if(
											Main.savedchest.containsKey(locxadd) && Main.savedchest.get(locxadd).equals(p.getScoreboard().getPlayerTeam(p).getName().toString()) ||
											Main.savedchest.containsKey(locxremove) && Main.savedchest.get(locxremove).equals(p.getScoreboard().getPlayerTeam(p).getName().toString()) ||
											Main.savedchest.containsKey(loczadd) && Main.savedchest.get(loczadd).equals(p.getScoreboard().getPlayerTeam(p).getName().toString()) ||
											Main.savedchest.containsKey(loczremove) && Main.savedchest.get(loczremove).equals(p.getScoreboard().getPlayerTeam(p).getName().toString())
											) {
												
												if(e.getBlock().getLocation().add(1, 0, 0).getBlock().getType() == Material.CHEST) {
													
													Main.savedchest.put(e.getBlock().getWorld().getName().toString() + "," + e.getBlock().getX() + "," + e.getBlock().getY() + "," + e.getBlock().getZ(), p.getScoreboard().getPlayerTeam(p).getName().toString());
													p.sendMessage("§aDu hast deine Teamkiste erweitert");
													p.playSound(p.getLocation(), Sound.PISTON_EXTEND, 1, 1);
												    for (Map.Entry<String, String> entry : Main.savedchest.entrySet()) {
												    	plugin.getConfig().set("lockchest." + entry.getKey(), entry.getValue());
												    }
													
												    plugin.saveConfig();
												    return;
													
												} else {
													
													if(e.getBlock().getLocation().add(-1, 0, 0).getBlock().getType() == Material.CHEST) {
														
														Main.savedchest.put(e.getBlock().getWorld().getName().toString() + "," + e.getBlock().getX() + "," + e.getBlock().getY() + "," + e.getBlock().getZ(), p.getScoreboard().getPlayerTeam(p).getName().toString());
														p.sendMessage("§aDu hast deine Teamkiste erweitert");
														p.playSound(p.getLocation(), Sound.PISTON_EXTEND, 1, 1);
													    for (Map.Entry<String, String> entry : Main.savedchest.entrySet()) {
													    	plugin.getConfig().set("lockchest." + entry.getKey(), entry.getValue());
													    }
														
													    plugin.saveConfig();
													    return;
														
													} else {
														
														if(e.getBlock().getLocation().add(0, 0, 1).getBlock().getType() == Material.CHEST) {
															
															Main.savedchest.put(e.getBlock().getWorld().getName().toString() + "," + e.getBlock().getX() + "," + e.getBlock().getY() + "," + e.getBlock().getZ(), p.getScoreboard().getPlayerTeam(p).getName().toString());
															p.sendMessage("§aDu hast deine Teamkiste erweitert");
															p.playSound(p.getLocation(), Sound.PISTON_EXTEND, 1, 1);
														    for (Map.Entry<String, String> entry : Main.savedchest.entrySet()) {
														    	plugin.getConfig().set("lockchest." + entry.getKey(), entry.getValue());
														    }
															
														    plugin.saveConfig();
														    return;
															
														} else {
															
															if(e.getBlock().getLocation().add(0, 0, -1).getBlock().getType() == Material.CHEST) {
																
																Main.savedchest.put(e.getBlock().getWorld().getName().toString() + "," + e.getBlock().getX() + "," + e.getBlock().getY() + "," + e.getBlock().getZ(), p.getScoreboard().getPlayerTeam(p).getName().toString());
																p.sendMessage("§aDu hast deine Teamkiste erweitert");
																p.playSound(p.getLocation(), Sound.PISTON_EXTEND, 1, 1);
															    for (Map.Entry<String, String> entry : Main.savedchest.entrySet()) {
															    	plugin.getConfig().set("lockchest." + entry.getKey(), entry.getValue());
															    }
																
															    plugin.saveConfig();
															    return;
																
															}
															
														}
														
													}
													
												}
												
											} else {
												
												e.setCancelled(true);
												p.sendMessage("§cDu kannst nicht die Teamkiste eines anderen Teams erweitern");
												return;
												
											}
								
							} else {
								
								return;
								
							}
							
						}
				
			} else {
				
				e.setCancelled(true);
				p.sendMessage("§cDu bist in keinen Team");
				return;
				
			}
			
			
			
			
			Inventory chestsave = Bukkit.createInventory(null, 27, "§6M§chtest du die Kiste sichern?");

			ItemStack ja = new ItemStack(Material.EMERALD_BLOCK, 1);
			ItemMeta jaim = (ItemMeta) ja.getItemMeta();
			jaim.setDisplayName("§aJa");
			List<String> jalore = new ArrayList<String>();
			jalore.add("§7Falls du eine andere Kiste gesichert hast,");
			jalore.add("§7wird die Sicherung der anderen Kiste entfernt");
			jaim.setLore(jalore);
			ja.setItemMeta(jaim);
			chestsave.setItem(11, ja);
			
			ItemStack nein = new ItemStack(Material.REDSTONE_BLOCK, 1);
			ItemMeta neinim = (ItemMeta) nein.getItemMeta();
			neinim.setDisplayName("§cNein");
			nein.setItemMeta(neinim);
			chestsave.setItem(15, nein);

			e.getPlayer().openInventory(chestsave);
			
			Main.openplayer.put(e.getPlayer(), e.getBlock().getLocation());
			
		}
		
	}

}
