package net.atheramc.survival.util;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Here are methods that simplify or simplify procedures in the plugin
 * 
 * @author Jan
 */
public class ExtensionMethods {
	
	/**
	 * Lets the player consume x items
	 * 
	 * @param player Player
	 * @param count How many items to be consumed
	 * @param mat Material
	 * @return True if items were removed, false if not
	 */
	public static boolean consumeItem(Player player, int count, Material mat) {
	    Map<Integer, ? extends ItemStack> item = player.getInventory().all(mat);

	    int found = 0;
	    for (ItemStack stack : item.values())
	        found += stack.getAmount();
	    if (count > found)
	        return false;

	    for (Integer index : item.keySet()) {
	        ItemStack stack = item.get(index);

	        int removed = Math.min(count, stack.getAmount());
	        count -= removed;

	        if (stack.getAmount() == removed)
	            player.getInventory().setItem(index, null);
	        else
	            stack.setAmount(stack.getAmount() - removed);

	        if (count <= 0)
	            break;
	    }

	    player.updateInventory();
	    return true;
	}
	
}
