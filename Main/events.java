package Main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import net.md_5.bungee.api.ChatColor;

public class events implements Listener
{
	private static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Seize");
	
	@EventHandler
	public void EntityDamage(EntityDamageByEntityEvent e)
	{
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player)
		{
			Player p1 = (Player) e.getEntity();  //피해자
			Player p2 = (Player) e.getDamager(); //가해자
			
			String p1_Team = main.config.getString(p1.getUniqueId().toString());
			String p2_Team = main.config.getString(p2.getUniqueId().toString());
			
			//팀킬 방지
			if(p1_Team.equalsIgnoreCase(p2_Team))
			{
				e.setCancelled(true);
			}
			//스폰킬 방지
			else if(game.inHome(p1.getLocation(), p1_Team))
			{
				e.setCancelled(true);
				p2.sendMessage(main.prelabel + ChatColor.RED + "스폰지역 안의 사람은 때릴 수 없습니다");
			}
		}
		else if(e.getEntity() instanceof EnderCrystal && e.getDamager() instanceof Player)
		{
			EnderCrystal core = (EnderCrystal) e.getEntity();
			Player p = (Player) e.getDamager();
			
			
			String p_team = main.config.getString(p.getUniqueId().toString() + ".team");
			
			Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
			
			if(scoreboard.getEntryTeam(core.getUniqueId().toString()).getName().equalsIgnoreCase("red"))
			{
				e.setCancelled(true);
				
				if(p_team.equalsIgnoreCase("red"))
				{
					if(p.getItemInHand().getItemMeta() != null)
					{
						if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "코어 체력 회복") && main.config.getInt("red.core.health") < setting.maxCoreHealth)
						{
							game.HealCore(p_team, 200);
							p.sendMessage(main.prelabel + "코어의 체력이 회복되었습니다");
							ItemStack item = p.getItemInHand();
							p.getItemInHand().setAmount(item.getAmount() - 1);
						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "코어 체력 회복"))
						{
							p.sendMessage(main.prelabel + "코어의 체력이 이미 최대입니다");
						}
					}
					
					p.sendMessage(main.prelabel + ChatColor.RED + "red " + ChatColor.RESET + "팀 코어 체력 : " + main.config.getInt("red.core.health"));
				}
				else if(p_team.equalsIgnoreCase("blue"))
				{
					if(main.config.getInt("red.core.health") < e.getDamage())
					{
						Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.RED + "red" + ChatColor.RESET + " 팀의 코어가 파괴되었습니다");

						for(Entity en : Bukkit.getWorld(main.config.getString("red.core.world")).getEntities())
						{
							if(en.getUniqueId().toString().equalsIgnoreCase(main.config.getString("red.core.UUID")))
							{
								Location location = en.getLocation();
								location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, location, 1);
								location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
								en.remove();
								break;
							}
						}
					}
					else
					{
						main.config.set("red.core.health", main.config.getInt("red.core.health") - 1);
						p.sendMessage(main.prelabel + ChatColor.RED + "red " + ChatColor.RESET + "팀 코어 체력 : " + main.config.getInt("red.core.health"));
						
						for(Player player : game.getTeamPlayer("red"))
						{
							player.sendMessage(main.prelabel + ChatColor.RED + "팀의 코어가 공격받고 있습니다");
						}
					}
				}
				
				e.setCancelled(true);
			}
			else if(scoreboard.getEntryTeam(core.getUniqueId().toString()).getName().equalsIgnoreCase("blue"))
			{
				e.setCancelled(true);
				
				if(p_team.equalsIgnoreCase("blue"))
				{
					if(p.getItemInHand().getItemMeta() != null)
					{
						if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "코어 체력 회복") && main.config.getInt("blue.core.health") < setting.maxCoreHealth)
						{
							game.HealCore(p_team, 200);
							p.sendMessage(main.prelabel + "코어의 체력이 회복되었습니다");
							ItemStack item = p.getItemInHand();
							p.getItemInHand().setAmount(item.getAmount() - 1);
						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "코어 체력 회복"))
						{
							p.sendMessage(main.prelabel + "코어의 체력이 이미 최대입니다");
						}
					}

					p.sendMessage(main.prelabel + ChatColor.BLUE + "blue " + ChatColor.RESET + "팀 코어 체력 : " + main.config.getInt("blue.core.health"));
				}
				else if(p_team.equalsIgnoreCase("red"))
				{
					if(main.config.getInt("blue.core.health") < 1)
					{
						Bukkit.getServer().broadcastMessage(main.prelabel + ChatColor.BLUE + "blue" + ChatColor.RESET + " 팀의 코어가 파괴되었습니다");
						
						for(Entity en : Bukkit.getWorld(main.config.getString("blue.core.world")).getEntities())
						{
							if(en.getUniqueId().toString().equalsIgnoreCase(main.config.getString("blue.core.UUID")))
							{
								Location location = en.getLocation();
								location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, location, 1);
								location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
								en.remove();
								
								break;
							}
						}
					}
					else
					{
						main.config.set("blue.core.health", main.config.getInt("blue.core.health") - 1);
						p.sendMessage(main.prelabel + ChatColor.BLUE + "blue " + ChatColor.RESET + "팀 코어 체력 : " + main.config.getInt("blue.core.health"));
						
						for(Player player : game.getTeamPlayer("blue"))
						{
							player.sendMessage(main.prelabel + ChatColor.RED + "팀의 코어가 공격받고 있습니다");
						}
					}
				}
			}
		}
		else if(e.getEntity() instanceof EnderCrystal && !(e.getDamager() instanceof Player))
		{
			e.setCancelled(true);
		}
		
		if(e.getEntity() instanceof Player)
		{
			Player p = (Player) e.getEntity();
			
			if(game.returnPlayer.contains(p))
			{
				game.returnPlayer.remove(p);
				p.sendMessage(main.prelabel + "귀환이 취소되었습니다");
			}
		}
	}
	
	@EventHandler
	public void RespawnSetting(PlayerRespawnEvent e)
	{
		Player p = e.getPlayer();
		
		String team = main.config.getString(p.getUniqueId().toString() + ".team");
		
		Location respawn = setting.getRespawnLoc(team);
		
		new BukkitRunnable() 
		{
			@Override
			public void run() 
			{
				p.teleport(respawn);
			}
		}.runTaskLater(plugin, 5);
	}
	
	@EventHandler
	public void PlayerMove(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		
		if(!p.getWorld().getName().equalsIgnoreCase("world"))
		{
			return;
		}
		
		Location loc = p.getLocation();
		String team = main.config.getString(p.getUniqueId().toString() + ".team");
		
		Location home = setting.getHomeLoc(team);
		home.setYaw(loc.getYaw());
		home.setPitch(loc.getPitch());
		Location respawn = setting.getRespawnLoc(team);
		respawn.setYaw(loc.getYaw());
		respawn.setPitch(loc.getPitch());
		Location hometp = setting.getHomeTpLoc(team);
		Location respawntp = setting.getRespawnTpLoc(team);
		
		if(hometp.distance(loc) < 0.5)
		{
			p.teleport(home);
			p.sendMessage(main.prelabel + "home 으로 이동했습니다");
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
		}
		else if(respawntp.distance(loc) < 0.5)
		{
			p.teleport(respawn);
			p.sendMessage(main.prelabel + "respawn 으로 이동했습니다");
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
		}
	}
	
	@EventHandler
	public void PlayerBreakBlock(BlockBreakEvent e)
	{
		Block redhometp = setting.getHomeTpLoc("red").add(0, -1, 0).getBlock();
		Block redrespawntp = setting.getRespawnTpLoc("red").add(0, -1, 0).getBlock();
		Block bluehometp = setting.getHomeTpLoc("blue").add(0, -1, 0).getBlock();
		Block bluerespawntp = setting.getRespawnTpLoc("blue").add(0, -1, 0).getBlock();
		Block b = e.getBlock();
		
		if(b.equals(redhometp) || b.equals(redrespawntp) || b.equals(bluehometp) || b.equals(bluerespawntp))
		{
			e.setCancelled(true);
		}
		
		Player p = e.getPlayer();
		String team1 = main.config.getString(p.getUniqueId().toString() + ".team");
		String team2 = "";
		
		if(team1.equals("red"))
		{
			team2 = "blue";
		}
		else if(team1.equals("blue"))
		{
			team2 = "red";
		}

		if(game.inHome(b.getLocation(), team2))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void PlayerPlaceBlock(BlockPlaceEvent e)
	{
		Block b = e.getBlock();
		
		Location loc1 = setting.getCoreLoc("red");
		Location loc2 = setting.getCoreLoc("blue");
		
		for(int i1 = -1; i1 <= 1; i1++)
		{
			for(int i2 = 0; i2 <= 2; i2++)
			{
				for(int i3 = -1; i3 <= 1; i3++)
				{
					if(loc1.clone().add(i1, i2, i3).getBlock().getLocation().equals(b.getLocation()) || loc2.clone().add(i1, i2, i3).getBlock().getLocation().equals(b.getLocation()))
					{
						e.setCancelled(true);
					}
				}
			}
		}
		
		Player p = e.getPlayer();
		String team1 = main.config.getString(p.getUniqueId().toString() + ".team");
		String team2 = "";
		
		if(team1.equals("red"))
		{
			team2 = "blue";
		}
		else if(team1.equals("blue"))
		{
			team2 = "red";
		}

		if(game.inHome(b.getLocation(), team2))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void PlayerInterect(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		
		if(e.getHand() != EquipmentSlot.HAND)
		{
			return;
		}
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(p.getItemInHand().getItemMeta() != null)
			{
				ItemStack item = p.getItemInHand();
				
				if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "귀환권"))
				{
					p.getItemInHand().setAmount(item.getAmount() - 1);
					game.returnHome(p);
				}
			}
		}
	}
}


















