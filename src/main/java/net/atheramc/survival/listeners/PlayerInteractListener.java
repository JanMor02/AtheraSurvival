package net.atheramc.survival.listeners;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.atheramc.survival.Main;
import net.atheramc.survival.util.DrinkItems;
import net.atheramc.survival.util.ExtensionMethods;

/**
 * PlayerInteractListener
 * 
 * @see org.bukkit.event.player.PlayerInteractEvent
 * @author Jan
 */
public class PlayerInteractListener implements Listener {

	// Biomes where there is only salty water to drink
	private ArrayList<Biome> saltyBiomes = new ArrayList<Biome>(Arrays.asList(Biome.OCEAN, Biome.COLD_OCEAN, Biome.DEEP_COLD_OCEAN, Biome.DEEP_FROZEN_OCEAN, Biome.DEEP_LUKEWARM_OCEAN, Biome.DEEP_OCEAN, Biome.FROZEN_OCEAN, Biome.LUKEWARM_OCEAN, Biome.WARM_OCEAN));
	// Biomes where there is only fresh water to drink
	private ArrayList<Biome> freshBiomes = new ArrayList<Biome>(Arrays.asList(Biome.RIVER, Biome.FROZEN_RIVER));
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getPlayer().getWorld().getDifficulty() == Difficulty.PEACEFUL || !Main.getPlayer(e.getPlayer().getUniqueId()).getThirstMode(Main.getPlayer(e.getPlayer().getUniqueId()))) return;
		
		Player p = e.getPlayer();
		Block water = null;

		for (Block b : p.getLineOfSight(null, 6)) {
			if (b.getType() == Material.WATER) {
				water = b;
				break;
			}
		}

		if (water != null) {
			if (e.getItem().getType() == Material.GLASS_BOTTLE) {
				e.setCancelled(true);
				if (saltyBiomes.contains(p.getWorld().getBiome(water.getLocation().getBlockX(), water.getLocation().getBlockZ()))) {
					ExtensionMethods.consumeItem(p, 1, Material.GLASS_BOTTLE);
					p.getInventory().addItem(DrinkItems.getSaltyWater());
				} else if (freshBiomes.contains(p.getWorld().getBiome(water.getLocation().getBlockX(), water.getLocation().getBlockZ()))) {
					ExtensionMethods.consumeItem(p, 1, Material.GLASS_BOTTLE);
					p.getInventory().addItem(DrinkItems.getPureWater());
				} else {
					ExtensionMethods.consumeItem(p, 1, Material.GLASS_BOTTLE);
					p.getInventory().addItem(DrinkItems.getDirtyWater());
				}
			}
		}

	}
}
