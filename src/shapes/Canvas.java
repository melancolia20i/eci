package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class Canvas
{
	/* Esta es una instancia de la misma clase la cual sera utilizada
	 * para tener una forma de acceder al unico canva que sera creado
	 * para todo el programa
	 * */
	private static Canvas host;	
	public  static int winsize = 120;
	
	public static Canvas getCanvas ()
	{
		if (host == null)
		{
			host = new Canvas("marble game", Canvas.winsize, Canvas.winsize, Color.white);
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
		frame  = new JFrame();
		canvas = new CanvasPane();
		
		frame.setContentPane(canvas);
		frame.setTitle(title);
		
		canvas.setPreferredSize(new Dimension(width, height));
		bgColor = bg;
		
		frame.pack();
		objs   = new ArrayList<Object>();
		shapes = new HashMap<Object, ShapeDescription>();
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
	
	public void draw (Object refobj, String color, Shape shape)
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
	
	public void setForegroundColor (String color)
	{
		      if (color.equals("red"))     { graphic.setColor(Color.red);     }
		else if (color.equals("black"))   { graphic.setColor(Color.black);   }
		else if (color.equals("blue"))    { graphic.setColor(Color.blue);    }
		else if (color.equals("yellow"))  { graphic.setColor(Color.yellow);  }
		else if (color.equals("green"))   { graphic.setColor(Color.green);   }
		else if (color.equals("magenta")) { graphic.setColor(Color.magenta); }
		else if (color.equals("white"))   { graphic.setColor(Color.white);   }
		else                              { graphic.setColor(Color.black);   }
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
		for (Iterator i = objs.iterator(); i.hasNext(); )
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
		private String colorStr;
		
		public ShapeDescription (Shape shape, String color)
		{
			this.shape = shape;
			colorStr = color;
		}
		
		public void draw (Graphics2D g2d)
		{
			setForegroundColor(colorStr);
			g2d.draw(shape);
			g2d.fill(shape);
		}
	}
}
