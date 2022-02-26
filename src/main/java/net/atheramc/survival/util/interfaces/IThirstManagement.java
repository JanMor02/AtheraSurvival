package net.atheramc.survival.util.interfaces;

import net.atheramc.survival.util.Baseplayer;
import net.atheramc.survival.util.enums.ExhaustionTypes;

public interface IThirstManagement {

	public void drink(Baseplayer bp, int drinkpoints, int saturation);
	
	public void toggleThirstMode(Baseplayer bp);
	public boolean getThirstMode(Baseplayer bp);

	public void addExhaustion(Baseplayer bp, ExhaustionTypes exhaustion);
	public float getExhaustion(Baseplayer bp);

	public int getDrinkPoints(Baseplayer bp);
	public int getSaturation(Baseplayer bp);
}
