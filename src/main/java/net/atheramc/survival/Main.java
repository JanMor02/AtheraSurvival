package net.atheramc.survival;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import net.atheramc.survival.util.Baseplayer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class Main extends JavaPlugin {

	static LuckPerms lp;
	static Main instance;
	
	static HashMap<UUID, Baseplayer> players;
	
	// You... should know it
	@Override
	public void onEnable() {
		instance = this;
		lp = LuckPermsProvider.get();
		players = new HashMap<UUID, Baseplayer>();
		
		loadConfig();
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
	 * Get the instance of the LuckpermsAPI.
	 * @return Instance of LuckpermsAPI
	 */
	public static LuckPerms getLuckPerms() {
		return lp;
	}
	
	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
}
