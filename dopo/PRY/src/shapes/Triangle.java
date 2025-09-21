package shapes;

import java.awt.*;

public class Triangle
{
	private int VERTICES = 3;

	private int row;
	private int col;

	private int height;
	private int width;

	private ColorType color;
	private boolean visible;

	public Triangle (int row, int col, int height, int width, ColorType color)
	{
		this.row     = row;
		this.col     = col;
		this.height  = height;
		this.width   = width;
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

	private void draw()
	{
		if (this.visible == false) { return; }

		Canvas canvas = Canvas.getCanvas();
		int[] xpoints = { this.col, this.col + (width/2), this.col - (width/2) };
		int[] ypoints = { this.row, this.row + height, this.row + height };
		canvas.draw(this, color, new Polygon(xpoints, ypoints, this.VERTICES));
		canvas.wait(10);
	}

	private void erase()
	{
		if (this.visible == false) { return; }
		Canvas canvas = Canvas.getCanvas();
		canvas.erase(this);
	}
}
