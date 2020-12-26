package me.Allt.Tracker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor
{
	  public static Player target;
	  
	  @Override
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	  {
		    if(!(sender instanceof Player)) return false;
		    
		    Player player = (Player)sender;
		    
		    if(cmd.getName().equalsIgnoreCase("givetracker"))
		    {
			      player.getInventory().addItem(ItemManager.compass);
			      player.sendMessage(ChatColor.GREEN + "Happy hunting!");
		    }
		    
		    if(cmd.getName().equalsIgnoreCase("settarget"))
		    {
			      target = player;
			      player.sendMessage(ChatColor.GREEN + "RUN!!");
		    }
		    
	            return true;
	  }
}
