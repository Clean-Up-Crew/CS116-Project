package Traffic.RoadNetwork;
import Traffic.RoadNetwork.BlockType;
import Traffic.RoadNetwork.Block;

public class TrafficBlock extends Block
{

	private int Color;//0 is Green, 1 is Yellow, 2 is Red
	private TrafficLight Light;
	
	//Pretty Basic Constructor
	public TrafficBlock(int type, int n, int G, int Y, int R, int state)
	{
		super(type, n);
		BlockType typeB = BlockType.valueOf("BLOCK_TRAFFIC");
		Light = new TrafficLight(G, Y, R, state);
		Color = state;
	}
	
	//Light Change
	public void LightChange()
	{
		Light.LightChange();
		Color = Light.getLight();
	}
	
	//Turn
	public void Turn(Block otherLane)
	{
		if(this.vehicle != null && this.ProcessedFlag == false)
		{
			if(Next.getAuto() == null && otherLane.getAuto() == null && otherLane.getNext().getAuto() == null)
			{
				otherLane.getNext().setAuto(vehicle);
				this.setAuto(null);
				this.setFlag(true);
				Next.setFlag(true);
				otherLane.getNext().setFlag(true);
			}
		}
	}
	
	//MoveForward
	public void MoveForward()
	{
		if(this.vehicle != null && this.ProcessedFlag == false)
		{
			if(Color == 0 || Color == 1)
			{
				if(Next.getAuto() == null)
				{
					Next.setAuto(this.vehicle);
					this.setAuto(null);
					this.setFlag(true);
					Next.setFlag(true);
				}
			}
		}
	}
	
	//Set Neighbors
	public void setNeighbors(Block[] neighbors)
	{
		if(BlockNo == 0)
		{
			Prev = null;
			Next = neighbors[BlockNo+1];
		}
		else if(BlockNo == neighbors.length-1)
		{
			Prev = neighbors[BlockNo-1];
			Next = null;
		}
		else
		{
			Prev = neighbors[BlockNo-1];
			Next = neighbors[BlockNo+1];
		}
	}
	
	//Get Color
	public int getColor()
	{
		return Color;
	}
}