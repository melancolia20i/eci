/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author	hever barrera batero ; juan diego patino munoz
 * @version	1
 *
 * Provides a class which draws and handles a circle which is used to
 * represent a robot
 */

public class Circle
{
	private final int     diameter;
	private final SColor  color;
	/**
	 * these two cannot be final as in the other shapes since
	 * a circle represents a robot, which by definition can
	 * change its position
	 */
	private int           pxrow;
	private int           pxcol;
	private boolean       visible;

	public Circle (final int pxrow, final int pxcol, final int diameter, final SColor color)
	{
		this.diameter = diameter;
		this.pxcol    = pxcol;
		this.pxrow    = pxrow;
		this.color    = color;
		this.visible  = false;
	}

	public void changevisibility (final boolean state)
	{
		if (state)
		{
			this.visible = true;
			this.draw();
			return;
		}
		this.erase();
		this.visible = false;
	}

	public void changeposition (final boolean show, final int newpxrow, final int newpxcol)
	{
		this.erase();
		this.pxrow = newpxrow;
		this.pxcol = newpxcol;

		if (show)
		{
			this.draw();
		}
	}

	private void draw ()
	{
		if (!this.visible) { return; }
		final Canvas canvas = Canvas.getcanvas();
		canvas.draw(this, this.color, new java.awt.geom.Ellipse2D.Double(this.pxcol, this.pxrow, this.diameter, this.diameter));
		canvas.pause(Misc.RENDERMS);
	}

	private void erase ()
	{
		if (!this.visible) { return; }
		final Canvas canvas = Canvas.getcanvas(); 
		canvas.erase(this);
	}
}
