package de.robinnathmann.minecraft.varo.methods;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class ActionBar {
	
	public static void sendActionBar(Player p, String message) {
		if (message == null)
			message = "";

		PlayerConnection con = ((CraftPlayer) p).getHandle().playerConnection;

		IChatBaseComponent chat = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);

		con.sendPacket(packet);

	}

}
