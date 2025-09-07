package silkroad;
import shapes.*;

public class Main
{
	public static void main (String []args)
	{
		Canvas.winsize = 200;

		new Circle(0, 0, 100, shapes.ColorType.RED, 0, 0).makeVisible(true);
	}
	
	public static void getKeyPressed (char key)
	{
		
	}
}
