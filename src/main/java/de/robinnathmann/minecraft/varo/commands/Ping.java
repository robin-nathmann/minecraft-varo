package de.robinnathmann.minecraft.varo.commands;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("ping")) {
			
			if(sender instanceof Player) {
				
				try {
					sender.sendMessage("§3Dein Ping: §e" + getPing((Player) sender) + " ms");
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | NoSuchFieldException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return true;
	}
	
	public static int getPing(Player player) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException{
		Object nms_player = player.getClass().getMethod("getHandle").invoke(player);
		Field fieldPing = nms_player.getClass().getDeclaredField("ping");
		fieldPing.setAccessible(true);
		return fieldPing.getInt(nms_player);
	}

}
