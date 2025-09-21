/* represents a rectangle drawable on the canvas.
 * A rectangle has position, size, color, and a
 * visibility state.
 *
 * @author  Hever Barrera ; Juan Diego Patiño
 * @version 0.2v
 */

public class Rectangle
{
	private int     row;
	private int     col;
	private int     height;
	private int     width;
	private Colors  color;
	private boolean visible;

	/* @param row          y coordinate
	 * @param col          x coordinate
	 * @param height       height of the rectangle
	 * @param width        width of the rectangle
	 * @param color        color of the rectangle
	 */
	public Rectangle (final int row, final int col, final int height, final int width, final Colors color)
	{
		this.row     = row;
		this.col     = col;
		this.width   = width;
		this.height  = height;
		this.color   = color;
		this.visible = false;
	}

	/* changes the visibility of the rectangle.
	 *
	 * @param              status true to show, false to hide
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

	/* draws the rectangle on the canvas if visible.
	 */
	private void draw ()
	{
		if (!this.visible) { return; }
		Canva canvas = Canva.getCanva();
		canvas.draw(this, this.color, new java.awt.Rectangle(this.col, this.row, this.width, this.height));
		canvas.wait(10);
	}

	/* erases the rectangle from the canvas if visible.
	 */
	private void erase ()
	{
		if (!this.visible) { return; }
		Canva canvas = Canva.getCanva();
		canvas.erase(this);
	}
}

