package Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class items
{
	public static ItemStack getIron()
	{
		int cost = 100;
		int count = 10;
		
		ItemStack item = new ItemStack(Material.IRON_INGOT, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getDiamond()
	{
		int cost = 80;
		int count = 1;
		
		ItemStack item = new ItemStack(Material.DIAMOND, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getPumpkin()
	{
		int cost = 200;
		int count = 32;
		
		ItemStack item = new ItemStack(Material.PUMPKIN, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getWheat()
	{
		int cost = 250;
		int count = 32;
		
		ItemStack item = new ItemStack(Material.WHEAT, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getRottenFlesh()
	{
		int cost = 200;
		int count = 16;
		
		ItemStack item = new ItemStack(Material.ROTTEN_FLESH, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getBone()
	{
		int cost = 200;
		int count = 16;
		
		ItemStack item = new ItemStack(Material.BONE, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getReturnPaper()
	{
		int cost = 150;
		int count = 1;
		
		ItemStack item = new ItemStack(Material.PAPER, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		itemMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "귀환권");
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getUndyingTotem()
	{
		int cost = 750;
		int count = 1;
		
		ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getEnchatedGoldenApple()
	{
		int cost = 700;
		int count = 1;
		
		ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getElytra()
	{
		int cost = 3000;
		int count = 1;
		
		ItemStack item = new ItemStack(Material.ELYTRA, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getShulkerBox()
	{
		int cost = 800;
		int count = 1;
		
		ItemStack item = new ItemStack(Material.SHULKER_BOX, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getVillageEgg()
	{
		int cost = 500;
		int count = 1;
		
		ItemStack item = new ItemStack(Material.VILLAGER_SPAWN_EGG, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getCoreHeal()
	{
		int cost = 500;
		int count = 1;
		
		ItemStack item = new ItemStack(Material.PAPER, count);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + cost + " Coin");
		itemMeta.setLore(lore);
		itemMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "코어 체력 회복");
		item.setItemMeta(itemMeta);
		
		return item;
	}
}
