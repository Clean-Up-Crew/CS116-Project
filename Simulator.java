package Traffic.Client;

import Traffic.RoadNetwork.*;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class Simulator
{
	public static void main(String[] args)
	{
		Vector<Auto> Completed = new Vector();
		int BigBoiTime = 0;
		int AvgWait = 0;
		
		//Get all the inputs
		Scanner in = new Scanner(System.in);
		
		System.out.println("\nWelcome to the Traffic Simulator. Please input the entry rate of vehicles (a decimal between 0 and 1).");
		double entRate = in.nextDouble();
		System.out.println("\nNow please enter the rate of turning at intersections (a decimal between 0 and 1).");
		double turnRate = in.nextDouble();
		System.out.println("\nNow please enter the duration in whole ticks of green lights.");
		int GDur = in.nextInt();
		System.out.println("\nFor yellow.");
		int YDur = in.nextInt();
		System.out.println("\nAnd now for red.");
		int RDur = in.nextInt();
		System.out.println("\nPlease input the duration of the simulation in number of ticks.");
		int SimDur = in.nextInt();
		System.out.println("\nAnd finally, please input the span of the lanes until the intersection.");
		int span = in.nextInt();
		System.out.println("\n\nRunning Simulation\n\n");
		
/*Ebrbrbrbrbrbrbrbrbr Run the Simulation Ebrbrbrbrbrbrbrbrbrbrbrbr*/
		
		//Initialization
		RoadNetwork Network = new RoadNetwork(span, GDur, YDur, RDur);
		
		//Actually Running
		for(int tick = 0; tick < SimDur; tick++)
		{
			System.out.println("\n" +"Tick " +tick +"\n");
			//Reset the Flags
			Network.resetFlags();
			
			//Light Control
			Network.Lights();
			
			//Vehicle Movement
			double Turn = Math.random();
			boolean turning = false;
			if(Turn <= turnRate)
				turning = true;
			Network.moveWE(turning);
			
			Turn = Math.random();
			turning = false;
			if(Turn <= turnRate)
				turning = true;
			Network.moveEW(turning);
			
			Turn = Math.random();
			turning = false;
			if(Turn <= turnRate)
				turning = true;
			Network.moveNS(turning);
			
			Turn = Math.random();
			turning = false;
			if(Turn <= turnRate)
				turning = true;
			Network.moveSN(turning);
			
			//Take 'em out
			Network.removeWE(Completed, tick);
			Network.removeEW(Completed, tick);
			Network.removeNS(Completed, tick);
			Network.removeSN(Completed, tick);
			
			//Put 'em in
			double Enter = Math.random();
			if(Enter <= entRate)
				Network.enterWE(tick);
			
			Enter = Math.random();
			if(Enter <= entRate)
				Network.enterEW(tick);
			
			Enter = Math.random();
			if(Enter <= entRate)
				Network.enterNS(tick);
			
			Enter = Math.random();
			if(Enter <= entRate)
				Network.enterSN(tick);
			
			//Update
			AvgWait = 0;
			for(int i = 0; i < Completed.size(); i++)
			{
				AvgWait += (Completed.elementAt(i).getExit() - Completed.elementAt(i).getEntry());
			}
			if(Completed.size() > 0)
				AvgWait /= Completed.size();
			System.out.println();
			System.out.println("The average wait time is " +AvgWait);
			System.out.println("The flow rate for the WE lane is " +Network.FlowWE);
			System.out.println("The flow rate for the EW lane is " +Network.FlowEW);
			System.out.println("The flow rate for the NS lane is " +Network.FlowNS);
			System.out.println("The flow rate for the SN lane is " +Network.FlowSN);
			System.out.println("The total flow rate is " +Completed.size());
		}
		
		try
		{
			FileWriter fw = new FileWriter("output.txt", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			for(int i = 0; i < Completed.size(); i++)
			{
				pw.println(Completed.elementAt(i).toString() +"\n");
			}
			bw.flush();
			pw.close();
		}
		catch (IOException e)
		{
			System.out.println("Text errors");
		}
	}
}