/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author	hever barrera batero ; juan diego patino munoz
 * @version	1
 *
 * Provides the class which implements the window and handles everything
 * related to the graphics (this code was modifed by us and refined by
 * ChatGPT in order to make it faster)
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Canvas extends JFrame
{
	private static Canvas    _host          = null;
	private static final int _winsz         = 500;
	private static final JProgressBar _pbar = new JProgressBar(0, 100);

	private List<Object> objs;
	private final Map<Object, ShapeDesc> shapes;
	private final Color                  bg;
	private final CanvasPane             canvas;
	private Graphics2D                   graphic;
	private Image                        img;

	public static Canvas getcanvas ()
	{
		if (_host == null)
		{
			_host = new Canvas(Misc.T0, _winsz, _winsz, SColor.sand.getcolor());
		}

		_host.setVisible(true);
		return _host;
	}

	private Canvas (final String title, final int width, final int height, final Color bg)
	{
		super(title);
		this.canvas = new CanvasPane();
		this.bg     = bg;
		this.objs   = new ArrayList<>();
		this.shapes = new HashMap<>();

		this.canvas.setPreferredSize(new Dimension(width, height));
		final JPanel root = new JPanel(new BorderLayout());

		root.add(this.canvas, BorderLayout.CENTER);
		_pbar.setValue(0);
		_pbar.setStringPainted(true);
		
		root.add(_pbar, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(root);
		pack();
	}

	@Override
	public void setVisible (final boolean state)
	{
		if (this.graphic == null)
		{
			final Dimension size =  this.canvas.getSize();
			this.img = this.canvas.createImage(size.width, size.height);
			this.graphic = (Graphics2D) this.img.getGraphics();

			this.graphic.setColor(bg);
			this.graphic.fillRect(0, 0, size.width, size.height);
			this.graphic.setColor(Color.BLACK);
		}
		super.setVisible(state);
	}

	public void draw (final Object ref, final SColor color, final Shape shape)
	{
		this.objs.remove(ref);
		this.objs.add(ref);
		this.shapes.put(ref, new ShapeDesc(shape, color));
		this.redraw();
	}

	public void erase (final Object ref)
	{
		this.objs.remove(ref);
		this.shapes.remove(ref);
		this.redraw();
	}

	public void setbackgroundcolor (final SColor color)
	{
		this.graphic.setColor(color.getcolor());
	}

	public void pause (final int ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch (Exception e)
		{
		}
	}

	private void redraw ()
	{
		this.clscanvas();
		for (Object obj: this.objs)
		{
			this.shapes.get(obj).draw(this.graphic);
		}
		this.canvas.repaint();
	}

	private void clscanvas ()
	{
		final Color c0 = this.graphic.getColor();
		final Dimension sz = this.canvas.getSize();

		this.graphic.setColor(this.bg);
		this.graphic.fill(new Rectangle(0, 0, sz.width, sz.height));

		this.graphic.setColor(c0);
	}

	private class CanvasPane extends JPanel
	{
		@Override
		protected void paintComponent (final Graphics g)
		{
			super.paintComponent(g);
			if (img != null)
			{
				g.drawImage(img, 0, 0, null);
			}
		}
	}

	private class ShapeDesc
	{
		private final Shape  shape;
		private final SColor color;

		public ShapeDesc (final Shape shape, final SColor color)
		{
			this.shape = shape;
			this.color = color;
		}

		private void draw (final Graphics2D g)
		{
			g.setColor(color.getcolor());
			g.draw(shape);
			g.fill(shape);
		}
	}
}
