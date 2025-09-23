/* esta clase implementa el concepto de un circulo
 * dentro del canvas
 *
 * @author  Hever Barrera ; Juan Diego Patino
 * @version 1
 */

public class Triangle
{
	private int     row;
	private int     col;
	private int     height;
	private int     width;
	private SColor  color;
	private boolean visible;

	public Triangle (final int row, final int col, final int height, final int width, final SColor color)
	{
		this.row     = row;
		this.col     = col;
		this.width   = width;
		this.height  = height;
		this.color   = color;
		this.visible = false;
	}

	public void visibility (final boolean state)
	{
		if (this.visible == state)
		{
			return;
		}
		if (state)
		{
			this.visible = true;
			this.draw();
			return;
		}
		this.erase();
		this.visible = false;
	}

	private void draw ()
	{
		if (!this.visible) { return; }
		final Canvas canvas = Canvas.getCanvas();

		int []xs = { this.col, this.col + (this.width  / 2), this.col - (this.width  / 2) };
		int []ys = { this.row, this.row + this.height      , this.row + this.height       };

		canvas.draw(this, this.color, new java.awt.Polygon(xs, ys, 3));
		canvas.wait(10);
	}

	private void erase ()
	{
		if (!this.visible) { return; }
		final Canvas canvas = Canvas.getCanvas();
		canvas.erase(this);
	}

}
