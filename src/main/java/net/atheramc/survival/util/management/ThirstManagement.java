package net.atheramc.survival.util.management;

import java.util.UUID;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.atheramc.survival.Main;
import net.atheramc.survival.util.Baseplayer;
import net.atheramc.survival.util.DrinkItems;
import net.atheramc.survival.util.enums.Drinkables;
import net.atheramc.survival.util.enums.ExhaustionTypes;
import net.atheramc.survival.util.interfaces.IThirstManagement;

/**
 * This class manages the thirst of the players
 * 
 * @author Jan
 */
public class ThirstManagement implements IThirstManagement {

	/**
	 * Adds drinkpoints und saturation to the players thirstlevel.
	 *  
	 * @param bp Baseplayer player
	 * @param drink What the player drank
	 */
	public void drink(Baseplayer bp, Drinkables drink, PotionEffect effect) {
		int newDrinkPoints = getDrinkPoints(bp) + drink.getDrinkPoints() <= 20 ? getDrinkPoints(bp) + drink.getDrinkPoints() : 20;
		int newSaturation = getSaturation(bp) + drink.getSaturation() <= 20 ? getSaturation(bp) + drink.getSaturation() : 20;
		
		setConfig(bp.getPlayer().getUniqueId(), "drinkpoints", newDrinkPoints);
		setConfig(bp.getPlayer().getUniqueId(), "saturationpoints", newSaturation);
		// Reset exhaustion
		setConfig(bp.getPlayer().getUniqueId(), "exhaustion", 0.0F);
		
		// Add statuseffect from consumed item
		if (effect != null)
			bp.getPlayer().addPotionEffect(effect);
		
		if (newDrinkPoints > 0) {
			bp.getPlayer().removePotionEffect(PotionEffectType.POISON);
		}
		
		if (newDrinkPoints > 2) {
			bp.getPlayer().removePotionEffect(PotionEffectType.CONFUSION);
			bp.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
		}
		
		if (newDrinkPoints > 6) {
			bp.getPlayer().removePotionEffect(PotionEffectType.SLOW);
			bp.getPlayer().removePotionEffect(PotionEffectType.WEAKNESS);
		}
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
		double newExhaustion = getExhaustion(bp) + exhaustion.getExhaustion(bp);
		
		if (newExhaustion >= 20.0) {
			exhaust(bp);
			newExhaustion -= 20.0;
		}
		setConfig(bp.getPlayer().getUniqueId(), "exhaustion", newExhaustion);
	}

	/**
	 * Gets the exhaustion of the player
	 * 
	 * @param bp Baseplayer player
	 * @return The exhaustion of the player
	 */
	public double getExhaustion(Baseplayer bp) {
		return Main.getInstance().getConfig().getDouble("players." + bp.getPlayer().getUniqueId() + ".exhaustion");
	}

	/**
	 * Gets the drinkpoints of the player
	 *
	 * @param bp Baseplayer player
	 * @return The drinkpoints of the player
	 */
	public int getDrinkPoints(Baseplayer bp) {
		return Main.getInstance().getConfig().getInt("players." + bp.getPlayer().getUniqueId() + ".drinkpoints");	
	}

	/**
	 * Gets the saturationpoints of the player
	 * 
	 * @param bp Baseplayer player
	 * @return The saturationpoints of the player
	 */
	public int getSaturation(Baseplayer bp) {
		return Main.getInstance().getConfig().getInt("players." + bp.getPlayer().getUniqueId() + ".saturationpoints");
	}
	
	/**
	 * Resets the thirst and saturation after death
	 * 
	 * @param bp Baseplayer player
	 */
	@Override
	public void resetAfterDeath(Baseplayer bp) {
		setConfig(bp.getPlayer().getUniqueId(), "exhaustion", 0);
		setConfig(bp.getPlayer().getUniqueId(), "drinkpoints", 20);
		setConfig(bp.getPlayer().getUniqueId(), "saturationpoints", 20);
	}
	
	/**
	 * Subtracts a player's drinking level, taking saturation into account.
	 * 
	 * @param bp Baseplayer player
	 * @return The players drinking level
	 */
	private void exhaust(Baseplayer bp) {
		int newSaturation = getSaturation(bp) - 1;
		int newDrinkPoints = newSaturation < 0 ? (newSaturation + getDrinkPoints(bp)) <= 0 ? 0 : (newSaturation + getDrinkPoints(bp)) : getDrinkPoints(bp); 
		
		// Add effects Slowness, Weakness
		if(newDrinkPoints <= 6 && newDrinkPoints > 2) {
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1), true);
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 1), true);
		}
		
		// Add effects Slowness, Weakness, Nausea and Blindness
		if (newDrinkPoints <= 2) {
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1), true);
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 1), true);
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000000, 1), true);
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000, 1), true);
		}
		
		// Add effects Slowness, Weakness, Nausea, Blindness and Poison
		if (newDrinkPoints == 0) {
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1), true);
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 1), true);
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000000, 1), true);
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000, 1), true);
			bp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1000000, 1), true);
		}
		
		setConfig(bp.getPlayer().getUniqueId(), "drinkpoints", newDrinkPoints);
		setConfig(bp.getPlayer().getUniqueId(), "saturationpoints", newSaturation <= 0 ? 0 : newSaturation);
	}
	
	/**
	 * Generic method to not forget to save the config
	 * ...and that 100x more lines do not have to be written.
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
