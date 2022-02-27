package net.atheramc.survival.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.atheramc.survival.Main;
import net.atheramc.survival.util.Baseplayer;

/**
 * EntityDamageByEntityListener
 * 
 * @see org.bukkit.event.player.EntityDamageByEntityEvent
 * @author Jan
 */
public class PlayerDeathListener implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Baseplayer bp = Main.getPlayer(e.getEntity().getUniqueId());
			
			bp.resetAfterDeath(bp);
		}
	}
}
