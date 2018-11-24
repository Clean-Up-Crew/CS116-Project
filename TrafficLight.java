package Traffic.RoadNetwork;
import Traffic.RoadNetwork.*;

public class TrafficLight
{
	private int Green = 0;
	private int Yellow = 0;
	private int Red = 0;
	private int GPerm = 0;
	private int YPerm = 0;
	private int RPerm = 0;
	public int state = 0;//0 is Green, 1 is Yellow, 2 is Red
	
	public TrafficLight(int Green, int Yellow, int Red, int state)
	{
		GPerm = Green;
		YPerm = Yellow;
		RPerm = Red;
		this.Green = Green;
		this.Yellow = Yellow;
		this.Red = Red;
		this.state = state;
	}
	
	public int getLight()
	{
		return state;
	}
	
	public void LightChange()
	{
		if(state == 0 && Green > 0)
			Green--;
		else if(state == 0 && Green == 0)
		{
			state = 1;
			Green = GPerm;
		}
		else if(state == 1 && Yellow > 0)
			Yellow--;
		else if(state == 1 && Yellow == 0)
		{
			state = 2;
			Yellow = YPerm;
		}
		else if(state == 2 && Red > 0)
			Red--;
		else if(state == 2 && Red == 0)
		{
			state = 0;
			Red = RPerm;
		}
	}
}