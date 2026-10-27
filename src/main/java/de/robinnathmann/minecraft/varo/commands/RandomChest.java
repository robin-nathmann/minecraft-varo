package de.robinnathmann.minecraft.varo.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RandomChest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("randomchest") && sender.hasPermission("varo.admin")) {
			
			if(sender instanceof Player) {
				
				Player p = (Player) sender;
				
	            ItemStack pb = new ItemStack(Material.BLAZE_ROD, 1);
	            ItemMeta pbm = pb.getItemMeta();
	            pbm.setDisplayName("§6Random Chest");
	            pbm.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
	            pb.setItemMeta(pbm);
	            
	            p.getInventory().addItem(pb);
	            p.updateInventory();
				
			}
			
		}
		
		return true;
	}
	
	

}
