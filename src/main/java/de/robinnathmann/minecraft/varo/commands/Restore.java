package de.robinnathmann.minecraft.varo.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.robinnathmann.minecraft.varo.main.Main;

public class Restore implements CommandExecutor {
	
	private Main plugin;
	public Restore(Main plugin) {
	    this.plugin = plugin;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("restore") && sender.hasPermission("varo.admin")) {
			
			if(args.length == 2) {
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null) {
					
					sender.sendMessage("§c" + args[0] + " ist aktuell nicht online");
					
				} else {
					
					if(plugin.getConfig().get("inventorybackup." + args[1] + ".inventory") != null | plugin.getConfig().get("inventorybackup." + args[1] + ".armor") != null) {

		                Object a = plugin.getConfig().get("inventorybackup." + args[1] + ".inventory");
		                Object b = plugin.getConfig().get("inventorybackup." + args[1] + ".armor");
		                
		                ItemStack[] inventory = null;
		                ItemStack[] armor = null;
		                if (a instanceof ItemStack[]){
		                      inventory = (ItemStack[]) a;
		                } else if (a instanceof List){
		                        List lista = (List) a;
		                        inventory = (ItemStack[]) lista.toArray(new ItemStack[0]);
		                }
		                if (b instanceof ItemStack[]){
		                        armor = (ItemStack[]) b;
		                  } else if (b instanceof List){
		                      List listb = (List) b;
		                      armor = (ItemStack[]) listb.toArray(new ItemStack[0]);
		                  }
		                target.getInventory().clear();
		                target.getInventory().setContents(inventory);
		                target.getInventory().setArmorContents(armor);
		                target.setLevel(plugin.getConfig().getInt("inventorybackup." + args[1] + ".xplvl"));
		                target.setExp((float) plugin.getConfig().getDouble("inventorybackup." + args[1] + ".exp"));
		                
		                target.sendMessage("§aDein Inventar wurde wiederhergestellt");
		                
						for(Player players : Bukkit.getOnlinePlayers()) {
							
							if(players.hasPermission("varo.admin")) {
								
								players.sendMessage("§cDas Inventar von §e" + target.getName() + " §cwurde von §e" + sender.getName() + " §cwiederhergestellt");
								
							}
							
						}
						
					} else {
						
						sender.sendMessage("§cUng§ltige Inventory-ID");
						
					}
					
				}
				
				
				
			} else {
				
				sender.sendMessage("§cBitte nutze: §e/restore <Spieler> <Inventory-ID>");
				
			}
			
		}
	
		return true;
	}

}
