package net.atheramc.survival.util.management;

import java.util.UUID;

import net.atheramc.survival.Main;
import net.atheramc.survival.util.Baseplayer;
import net.atheramc.survival.util.enums.ExhaustionTypes;
import net.atheramc.survival.util.interfaces.IThirstManagement;

public class ThirstManagement implements IThirstManagement {

	/**
	 * Adds drinkpoints und saturation to the players thirstlevel.
	 *  
	 * @param bp Baseplayer player
	 * @param drinkpoints How much drinkpoints the player got
	 * @param saturation How much saturation the player got
	 */
	public void drink(Baseplayer bp, int drinkpoints, int saturation) {
		int newDrinkPoints = getDrinkPoints(bp) + drinkpoints <= 20 ? getDrinkPoints(bp) + drinkpoints : 20;
		int newSaturation = getSaturation(bp) + saturation <= 20 ? getSaturation(bp) + saturation : 20;
		
		setConfig(bp.getPlayer().getUniqueId(), "drinkpoints", newDrinkPoints);
		setConfig(bp.getPlayer().getUniqueId(), "saturationpoints", newSaturation);
	}

	/**
	 * Toggles a player's thirst mode
	 * @param bp Baseplayer player
	 */
	public void toggleThirstMode(Baseplayer bp) {
		setConfig(bp.getPlayer().getUniqueId(), "active", !getThirstMode(bp));
	}

	/**
	 * Gets the thirst mode
	 * @param bp Baseplayer player
	 */
	public boolean getThirstMode(Baseplayer bp) {
		return Main.getInstance().getConfig().getBoolean("players." + bp.getPlayer().getUniqueId() + ".active");
	}
	
	/**
	 * Adds exhaustion to the player. As soon as the value exceeds 0.5,
	 * the player automatically gets thirstpoints subtracted.
	 * 
	 * @param bp Baseplayer player
	 * @param exhaustion Exhaustion to be added
	 */
	public void addExhaustion(Baseplayer bp, ExhaustionTypes exhaustion) {
		float newExhaustion = getExhaustion(bp) + exhaustion.getExhaustion(bp);
		
		if (newExhaustion >= 0.5F) {
			exhaust(bp);
			newExhaustion -= 0.5F;
		}
	
		setConfig(bp.getPlayer().getUniqueId(), "exhaustion", newExhaustion);
	}

	/**
	 * Gets the exhaustion of the player
	 * 
	 * @param bp Baseplayer player
	 * @return The exhaustion of the player
	 */
	public float getExhaustion(Baseplayer bp) {
		return (float)Main.getInstance().getConfig().getDouble("players." + bp.getPlayer().getUniqueId() + ".exhaustion");
	}

	/**
	 * Gets the drinkpoints of the player
	 *
	 * @param bp Baseplayer player
	 * @return The drinkpoints of the player
	 */
	public int getDrinkPoints(Baseplayer bp) {
		return Main.getInstance().getConfig().getInt("players." + bp.getPlayer().getUniqueId() + "drinkpoints");	
	}

	/**
	 * Gets the saturationpoints of the player
	 * 
	 * @param bp Baseplayer player
	 * @return The saturationpoints of the player
	 */
	public int getSaturation(Baseplayer bp) {
		return Main.getInstance().getConfig().getInt("players." + bp.getPlayer().getUniqueId() + "saturationpoints");
	}
	
	/**
	 * Subtracts a player's drinking level, taking saturation into account.
	 * 
	 * @param bp Baseplayer player
	 * @return The players drinking level
	 */
	private void exhaust(Baseplayer bp) {
		int newSaturation = getSaturation(bp) - 1;
		int newDrinkPoints = newSaturation < 0 ? (getDrinkPoints(bp) - newSaturation) <= 0 ? 0 : getDrinkPoints(bp) - newSaturation : getDrinkPoints(bp); 
		
		setConfig(bp.getPlayer().getUniqueId(), "drinkpoints", newDrinkPoints);
		setConfig(bp.getPlayer().getUniqueId(), "saturationpoints", newSaturation);
	}
	
	/**
	 * Generic method to not forget to save the config
	 * 
	 * @param <T> The type of object
	 * @param uuid UUID of the player
	 * @param path Path in config
	 * @param obj What should be set in the config
	 */
	private <T> void setConfig(UUID uuid, String path, T obj) {
		Main.getInstance().getConfig().set("players." + uuid + "." + path, obj);
		Main.getInstance().saveConfig();
	}
}
