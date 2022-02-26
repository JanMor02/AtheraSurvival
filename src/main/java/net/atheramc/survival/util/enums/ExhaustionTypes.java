package net.atheramc.survival.util.enums;

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

	SWIMMING(0.03F), 
	BREAK_BLOCK(0.015F),
	SPRINTING(0.3F), JUMP(0.15F), 
	HIT_MOB(0.3F), 
	HUNGER_EFFECT(0.3F);
	
	private float exhaustion;

	private ExhaustionTypes(float exhaustion) {
		this.exhaustion = exhaustion;
	}

	/**
	 * Multiplies the exhaustion with the difficulty of the world the player is in
	 * 
	 * @param Baseplayer player
	 * @return How much drinking is subtracted
	 */
	public float getExhaustion(Baseplayer bp) {
		if (ExtensionMethods.fromDifficulty(bp.getPlayer().getWorld().getDifficulty()) != 0)
			return this.exhaustion * ExtensionMethods.fromDifficulty(bp.getPlayer().getWorld().getDifficulty());
		else
			return 0;
	}

}
