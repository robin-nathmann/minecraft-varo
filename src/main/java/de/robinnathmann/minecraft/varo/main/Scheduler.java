package de.robinnathmann.minecraft.varo.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Team;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;
import de.robinnathmann.minecraft.varo.methods.ActionBar;
import de.robinnathmann.minecraft.varo.methods.Discord;
import de.robinnathmann.minecraft.varo.methods.HashMapKeysByValues;
import de.robinnathmann.minecraft.varo.methods.PlayerNearNoKick;
import de.robinnathmann.minecraft.varo.methods.RandomFirework;

public class Scheduler {
	
	public static void registerScheduler(Plugin plugin) {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				
				World w = null;
				
				for(World worlds : Bukkit.getWorlds()) {
					
					if(worlds.getName() != Bukkit.getWorld(plugin.getConfig().getString("wartelobby.World")).getName()) {
						
						w = worlds;
						
					}
					
				}
				
				if(Main.currentbordervalue != null && w.getWorldBorder().getSize() <= Main.currentbordervalue) {
					
					Main.currentbordervalue = null;
					
				}
				
				if(Main.worldborderqueue != null && !Main.worldborderqueue.isEmpty() && Main.currentbordervalue == null) {
					
					Integer max = Collections.max(Main.worldborderqueue);					
					
					double vorher = (w.getWorldBorder().getSize() - 1)/2;
					double nacher = (max - 1)/2;
					double unterschied = vorher - nacher;
					Integer unterschiedint = (int) unterschied;
					
					Bukkit.broadcastMessage("§3Die Weltgrenze wird durch den Tod von §e" + Main.worldborderqueueplayer.get(max) + " §3um §e" + unterschiedint + " §3Bl§cke innerhalb von §e10 §3Minuten verkleinert");
					Discord.sendBroadcast("Die Weltgrenze wird durch den Tod von " + Main.worldborderqueueplayer.get(max) + " um " + unterschiedint + " Bl§cke innerhalb von 10 Minuten verkleinert");
					
					Main.worldborderqueue.remove(max);
					Main.currentbordervalue = max;
					Main.worldborderqueueplayer.remove(max);
					
					for(World worlds : Bukkit.getWorlds()) {
						
						if(worlds.getName() != Bukkit.getWorld(plugin.getConfig().getString("wartelobby.World")).getName()) {
							
							worlds.getWorldBorder().setSize(max, 600);
							
						}
						
					}
					
				}
				
				String sdf2 = Main.sdf.format(new Date());
				int hour = (Integer.parseInt(sdf2));
				
				String sdf2min = Main.sdfmin.format(new Date());
				int min = Integer.parseInt(sdf2min);
				
				String sdf2sec = Main.sdfsec.format(new Date());
				int sec = Integer.parseInt(sdf2sec);
				
				if(hour == 00 & min == 00 & sec == 00) {
					
					Main.onlinetimeatday.clear();
					Main.coordstrike.clear();
					
				}
				
				if(Main.end == true) {
					
					for(Player players : Bukkit.getOnlinePlayers()) {
                    	
                    	if(!Main.sp.getPlayerList().contains(players)) {
    						
                    		Main.sp.addPlayer(players);
    						
    					}
                    	
                    }
				
					if (Main.sp.getSong().getLength() == Main.sp.getTick()) {

						for (Player players : Bukkit.getOnlinePlayers()) {

							players.sendTitle("§6The End", "§bThanks for playing");
								
							if(players.getGameMode() != GameMode.SPECTATOR) {
								
								RandomFirework.getManager().launchRandomFirework(players.getLocation());
								
							}
							
							Main.firework = 1;

						}

						Main.sp.destroy();
						Main.end = false;

					}

				}
				
				if(Main.firework == 100) {
					
					for(Player players : Bukkit.getOnlinePlayers()) {
						
						if(players.getGameMode() != GameMode.SPECTATOR) {
							
							RandomFirework.getManager().launchRandomFirework(players.getLocation());
							
						}
											
					}
					
					Main.firework = 1;
					
				}
				
				if(Main.firework != 0) {
					
					Main.firework++;
					
				}
												
				for(World worlds : Bukkit.getWorlds()) {
					
					if(worlds.getName() != Main.World.getName()) {
						
						if(worlds.getWorldBorder().getDamageAmount() != 0) {
							
							worlds.getWorldBorder().setDamageAmount(0);
							
						}
						
						if(worlds.getWorldBorder().getDamageBuffer() != 0) {
							
							worlds.getWorldBorder().setDamageBuffer(0);
							
						}
						
						if(worlds.getWorldBorder().getWarningDistance() != 0) {
							
							worlds.getWorldBorder().setWarningDistance(0);
							
						}
						
						if(worlds.getWorldBorder().getWarningTime() != Integer.MAX_VALUE) {
							
							worlds.getWorldBorder().setWarningTime(Integer.MAX_VALUE);
							
						}
						
					}
					
				}

				if(plugin.getConfig().getBoolean("beforegame") == true) {
					
					for (World worlds : Bukkit.getWorlds()) {
						
						if(worlds.getName() == Main.World.getName()) {						
							
						} else {
							
							worlds.setFullTime(0);
							
							worlds.getWorldBorder().setSize(Main.defaultworldborder);
							
						}
						
					}
					
					for (Player players : Bukkit.getOnlinePlayers()) {
						players.setFoodLevel(20);
						players.setSaturation(20);
						players.setFireTicks(0);
						
						if(players.getGameMode() == GameMode.SURVIVAL) {
							
							players.setGameMode(GameMode.ADVENTURE);
							
						}
						
					}
					
				} else {
					
					if(Bukkit.getScoreboardManager().getMainScoreboard().getTeams().size() == 1) {
						
						if(plugin.getConfig().getBoolean("end") == false) {
							
							for(Team teams : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
								
								Bukkit.broadcastMessage("§aDas Team §e" + teams.getName() + " §ahat §eVaro §agewonnen!");
								Discord.sendBroadcast("Das Team " + teams.getName() + " hat Varo gewonnen!");
								
							}
							
							for(World worlds : Bukkit.getWorlds()) {
								
								if(worlds.getName() != Bukkit.getWorld(plugin.getConfig().getString("wartelobby.World")).getName()) {
									
									worlds.getWorldBorder().setSize(Main.defaultworldborder);
									
								}
								
							}
							
							Main.end = true;
							plugin.getConfig().set("end", true);
							plugin.saveConfig();
							
							SongPlayer sptwo = new RadioSongPlayer(Main.s);
							Main.sp = sptwo;
							
							Main.sp.setAutoDestroy(false);
							
							Main.sp.setVolume((byte) 1);
							
							Main.sp.setPlaying(true);
							
						}
						
					}
					
					for (Player players : Bukkit.getOnlinePlayers()) {
						
						if(players.getGameMode() == GameMode.ADVENTURE) {
							
							players.setGameMode(GameMode.SURVIVAL);
							
						}
						
					}
					
					for(Team teams : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
						
						if(teams.getEntries().size() < 1) {
							
							if(Main.savedchest.containsValue(teams.getName())) {
								
								for(String all : HashMapKeysByValues.getKeysByValue(Main.savedchest, teams.getName())) {
									
									Main.savedchest.remove(all);
									
									plugin.getConfig().set("lockchest." + all, null);	
								    plugin.saveConfig();
									
								}
								
							}
							
							teams.unregister();
							
						}
						
					}
					
				}
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					
					if(Main.openplayer.containsKey(players)) {
						
						if(!players.getOpenInventory().getTitle().equals("§6M§chtest du die Kiste sichern?")) {
							
							Inventory chestsave = Bukkit.createInventory(null, 27, "§6M§chtest du die Kiste sichern?");

							ItemStack ja = new ItemStack(Material.EMERALD_BLOCK, 1);
							ItemMeta jaim = (ItemMeta) ja.getItemMeta();
							jaim.setDisplayName("§aJa");
							List<String> jalore = new ArrayList<String>();
							jalore.add("§7Falls du eine andere Kiste gesichert hast,");
							jalore.add("§7wird die Sicherung der anderen Kiste entfernt");
							jaim.setLore(jalore);
							ja.setItemMeta(jaim);
							chestsave.setItem(11, ja);
							
							ItemStack nein = new ItemStack(Material.REDSTONE_BLOCK, 1);
							ItemMeta neinim = (ItemMeta) nein.getItemMeta();
							neinim.setDisplayName("§cNein");
							nein.setItemMeta(neinim);
							chestsave.setItem(15, nein);

							players.openInventory(chestsave);
							
						}
						
					}
					
					if(players.hasPermission("varo.admin")) {
						players.setDisplayName("§4" + players.getName());
					} else {
						players.setDisplayName("§a" + players.getName());
					}
					
					if(players.getScoreboard().getPlayerTeam(players) != null) {
						
					    players.setPlayerListName("§e#" + players.getScoreboard().getPlayerTeam(players).getDisplayName() + "§1 - " + players.getDisplayName());
						
					} else {					
					    players.setPlayerListName(players.getDisplayName());
					}
					
					if(players.getUniqueId().toString().equals(Main.specuuid)) {
						
						if(players.getGameMode() != GameMode.SPECTATOR && players.getGameMode() != GameMode.CREATIVE) {
							
							players.setGameMode(GameMode.SPECTATOR);
							
						}
						
						if(players.isOp() == false) {
							
							players.setOp(true);
							
						}
						
						for(Player allplayers : Bukkit.getOnlinePlayers()) {
							
							allplayers.hidePlayer(players);
							
						}
						
						
					} else {
						
						if(Main.startprotection.containsKey(players)) {
							
							Main.startprotection.put(players, Math.max(0, Main.startprotection.get(players) + 1));
							
						}
						
						if(!Main.onlinetimeatday.containsKey(players.getName())) {
							
							Main.onlinetimeatday.put(players.getName(), 0);
							
						} else {
							
							if(plugin.getConfig().getBoolean("beforegame") == false) {
								
								if(!Main.startprotection.containsKey(players)) {
									
									if(!Main.nokick.contains(players)) {
										
										Main.onlinetimeatday.put(players.getName(), Math.max(0, Main.onlinetimeatday.get(players.getName()) + 1));
										
									} else {
										
										ActionBar.sendActionBar(players, ChatColor.RED + "Es ist ein Spieler in der N§he, du kannst nicht gekickt werden");

									}
									
								}
								
								
							}
							
						}
						
						if(Main.startprotection.containsKey(players)) {
							
							players.setRemainingAir(Main.oxygen.get(players));
							
							for(PotionEffect pe : players.getActivePotionEffects()) {
								players.removePotionEffect(pe.getType());
							}								
							players.addPotionEffects(Main.statuseffects.get(players));
							
							players.setFireTicks(Main.fireticks.get(players));
							
							if(Main.startprotection.get(players) == 100) {					
								Bukkit.broadcastMessage("§e" + players.getName() + " §3ist in §e5 §3Sekunden angreifbar");
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);	
							}
							
							if(Main.startprotection.get(players) == 120) {					
								Bukkit.broadcastMessage("§e" + players.getName() + " §3ist in §e4 §3Sekunden angreifbar");
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);	
							}
							
							if(Main.startprotection.get(players) == 140) {					
								Bukkit.broadcastMessage("§e" + players.getName() + " §3ist in §e3 §3Sekunden angreifbar");
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
							
							if(Main.startprotection.get(players) == 160) {					
								Bukkit.broadcastMessage("§e" + players.getName() + " §3ist in §e2 §3Sekunden angreifbar");
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
							
							if(Main.startprotection.get(players) == 180) {					
								Bukkit.broadcastMessage("§e" + players.getName() + " §3ist in §eeiner §3Sekunde angreifbar");
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
							
							if(Main.startprotection.get(players) > 199) {					
								Bukkit.broadcastMessage("§e" + players.getName() + " §3ist nun angreifbar");	
								players.playSound(players.getLocation(), Sound.NOTE_PLING, 1, 1);
								Main.startprotection.remove(players);
								Main.oxygen.remove(players);
								Main.statuseffects.remove(players);
								Main.fireticks.remove(players);
							}
							
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 6000) {
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e10 §3Minuten zur Halbzeit gekickt");	
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 12000) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e5 §3Minuten zur Halbzeit gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 16800) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §eeiner §3Minute zur Halbzeit gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17000) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e50 §3Sekunden zur Halbzeit gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17200) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e40 §3Sekunden zur Halbzeit gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17400) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e30 §3Sekunden zur Halbzeit gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17600) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e20 §3Sekunden zur Halbzeit gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17700) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e15 §3Sekunden zur Halbzeit gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17800) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e10 §3Sekunden zur Halbzeit gekickt");			
						}
						

						if(Main.onlinetimeatday.get(players.getName()) > 17800 && Main.onlinetimeatday.get(players.getName()) < 18000) {
							
							PlayerNearNoKick.shouldPlayerBeKicked(players, plugin);
							
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17900) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e5 §3Sekunden zur Halbzeit gekickt");
							players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);		
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17920) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e4 §3Sekunden zur Halbzeit gekickt");	
							players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);		
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17940) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e3 §3Sekunden zur Halbzeit gekickt");	
							players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);		
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17960) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e2 §3Sekunden zur Halbzeit gekickt");
							players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 17980) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §eeiner §3Sekunde zur Halbzeit gekickt");	
							players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 18000) {								
							players.kickPlayer("§4Du hast die Halbzeit von §e15 §4Minuten erreicht\n\n§aDu kannst jetzt oder zu einem sp§teren Zeitpunkt am heutigen Tag vor 21 Uhr weiterspielen\n\n§4BITTE BEACHTE: §eParagraph 7 Absatz 1");
							players.playSound(players.getLocation(), Sound.NOTE_PLING, 1, 1);
							Main.onlinetimeatday.put(players.getName(), 18001);
						}
						
						//###############################
						
						if(Main.onlinetimeatday.get(players.getName()) == 24000) {
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e10 §3Minuten gekickt");	
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 30000) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e5 §3Minuten gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 34800) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §eeiner §3Minute gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35000) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e50 §3Sekunden gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35200) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e40 §3Sekunden gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35400) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e30 §3Sekunden gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35600) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e20 §3Sekunden gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35700) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e15 §3Sekunden gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35800) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e10 §3Sekunden gekickt");			
						}
						
						if(Main.onlinetimeatday.get(players.getName()) > 35800 && Main.onlinetimeatday.get(players.getName()) < 36000) {
							
							PlayerNearNoKick.shouldPlayerBeKicked(players, plugin);
							
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35900) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e5 §3Sekunden gekickt");
							players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);		
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35920) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e4 §3Sekunden gekickt");	
							players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);		
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35940) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e3 §3Sekunden gekickt");	
							players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);		
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35960) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §e2 §3Sekunden gekickt");
							players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
						}
						
						if(Main.onlinetimeatday.get(players.getName()) == 35980) {		
							Bukkit.broadcastMessage("§e" + players.getName() + " §3wird in §eeiner §3Sekunde gekickt");	
							players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
						}
									
						if(Main.onlinetimeatday.get(players.getName()) > 35999) {	
							players.kickPlayer("§4Du hast die Maximale Onlinezeit von §e30 §4Minuten §berschritten\n\n§aDu kannst Morgen weiterspielen");
							players.playSound(players.getLocation(), Sound.NOTE_PLING, 1, 1);
						}
						
					}
					
				}

			}

		}, 0, 0);
		
	}

}
