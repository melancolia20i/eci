package shapes;

import java.awt.*;
import java.awt.geom.*;

public class Circle
{
	public static final double PI =  3.141592654;
	
	private int diameter;
	private int col;
	private int row;
	private ColorType color;
	private boolean visible;
	
	public Circle (int row, int col, int dia, ColorType color)
	{
		this.col      = col;
		this.row      = row;
		this.color    = color;
		this.visible  = false;
		this.diameter = dia;
	}
	
	public void makeVisible (boolean show)
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
		canvas.draw(this, color, new Ellipse2D.Double(this.col, this.row, this.diameter, this.diameter));
		canvas.wait(10);
	}
	
	private void erase ()
	{
		if (this.visible == false) { return; }
		Canvas canvas = Canvas.getCanvas();
		canvas.erase(this);
	}
	
	public void update_position (int row, int col)
	{
		erase();
		this.col = col;
		this.row = row;
		draw();
	}
	
	public ColorType getColor ()
	{
		return this.color;
	}	
}
