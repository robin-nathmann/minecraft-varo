package de.robinnathmann.minecraft.varo.methods;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
//import de.yoshicraft.justforfun.main.DiscordMC;
//import de.yoshicraft.justforfun.main.MessageAPI;

public class Discord {

	public static void sendBroadcast(String msg) {
		
		// 2025
		TextChannel broadcastChannel = DiscordSRV.getPlugin().getMainTextChannel();
		
		if (broadcastChannel != null) {
			broadcastChannel.sendMessage(msg).submit();
			System.out.print("Discord: " + msg);
		} else {
			System.out.print("Discord: ERROR SENDING BROADCAST (CHANNEL == NULL)");
		}
		
		
		// 2020
		//MessageAPI.sendToDiscord(DiscordMC.getClient().getGuilds().get(0).getChannelsByName(plugin.getConfig().getString("discordnotificationchannel")), msg);
		
	}
	
}
