package net.atheramc.survival.util.enums;

/**
 * Defines drinkable items/items that restore drinking that can be used from the
 * plugin.
 * Reference: 
 * https://minecraft.fandom.com/wiki/Drinks
 * https://minecraft.fandom.com/wiki/Hunger
 * 
 * The enums are named like the materials from org.Bukkit.Material,
 * as this makes them easier to find when a player consumes them.
 */
public enum Drinkables {

	// Consumables that give water
	DIRTY_WATER(8, 4),
	SALTY_WATER(8, 0),
	BOILED_WATER(8, 8),
	PURE_WATER(10, 12);
	
	private int drinkPoints;
	private int saturation;

	private Drinkables(int drinkPoints, int saturation) {
		this.drinkPoints = drinkPoints;
		this.saturation = saturation;
	}
	
	/**
	 * Gets the drinkpoints of the item
	 * @return Number of drinkpoints
	 */
	public int getDrinkPoints() {
		return this.drinkPoints;
	}
	
	/**
	 * Gets the saturation of the item
	 * @return Number of saturationpoints
	 */
	public int getSaturation() {
		return this.saturation;
	}
}