/**
 * Write a description of class Rectangle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Rectangle
{
	private int     row;
	private int     col;
	private int     height;
	private int     width;
	private Colors  color;
	private boolean visible;

	public Rectangle (final int row, final int col, final int height, final int width, final Colors color)
	{
		this.row     = row;
		this.col     = col;
		this.width   = width;
		this.height  = height;
		this.color   = color;
		this.visible = false;
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

	private void draw ()
	{
		if (!this.visible) { return; }
		Canva canvas = Canva.getCanva();
		canvas.draw(this, this.color, new java.awt.Rectangle(this.col, this.row, this.width, this.height));
		canvas.wait(10);
	}

	private void erase ()
	{
		if (!this.visible) { return; }
		Canva canvas = Canva.getCanva();
		canvas.erase(this);
	}
}
