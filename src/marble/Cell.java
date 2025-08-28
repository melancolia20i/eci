package marble;

public class Cell
{
	private int xpos, ypos;
	private boolean permissive;
	private String onlyAccepts;
	
	public Cell (int xpos, int ypos)
	{
		this.xpos = xpos;
		this.ypos = ypos;
		this.permissive  = true;
		this.onlyAccepts = null;
	}
	
	public void turnopermissive (String oaccepts)
	{	
		this.permissive = false;
		this.onlyAccepts = oaccepts;
	}
	
	public int getx () { return this.xpos; }
	public int gety () { return this.ypos; }
	
	public boolean ampermissive () { return this.permissive; }
	public String onlyaccept () { return this.onlyAccepts; }
}
