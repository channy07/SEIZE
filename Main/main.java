package Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class main extends JavaPlugin
{
	public static FileConfiguration config = Bukkit.getServer().spigot().getConfig();
	public static String prelabel = "[" + ChatColor.GOLD + "SEIZE" + ChatColor.RESET + "] ";
	
	@Override
	public void onEnable()
	{
		System.out.println("----------------------------------------------");
		System.out.println("  P r o j e c t   S E I Z E ");
		System.out.println(" bug report : channy070123@gmail.com");
		System.out.println("----------------------------------------------");
		
		this.getCommand("seize").setExecutor(new commands());
		this.getCommand("seize").setTabCompleter(new commandTapCompleter());
		getServer().getPluginManager().registerEvents(new events(), this);
		getServer().getPluginManager().registerEvents(new shopManager(), this);

		this.saveDefaultConfig();
		config = this.getConfig();
		
		new BukkitRunnable() 
		{
			@Override
			public void run() 
			{
				Location loc1 = setting.getCoreLoc("red");
				Location loc2 = setting.getCoreLoc("blue");
				
				for(int i1 = -1; i1 <= 1; i1++)
				{
					for(int i2 = 0; i2 <= 2; i2++)
					{
						for(int i3 = -1; i3 <= 1; i3++)
						{
							loc1.clone().add(i1, i2, i3).getBlock().setType(Material.AIR);
							loc2.clone().add(i1, i2, i3).getBlock().setType(Material.AIR);
						}
					}
				}
				
				loc1.add(0, -1, 0).getBlock().setType(Material.BEDROCK);
				loc2.add(0, -1, 0).getBlock().setType(Material.BEDROCK);
			}
		}.runTaskTimer(game.plugin, 0, 1);
	}
	
	@Override
	public void onDisable()
	{
		saveConfig();
		
		System.out.println("----------------------------------------------");
		System.out.println("  P r o j e c t   S E I Z E ");
		System.out.println(" bug report : channy070123@gmail.com");
		System.out.println("----------------------------------------------");
	}
	
	public void loadConfig()
	{
		config.options().copyDefaults(true);
		saveConfig();
	}
}
