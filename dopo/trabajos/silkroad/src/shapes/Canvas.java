package shapes;

import javax.swing.*;

import silkroad.Main;

import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Canvas extends JFrame
{
	/* Esta es una instancia de la misma clase la cual sera utilizada
	 * para tener una forma de acceder al unico canva que sera creado
	 * para todo el programa
	 * */
	private static Canvas host;	
	private final static int winsize = 500;
	
	public static Canvas getCanvas ()
	{
		if (host == null)
		{
			host = new Canvas("silkroad game", Canvas.winsize, Canvas.winsize + 10, ColorType.SAND.getColor());
		}	
		host.setVisible(true);
		return host;	
	}
	
	private JFrame       frame;
	private CanvasPane   canvas;
	private Graphics2D   graphic;
	private Color        bgColor;
	private Image        canvasImg;
	private List<Object> objs;
	
	private HashMap<Object, ShapeDescription> shapes;
	
	private Canvas (String title, int width, int height, Color bg)
	{
		this.frame = new JFrame();
		this.canvas = new CanvasPane();
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setTitle(title);
		
		this.canvas.setPreferredSize(new Dimension(width, height));	
		this.bgColor = bg;
		
		this.objs = new ArrayList<Object>();
		this.shapes = new HashMap<Object, ShapeDescription>();
		
		this.frame.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) { Main.getKeyPressed(e.getKeyChar()); }
			@Override public void keyReleased(KeyEvent e) {}
			@Override public void keyPressed(KeyEvent e) {}
		});
		
		JPanel root = new JPanel(new BorderLayout());
		root.add(this.canvas, BorderLayout.CENTER);
		
		JProgressBar bar = new JProgressBar(0, 100);
		bar.setValue(0);
		bar.setStringPainted(true);
		root.add(bar, BorderLayout.SOUTH);
		
		this.frame.setContentPane(root);
		this.pack();
	}
	
	public void setVisible (boolean visible)
	{
		if (graphic == null)
		{
			Dimension size = canvas.getSize();

			canvasImg = canvas.createImage(size.width, size.height);
			graphic   = (Graphics2D) canvasImg.getGraphics();
			
			graphic.setColor(bgColor);
			graphic.fillRect(0, 0, size.width, size.height);
			graphic.setColor(Color.black);
		}
		frame.setVisible(true);
	}
	
	public void draw (Object refobj, ColorType color, Shape shape)
	{
		objs.remove(refobj);
		objs.add(refobj);
		shapes.put(refobj, new ShapeDescription(shape, color));
		redraw();
	}
	
	public void erase (Object refobj)
	{
		objs.remove(refobj);
		shapes.remove(refobj);
		redraw();
	}
	
	public void setBkgColor (ColorType color)
	{
		graphic.setColor(color.getColor());	
	}
	
	public void wait (int ms)
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
		erase();
		for (Iterator<Object> i = objs.iterator(); i.hasNext(); )
		{
			shapes.get(i.next()).draw(graphic);
		}
		canvas.repaint();
	}
	
	private void erase ()
	{
		Color original = graphic.getColor();
		graphic.setColor(bgColor);

		Dimension sz = canvas.getSize();
		graphic.fill(new java.awt.Rectangle(0, 0, sz.width, sz.height));
		
		graphic.setColor(original);
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
		private ColorType color;
		
		public ShapeDescription (Shape shape, ColorType color)
		{
			this.shape = shape;
			this.color = color;
		}
		
		public void draw (Graphics2D g2d)
		{
			setBkgColor(this.color);
			g2d.draw(this.shape);
			g2d.fill(this.shape);
		}
	}
	
	public JFrame getFrame ()
	{
		return this.frame;
	}
}
