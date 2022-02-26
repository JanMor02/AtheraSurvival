package net.atheramc.survival.util;

import org.bukkit.Difficulty;

import net.atheramc.survival.util.enums.Difficulties;

public class ExtensionMethods {

	/**
	 * Converts an org.bukkit.Difficulty-Enum to the corresponding int (0, 1, 2, 3)
	 * 
	 * @see org.bukkit.Difficulty
	 * @return The corresponding int (0, 1, 2, 3) // -1 if an error occured
	 */
	public static int fromDifficulty(Difficulty diff) {
		int value = -1;
		
		for(Difficulties difficulty : Difficulties.values())
			value = diff == difficulty.getDifficulty() ? difficulty.getValue() : -1;
			
		return value;
	}
	
	/**
	 * Converts an Int (0, 1, 2, 3) to the corresponding org.bukkit.Difficulty-Enum
	 * 
	 * @see org.bukkit.Difficulty
	 * @return The corresponding org.bukkit.Difficulty-Enum // null if an error occured
	 */
	public static Difficulty fromInt(int value) {
		if (value == -1 || value > 3) return null;
		
		Difficulty diff = null;
		
		for(Difficulties difficulty : Difficulties.values())
			diff = value == difficulty.getValue() ? difficulty.getDifficulty() : null;
		
		return diff;
	}
	
}
