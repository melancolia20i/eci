/* esta clase implementa el concepto de un rectangulo
 * dentro del canvas
 *
 * @author  Hever Barrera ; Juan Diego Patino
 * @version 1
 */

public class Rectangle
{
	private int     row;
	private int     col;
	private int     height;
	private int     width;
	private SColor  color;
	private boolean visible;

	public Rectangle (final int row, final int col, final int height, final int width, final SColor color)
	{
		this.row     = row;
		this.col     = col;
		this.height  = height;
		this.width   = width;
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
		if (!this.visible)
		{
			return;
		}

		final Canvas canvas = Canvas.getCanvas();
		canvas.draw(this, this.color, new java.awt.Rectangle(this.col, this.row, this.width, this.height));
		canvas.wait(Canvas.MS);
	}

	private void erase ()
	{
		if (!this.visible)
		{
			return;
		}
		final Canvas canvas = Canvas.getCanvas();
		canvas.erase(this);
	}
}
