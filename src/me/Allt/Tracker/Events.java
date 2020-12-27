package me.Allt.Tracker;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.CompassMeta;

public class Events implements Listener
{
	  @EventHandler
	  public static void onRightClick(PlayerInteractEvent event)
	  {
		    //If we right click anywhere
		    if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
		    {
			      //If the item that we click with exists
			      if(event.getItem() != null)
			      {
					//If the item is OUR special compass
					if(event.getItem().getItemMeta().equals(ItemManager.compass.getItemMeta()))
					{
						  Player player = event.getPlayer();
						  
						  if(RunnerInstanceControl.getRunnerCount() == 0)
						  {
							    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "No runners have been added yet!");
							    return;
						  }
						  
						  RunnerInstance hunter = RunnerInstanceControl.getHunter(player);
						  Player runner = RunnerInstanceControl.getRunner(hunter.getRunnerID());
						  
						  //If target doesn't exist
						  if(runner == null || runner.isDead())
						  {
							    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "There is no target!");
							    return;
						  }
						  
						  //We are in same dimension
						  if(player.getWorld().getEnvironment() == runner.getWorld().getEnvironment())
						  {
							    //Get location of target
							    Location targetLocation = runner.getLocation();
							    //Reset the compass meta (has to be done to work)
							    ItemManager.ResetCompass(player);
							    
							    //Overworld
							    if(runner.getWorld().getEnvironment() == Environment.NORMAL)
							    {
								      //Just set the compass to point at target
								      player.setCompassTarget(targetLocation);
								      player.sendMessage(ChatColor.GOLD + "Now tracking: " + ChatColor.GREEN  + "" + ChatColor.BOLD + runner.getName());
								     
								      return;
							    }
							    
							    //Nether
							    if(runner.getWorld().getEnvironment() == Environment.NETHER)
							    {
								      //We create a Compass from the Item
								      CompassMeta compass = (CompassMeta)player.getInventory().getItemInMainHand().getItemMeta();
								      //In the nether, the compass can only point to a lodestone, unlike in the overworld where it can point to any coordinate.
								      compass.setLodestone(targetLocation);
								      compass.setLodestoneTracked(false);
								      //Change the meta to the new meta
								      player.getInventory().getItemInMainHand().setItemMeta(compass);
								      
								      //Update the global compass meta
								      ItemManager.compass.setItemMeta(compass);
								      
								      player.sendMessage(ChatColor.GOLD + "Now tracking: " + ChatColor.GREEN  + "" + ChatColor.BOLD + runner.getName());
								      
								      return;
							    }
							    
							    return;
						  }
						  else //Not in same dimension
						  {
							    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Target is in another dimension!");
							    return;
						  }
					}
			      }
		    }
	  }
	  
	  @EventHandler
	  public static void onLeftClick(PlayerInteractEvent event)
	  {
		    //If we right click anywhere
		    if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
		    {
			      //If the item that we click with exists
			      if(event.getItem() != null)
			      {
					//If the item is OUR special compass
					if(event.getItem().getItemMeta().equals(ItemManager.compass.getItemMeta()))
					{
						  Player player = event.getPlayer();
						  
						  //If there are 0 runners
						  if(RunnerInstanceControl.getRunnerCount() == 0)
						  {
							    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "There are no players to track!");
							    return;
						  }
						  
						  RunnerInstance hunter = RunnerInstanceControl.getHunter(player);  
						  if(hunter == null)
						  {
							    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You are not a hunter!");
							    return;
						  }
						  
						  int maxRunners = RunnerInstanceControl.getRunnerCount();
						  int runnerID = hunter.getRunnerID();
						  
						  runnerID++;		  
						  if(runnerID >= maxRunners) runnerID = 0;
						  
						  hunter.setRunner(runnerID);
						  
						  Player runner = RunnerInstanceControl.getRunner(runnerID);
						  player.sendMessage("§6Now tracking: " + "§4§l" + runner.getDisplayName());
					}
			      }
		    }
	  }
}
