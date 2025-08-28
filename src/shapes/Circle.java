package shapes;

import java.awt.*;
import java.awt.geom.*;

public class Circle
{
	public static final double PI =  3.141592654;
	
	private int diameter;
	private int xpos;
	private int ypos;
	private String color;
	private boolean visible;
	
	public Circle (int xpos, int ypos, int dia, String color)
	{
		this.xpos     = xpos;
		this.ypos     = ypos;
		this.color    = color;
		this.visible  = false;
		this.diameter = dia;
	}
	
	public void show (boolean show)
	{
		if (show)
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
		canvas.draw(this, color, new Ellipse2D.Double(this.xpos, this.ypos, this.diameter, this.diameter));
		canvas.wait(10);
	}
	
	private void erase ()
	{
		if (this.visible == false) { return; }
		Canvas canvas = Canvas.getCanvas();
		canvas.erase(this);
	}	
}
