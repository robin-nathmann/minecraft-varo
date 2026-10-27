package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.Discord;

public class KoordinatenStrike implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("koordinatenstrike") && sender.hasPermission("varo.admin")) {
			
			if(args.length == 1) {
						
				sender.sendMessage("§aDu hast dem Spieler §e" + args[0] + " §aeinen Koordinaten Strike verpasst");
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null) {
					
					Bukkit.broadcastMessage("§3Koordinaten von §e" + args[0]);
					Bukkit.broadcastMessage("§3X§7: §e" + Main.logoutloc.get(args[0]).getBlockX());
					Bukkit.broadcastMessage("§3Y§7: §e" + Main.logoutloc.get(args[0]).getBlockY());
					Bukkit.broadcastMessage("§3Z§7: §e" + Main.logoutloc.get(args[0]).getBlockZ());
					
					Discord.sendBroadcast("Koordinaten von " + args[0] + "\nX: " + Main.logoutloc.get(args[0]).getBlockX() + "\nY: " + Main.logoutloc.get(args[0]).getBlockY() + "\nZ: " + Main.logoutloc.get(args[0]).getBlockZ());
					
				} else {
					
					Bukkit.broadcastMessage("§3Koordinaten von §e" + target.getName());
					Bukkit.broadcastMessage("§3X§7: §e" + target.getLocation().getBlockX());
					Bukkit.broadcastMessage("§3Y§7: §e" + target.getLocation().getBlockY());
					Bukkit.broadcastMessage("§3Z§7: §e" + target.getLocation().getBlockZ());
					
					Discord.sendBroadcast("Koordinaten von " + target.getName() + "\nX: " + target.getLocation().getBlockX() + "\nY: " + target.getLocation().getBlockY() + "\nZ: " + target.getLocation().getBlockZ());
					
				}
																
				Main.coordstrike.add(args[0]);
				
			} else {
				
				sender.sendMessage("§cBitte nutze: §e/koordinatenstrike <Player>");
				
			}
			
		}
		
		return true;
	}

}
