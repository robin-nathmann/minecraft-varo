package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class BlockBreakListener implements Listener {
	
	private Main plugin;
	public BlockBreakListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setCancelled(true);
			return;
			
		}
		
		if(plugin.getConfig().getBoolean("beforegame") == true && e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			
			e.setCancelled(true);
			return;
			
		} else {
			
			if(plugin.getConfig().getStringList("protectedblocks").contains(e.getBlock().getLocation().toString())) {
				
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cDu kannst diesen Block nicht abbauen");
				return;
				
			} else {
				
				if(Main.startprotection.containsKey(e.getPlayer())) {
					
					e.setCancelled(true);
					
				} else {
					
					if(e.getBlock().getType() == Material.CHEST) {
						
						String rightloc = e.getBlock().getWorld().getName().toString() + "," + e.getBlock().getX() + "," + e.getBlock().getY() + "," + e.getBlock().getZ();
						
						if(Main.savedchest.containsKey(rightloc)) {
							
							if(e.getPlayer().getScoreboard().getPlayerTeam(e.getPlayer()) != null) {
								
								if(Main.savedchest.get(rightloc).equals(e.getPlayer().getScoreboard().getPlayerTeam(e.getPlayer()).getName().toString())) {
									
									e.getPlayer().sendMessage("§4Du hast deine Teamkiste abgebaut und damit die Sicherung der abgebauten Kiste entfernt");
									Main.savedchest.remove(rightloc);
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_BREAK, 1, 1);
									plugin.getConfig().set("lockchest." + rightloc, null);	
								    plugin.saveConfig();
									
								} else {
									
									e.getPlayer().sendMessage("§cDiese Kiste wurde von Team §e" + Main.savedchest.get(rightloc) + " §cgesichert");
									e.setCancelled(true);
									
								}
								
							} else {
								
								e.getPlayer().sendMessage("§cDu bist in keinen Team");
								e.setCancelled(true);
								
							}
							
						}
						
					}
					
				}
				
			}
					
		}
		
		
	}

}
