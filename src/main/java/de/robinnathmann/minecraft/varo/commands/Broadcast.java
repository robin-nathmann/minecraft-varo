package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.robinnathmann.minecraft.varo.methods.Discord;

public class Broadcast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("broadcast") && sender.hasPermission("varo.admin")) {
			
			if (args.length > 0) {
				String message = "";
				for (int i = 0; i < args.length; i++) {
					message = message + args[i] + " ";
				}

				message = ChatColor.translateAlternateColorCodes('&', message);

				Bukkit.broadcastMessage("§8[§6Varo§8] §a" + message);
				Discord.sendBroadcast(message);

			} else {
				sender.sendMessage("§cBitte nutze: §e/broadcast <Nachricht>");
			}
			
		}
		
		return true;
	}

}
