package de.robinnathmann.minecraft.varo.main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

import de.robinnathmann.minecraft.varo.commands.AddTeam;
import de.robinnathmann.minecraft.varo.commands.AllowSpectatorJoin;
import de.robinnathmann.minecraft.varo.commands.Ban;
import de.robinnathmann.minecraft.varo.commands.Broadcast;
import de.robinnathmann.minecraft.varo.commands.Clear;
import de.robinnathmann.minecraft.varo.commands.Debug;
import de.robinnathmann.minecraft.varo.commands.Finish;
import de.robinnathmann.minecraft.varo.commands.Invsee;
import de.robinnathmann.minecraft.varo.commands.Kick;
import de.robinnathmann.minecraft.varo.commands.KoordinatenStrike;
import de.robinnathmann.minecraft.varo.commands.MemberException;
import de.robinnathmann.minecraft.varo.commands.Ping;
import de.robinnathmann.minecraft.varo.commands.ProtectBlock;
import de.robinnathmann.minecraft.varo.commands.RandomChest;
import de.robinnathmann.minecraft.varo.commands.Respawn;
import de.robinnathmann.minecraft.varo.commands.Restore;
import de.robinnathmann.minecraft.varo.commands.SetRespawn;
import de.robinnathmann.minecraft.varo.commands.Start;
import de.robinnathmann.minecraft.varo.commands.Unban;
import de.robinnathmann.minecraft.varo.commands.Wartelobby;
import de.robinnathmann.minecraft.varo.commands.WorldborderDecrease;
import de.robinnathmann.minecraft.varo.commands.Zeit;
import de.robinnathmann.minecraft.varo.listeners.AsyncPlayerChatListener;
import de.robinnathmann.minecraft.varo.listeners.BlockBreakListener;
import de.robinnathmann.minecraft.varo.listeners.BlockDispenseListener;
import de.robinnathmann.minecraft.varo.listeners.BlockFromToListener;
import de.robinnathmann.minecraft.varo.listeners.BlockPhysicsListener;
import de.robinnathmann.minecraft.varo.listeners.BlockPistonExtendListener;
import de.robinnathmann.minecraft.varo.listeners.BlockPistonRetractListener;
import de.robinnathmann.minecraft.varo.listeners.BlockPlaceListener;
import de.robinnathmann.minecraft.varo.listeners.EntityChangeBlockListener;
import de.robinnathmann.minecraft.varo.listeners.EntityDamageByEntityListener;
import de.robinnathmann.minecraft.varo.listeners.EntityDamageListener;
import de.robinnathmann.minecraft.varo.listeners.EntityExplodeListener;
import de.robinnathmann.minecraft.varo.listeners.EntityRegainHealthListener;
import de.robinnathmann.minecraft.varo.listeners.EntityTargetListener;
import de.robinnathmann.minecraft.varo.listeners.FoodLevelChangeListener;
import de.robinnathmann.minecraft.varo.listeners.HangingBreakByEntityListener;
import de.robinnathmann.minecraft.varo.listeners.InventoryClickListener;
import de.robinnathmann.minecraft.varo.listeners.InventoryMoveItemListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerArmorStandManipulateListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerBedEnterListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerBucketEmptyListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerCommandPreprocessListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerDeathListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerDropItemListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerEditBookListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerInteractEntityListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerInteractListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerJoinListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerLeashEntityListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerLoginListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerMoveListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerPickupItemListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerQuitListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerRespawnListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerShearEntityListener;
import de.robinnathmann.minecraft.varo.listeners.PlayerUnleashEntityListener;
import de.robinnathmann.minecraft.varo.listeners.PortalCreateListener;
import de.robinnathmann.minecraft.varo.listeners.ServerListPingListener;
import de.robinnathmann.minecraft.varo.listeners.VehicleDamageListener;
import de.robinnathmann.minecraft.varo.methods.RandomFirework;
import de.robinnathmann.minecraft.varo.packetlisteners.Tabcomplete;

public class Main extends JavaPlugin implements Listener {
	
	public void onEnable() {
		
		registerEvents();
		registerCommands();
		registerConfig();
		Scheduler.registerScheduler(this);
		Tabcomplete.registerTabcomplete(this);		
		setConfigHashMap();
		
		RandomFirework.getManager().addColors();
		RandomFirework.getManager().addTypes();
		
		if(getConfig().getString("wartelobby.World") != null) {
			
			World = Bukkit.getWorld(getConfig().getString("wartelobby.World"));
			
		}
		
		pl = this;
		specuuid = pl.getConfig().getString("spectatoruuid");
		f = new File(pl.getDataFolder() + File.separator + "Win.nbs");
		s = NBSDecoder.parse(f);
		defaultworldborder = getConfig().getInt("defaultworldborder");
		
	}
	
	public void registerEvents() {
		
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(this), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamageListener(this), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(this), this);
		Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerPickupItemListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocessListener(), this);
		Bukkit.getPluginManager().registerEvents(new ServerListPingListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PortalCreateListener(this), this);
		Bukkit.getPluginManager().registerEvents(new EntityExplodeListener(this), this);
		Bukkit.getPluginManager().registerEvents(new InventoryMoveItemListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockPhysicsListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityChangeBlockListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerBucketEmptyListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerShearEntityListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerArmorStandManipulateListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerBedEnterListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerEditBookListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerUnleashEntityListener(this), this);
		Bukkit.getPluginManager().registerEvents(new EntityRegainHealthListener(this), this);
		Bukkit.getPluginManager().registerEvents(new FoodLevelChangeListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeashEntityListener(this), this);
		Bukkit.getPluginManager().registerEvents(new VehicleDamageListener(this), this);
		Bukkit.getPluginManager().registerEvents(new HangingBreakByEntityListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(this), this);
		Bukkit.getPluginManager().registerEvents(new EntityTargetListener(this), this);
		Bukkit.getPluginManager().registerEvents(new BlockFromToListener(this), this);
		Bukkit.getPluginManager().registerEvents(new BlockPistonExtendListener(this), this);
		Bukkit.getPluginManager().registerEvents(new BlockPistonRetractListener(this), this);
		Bukkit.getPluginManager().registerEvents(new BlockDispenseListener(this), this);
		
	}
	
	public void registerCommands() {
		
		getCommand("ping").setExecutor(new Ping());
		getCommand("kick").setExecutor(new Kick());
		getCommand("broadcast").setExecutor(new Broadcast());
		getCommand("ban").setExecutor(new Ban(this));
		getCommand("wartelobby").setExecutor(new Wartelobby(this));
		getCommand("unban").setExecutor(new Unban(this));
		getCommand("respawn").setExecutor(new Respawn(this));
		getCommand("inventoryclear").setExecutor(new Clear(this));
		getCommand("restore").setExecutor(new Restore(this));
		getCommand("allowspectatorjoin").setExecutor(new AllowSpectatorJoin());
		getCommand("protectblock").setExecutor(new ProtectBlock());
		getCommand("randomchest").setExecutor(new RandomChest());
		getCommand("addteam").setExecutor(new AddTeam());
		getCommand("finish").setExecutor(new Finish(this));
		getCommand("invsee").setExecutor(new Invsee(this));
		getCommand("debug").setExecutor(new Debug(this));
		getCommand("start").setExecutor(new Start(this));
		getCommand("koordinatenstrike").setExecutor(new KoordinatenStrike());
		getCommand("memberexception").setExecutor(new MemberException(this));
		getCommand("worldborderdecrease").setExecutor(new WorldborderDecrease(this));
		getCommand("setrespawn").setExecutor(new SetRespawn(this));
		getCommand("zeit").setExecutor(new Zeit());
		
	}
	
	public void registerConfig() {
		
		getConfig().addDefault("setup", true);
		getConfig().addDefault("alreadystarted", false);
		getConfig().addDefault("beforegame", true);
		getConfig().addDefault("schutzzeit", false);
		getConfig().addDefault("end", false);
		getConfig().addDefault("spectatoruuid", "Ersetze diesen Text durch der UUID des Spectator Accounts");
		getConfig().addDefault("TS3", "Ersetze diesen Text durch die TS IP");
		getConfig().addDefault("Regeln", "Ersetze diesen Text durch den Link der Regeln");
		getConfig().addDefault("Discord", "Ersetze diesen Text durch den Discord Link");
		getConfig().addDefault("Projektleiter", "Ersetze diesen Text durch die Projektleiter");
		getConfig().addDefault("maxplayer", 24);
		getConfig().addDefault("worldborderdecrease", 20);
		getConfig().addDefault("defaultworldborder", 1451);
		getConfig().addDefault("discordnotificationchannel", "benachrichtigungen");

		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void setConfigHashMap() {
		
		if(getConfig().getConfigurationSection("lockchest") == null) {
			
			getConfig().createSection("lockchest");
			saveConfig();
			
		}
			
		for (String key : getConfig().getConfigurationSection("lockchest").getKeys(false))
		{
		    savedchest.put(key, getConfig().getString("lockchest." + key));
		}
		
	}
	
	public static Plugin pl;
	
	public static Integer time = 0;
	public static int task;
	public static boolean specallow = false;
	public static Integer firework = 0;
	public static String specuuid;
	public static HashMap<Player, Location> openplayer = new HashMap<Player, Location>();
	public static HashMap<String, String> savedchest = new HashMap<String, String>();
	public static Boolean end = false;
	
	public static ArrayList<String> coordstrike = new ArrayList<String>(); 
	public static HashMap<String, Location> logoutloc = new HashMap<String, Location>();
	
	public static SongPlayer sp;
	public static File f;
	public static Song s;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("HH");
	public static SimpleDateFormat sdfmin = new SimpleDateFormat("mm");
	public static SimpleDateFormat sdfsec = new SimpleDateFormat("ss");

	public static HashMap<String, Integer> onlinetimeatday = new HashMap<String, Integer>();
	public static HashMap<Player, Integer> startprotection = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> oxygen = new HashMap<Player, Integer>();
	public static HashMap<Player, Collection<PotionEffect>> statuseffects = new HashMap<Player, Collection<PotionEffect>>();
	public static HashMap<Player, Integer> fireticks = new HashMap<Player, Integer>();
	
	public static World World;
	public static Integer defaultworldborder;
	
	public static ArrayList<Integer> worldborderqueue = new ArrayList<Integer>();
	public static ArrayList<Player> nokick = new ArrayList<Player>();
	public static HashMap<Integer, String> worldborderqueueplayer = new HashMap<Integer, String>();
	public static Integer currentbordervalue;
	
}
