package shapes;

import java.awt.*;

public class Rectangle
{
	public static int EDGES = 4;
	
	private int xpos;
	private int ypos;
	
	private int height;
	private int width;
	
	private String color;
	private boolean visible;
	
	public Rectangle (int xpos, int ypos, int height, int width, String color)
	{	
		this.xpos    = xpos;
		this.ypos    = ypos;
		this.height  = height;
		this.width   =  width;
		this.color   = color;
		this.visible = false;
	}
	
	public void makeVisible (boolean show)
	{
		if (show == true)
		{
			this.visible = true;
			this.draw();
		}
		else
		{
			this.erase();
			this.visible = false;
		}
	}
	
	private void draw ()
	{
		if (this.visible == false) { return; }
		Canvas canvas = Canvas.getCanvas();
		canvas.draw(this, color, new java.awt.Rectangle(this.xpos, this.ypos, this.width, this.height));
		canvas.wait(10);
	}
	
	private void erase ()
	{
		if (this.visible == false) { return; }
		Canvas canvas = Canvas.getCanvas();
		canvas.erase(this);
	}
}
