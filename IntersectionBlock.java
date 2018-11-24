package Traffic.RoadNetwork;
import Traffic.RoadNetwork.BlockType;
import Traffic.RoadNetwork.Block;

public class IntersectionBlock extends Block
{
	//Pretty Basic Constructor
	public IntersectionBlock(int type, int n)
	{
		super(type, n);
		BlockType typeB = BlockType.valueOf("BLOCK_INTERSECT");
	}
	
	//Turn
	public void Turn(Block otherLane)
	{
		if(this.vehicle != null && this.ProcessedFlag == false)
		{
			if(otherLane.getAuto() == null && otherLane.getNext().getAuto() == null)
			{
				otherLane.getNext().setAuto(vehicle);
				this.setAuto(null);
				this.setFlag(true);
				otherLane.getNext().setFlag(true);
			}
		}
	}
	
	public void LightChange(){}
	public int getColor()
	{
		return 0;
	}
	
	//MoveForward
	public void MoveForward()
	{
		if(this.vehicle != null && this.ProcessedFlag == false)
		{
			if(this.getNext().getAuto() == null)
			{
				Next.setAuto(this.vehicle);
				this.setAuto(null);
				Next.setFlag(true);
				this.setFlag(true);
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
}