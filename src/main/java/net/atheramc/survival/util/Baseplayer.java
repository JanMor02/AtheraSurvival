package net.atheramc.survival.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.atheramc.survival.util.management.ThirstManagement;

/**
 * @author Jan
 * 
 * The Baseplayer is a player object that can be used by the plugin. It
 * stores all relevant information about a player and acts as a central
 * management class for each individual player.
 * 
 * @see org.Bukkit.Player
 */
public class Baseplayer extends ThirstManagement {

	private Player p;

	public Baseplayer(Player p) {
		this.p = p;
	}

	public Baseplayer(UUID uuid) {
		this.p = Bukkit.getPlayer(uuid);
	}

	public boolean hasPermission(String permission) {
		if (p.hasPermission("thirst." + permission) || p.hasPermission("thirst.*"))
			return true;
		return false;
	}

	public Player getPlayer() {
		return this.p;
	}
}