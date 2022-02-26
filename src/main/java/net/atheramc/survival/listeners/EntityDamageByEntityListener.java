package net.atheramc.survival.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.atheramc.survival.Main;
import net.atheramc.survival.util.Baseplayer;
import net.atheramc.survival.util.enums.ExhaustionTypes;

public class EntityDamageByEntityListener implements Listener {

	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		// Check if damager is Player
		if (e.getDamager() instanceof Player) {
			Baseplayer bp = Main.getPlayer(e.getDamager().getUniqueId());
			bp.addExhaustion(bp, ExhaustionTypes.HIT_MOB);
		}
	}
}
