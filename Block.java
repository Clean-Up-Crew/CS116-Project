package Traffic.RoadNetwork;//change as needed
import Traffic.RoadNetwork.BlockType;

abstract class Block
{		
	protected BlockType type; //0:normal,1:intersection,2:traffic-light
	protected int BlockNo;
	protected Auto vehicle; // object of the vehicle occupying the block
	protected Block Next; // the next block on the lane
	protected Block Prev; // previous block on the lane
	protected boolean ProcessedFlag;//flag for indicate whether the block has been processed during current tick of the simulation
	
	Block(int t, int no)
	{
		String value = "";
		if(t == 0)
			value = "BLOCK_NORMAL";
		else if(t == 1)
			value = "BLOCK_INTERSECT";
		else
			value = "BLOCK_TRAFFIC";
		
		this.setType(BlockType.valueOf(value));
		this.setBlockNo(no);
	}
	public BlockType getType()
	{
		return this.type;
	}
	public void setType(BlockType t)
	{
		this.type=t;
	}
	public int getBlockNo()
	{
		return this.BlockNo;
	}
	public void setBlockNo(int posNo)
	{
		this.BlockNo=posNo;
	}
	public Auto getAuto()
	{
		return this.vehicle;
	}
	public void setAuto(Auto v)
	{
		this.vehicle=v;
	}
	public Block getNext()
	{
		return this.Next;
	}
	public void setNext(Block nextBlock)
	{
		this.Next=nextBlock;			
	}
	public Block getPrev()
	{
		return this.Prev;
	}
	public void setPrev(Block prevBlock)
	{
		this.Prev=prevBlock;			
	}
	public boolean getFlag()
	{
		return this.ProcessedFlag;
	}
	public void setFlag(boolean newFlag)
	{
		this.ProcessedFlag = newFlag;
	}
	public abstract void LightChange();
	public abstract int getColor();
	public abstract void Turn(Block otherLane);
	public abstract void setNeighbors(Block[] neighhors);//Initialization to set the road network
	public abstract void MoveForward();//method to move the vehicle to the next place in the road
}