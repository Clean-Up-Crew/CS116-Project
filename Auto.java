package Traffic.RoadNetwork;
import Traffic.RoadNetwork.*;

public class Auto
{
	private int ID;
	private static int counter = 10000;
	private int Entry;
	private String EntLane;
	private String ExitLane;
	private int Exit = -1;
	public boolean turned = false;
	
	public Auto(int Entry, String EntLane)
	{
		this.Entry = Entry;
		this.EntLane = EntLane;
		this.ExitLane = "";
		ID = counter;
		counter++; 
	}
	
	public int getEntry()
	{
		return Entry;
	}
	
	public int getExit()
	{
		return Exit;
	}
	
	public void setExit(int newExit)
	{
		Exit = newExit;
	}
	
	public void setExitLane(String exit)
	{
		ExitLane = exit;
	}
	
	public String toString()
	{
		return "ID: " +ID
			+" \nEntry Time: " +Entry
			+" \nEntry Lane: " +EntLane
			+" \nExit Time: " +Exit
			+" \nExit Lane: " +ExitLane;
	}
}