package de.robinnathmann.minecraft.varo.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.robinnathmann.minecraft.varo.main.Main;
import de.robinnathmann.minecraft.varo.methods.Discord;

public class Start implements CommandExecutor {
	
	private Main plugin;
	public Start(Main plugin) {
	    this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("start") && sender.hasPermission("varo.admin")) {
			
			if(plugin.getConfig().getBoolean("alreadystarted") == false) {
				
				plugin.getConfig().set("alreadystarted", true);
				
				List<String> member = plugin.getConfig().getStringList("member");
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(!(players.getUniqueId().toString().equals(plugin.getConfig().getString("spectatoruuid")))) {
						member.add(players.getUniqueId().toString());
					}		
				}
				plugin.getConfig().set("member", member);
				
				plugin.saveConfig();
				
				Bukkit.broadcastMessage("§3Varo startet in §e60 §3Sekunden");
				for(Player players : Bukkit.getOnlinePlayers()) {
					players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
				}
				
				Main.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

					@SuppressWarnings("deprecation")
					@Override
					public void run() {
					
						if(Main.time == 10) {
							Bukkit.broadcastMessage("§3Varo startet in §e50 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 20) {
							Bukkit.broadcastMessage("§3Varo startet in §e40 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 30) {
							Bukkit.broadcastMessage("§3Varo startet in §e30 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 40) {
							Bukkit.broadcastMessage("§3Varo startet in §e20 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 45) {
							Bukkit.broadcastMessage("§3Varo startet in §e15 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 50) {
							Bukkit.broadcastMessage("§3Varo startet in §e10 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 55) {
							Bukkit.broadcastMessage("§3Varo startet in §e5 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.sendTitle("§45", null);
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 56) {
							Bukkit.broadcastMessage("§3Varo startet in §e4 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.sendTitle("§c4", null);
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 57) {
							Bukkit.broadcastMessage("§3Varo startet in §e3 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.sendTitle("§63", null);
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 58) {
							Bukkit.broadcastMessage("§3Varo startet in §e2 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.sendTitle("§e2", null);
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 59) {
							Bukkit.broadcastMessage("§3Varo startet in §eeiner §3Sekunde");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.sendTitle("§a1", null);
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 60) {
							Bukkit.broadcastMessage("§eVaro beginnt!");
							Discord.sendBroadcast("Varo beginnt!");
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e10 §3Minuten");
							plugin.getConfig().set("beforegame", false);
							plugin.getConfig().set("schutzzeit", true);
							plugin.saveConfig();
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.sendTitle("", null);
								players.playSound(players.getLocation(), Sound.NOTE_PLING, 1, 1);
								players.getInventory().clear();
								players.getInventory().setArmorContents(null);
								players.updateInventory();
								players.setLevel(0);
								players.setExp(0);
							}
						}
						
						if(Main.time == 360) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e5 §3Minuten");
						}
						
						if(Main.time == 600) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e1 §3Minute");
						}
						
						if(Main.time == 610) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e50 §3Sekunden");
						}
						
						if(Main.time == 620) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e40 §3Sekunden");
						}
						
						if(Main.time == 630) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e30 §3Sekunden");
						}
						
						if(Main.time == 640) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e20 §3Sekunden");
						}
						
						if(Main.time == 645) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e15 §3Sekunden");
						}
						
						if(Main.time == 650) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e10 §3Sekunden");
						}
						
						if(Main.time == 655) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e5 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 656) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e4 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 657) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e3 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 658) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §e2 §3Sekunden");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 659) {
							Bukkit.broadcastMessage("§3Die Schutzzeit endet in §eeiner §3Sekunde");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
							}
						}
						
						if(Main.time == 660) {
							Bukkit.broadcastMessage("§3Die Schutzzeit ist vorbei!");
							Discord.sendBroadcast("Die Schutzzeit ist vorbei!");
							for(Player players : Bukkit.getOnlinePlayers()) {
								players.playSound(players.getLocation(), Sound.NOTE_PLING, 1, 1);
							}
							plugin.getConfig().set("schutzzeit", false);
							plugin.saveConfig();
						}
						
		                if(Main.time > 660) {
		                    
		                    Bukkit.getScheduler().cancelTask(Main.task);
		                }
						
						Main.time++;
						

					}

				}, 20, 20);
				
			} else {
				sender.sendMessage("§cVaro ist bereits gestartet");
			}
			
		}
		
		return true;
	}

}
