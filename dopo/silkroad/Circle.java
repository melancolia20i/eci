/* esta clase implementa el concepto de un circulo
 * dentro del canvas
 *
 * @author  Hever Barrera ; Juan Diego Patino
 * @version 1
 */

public class Circle
{
	private int     diameter;
	private int     row;
	private int     col;
	private SColor  color;
	private boolean visible;

	public Circle (final int row, final int col, final int diameter, final SColor color)
	{
		this.row      = row;
		this.col      = col;
		this.diameter = diameter;
		this.color    = color;
		this.visible  = false;
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

	/* dado que los robots son dibujados como circulos, necesitamos ser capaces de
	 * moverlos por el mapa
	 *
	 * @param row         nueva fila
	 * @param col         nueva columna
	 * @param show        true si se quiere dibujar, false si no
	 */
	public void changePosition (final int row, final int col, final boolean show)
	{
		this.erase();
		this.row = row;
		this.col = col;

		if (show)
		{
			this.visible = true;
			this.draw();
		}
	}

	private void draw ()
	{
		if (!this.visible)
		{
			return;
		}

		final Canvas canvas = Canvas.getCanvas();
		canvas.draw(this, this.color, new java.awt.geom.Ellipse2D.Double(this.col, this.row, this.diameter, this.diameter));
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
