package de.robinnathmann.minecraft.varo.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.Server;
import de.robinnathmann.minecraft.varo.packetlisteners.OnlinePlayer;
import de.robinnathmann.minecraft.varo.packetlisteners.ServerListHover;

public class ServerListPingListener implements Listener {
	
	private Main plugin;
	public ServerListPingListener(Main plugin) {
	    this.plugin = plugin;
	}
	
	@EventHandler
	public void onServerListPing(ServerListPingEvent e) {
		
		e.setMaxPlayers(plugin.getConfig().getInt("maxplayer"));
		
		String specuuid = plugin.getConfig().getString("spectatoruuid");
		UUID specuuidreal = UUID.fromString(specuuid);
		Integer op = Bukkit.getOnlinePlayers().size();
		
		if(Bukkit.getPlayer(specuuidreal) != null) {
			
			OnlinePlayer.setOnlinePlayer(Math.max(0, op - 1), plugin);
			
		} else {
			
			OnlinePlayer.setOnlinePlayer(op, plugin);
			
		}
		
		Boolean isServerClosed = !Server.isServerOpen();
		
		ServerListHover.setServerListHover(plugin, isServerClosed);
		
	}

}
