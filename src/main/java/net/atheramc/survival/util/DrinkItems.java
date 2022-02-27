package net.atheramc.survival.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class DrinkItems {

	public static final ArrayList<String> lores = new ArrayList<String>(Arrays.asList("§8>§7> §bQualität: §cSalzig", "§8>§7> §bQualität: §cSchmutzig", "§8>§7> §bQualität: §aAbgekocht", "§8>§7> §bQualität: §aRein"));
	
	public static ItemStack getSaltyWater()  {
	    ItemStack is = new ItemStack(Material.POTION, 1);
	       
        PotionMeta meta = (PotionMeta) is.getItemMeta();
        meta.setDisplayName("Wasserflasche");
        meta.setLore(Arrays.asList(lores.get(0)));
        meta.setDisplayName("Wasserflasche");
        meta.clearCustomEffects();
        meta.setBasePotionData(new PotionData(PotionType.WATER));
        is.setItemMeta(meta);
        
        return is;
	}
	
	public static ItemStack getDirtyWater()  {
	    ItemStack is = new ItemStack(Material.POTION, 1);
	       
        PotionMeta meta = (PotionMeta) is.getItemMeta();
        meta.setLore(Arrays.asList(lores.get(1)));
        meta.setDisplayName("Wasserflasche");
        meta.clearCustomEffects();
        meta.setBasePotionData(new PotionData(PotionType.WATER));
        is.setItemMeta(meta);
        
        return is;
	}
	
	public static ItemStack getBoiledWater()  {
	    ItemStack is = new ItemStack(Material.POTION, 1);
	       
        PotionMeta meta = (PotionMeta) is.getItemMeta();
        meta.setLore(Arrays.asList(lores.get(2)));
        meta.setDisplayName("Wasserflasche");
        meta.clearCustomEffects();
        meta.setBasePotionData(new PotionData(PotionType.WATER));
        is.setItemMeta(meta);
        
        return is;
	}
	
	public static ItemStack getPureWater()  {
	    ItemStack is = new ItemStack(Material.POTION, 1);
	       
        PotionMeta meta = (PotionMeta) is.getItemMeta();
        meta.setLore(Arrays.asList(lores.get(3)));
        meta.setDisplayName("Wasserflasche");
        meta.clearCustomEffects();
        meta.setBasePotionData(new PotionData(PotionType.WATER));
        is.setItemMeta(meta);
        
        return is;
	}
}
