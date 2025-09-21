/* represents a circle that can be drawn on the canvas.
 * Circles have a position (row, col), diameter, color,
 * and a visibility state.
 *
 * @author  Hever Barrera ; Juan Diego Patiño
 * @version 0.2v
 */

import java.awt.*;
import java.awt.geom.*;

public class Circle {
	private int     diameter;
	private int     col;
	private int     row;
	private Colors  color;
	private boolean visible;

	/* creates a circle object with initial position, diameter and color.
	 *
	 * @param row          y coordinate of the circle
	 * @param col          x coordinate of the circle
	 * @param diameter     diameter of the circle
	 * @param color        color of the circle
	 */
	public Circle(final int row, final int col, final int diameter, final Colors color)
	{
		this.diameter = diameter;
		this.row      = row;
		this.col      = col;
		this.color    = color;
		this.visible  = false;
	}

	/* changes the visibility of the circle.
	 *
	 * @param status true to show, false to hide
	 */
	public void changeVisibility(final boolean status)
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

	/* updates the circle position on the canvas.
	 *
	 * @param newrow       new y coordinate
	 * @param newcol       new x coordinate
	 */
	public void updatePosition (final int newrow, final int newcol)
	{
		this.erase();
		this.row = newrow;
		this.col = newcol;
		this.draw();
	}

	/* draws the circle on the canvas, if visible.
	 */
	private void draw ()
	{
		if (!this.visible) { return; }
		Canva canva = Canva.getCanva();
		canva.draw(this, this.color,
				new Ellipse2D.Double(this.col, this.row, this.diameter, this.diameter));
		canva.wait(10);
	}

	/* erases the circle from the canvas, if visible.
	 */
	private void erase ()
	{
		if (!this.visible) { return; }
		Canva canva = Canva.getCanva();
		canva.erase(this);
	}
}

