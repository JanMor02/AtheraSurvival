package net.atheramc.survival.listeners;

import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.atheramc.survival.Main;
import net.atheramc.survival.util.Baseplayer;
import net.atheramc.survival.util.enums.ExhaustionTypes;

/**
 * EntityDamageByEntityListener
 * 
 * @see org.bukkit.event.player.EntityDamageByEntityEvent
 * @author Jan
 */
public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		// Check if damager is Player
		if (e.getDamager() instanceof Player) {
			Baseplayer bp = Main.getPlayer(e.getDamager().getUniqueId());
			if (bp.getPlayer().getWorld().getDifficulty() == Difficulty.PEACEFUL || !bp.getThirstMode(bp)) return;
			
			// Add exhaustion for Mob-Hits
			bp.addExhaustion(bp, ExhaustionTypes.HIT_MOB);
		}
	}
}
