package me.Allt.Tracker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor
{
	  @Override
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	  {
		    if(!(sender instanceof Player)) return false;
		    
		    Player player = (Player)sender;
		    
		    if(cmd.getName().equalsIgnoreCase("givetracker"))
		    {
			      RunnerInstanceControl.AddHunter(player);
			      
			      player.getInventory().addItem(ItemManager.compass);
			      player.sendMessage(ChatColor.GREEN + "Happy hunting!");
		    }
		    
		    if(cmd.getName().equalsIgnoreCase("settarget"))
		    {
			      RunnerInstanceControl.AddRunner(player);      
			      player.sendMessage(ChatColor.GREEN + "Good luck!");
		    }
		    
	            return true;
	  }
}
