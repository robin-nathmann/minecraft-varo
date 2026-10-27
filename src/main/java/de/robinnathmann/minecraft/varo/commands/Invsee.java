package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import de.robinnathmann.minecraft.varo.main.Main;

public class Invsee implements CommandExecutor {
	
	private Main plugin;
	public Invsee(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("Invsee")) {
			
			if(sender instanceof Player) {
				
				Player p = (Player) sender;
				
				if(p.getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid"))) {
					
					if(args.length == 1) {
						
						if(Bukkit.getServer().getPlayer(args[0]) != null) {
							
				            Player tplayer = p.getServer().getPlayer(args[0]);
				            Inventory tinv = tplayer.getInventory();				            
				            p.openInventory(tinv);
							
						} else {
							
							sender.sendMessage("§c" + args[0] + " ist aktuell nicht online");
							
						}
						
					} else {
						
						sender.sendMessage("§cBitte nutze: §e/invsee <Spieler>");
						
					}
					
				} else {
					
					p.sendMessage("§cDu kannst diesen Befehl nur mit den Spectator Account ausf§hren");
					
				}
				
			}
			
		}
		
		return true;
	}

}
