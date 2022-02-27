package net.atheramc.survival.util.interfaces;

import org.bukkit.potion.PotionEffect;

import net.atheramc.survival.util.Baseplayer;
import net.atheramc.survival.util.enums.Drinkables;
import net.atheramc.survival.util.enums.ExhaustionTypes;

public interface IThirstManagement {

	public void drink(Baseplayer bp, Drinkables drink, PotionEffect effect);
	
	public void toggleThirstMode(Baseplayer bp);
	public boolean getThirstMode(Baseplayer bp);

	public void addExhaustion(Baseplayer bp, ExhaustionTypes exhaustion);
	public double getExhaustion(Baseplayer bp);

	public int getDrinkPoints(Baseplayer bp);
	public int getSaturation(Baseplayer bp);
	
	public void resetAfterDeath(Baseplayer bp);
}
