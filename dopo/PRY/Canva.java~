/**
 * Write a description of class Canva here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.swing.*;

import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class Canva extends JFrame
{
	private static Canva host;
	private static int winsize = 500;

	public static Canva getCanva ()
	{
		if (host == null)
		{
			host = new Canva(Main.TITLE, Canva.winsize, Canva.winsize, Colors.SAND.getColor());
		}

		host.setVisible(true);
		return host;
	}

	private JFrame                            frame;
	private CanvasPane                        canvas;
	private Graphics2D                        graphic;
	private Color                             bg;
	private Image                             canvasImg;
	private List<Object>                      objs;
	private HashMap<Object, ShapeDescription> shapes;

	private static JProgressBar pbar;

	private Canva (final String title, final int width, final int height, final Color bg)
	{
		this.frame  = new JFrame();
		this.canvas = new CanvasPane();

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);

		this.canvas.setPreferredSize(new Dimension(width, height));
		this.bg = bg;

		this.objs = new ArrayList<Object>();
		this.shapes = new HashMap<Object, ShapeDescription>();

		JPanel root = new JPanel(new BorderLayout());
		root.add(this.canvas, BorderLayout.CENTER);

		pbar = new JProgressBar(0, 100);
		pbar.setValue(0);

		pbar.setStringPainted(true);
		root.add(pbar, BorderLayout.SOUTH);

		this.frame.setContentPane(root);
		this.frame.pack();
	}

	public void setVisible (final boolean state)
	{
		if (this.graphic == null)
		{
			Dimension size = this.canvas.getSize();
			this.canvasImg = this.canvas.createImage(size.width, size.height);
			this.graphic   = (Graphics2D) this.canvasImg.getGraphics();

			this.graphic.setColor(this.bg);
			this.graphic.fillRect(0, 0, size.width, size.height);
			this.graphic.setColor(Color.black);
		}
		this.frame.setVisible(true);
	}

	public void draw (final Object refObj, final Colors color, final Shape shape)
	{
		this.objs.remove(refObj);
		this.objs.add(refObj);
		this.shapes.put(refObj, new ShapeDescription(shape, color));
		redraw();
	}

	public void erase (final Object refObj)
	{
		this.objs.remove(refObj);
		this.shapes.remove(refObj);
		redraw();
	}

	public void setbgcolor (final Colors color)
	{
		this.graphic.setColor(color.getColor());
	}

	public void wait (final int ms)
	{
		try { Thread.sleep(ms); } catch (Exception e) {}
	}

	private void redraw ()
	{
		erase();
		for (Iterator<Object> i = objs.iterator(); i.hasNext(); )
		{
			this.shapes.get(i.next()).draw(this.graphic);
		}
		this.canvas.repaint();
	}

	private void erase ()
	{
		Color original = this.graphic.getColor();
		this.graphic.setColor(this.bg);

		Dimension sz = this.canvas.getSize();
		this.graphic.fill(new java.awt.Rectangle(0, 0, sz.width, sz.height));

		this.graphic.setColor(original);
	}

	private class CanvasPane extends JPanel
	{
		public void paint (Graphics g)
		{
			g.drawImage(canvasImg, 0, 0, null);
		}
	}

	private class ShapeDescription
	{
		private Shape shape;
		private Colors color;

		public ShapeDescription (final Shape shape, final Colors color)
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

	public static void updateprogressbar (final int to)
	{
		pbar.setValue(to);
	}
}
