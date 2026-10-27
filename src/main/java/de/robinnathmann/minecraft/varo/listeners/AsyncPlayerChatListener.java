package de.robinnathmann.minecraft.varo.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.robinnathmann.minecraft.varo.main.Main;

public class AsyncPlayerChatListener implements Listener {
	
	private Main plugin;
	public AsyncPlayerChatListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
			
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cDu kannst als Spectator den Chat nicht benutzen");
			e.getPlayer().sendMessage("§aUm Ank§ndigungen usw. zu machen kannst du §e/bc <Nachricht> §abenutzen");
			
		} else {
			
			if(p.getScoreboard().getPlayerTeam(p) != null) {
				
				e.setFormat("§8[§e#" + p.getScoreboard().getPlayerTeam(p).getDisplayName() + "§8] " + p.getDisplayName() + "§7: §f" + e.getMessage());
				
			} else {
				
				e.setFormat(p.getDisplayName() + "§7: §f" + e.getMessage());
			}
			
		}
		
	}

}
