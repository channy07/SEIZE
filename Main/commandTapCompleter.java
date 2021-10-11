package Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class commandTapCompleter implements TabCompleter 
{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command c, String label, String[] args) 
	{
		if(c.getName().equalsIgnoreCase("seize"))
		{
			if(args.length == 1)
			{
				List<String> commands = new ArrayList<String>();
				
				commands.add("help");
				commands.add("set");
				commands.add("join");
				
				return commands;
			}
			
			if(args.length == 2 && args[0].equalsIgnoreCase("set"))
			{
				List<String> commands = new ArrayList<String>();
				
				commands.add("core");
				commands.add("spawn");
				commands.add("spawntp");
				commands.add("home");
				commands.add("hometp");
				commands.add("shop");
				
				return commands;
			}
			else if(args.length == 2 && args[0].equalsIgnoreCase("join"))
			{
				List<String> commands = new ArrayList<String>();
				
				commands.add("red");
				commands.add("blue");
				
				return commands;
			}
			
			if(args.length == 3)
			{
				if(args[0].equalsIgnoreCase("set"))
				{
					if(args[1].equalsIgnoreCase("shop"))
					{
						List<String> commands = new ArrayList<String>();
						
						commands.add("sell");
						commands.add("buy");
						
						return commands;
					}
				}
			}
			
			if(args[0].equalsIgnoreCase("set") && args.length == 3 && 
					(args[1].equalsIgnoreCase("spawn") || args[1].equalsIgnoreCase("home") || args[1].equalsIgnoreCase("core") || args[1].equalsIgnoreCase("spawntp") || args[1].equalsIgnoreCase("hometp")))
			{
				List<String> commands = new ArrayList<String>();
				
				commands.add("red");
				commands.add("blue");
				
				return commands;
			}
		}	
		
		return null;
	}

}
