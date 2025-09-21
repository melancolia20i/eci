/* represents a triangle drawable on the canvas.
 * A rectangle has position, size, color, and a
 * visibility state.
 *
 * @author  Hever Barrera ; Juan Diego Patiño
 * @version 0.2v
 */

import java.awt.*;

public class Triangle
{
	final private static int VERTICES = 3;

	private int     row;
	private int     col;
	private int     height;
	private int     width;
	private Colors  color;
	private boolean visible;

	/* @param row          y-coordinate of the triangle
	 * @param col          x-coordinate of the triangle
	 * @param height       height of the triangle
	 * @param width        width of the triangle
	 * @param color        color of the triangle
	 */
	public Triangle (final int row, final int col, final int height, final int width, final Colors color)
	{
		this.row     = row;
		this.col     = col;
		this.width   = width;
		this.height  = height;
		this.color   = color;
		this.visible = false;
	}

	/* toggles the visibility of the triangle
	 *
	 * @param status       true to show, false to hide
	 */
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

	/* draws the triangle if visible
	 */
	private void draw ()
	{
		if (!this.visible) { return; }
		Canva canvas = Canva.getCanva();

		int []xs = { this.col, this.col + (this.width  / 2), this.col - (this.width  / 2) };
		int []ys = { this.row, this.row + this.height      , this.row + this.height       };

		canvas.draw(this, this.color, new Polygon(xs, ys, VERTICES));
		canvas.wait(10);
	}

	/* erases the triangle from the canvas
	 */
	private void erase ()
	{
		if (!this.visible) { return; }
		Canva canvas = Canva.getCanva();
		canvas.erase(this);
	}
}

