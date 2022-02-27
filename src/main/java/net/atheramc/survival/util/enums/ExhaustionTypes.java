package net.atheramc.survival.util.enums;

import org.bukkit.Difficulty;

import net.atheramc.survival.util.Baseplayer;
import net.atheramc.survival.util.ExtensionMethods;

/**
 * Source: https://minecraft.fandom.com/de/wiki/Hunger#Ersch.C3.B6pfung All
 * exhaustion points for hunger have been tripled, as in real life you die of
 * thirst faster than starvation.
 * 
 * These are then multiplied by the Difficulty of the corresponding world. If
 * Difficulty Peacefull (0), then no drinking is deducted.
 */
public enum ExhaustionTypes {

	SWIMMING(0.03), 
	BREAK_BLOCK(0.015),
	SPRINTING(0.3),
	HIT_MOB(0.3);

	private double exhaustion;

	private ExhaustionTypes(double exhaustion) {
		this.exhaustion = exhaustion;
	}

	/**
	 * Multiplies the exhaustion with the difficulty of the world the player is in
	 * 
	 * @param Baseplayer player
	 * @return How much drinking is subtracted
	 */
	@SuppressWarnings("deprecation")
	public double getExhaustion(Baseplayer bp) {
		return this.exhaustion * bp.getPlayer().getWorld().getDifficulty().getValue();
	}

}
