package net.atheramc.survival.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.atheramc.survival.Main;

/**
 * PlayerJoinListener
 * 
 * @see org.bukkit.event.player.PlayerJoinEvent
 * @author Jan
 */
public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Main.registerPlayer(e.getPlayer());
		e.getPlayer().sendMessage("Hello there");
		if (!Main.getInstance().getConfig().contains("players." + e.getPlayer().getUniqueId())) {
			Main.getInstance().getConfig().set("players." + e.getPlayer().getUniqueId() + ".active", true);
			Main.getInstance().getConfig().set("players." + e.getPlayer().getUniqueId() + ".exhaustion", 0.0F);
			Main.getInstance().getConfig().set("players." + e.getPlayer().getUniqueId() + ".drinkpoints", 20);
			Main.getInstance().getConfig().set("players." + e.getPlayer().getUniqueId() + ".saturationpoints", 20);
			Main.getInstance().saveConfig();
		}
	}
	
}
