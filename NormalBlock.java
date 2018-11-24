package Traffic.RoadNetwork;
import Traffic.RoadNetwork.BlockType;
import Traffic.RoadNetwork.Block;

public class NormalBlock extends Block
{
	//Pretty Basic Constructor
	public NormalBlock(int type, int n)
	{
		super(type, n);
		BlockType typeB = BlockType.valueOf("BLOCK_NORMAL");
	}
	
	//MoveForward
	public void MoveForward()
	{
		if(this.vehicle != null && ProcessedFlag == false)
		{
			if(this.getNext().getAuto() == null)
			{
				Next.setAuto(this.vehicle);
				this.setAuto(null);
				this.setFlag(true);
				Next.setFlag(true);
			}
		}
	}
	
	public void LightChange(){}
	public void Turn(Block otherLane){}
	public int getColor()
	{
		return 0;
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