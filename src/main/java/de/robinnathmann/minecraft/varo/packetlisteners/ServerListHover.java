package de.robinnathmann.minecraft.varo.packetlisteners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;

import de.robinnathmann.minecraft.varo.main.Main;

public class ServerListHover {
	
	@SuppressWarnings("deprecation")
	public static void setServerListHover(Plugin plugin, Boolean outoftime) {
		
        List<WrappedGameProfile> names = new ArrayList<WrappedGameProfile>();
        
        Integer counter = 1;
        
        for(Player players : Bukkit.getOnlinePlayers()) {
        	
        	if(!(players.getUniqueId().toString().equals(Main.specuuid))) {
        		
        		if(players.hasPermission("varo.admin")) {
        			
        			names.add(new WrappedGameProfile(counter.toString(), "§4" + players.getName()));
        			counter++;
        			
        		} else {
        			
        			names.add(new WrappedGameProfile(counter.toString(), "§a" + players.getName()));
        			counter++;
        			
        		}
        		
        	}
        	
        }

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL,
                Arrays.asList(PacketType.Status.Server.OUT_SERVER_INFO), ListenerOptions.ASYNC) {
                @Override
                public void onPacketSending(PacketEvent event) {
                    event.getPacket().getServerPings().read(0).setPlayers(names);
                    
                    if(outoftime == true) {
                    	
                    	event.getPacket().getServerPings().read(0).setVersionProtocol(-1);
                    	event.getPacket().getServerPings().read(0).setVersionName("Nur von 16 bis 21 Uhr betretbar");
                    	
                    } else {
                    	
                    	event.getPacket().getServerPings().read(0).setVersionProtocol(47);
                    	event.getPacket().getServerPings().read(0).setVersionName("1.8");
                    	
                    }
                    
                }
            });
		
	}

}
