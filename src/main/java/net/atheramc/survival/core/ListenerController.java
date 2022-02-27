package net.atheramc.survival.core;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import net.atheramc.survival.Main;
import net.atheramc.survival.listeners.BlockBreakListener;
import net.atheramc.survival.listeners.EntityDamageByEntityListener;
import net.atheramc.survival.listeners.PlayerDeathListener;
import net.atheramc.survival.listeners.PlayerInteractListener;
import net.atheramc.survival.listeners.PlayerItemConsumeListener;
import net.atheramc.survival.listeners.PlayerJoinListener;
import net.atheramc.survival.listeners.PlayerMoveListener;

/**
 * Centrally registers all events of the plugin
 * 
 * @author Jan
 */
public class ListenerController {
	
	public ListenerController() {
		registerListener(new BlockBreakListener());
		registerListener(new EntityDamageByEntityListener());
		registerListener(new PlayerInteractListener());
		registerListener(new PlayerItemConsumeListener());
		registerListener(new PlayerJoinListener());
		registerListener(new PlayerMoveListener());
		registerListener(new PlayerDeathListener());
	}
	
	/**
	 * Registers an listener
	 * @param listener Listener to be registered
	 */
	private void registerListener(Listener listener) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, Main.getInstance());
	}
}
