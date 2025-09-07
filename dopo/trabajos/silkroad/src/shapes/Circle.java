package shapes;

import java.awt.*;
import java.awt.geom.*;

public class Circle
{
	public static final double PI =  3.141592654;
	
	private int diameter;
	private int col;
	private int row;
	private ColorType color;
	private boolean visible;
	
	/* Estas variables hacen alusion a la posicion dentro
	 * de la tabla de referencia
	 * */
	private int t_row;
	private int t_col;
	
	public Circle (int row, int col, int dia, ColorType color, int t_row, int t_col)
	{
		this.col      = col;
		this.row      = row;
		this.color    = color;
		this.visible  = false;
		this.diameter = dia;
		this.t_row    = t_row;
		this.t_col    = t_col;
	}
	
	public void makeVisible (boolean show)
	{
		if (show)
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
		if (this.visible == false) { return; }
		Canvas canvas = Canvas.getCanvas();
		canvas.draw(this, color, new Ellipse2D.Double(this.col, this.row, this.diameter, this.diameter));
		canvas.wait(10);
	}
	
	private void erase ()
	{
		if (this.visible == false) { return; }
		Canvas canvas = Canvas.getCanvas();
		canvas.erase(this);
	}
	
	
	public int get_table_row () { return this.t_row; }
	public int get_table_col () { return this.t_col; }
	
	public void update_position (int row, int col)
	{
		erase();
		this.col = col;
		this.row = row;
		draw();
	}
	
	public ColorType getColor ()
	{
		return this.color;
	}

	public void set_table_row (int val) { this.t_row = val; }
	public void set_table_col (int val) { this.t_col = val; }
	
	public void i_am_already_paired ()
	{
		erase();
		this.visible = false;
	}
	
	public boolean alreadySolved ()
	{
		return this.visible == false;
	}
}
