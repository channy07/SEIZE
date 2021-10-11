package Main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.world.level.levelgen.feature.configurations.WorldGenFeatureBlockPileConfiguration;

public class commands implements CommandExecutor
{
	FileConfiguration config = main.config;
	File file = new File(Bukkit.getServer().getPluginManager().getPlugin("Seize").getDataFolder(), "config.yml");
	
	@Override
	public boolean onCommand(CommandSender sender, Command c, String label, String[] args) 
	{
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			Location loc = p.getLocation();
			
			if(c.getName().equalsIgnoreCase("seize"))
			{
				if(args[0].equalsIgnoreCase("help"))
				{
					
				}
				else if(args[0].equalsIgnoreCase("set"))
				{
					if(args[1].equalsIgnoreCase("core"))
					{
						if(args[2].equalsIgnoreCase("red"))
						{
							setting.SetRedCore(loc);
						}
						else if(args[2].equalsIgnoreCase("blue"))
						{
							setting.SetBlueCore(loc);
						}
					}
					else if(args[1].equalsIgnoreCase("spawn"))
					{
						if(args[2].equalsIgnoreCase("red"))
						{
							setting.SetRedRespawn(loc);
						}
						else if(args[2].equalsIgnoreCase("blue"))
						{
							setting.SetBlueRespawn(loc);
						}
					}
					else if(args[1].equalsIgnoreCase("spawntp"))
					{
						if(args[2].equalsIgnoreCase("red"))
						{
							setting.SetRedRespawnTeleport(loc);
						}
						else if(args[2].equalsIgnoreCase("blue"))
						{
							setting.SetBlueRespawnTeleport(loc);
						}
					}
					else if(args[1].equalsIgnoreCase("home"))
					{
						if(args[2].equalsIgnoreCase("red"))
						{
							setting.SetRedHome(loc);
						}
						else if(args[2].equalsIgnoreCase("blue"))
						{
							setting.SetBlueHome(loc);
						}
					}
					else if(args[1].equalsIgnoreCase("hometp"))
					{
						if(args[2].equalsIgnoreCase("red"))
						{
							setting.SetRedHomeTeleport(loc);
						}
						else if(args[2].equalsIgnoreCase("blue"))
						{
							setting.SetBlueHomeTeleport(loc);
						}
					}
					else if(args[1].equalsIgnoreCase("shop"))
					{
						if(args[2].equalsIgnoreCase("buy"))
						{
							setting.SetShopBuy(loc);
						}
						else if(args[2].equalsIgnoreCase("sell"))
						{
							setting.SetShopSell(loc);
						}
					}
				}
				else if(args[0].equalsIgnoreCase("join"))
				{
					if(args[1].equalsIgnoreCase("red"))
					{
						setting.JoinRedTeam(p);
					}
					else if(args[1].equalsIgnoreCase("blue"))
					{
						setting.JoinBlueTeam(p);
					}
				}
				else if(args[0].equalsIgnoreCase("coin"))
				{
					if(args.length == 1)
					{
						p.sendMessage(main.prelabel + "현재 coin : " + ChatColor.GOLD + game.getCoin(p));
					}
					else
					{
						if(args.length == 2)
						{
							p.sendMessage(main.prelabel + "지급할 금액을 입력해 주세요");
						}
						else if(args.length == 3)
						{
							String name = args[1];
							String cost = args[2];
							boolean checkName = false;
							boolean checkCost = false;
							int num = 0;
							Player receiver = null;
							
							for(Player player : Bukkit.getOnlinePlayers())
							{
								if(player.getName().equalsIgnoreCase(name))
								{
									checkName = true;
									receiver = player;
									break;
								}
							}
							
							try
							{
								num = Integer.parseInt(cost);
								checkCost = true;
							} 
							catch (NumberFormatException e) 
							{
								checkCost = false;
								p.sendMessage(main.prelabel + "금액을 다시 설정해 주세요");
								return false;
							}
							
							if(checkCost && checkName && game.checkCoin(p, num) && receiver != null && num >= 1)
							{
								game.giveCoin(p, receiver, num);
								p.sendMessage(main.prelabel + name + "에게 " + ChatColor.GOLD + cost + " Coin" + ChatColor.RESET + "을 지급했습니다 (현재 Coin : " + game.getCoin(p) + ")");
								receiver.sendMessage(main.prelabel + p.getName() + "에게 " + ChatColor.GOLD + cost + " Coin" + ChatColor.RESET + "을 받았습니다 (현재 Coin : " + game.getCoin(receiver) + ")");
							}
							else
							{
								p.sendMessage(main.prelabel + "플레이어가 존재하지 않거나 금액이 부족합니다");
							}
						}
					}
				}
			}
		}
		
		return false;
	}

}
