package Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class shopManager implements Listener
{
	@EventHandler
	public void InventoryClick(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();
		
		if(e.getView().getTitle().equalsIgnoreCase("shop buy"))
		{
			if(e.getSlot() == 12)
			{
				int cost = 150;
				
				if(game.checkCoin(p, cost))
				{
					game.addCoin(p, -cost);
					ItemStack returnpaper = new ItemStack(Material.PAPER, 1);
					ItemMeta meta = returnpaper.getItemMeta();
					meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "귀환권");
					returnpaper.setItemMeta(meta);
					p.getInventory().addItem(returnpaper);
					
					p.sendMessage(main.prelabel + "아이템을 구매했습니다");
				}
				else
				{
					p.sendMessage(main.prelabel + "금액이 부족합니다");
				}
			}
			else if(e.getSlot() == 14)
			{
				int cost = 750;
				
				if(game.checkCoin(p, cost))
				{
					game.addCoin(p, -cost);
					p.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING, 1));
					
					p.sendMessage(main.prelabel + "아이템을 구매했습니다");
				}
				else
				{
					p.sendMessage(main.prelabel + "금액이 부족합니다");
				}
			}
			else if(e.getSlot() == 20)
			{
				int cost = 700;
				
				if(game.checkCoin(p, cost))
				{
					game.addCoin(p, -cost);
					p.getInventory().addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1));
					
					p.sendMessage(main.prelabel + "아이템을 구매했습니다");
				}
				else
				{
					p.sendMessage(main.prelabel + "금액이 부족합니다");
				}
			}
			else if(e.getSlot() == 22)
			{
				int cost = 3000;
				
				if(game.checkCoin(p, cost))
				{
					game.addCoin(p, -cost);
					p.getInventory().addItem(new ItemStack(Material.ELYTRA, 1));
					
					p.sendMessage(main.prelabel + "아이템을 구매했습니다");
				}
				else
				{
					p.sendMessage(main.prelabel + "금액이 부족합니다");
				}
			}
			else if(e.getSlot() == 24)
			{
				int cost = 800;
				
				if(game.checkCoin(p, cost))
				{
					game.addCoin(p, -cost);
					p.getInventory().addItem(new ItemStack(Material.SHULKER_BOX, 1));
					
					p.sendMessage(main.prelabel + "아이템을 구매했습니다");
				}
				else
				{
					p.sendMessage(main.prelabel + "금액이 부족합니다");
				}
			}
			else if(e.getSlot() == 30)
			{
				int cost = 500;
				
				if(game.checkCoin(p, cost))
				{
					game.addCoin(p, -cost);
					p.getInventory().addItem(new ItemStack(Material.VILLAGER_SPAWN_EGG, 1));
					
					p.sendMessage(main.prelabel + "아이템을 구매했습니다");
				}
				else
				{
					p.sendMessage(main.prelabel + "금액이 부족합니다");
				}
			}
			else if(e.getSlot() == 32)
			{
				int cost = 500;
				
				if(game.checkCoin(p, cost))
				{
					game.addCoin(p, -cost);
					ItemStack coreheal = new ItemStack(Material.PAPER, 1);
					ItemMeta meta = coreheal.getItemMeta();
					meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "코어 체력 회복");
					coreheal.setItemMeta(meta);
					p.getInventory().addItem(coreheal);
					
					p.sendMessage(main.prelabel + "아이템을 구매했습니다");
				}
				else
				{
					p.sendMessage(main.prelabel + "금액이 부족합니다");
				}
			}
			
			e.setCancelled(true);
		}
		else if(e.getView().getTitle().equalsIgnoreCase("shop sell"))
		{
			if(e.getSlot() == 20)
			{
				if(canSell(p, Material.IRON_INGOT, 10))
				{
					sellItem(p, Material.IRON_INGOT, 10, 100);
					p.sendMessage(main.prelabel + "철 10개를 판매했습니다 (현재 Coin : " + getCoin(p) + ")");
				}
			}
			else if(e.getSlot() == 22)
			{
				if(canSell(p, Material.DIAMOND, 1))
				{
					sellItem(p, Material.DIAMOND, 1, 80);
					p.sendMessage(main.prelabel + "다이아몬드 1개를 판매했습니다 (현재 Coin : " + getCoin(p) + ")");
				}
			}
			else if(e.getSlot() == 24)
			{
				if(canSell(p, Material.PUMPKIN, 32))
				{
					sellItem(p, Material.PUMPKIN, 32, 200);
					p.sendMessage(main.prelabel + "호박 32개를 판매했습니다 (현재 Coin : " + getCoin(p) + ")");
				}
			}
			else if(e.getSlot() == 29)
			{
				if(canSell(p, Material.WHEAT, 10))
				{
					sellItem(p, Material.WHEAT, 10, 250);
					p.sendMessage(main.prelabel + "밀 32개를 판매했습니다 (현재 Coin : " + getCoin(p) + ")");
				}
			}
			else if(e.getSlot() == 31)
			{
				if(canSell(p, Material.ROTTEN_FLESH, 16))
				{
					sellItem(p, Material.ROTTEN_FLESH, 16, 200);
					p.sendMessage(main.prelabel + "썩은 고기 16개를 판매했습니다 (현재 Coin : " + getCoin(p) + ")");
				}
			}
			else if(e.getSlot() == 33)
			{
				if(canSell(p, Material.BONE, 16))
				{
					sellItem(p, Material.BONE, 16, 200);
					p.sendMessage(main.prelabel + "뼈다귀 16개를 판매했습니다 (현재 Coin : " + getCoin(p) + ")");
				}
			}
			
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void ShopInteract(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			Block b = e.getClickedBlock();
			
			if(b.getType() == Material.CHEST)
			{
				if(b.equals(setting.getShopBuy(p.getWorld())))
				{
					e.setCancelled(true);
					p.openInventory(getShopBuy());
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
				}
				else if(b.equals(setting.getShopSell(p.getWorld())))
				{
					e.setCancelled(true);
					p.openInventory(getShopSell());
					p.getWorld().playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
				}
			}
		}
	}
	
	public boolean canBuy(Player p, int i)
	{
		if(getCoin(p) >= i)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean canSell(Player p, Material m, int i)
	{
		int Count = 0;
		
		for(ItemStack item : p.getInventory().getContents())
		{
			if(item != null && item.getType() == m)
	        {
				Count += item.getAmount();
				
	            if(Count >= i)
	            {
	                return true;
	            }
	        }
		}
		
		return false;
	}
	
	public int getCoin(Player p)
	{
		return main.config.getInt(p.getUniqueId() + ".coin");
	}
	
	public void addCoin(Player p, int i)
	{
		main.config.set(p.getUniqueId() + ".coin", getCoin(p) + i);
	}
	
	public Inventory getShopSell()
	{
		Inventory inv = Bukkit.createInventory(null, 54, "SHOP SELL");
		
		ItemStack frame = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta frameMeta = frame.getItemMeta();
		frameMeta.setDisplayName("");
		frame.setItemMeta(frameMeta);
		
		for(int i=0; i<=8; i++)
		{
			inv.setItem(i, frame);
			inv.setItem(i+45, frame);
		}
		
		for(int i=1; i<=5; i++)
		{
			inv.setItem(i*9, frame);
			inv.setItem(i*9+8, frame);
		}
		
		inv.setItem(20, items.getIron());
		inv.setItem(22, items.getDiamond());
		inv.setItem(24, items.getPumpkin());
		inv.setItem(29, items.getWheat());
		inv.setItem(31, items.getRottenFlesh());
		inv.setItem(33, items.getBone());
		
		return inv;
	}
	
	public Inventory getShopBuy()
	{
		Inventory inv = Bukkit.createInventory(null, 45, "SHOP BUY");
		
		ItemStack frame = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta frameMeta = frame.getItemMeta();
		frameMeta.setDisplayName("");
		frame.setItemMeta(frameMeta);
		
		for(int i=0; i<=8; i++)
		{
			inv.setItem(i, frame);
			inv.setItem(i+36, frame);
		}
		
		for(int i=1; i<=4; i++)
		{
			inv.setItem(i*9, frame);
			inv.setItem(i*9+8, frame);
		}
		
		inv.setItem(12, items.getReturnPaper());
		inv.setItem(14, items.getUndyingTotem());
		inv.setItem(20, items.getEnchatedGoldenApple());
		inv.setItem(22, items.getElytra());
		inv.setItem(24, items.getShulkerBox());
		inv.setItem(30, items.getVillageEgg());
		inv.setItem(32, items.getCoreHeal());
		
		return inv;
	}
	
	public void sellItem(Player p, Material m, int count, int cost)
	{
		int Count = 0;
		
		for(ItemStack item : p.getInventory().getContents())
		{
			if(item != null && item.getType() == m)
	        {
				Count += item.getAmount();
				
	            if(Count >= count)
	            {
	                item.setAmount(item.getAmount() - (count));
	                break;
	            }
	        }
		}
		
		game.addCoin(p, cost);
	}
}
