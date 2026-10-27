package de.robinnathmann.minecraft.varo.packetlisteners;

import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

public class Tabcomplete {
	
	@SuppressWarnings("deprecation")
	public static void registerTabcomplete(Plugin plugin) {
		
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, ConnectionSide.CLIENT_SIDE,
                ListenerPriority.NORMAL, new Integer[]{Integer.valueOf(203)}) {
            public void onPacketReceiving(PacketEvent event) {
                if (event.getPacketID() == 203) {
                	
                    if (!event.getPlayer().hasPermission("varo.admin")) {
                    	
                    	event.setCancelled(true);
                    	
                    }                  
                	
                }

            }
        });
		
	}

}
