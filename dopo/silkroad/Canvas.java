/* esta clase nos permite tener una ventana sobre la cual dibujar; la
 * clase fue modificada con el fin de hacerla funcionar bajo las nuevas
 * condiciones
 *
 * @author  Hever Barrera ; Juan Diego Patino
 * @version 1
 */

import javax.swing.*;
import java.awt.*;

import java.util.List;
import java.util.HashMap;

import java.util.Iterator;
import java.util.ArrayList;

public class Canvas
{
	private static Canvas        _HOST  = null;
	private static JProgressBar  _PBAR  = null;
	private static int           _WINSZ = 500;

	public static int MS = 10;

	public static Canvas getCanvas ()
	{
		if (_HOST == null)
		{
			_HOST = new Canvas(Silkroad.TITLE, _WINSZ, _WINSZ, SColor.SAND.getColor());
		}

		_HOST.visibility(true);
		return _HOST;
	}

	public static void updateProgressbar (final int value)
	{
		_PBAR.setValue(value);
	}

	private JFrame                            frame;
	private CanvasPane                        canvas;
	private Graphics2D                        graphics;
	private Color                             bg;
	private Image                             canvasimg;
	private List<Object>                      objs;
	private HashMap<Object, ShapeDescription> shapes;

	private Canvas (final String title, final int width, final int height, final Color bg)
	{
		this.frame  = new JFrame();
		this.canvas = new CanvasPane();
		this.objs   = new ArrayList<>();
		this.shapes = new HashMap<Object, ShapeDescription>();

		_PBAR       = new JProgressBar(0, 100);
		this.bg     = bg;

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setTitle(title);
		this.canvas.setPreferredSize(new Dimension(width, height));

		final JPanel root = new JPanel(new BorderLayout());
		root.add(this.canvas, BorderLayout.CENTER);

		_PBAR.setValue(0);
		_PBAR.setStringPainted(true);

		root.add(_PBAR, BorderLayout.SOUTH);

		this.frame.setContentPane(root);
		this.frame.pack();
	}

	public void visibility (final boolean state)
	{
		if (this.graphics == null)
		{
			final Dimension size = this.canvas.getSize();
			this.canvasimg = this.canvas.createImage(size.width, size.height);
			this.graphics  = (Graphics2D) this.canvasimg.getGraphics();

			this.graphics.setColor(this.bg);
			this.graphics.fillRect(0, 0, size.width, size.height);
			this.graphics.setColor(Color.black);
		}
		this.frame.setVisible(state);
	}

	public void draw (final Object ref, final SColor color, final Shape shape)
	{
		this.objs.remove(ref);
		this.objs.add(ref);
		this.shapes.put(ref, new ShapeDescription(shape, color));
		this.redraw();
	}

	public void erase (final Object ref)
	{
		this.objs.remove(ref);
		this.shapes.remove(ref);
		this.redraw();
	}

	public void wait (int ms)
	{
		try { Thread.sleep(ms); } catch (Exception e) {}
	}


	private void setbgcolor (final SColor color)
	{
		this.graphics.setColor(color.getColor());
	}

	private void redraw ()
	{
		this.erase();
		for (Iterator<Object> i = objs.iterator(); i.hasNext(); )
		{
			this.shapes.get(i.next()).draw(this.graphics);
		}
		this.canvas.repaint();
	}

	private void erase ()
	{
		final Color original = this.graphics.getColor();
		this.graphics.setColor(this.bg);

		final Dimension size = this.canvas.getSize();
		this.graphics.fill(new java.awt.Rectangle(0, 0, size.width, size.height));

		this.graphics.setColor(original);
	}

	private class CanvasPane extends JPanel
	{
		public void paint (final Graphics g)
		{
			g.drawImage(canvasimg, 0, 0, null);
		}
	}

	private class ShapeDescription
	{
		private Shape  shape;
		private SColor color;

		public ShapeDescription (final Shape shape, final SColor color)
		{
			this.shape = shape;
			this.color = color;
		}

		private void draw (final Graphics2D g)
		{
			setbgcolor(this.color);
			g.draw(this.shape);
			g.fill(this.shape);
		}
	}
}
