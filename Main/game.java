package Main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import net.md_5.bungee.api.ChatColor;

public class game 
{
	public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Seize");
	
	public static ArrayList<Player> returnPlayer = new ArrayList<Player>();
	
	public static ArrayList<Player> getTeamPlayer(String team)
	{
		ArrayList<Player> list = new ArrayList<Player>();
		
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			if(main.config.getString(p.getUniqueId().toString() + ".team").equalsIgnoreCase(team))
			{
				list.add(p);
			}
		}
		
		return list;
	}
	
	public static void HealCore(String team, int i)
	{
		int health = main.config.getInt(team + ".core.health");
		
		if(health + i > setting.maxCoreHealth)
		{
			main.config.set(team + ".core.health", setting.maxCoreHealth);
		}
		else
		{
			main.config.set(team + ".core.health", health + i);
		}
	}

	public static boolean inHome(Location loc, String team) 
	{
		int x = main.config.getInt(team + ".home.x");
		int y = main.config.getInt(team + ".home.y");
		int z = main.config.getInt(team + ".home.z");
		
		if(loc.getX() <= x + 4 && loc.getX() >= x - 4 && loc.getY() <= y + 4 && loc.getY() >= y - 1 && loc.getZ() <= z + 4 && loc.getZ() >= z - 4)
		{
			return true;
		}
		
		return false;
	}
	
	public static void returnHome(Player p)
	{
		String team = main.config.getString(p.getUniqueId().toString() + ".team");
		Location home = setting.getHomeLoc(team);
		
		returnPlayer.add(p);
		
		new BukkitRunnable() 
		{
			int i = 1;
			
			@Override
			public void run() 
			{
				if(returnPlayer.contains(p))
				{
					if(i == 1)
					{
						p.sendMessage(main.prelabel + "5초 후 이동합니다");
					}
				
					if(i > 1 && i <= 5)
					{
						p.sendMessage(main.prelabel + (6 - i));
					}
				
					if(i == 5)
					{
						returnPlayer.remove(p);
						p.teleport(home);
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
						this.cancel();
					}
				
					i++;
				}
				else
				{
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 0, 20);
	}
	
	public static int getCoin(Player p)
	{
		int coin = main.config.getInt(p.getUniqueId().toString() + ".coin");
		
		return coin;
	}
	
	public static void addCoin(Player p, int i)
	{
		int coin = getCoin(p);
		
		main.config.set(p.getUniqueId().toString() + ".coin", coin + i);
	}
	
	public static void giveCoin(Player giver, Player receiver, int i)
	{
		addCoin(giver, -i);
		addCoin(receiver, i);
	}
	
	public static boolean checkCoin(Player p, int i)
	{
		int coin = getCoin(p);
		
		return (coin >= i);
	}
}














































