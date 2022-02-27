package net.atheramc.survival;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.atheramc.survival.core.ListenerController;
import net.atheramc.survival.util.Baseplayer;

/**
 * Main class
 * 
 * @author Jan
 */
public class Main extends JavaPlugin {

	static Main instance;
	
	static HashMap<UUID, Baseplayer> players;
	
	// You... should know it
	@Override
	public void onEnable() {
		instance = this;
		players = new HashMap<UUID, Baseplayer>();
		
		for (Player all : Bukkit.getOnlinePlayers())
			registerPlayer(all);
		
		loadConfig();
		
		new ListenerController();
	}
	
	// You... should know it
	@Override
	public void onDisable() {
		
	}
		
	/**
	 * Get the instance of the main class.
	 * @return Instance of Main
	 */
	public static Main getInstance() {
		return instance;
	}
	
	/**
	 * Get an specific player on the server
	 * @param uuid UUID of the player
	 * @return Baseplayer
	 */
	public static Baseplayer getPlayer(UUID uuid) {
		return players.get(uuid);
	}
	
	/**
	 * Registers a player and put it in baseplayer
	 * 
	 * @param p Player
	 */
	public static void registerPlayer(Player p) {
		players.put(p.getUniqueId(), new Baseplayer(p));
	}
	
	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
}
