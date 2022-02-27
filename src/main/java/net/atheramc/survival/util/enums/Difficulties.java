package net.atheramc.survival.util.enums;

import org.bukkit.Difficulty;

/*
 * Maps the org.bukkit.Difficulty to its corresponding integer
 */
public enum Difficulties {
	
	PEACEFUL(Difficulty.PEACEFUL, 0),
	EASY(Difficulty.EASY, 1),
	NORMAL(Difficulty.NORMAL, 2),
	HARD(Difficulty.HARD, 3);

	Difficulty difficulty;
	int value;

	private Difficulties(Difficulty difficulty, int value) {
		this.difficulty = difficulty;
		this.value = value;
	}

	/**
	 * Gets the org.bukkit.Difficulty 
	 * @return org.bukkit.Difficulty 
	 */
	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	/**
	 * Gets the org.bukkit.Difficultys corresponding integer
	 * @return Corresponding integer 
	 */
	public int getValue() {
		return this.value;
	}
}