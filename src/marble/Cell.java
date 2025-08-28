package marble;

import shapes.Rectangle;

public class Cell
{
	private int xpos;
	private int ypos;

	private boolean permissive;
	private String   onlyAccepts;
	
	private Rectangle floor;
	
	public Cell (int xpos, int ypos)
	{
		this.xpos = xpos;
		this.ypos = ypos;
		this.permissive  = true;
		this.onlyAccepts = null;
	}
	
	public void turnopermissive (String oaccepts, int cellsz)
	{	
		this.permissive = false;
		this.onlyAccepts = oaccepts;
		
		this.floor = new Rectangle(this.ypos * cellsz, this.xpos * cellsz, cellsz, cellsz, this.onlyAccepts);
		this.floor.makeVisible(true);
	}
	
	public int getx () { return this.xpos; }
	public int gety () { return this.ypos; }
	
	public boolean ampermissive () { return this.permissive; }
	public String onlyaccept () { return this.onlyAccepts; }
}
