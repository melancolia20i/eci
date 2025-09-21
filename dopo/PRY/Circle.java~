/**
 * Write a description of class circle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.geom.*;

public class Circle
{
	private int     diameter;
	private int     col;
	private int     row;
	private Colors  color;
	private boolean visible;

	public Circle (final int row, final int col, final int diameter, final Colors color)
	{
		this.diameter = diameter;
		this.row      = row;
		this.col      = col;
		this.color    = color;
		this.visible  = false;
	}

	public void changeVisibility (final boolean status)
	{
		if (status)
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

	public void updatePosition (final int newrow, final int newcol)
	{
		this.erase();
		this.row = row;
		this.col = col;
		this.draw();
	}

	private void draw ()
	{
		if (!this.visible) { return; }
		Canva canva = Canva.getCanva();
		canva.draw(this, this.color, new Ellipse2D.Double(this.col, this.row, this.diameter, this.diameter));
		canva.wait(10);
	}

	private void erase ()
	{
		if (!this.visible) { return; }
		Canva canva = Canva.getCanva();
		canva.erase(this);
	}
}
