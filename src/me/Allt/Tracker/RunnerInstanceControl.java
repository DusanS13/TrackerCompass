package me.Allt.Tracker;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class RunnerInstanceControl 
{
	  private static List<RunnerInstance> hunterInfo = new ArrayList<RunnerInstance>();
	  private static List<Player> hunters = new ArrayList<Player>();
	  private static List<Player> runners = new ArrayList<Player>();
	  
	  public static void AddHunter(Player p)
	  {
		    if(hunters.contains(p)) return;
		    
		    hunters.add(p);
		    
		    RunnerInstance newHunter = new RunnerInstance(p);
		    hunterInfo.add(newHunter);
	  }
	  
	  public static void AddRunner(Player p)
	  {
		    if(runners.contains(p)) return;
		    
		    runners.add(p);
	  }
	  
	  public static RunnerInstance getHunter(Player p)
	  {
		    for (RunnerInstance hunter : hunterInfo) 
		    {
			      if(hunter.getHunter() == p) return hunter;
		    }
		    
		    return null;
	  }
	  
	  public static Player getRunner(int runnerID)
	  {
		    return runners.get(runnerID);
	  }
	  
	  public static int getRunnerCount()
	  {
		    return runners.size();
	  }
}
