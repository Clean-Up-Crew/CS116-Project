package Traffic.RoadNetwork;
import Traffic.RoadNetwork.*;
import java.util.Vector;

public class RoadNetwork
{
	private int span;
	private Block[] WE;
	private Block[] EW;
	private Block[] SN;
	private Block[] NS;
	private int total;
	public int FlowWE = 0;
	public int FlowEW = 0;
	public int FlowNS = 0;
	public int FlowSN = 0;
	
	public RoadNetwork(int span, int G, int Y, int R)
	{
		this.span = span;
		total = 2*span + 2;
		WE = new Block[total];
		EW = new Block[total];
		NS = new Block[total];
		SN = new Block[total];
		
		for(int i = 0; i < total; i++)
		{
			if(i == span || i == span+1)
			{
				WE[i] = new IntersectionBlock(1, i);
				EW[i] = new IntersectionBlock(1, i);
				SN[i] = new IntersectionBlock(1, i);
				NS[i] = new IntersectionBlock(1, i);
			}
			else if(i == span-1)
			{
				WE[i] = new TrafficBlock(2, i, G, Y, R, 0);
				EW[i] = new TrafficBlock(2, i, G, Y, R, 0);
				SN[i] = new TrafficBlock(2, i, G, Y, R, 2);
				NS[i] = new TrafficBlock(2, i, G, Y, R, 2);
			}
			else
			{
				WE[i] = new NormalBlock(0, i);
				EW[i] = new NormalBlock(0, i);
				SN[i] = new NormalBlock(0, i);
				NS[i] = new NormalBlock(0, i);
			}
		}
		
		for(int i = 0; i < total; i++)
		{
			WE[i].setNeighbors(WE);
			EW[i].setNeighbors(EW);
			NS[i].setNeighbors(NS);
			SN[i].setNeighbors(SN);
		}
	}
	
	//Reset the flags
	public void resetFlags()
	{
		for(int i = 0; i < total; i++)
		{
			WE[i].setFlag(false);
			EW[i].setFlag(false);
			NS[i].setFlag(false);
			SN[i].setFlag(false);
		}
	}

	//The Lights
	public void Lights()
	{
		WE[span-1].LightChange();
		EW[span-1].LightChange();
		SN[span-1].LightChange();
		NS[span-1].LightChange();
	}
	
	//Move WE
	public void moveWE(boolean turning)
	{
		for(int i = total-2; i >= 0; i--)
		{
			if(turning == true && i == span)
				WE[i].Turn(NS[i+1]);
			else if(turning == true && i == span+1)
				WE[i].Turn(SN[i-1]);
			else if(turning == true && WE[span-1].getColor() == 2 && i == span-1)
				WE[i].Turn(NS[i+2]);
			else if(i == span-1 && NS[span+1].getAuto() != null);
			else if(i == span && SN[span].getAuto() != null);
			else
				WE[i].MoveForward();
		}
	}
	
	//Move EW
	public void moveEW(boolean turning)
	{
		for(int i = total-2; i >= 0; i--)
		{
			if(turning == true && i == span)
				EW[i].Turn(SN[i+1]);
			else if(turning == true && i == span+1)
				EW[i].Turn(NS[i-1]);
			else if(turning == true && EW[span-1].getColor() == 2 && i == span-1)
				EW[i].Turn(SN[i+2]);
			else if(i == span-1 && SN[span+1].getAuto() != null);
			else if(i == span && NS[span].getAuto() != null);
			else
				EW[i].MoveForward();
		}
	}
	
	//Move NS
	public void moveNS(boolean turning)
	{
		for(int i = total-2; i >= 0; i--)
		{
			if(turning == true && i == span)
				NS[i].Turn(EW[i+1]);
			else if(turning == true && i == span+1)
				NS[i].Turn(WE[i-1]);
			else if(turning == true && NS[span-1].getColor() == 2 && i == span-1)
				NS[i].Turn(EW[i+2]);
			else if(i == span-1 && EW[span+1].getAuto() != null);
			else if(i == span && WE[span].getAuto() != null);
			else
				NS[i].MoveForward();
		}
	}
	
	//Move SN
	public void moveSN(boolean turning)
	{
		for(int i = total-2; i >= 0; i--)
		{
			if(turning == true && i == span)
				SN[i].Turn(WE[i+1]);
			else if(turning == true && i == span+1)
				SN[i].Turn(EW[i-1]);
			else if(turning == true && SN[span-1].getColor() == 2 && i == span-1)
				SN[i].Turn(WE[i+2]);
			else if(i == span-1 && WE[span+1].getAuto() != null);
			else if(i == span && EW[span].getAuto() != null);
			else
				SN[i].MoveForward();
		}
	}
	
	//Remove WE
	public void removeWE(Vector<Auto> Completed, int Tick)
	{
		if(WE[total-1].getAuto() != null && WE[total-1].getFlag() == false)
		{
			FlowWE++;
			WE[total-1].getAuto().setExit(Tick);
			WE[total-1].getAuto().setExitLane("WE");
			System.out.println(WE[total-1].getAuto().toString());
			Completed.add(WE[total-1].getAuto());
			WE[total-1].setAuto(null);
		}
	}
	
	//Remove EW
	public void removeEW(Vector<Auto> Completed, int Tick)
	{
		if(EW[total-1].getAuto() != null && EW[total-1].getFlag() == false)
		{
			FlowEW++;
			EW[total-1].getAuto().setExit(Tick);
			EW[total-1].getAuto().setExitLane("EW");
			System.out.println(EW[total-1].getAuto().toString());
			Completed.add(EW[total-1].getAuto());
			EW[total-1].setAuto(null);
		}
	}
	
	//Remove NS
	public void removeNS(Vector<Auto> Completed, int Tick)
	{
		if(NS[total-1].getAuto() != null && NS[total-1].getFlag() == false)
		{
			FlowNS++;
			NS[total-1].getAuto().setExit(Tick);
			NS[total-1].getAuto().setExitLane("NS");
			System.out.println(NS[total-1].getAuto().toString());
			Completed.add(NS[total-1].getAuto());
			NS[total-1].setAuto(null);
		}
	}
	
	//Remove SN
	public void removeSN(Vector<Auto> Completed, int Tick)
	{
		if(SN[total-1].getAuto() != null && SN[total-1].getFlag() == false)
		{
			FlowSN++;
			SN[total-1].getAuto().setExit(Tick);
			SN[total-1].getAuto().setExitLane("SN");
			System.out.println(SN[total-1].getAuto().toString());
			Completed.add(SN[total-1].getAuto());
			SN[total-1].setAuto(null);
		}
	}
	
	//Enter WE
	public void enterWE(int tick)
	{
		if(WE[0].getAuto() == null)
		{
			Auto newAuto = new Auto(tick, "WE");
			WE[0].setAuto(newAuto);
		}
	}
	
	//Enter EW
	public void enterEW(int tick)
	{
		if(EW[0].getAuto() == null)
		{
			Auto newAuto = new Auto(tick, "EW");
			EW[0].setAuto(newAuto);
		}
	}
	
	//Enter NS
	public void enterNS(int tick)
	{
		if(NS[0].getAuto() == null)
		{
			Auto newAuto = new Auto(tick, "NS");
			NS[0].setAuto(newAuto);
		}
	}
	
	//Enter SN
	public void enterSN(int tick)
	{
		if(SN[0].getAuto() == null)
		{
			Auto newAuto = new Auto(tick, "SN");
			SN[0].setAuto(newAuto);
		}
	}
}