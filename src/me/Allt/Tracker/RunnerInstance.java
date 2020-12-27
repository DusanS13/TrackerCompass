package me.Allt.Tracker;

import org.bukkit.entity.Player;

public class RunnerInstance 
{
	  private Player runner;
	  private int runnerID;
	  private Player hunter;
	  
	  RunnerInstance(Player hunter)
	  {
		    this.hunter = hunter;
		    runnerID = 0;
	  }
	  
	  public void setRunner(Player runner)
	  {
		    this.runner = runner;
	  }
	  
	  public void setRunner(int id)
	  {
		    this.runnerID = id;
	  }
	  
	  public Player getRunner()
	  {
		    return runner;
	  }
	  
	  public Player getHunter()
	  {
		    return hunter;
	  }
	  
	  public int getRunnerID()
	  {
		    return runnerID;
	  }
}
