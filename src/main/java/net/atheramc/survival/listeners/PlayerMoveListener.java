package net.atheramc.survival.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.atheramc.survival.Main;
import net.atheramc.survival.util.Baseplayer;
import net.atheramc.survival.util.enums.ExhaustionTypes;


public class PlayerMoveListener implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (e.getPlayer().isInsideVehicle()) return;
		if ((e.getFrom().getBlockX() == e.getTo().getBlockX()) && (e.getFrom().getBlockZ() == e.getTo().getBlockZ()) && (e.getFrom().getBlockY() == e.getTo().getBlockY())) return;
		
		Baseplayer bp = Main.getPlayer(e.getPlayer().getUniqueId());
		
		if (e.getPlayer().isSprinting() && !e.getPlayer().isSwimming() && !e.getPlayer().isSneaking() && !e.getPlayer().isFlying() && !e.getPlayer().isGliding() && !e.getPlayer().isRiptiding()) {
			// Player is sprinting
			bp.addExhaustion(bp, ExhaustionTypes.SPRINTING);
		} else if (e.getPlayer().isSwimming() || e.getPlayer().isInWater()) {
			// Player is swimming
			bp.addExhaustion(bp, ExhaustionTypes.SWIMMING);
		}
	}
}
