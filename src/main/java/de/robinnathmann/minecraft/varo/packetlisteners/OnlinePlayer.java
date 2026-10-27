package de.robinnathmann.minecraft.varo.packetlisteners;


import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;

public class OnlinePlayer {
	
	public static void setOnlinePlayer(int onlineplayer, Plugin plugin) {
		
        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(PacketAdapter.params(plugin, PacketType.Status.Server.OUT_SERVER_INFO).optionAsync()) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        WrappedServerPing serverPing = event.getPacket().getServerPings().read(0);
                        serverPing.setPlayersOnline(onlineplayer);
                    }
                });
		
	}

}
