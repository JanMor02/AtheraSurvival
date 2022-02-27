package net.atheramc.survival.listeners;

import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.atheramc.survival.Main;
import net.atheramc.survival.util.Baseplayer;
import net.atheramc.survival.util.DrinkItems;
import net.atheramc.survival.util.enums.Drinkables;

/**
 * PlayerItemConsumeListener
 * 
 * @see org.bukkit.event.player.PlayerItemConsumeEvent
 * @author Jan
 */
public class PlayerItemConsumeListener implements Listener {

	@EventHandler
	public void onPlayerConsume(PlayerItemConsumeEvent e) {
		Baseplayer bp = Main.getPlayer(e.getPlayer().getUniqueId());

		if (bp.getPlayer().getWorld().getDifficulty() == Difficulty.PEACEFUL || !bp.getThirstMode(bp)) return;
		if (e.getItem().getType() == Material.POTION) {
			if (DrinkItems.lores.contains(e.getItem().getItemMeta().getLore().get(0))) {
				String lore = e.getItem().getItemMeta().getLore().get(0).split(" ")[2].replaceAll("§c", "").replaceAll("§a", "");
				System.out.println("var quality: " + lore);
	

				// Salty
				if (lore.contains("Salzig")) {
					bp.drink(bp, Drinkables.SALTY_WATER, new PotionEffect(PotionEffectType.HUNGER, 600, 1));
					// Dirty
				} else if (lore.contains("Schmutzig")) {
					bp.drink(bp, Drinkables.DIRTY_WATER, new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
					// Boiled
				} else if (lore.contains("Abgekocht")) {
					bp.drink(bp, Drinkables.BOILED_WATER, null);
					// Pure
				} else if (lore.contains("Rein")) {
					bp.drink(bp, Drinkables.PURE_WATER, new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
				}
			}
		}
	}
}
