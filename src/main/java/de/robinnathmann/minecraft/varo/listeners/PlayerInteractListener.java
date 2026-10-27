package de.robinnathmann.minecraft.varo.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import de.robinnathmann.minecraft.varo.main.Main;

public class PlayerInteractListener implements Listener {

	private Main plugin;

	public PlayerInteractListener(Main plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {

		Player p = e.getPlayer();

		if(e.getPlayer().getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid")) && e.getPlayer().getGameMode() != GameMode.SPECTATOR) {
			
			e.setCancelled(true);
			
		}
		
		if(Main.startprotection.containsKey(p)) {
			
			e.setCancelled(true);
			p.updateInventory();
			return;
			
		}

		if (!(e.getAction() == Action.PHYSICAL)) {

			if (plugin.getConfig().getBoolean("beforegame") == true) {

				if (p.getGameMode() != GameMode.CREATIVE) {

					e.setCancelled(true);
					p.updateInventory();

					return;

				} else {
					
					if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType() == Material.CHEST) {
						
						if(!(p.getItemInHand().getType() == Material.BLAZE_ROD && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.ARROW_FIRE))) {
							
							if(!(p.getItemInHand().getType() == Material.BLAZE_ROD && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.ARROW_INFINITE))) {
								
								e.setCancelled(true);
								p.sendMessage("§cDu kannst erst ab Spielstart in Kisten gucken");
								return;
								
							}
							
						}
						
					}
					
				}

			}

			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

				if (p.getItemInHand().getType() == Material.BLAZE_ROD && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.ARROW_FIRE)) {

					e.setCancelled(true);

					if (plugin.getConfig().getStringList("protectedblocks")
							.contains(e.getClickedBlock().getLocation().toString())) {

						List<String> pb = plugin.getConfig().getStringList("protectedblocks");
						pb.remove(e.getClickedBlock().getLocation().toString());
						plugin.getConfig().set("protectedblocks", pb);
						plugin.saveConfig();

						p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);

						p.sendMessage("§cDu hast den Block an den Koordinaten §e" + e.getClickedBlock().getX() + " "
								+ e.getClickedBlock().getY() + " " + e.getClickedBlock().getZ() + " §centsichert");

					} else {

						List<String> pb = plugin.getConfig().getStringList("protectedblocks");
						pb.add(e.getClickedBlock().getLocation().toString());
						plugin.getConfig().set("protectedblocks", pb);
						plugin.saveConfig();

						p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);

						p.sendMessage("§aDu hast den Block an den Koordinaten §e" + e.getClickedBlock().getX() + " "
								+ e.getClickedBlock().getY() + " " + e.getClickedBlock().getZ() + " §agesichert");

					}

				} else {

					if (p.getItemInHand().getType() == Material.BLAZE_ROD && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.ARROW_INFINITE)) {

						e.setCancelled(true);

						if (e.getClickedBlock().getType() == Material.CHEST) {

							Chest c = (Chest) e.getClickedBlock().getState();
							Inventory inv = c.getBlockInventory();

							inv.clear();

							Random r = new Random();
							int l = r.nextInt(16);

							List<ItemStack> items = new ArrayList<>();

							for (String all : plugin.getConfig().getStringList("randomchest")) {
								int ID = 0;
								int subID = 0;
								int amount = 0;
								int chance = 0;

								if (all.contains(":")) {

									String[] array = all.split(":");
									ID = Integer.valueOf(array[0]);
									String a = array[1];
									a = a.substring(0, 1);
									subID = Integer.valueOf(a);

								}

								String[] array = all.split(", ");
								amount = Integer.valueOf(array[1]);
								chance = Integer.valueOf(array[2]);

								for (int i = 0; i < chance; i++) {

									items.add(new ItemStack(ID, amount, (short) subID));

								}

							}

							while (l != 0) {

								l--;

								Random r2 = new Random();

								Random r3 = new Random();

								int n2 = r2.nextInt(27);

								int n3 = r3.nextInt(items.size());

								inv.setItem(n2, items.get(n3));

							}

							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
							p.sendMessage("§aDu hast diese Kiste zuf§llig generiert");

						}

					} else {

						if (e.getClickedBlock().getType() == Material.CHEST) {
							
							if (p.isSneaking()) {

								if(p.getItemInHand().getType() != Material.AIR) {

									if (p.getItemInHand().getType().isBlock()) {
										
										return;

									} else {
										
										e.setCancelled(true);
										return;
										
									}

								}

							}
							
							String loc = e.getClickedBlock().getWorld().getName().toString() + "," + e.getClickedBlock().getX() + "," + e.getClickedBlock().getY() + "," + e.getClickedBlock().getZ();

							p.updateInventory();

							if (Main.savedchest.containsKey(loc)) {

								if (p.getScoreboard().getPlayerTeam(p) != null) {

									if (Main.savedchest.get(loc).equals(p.getScoreboard().getPlayerTeam(p).getName().toString())) {

										p.sendMessage("§aDu hast deine Teamkiste ge§ffnet");

									} else {

										e.setCancelled(true);
										p.sendMessage("§cDiese Kiste wurde von Team §e" + Main.savedchest.get(loc) + " §cgesichert");

									}

								} else {

									if (p.getGameMode() == GameMode.SPECTATOR) {

										p.sendMessage("§cDiese Kiste wurde von Team §e" + Main.savedchest.get(loc) + " §cgesichert");

									} else {

										e.setCancelled(true);
										p.sendMessage("§cDu bist in keinen Team");

									}

								}

							} else {

								p.sendMessage("§aDiese Kiste ist nicht gesichert");

							}

						}

					}

				}

			}

		}

	}

}
