package net.atheramc.survival.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.atheramc.survival.Main;
import net.atheramc.survival.util.management.ThirstManagement;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * The Baseplayer is a player object that can be used by the plugin. It
 * stores all relevant information about a player and acts as a central
 * management class for each individual player.
 * 
 * @author Jan
 * @see org.Bukkit.Player
 */
public class Baseplayer extends ThirstManagement {

	private Player p;

	public Baseplayer(Player p) {
		this.p = p;
		initalize();
	}

	public Baseplayer(UUID uuid) {
		this.p = Bukkit.getPlayer(uuid);
		initalize();
	}
	
	private void initalize() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), ()->{
        	p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(getThirstBarString()));
        },20, 20);
	}

	/**
	 * Checks if player has Permission thirst.[permission] or thirst.*
	 * @param permission Permissionstring
	 * @return True (has permission), False (has no permission)
	 */
	public boolean hasPermission(String permission) {
		if (p.hasPermission("thirst." + permission) || p.hasPermission("thirst.*"))
			return true;
		return false;
	}

	/**
	 * Get the Bukkit-Player of the Baseplayer
	 * 
	 * @see org.bukkit.entity.Player
	 * @return Bukkit-Player of the Baseplayer
	 */
	public Player getPlayer() {
		return this.p;
	}
	
	private String getThirstBarString() {
		String thirstBar[] = new String[10]; 
		
		String full = "§9❖";
		String half = "§b❖";
		String empty = "§7❖";
		
		// 16 -- 8
		if((getDrinkPoints(this) & 1) == 0) {
			for(int i = 10; i > 0; i--) {
				//	10			9
				if (i > 10 - (getDrinkPoints(this) / 2)) {
					thirstBar[i-1] = full;
				} else {
					thirstBar[i-1] = empty;
				}
			}
		} else {
			//17 -- 8,5
			for(int i = 10; i > 0; i--) {
				if (i > 10 - (getDrinkPoints(this) / 2)) {
					thirstBar[i-1] = full;
				} else if (i == 10 - (getDrinkPoints(this) / 2)) {
					thirstBar[i-1] = half;
				} else {
					thirstBar[i-1] = empty;
				}
			}
		}
		return String.join("", thirstBar);
	}
}