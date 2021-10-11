package Main;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class setting 
{
	//코어
	//스폰
	//홈
	//홈 이동
	//리스폰 이동
	
	public static int maxCoreHealth = 4000;
	
	public static void JoinRedTeam(Player p)
	{
		main.config.set(p.getUniqueId().toString() + ".team", "red");
		Bukkit.getServer().broadcastMessage(main.prelabel + p.getName() + "님이 " + ChatColor.RED + "red" + ChatColor.RESET + "팀에 참가했습니다");
	}
	
	public static void JoinBlueTeam(Player p)
	{
		main.config.set(p.getUniqueId().toString() + ".team", "blue");
		Bukkit.getServer().broadcastMessage(main.prelabel + p.getName() + "님이 " + ChatColor.BLUE + "blue" + ChatColor.RESET + "팀에 참가했습니다");
	}
	
	public static void SetRedCore(Location loc)
	{
		main.config.set("red.core.world", loc.getWorld().getName());
		main.config.set("red.core.x", loc.getBlockX());
		main.config.set("red.core.y", loc.getBlockY());
		main.config.set("red.core.z", loc.getBlockZ());
		main.config.set("red.core.health", maxCoreHealth);
		
		loc.add(0, -1, 0).getBlock().setType(Material.BEDROCK);
		
		EnderCrystal core = (EnderCrystal) loc.getWorld().spawnEntity(getCoreLoc("red").add(0, 1, 0), EntityType.ENDER_CRYSTAL);
		
		Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("red");
		team.setColor(ChatColor.RED);
		team.addEntry(core.getUniqueId().toString());

		core.setGlowing(true);
		
		main.config.set("red.core.UUID", core.getUniqueId().toString());
		
		Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.RED + "red" + ChatColor.RESET + "팀의 코어가 설정되었습니다");
	}
	
	public static void SetBlueCore(Location loc)
	{
		main.config.set("blue.core.world", loc.getWorld().getName());
		main.config.set("blue.core.x", loc.getBlockX());
		main.config.set("blue.core.y", loc.getBlockY());
		main.config.set("blue.core.z", loc.getBlockZ());
		
		main.config.set("blue.core.health", maxCoreHealth);
		
		loc.add(0, -1, 0).getBlock().setType(Material.BEDROCK);
		
		EnderCrystal core = (EnderCrystal) loc.getWorld().spawnEntity(getCoreLoc("blue").add(0, 1, 0), EntityType.ENDER_CRYSTAL);
		
		Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("blue");
		team.setColor(ChatColor.BLUE);
		team.addEntry(core.getUniqueId().toString());
		
		core.setGlowing(true);
		
		main.config.set("blue.core.UUID", core.getUniqueId().toString());
		
		Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.BLUE + "blue" + ChatColor.RESET + "팀의 코어가 설정되었습니다");
	}
	
	public static void SetRedHome(Location loc)
	{
		if(Bukkit.getWorld(main.config.getString("red.core.world")) != loc.getWorld())
		{
			Bukkit.getServer().broadcastMessage(main.prelabel + "코어와 같은 월드에 생성해야 합니다");
			return;
		}
		
		main.config.set("red.home.x", loc.getBlockX());
		main.config.set("red.home.y", loc.getBlockY());
		main.config.set("red.home.z", loc.getBlockZ());
		
		loc.add(0, -1, 0);
		
		for(int i1 = -3; i1 <= 3; i1++)
		{
			for(int i2 = -3; i2 <= 3; i2++)
			{
				loc.clone().add(i1, 0, i2).getBlock().setType(Material.BEDROCK);
			}
		}
		
		Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.RED + "red" + ChatColor.RESET + "팀의 스폰지역이 설정되었습니다");
	}
	
	public static void SetRedHomeTeleport(Location loc)
	{
		if(Bukkit.getWorld(main.config.getString("red.core.world")) != loc.getWorld())
		{
			Bukkit.getServer().broadcastMessage(main.prelabel + "코어와 같은 월드에 생성해야 합니다");
			return;
		}
		
		main.config.set("red.home.tp.x", loc.getBlockX());
		main.config.set("red.home.tp.y", loc.getBlockY());
		main.config.set("red.home.tp.z", loc.getBlockZ());
		
		loc.add(0, -1, 0);
		
		loc.getBlock().setType(Material.GOLD_BLOCK);
		
		Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.RED + "red" + ChatColor.RESET + "팀의 스폰 이동구역이 설정되었습니다");
	}
	
	public static void SetBlueHome(Location loc)
	{
		if(Bukkit.getWorld(main.config.getString("blue.core.world")) != loc.getWorld())
		{
			Bukkit.getServer().broadcastMessage(main.prelabel + "코어와 같은 월드에 생성해야 합니다");
			return;
		}
		
		main.config.set("blue.home.x", loc.getBlockX());
		main.config.set("blue.home.y", loc.getBlockY());
		main.config.set("blue.home.z", loc.getBlockZ());
		
		loc.add(0, -1, 0);
		
		for(int i1 = -3; i1 <= 3; i1++)
		{
			for(int i2 = -3; i2 <= 3; i2++)
			{
				loc.clone().add(i1, 0, i2).getBlock().setType(Material.BEDROCK);
			}
		}
		
		Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.BLUE + "blue" + ChatColor.RESET + "팀의 홈이 설정되었습니다");
	}
	
	public static void SetBlueHomeTeleport(Location loc)
	{
		if(Bukkit.getWorld(main.config.getString("blue.core.world")) != loc.getWorld())
		{
			Bukkit.getServer().broadcastMessage(main.prelabel + "코어와 같은 월드에 생성해야 합니다");
			return;
		}
		
		main.config.set("blue.home.tp.x", loc.getBlockX());
		main.config.set("blue.home.tp.y", loc.getBlockY());
		main.config.set("blue.home.tp.z", loc.getBlockZ());
		
		loc.add(0, -1, 0);
		
		loc.getBlock().setType(Material.GOLD_BLOCK);
		
		Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.BLUE + "blue" + ChatColor.RESET + "팀의 스폰 이동구역이 설정되었습니다");
	}
	
	public static void SetRedRespawn(Location loc)
	{
		if(Bukkit.getWorld(main.config.getString("red.core.world")) != loc.getWorld())
		{
			Bukkit.getServer().broadcastMessage(main.prelabel + "코어와 같은 월드에 생성해야 합니다");
			return;
		}
		
		main.config.set("red.respawn.x", loc.getBlockX());
		main.config.set("red.respawn.y", loc.getBlockY());
		main.config.set("red.respawn.z", loc.getBlockZ());
		
		Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.RED + "red" + ChatColor.RESET + "팀의 스폰지역이 설정되었습니다");
	}
	
	public static void SetRedRespawnTeleport(Location loc)
	{
		if(Bukkit.getWorld(main.config.getString("red.core.world")) != loc.getWorld())
		{
			Bukkit.getServer().broadcastMessage(main.prelabel + "코어와 같은 월드에 생성해야 합니다");
			return;
		}
		
		main.config.set("red.respawn.tp.x", loc.getBlockX());
		main.config.set("red.respawn.tp.y", loc.getBlockY());
		main.config.set("red.respawn.tp.z", loc.getBlockZ());
		
		loc.add(0, -1, 0);
		
		loc.getBlock().setType(Material.GOLD_BLOCK);
		
		Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.RED + "red" + ChatColor.RESET + "팀의 리스폰 이동구역이 설정되었습니다");
	}
	
	public static void SetBlueRespawn(Location loc)
	{
		if(Bukkit.getWorld(main.config.getString("blue.core.world")) != loc.getWorld())
		{
			Bukkit.getServer().broadcastMessage(main.prelabel + "코어와 같은 월드에 생성해야 합니다");
			return;
		}
		
		main.config.set("blue.respawn.x", loc.getBlockX());
		main.config.set("blue.respawn.y", loc.getBlockY());
		main.config.set("blue.respawn.z", loc.getBlockZ());
		
		Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.BLUE + "blue" + ChatColor.RESET + "팀의 스폰지역이 설정되었습니다");
		
		
	}
	
	public static void SetBlueRespawnTeleport(Location loc)
	{
		if(Bukkit.getWorld(main.config.getString("blue.core.world")) != loc.getWorld())
		{
			Bukkit.getServer().broadcastMessage(main.prelabel + "코어와 같은 월드에 생성해야 합니다");
			return;
		}
		
		main.config.set("blue.respawn.tp.x", loc.getBlockX());
		main.config.set("blue.respawn.tp.y", loc.getBlockY());
		main.config.set("blue.respawn.tp.z", loc.getBlockZ());
		
		loc.add(0, -1, 0);
		
		loc.getBlock().setType(Material.GOLD_BLOCK);
		
		Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.BLUE + "blue" + ChatColor.RESET + "팀의 리스폰 이동구역이 설정되었습니다");
	}
	
	public static void SetShopBuy(Location loc)
	{
		main.config.set("shop.buy.loc.x", loc.getBlockX());
		main.config.set("shop.buy.loc.y", loc.getBlockY());
		main.config.set("shop.buy.loc.z", loc.getBlockZ());
		
		Bukkit.getServer().broadcastMessage(main.prelabel + "상점의 구매 구역이 설정되었습니다");
	}
	
	public static void SetShopSell(Location loc)
	{
		main.config.set("shop.sell.loc.x", loc.getBlockX());
		main.config.set("shop.sell.loc.y", loc.getBlockY());
		main.config.set("shop.sell.loc.z", loc.getBlockZ());
		
		Bukkit.getServer().broadcastMessage(main.prelabel + "상점의 판매 구역이 설정되었습니다");
	}
	
	public static Location getloc(Location loc)
	{
		int x1 = (int) loc.getX();
		int y = (int) loc.getY();
		int z1 = (int) loc.getZ();
		
		double x2 = x1 + 0.5;
		double z2 = z1 + 0.5;
		
		return new Location(loc.getWorld(), x2, y, z2);
	}
	
	public static Location getCoreLoc(String team)
	{
		return getloc(new Location(Bukkit.getWorld("world"), 
				main.config.getInt(team + ".core.x"), main.config.getInt(team + ".core.y"), main.config.getInt(team + ".core.z")));
	}
	
	public static Location getHomeLoc(String team)
	{
		return getloc(new Location(Bukkit.getWorld("world"), 
				main.config.getInt(team + ".home.x"), main.config.getInt(team + ".home.y"), main.config.getInt(team + ".home.z")));
	}
	
	public static Location getRespawnLoc(String team)
	{
		return getloc(new Location(Bukkit.getWorld("world"), 
				main.config.getInt(team + ".respawn.x"), main.config.getInt(team + ".respawn.y"), main.config.getInt(team + ".respawn.z")));
	}
	
	public static Location getHomeTpLoc(String team)
	{
		return getloc(new Location(Bukkit.getWorld("world"), 
				main.config.getInt(team + ".home.tp.x"), main.config.getInt(team + ".home.tp.y"), main.config.getInt(team + ".home.tp.z")));
	}
	
	public static Location getRespawnTpLoc(String team)
	{
		return getloc(new Location(Bukkit.getWorld("world"), 
				main.config.getInt(team + ".respawn.tp.x"), main.config.getInt(team + ".respawn.tp.y"), main.config.getInt(team + ".respawn.tp.z")));
	}
	
	public static Block getShopBuy(World world)
	{
		Location loc = new Location(world, main.config.getInt("shop.buy.loc.x"), main.config.getInt("shop.buy.loc.y"), main.config.getInt("shop.buy.loc.z"));
		Block b = loc.getBlock();
		
		return b;
	}
	
	public static Block getShopSell(World world)
	{
		Location loc = new Location(world, main.config.getInt("shop.sell.loc.x"), main.config.getInt("shop.sell.loc.y"), main.config.getInt("shop.sell.loc.z"));
		Block b = loc.getBlock();
		
		return b;
	}
	
	public static EnderCrystal getCore(String team)
	{
		EnderCrystal core = null;
		
		for(Entity en : Bukkit.getWorld("world").getEntities())
		{
			if(main.config.getString(team + ".core.UUID").equalsIgnoreCase(en.getUniqueId().toString()))
			{
				core = (EnderCrystal) en;
				return core;
			}
		}
		
		return core;
	}
}











