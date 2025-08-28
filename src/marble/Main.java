package marble;

import shapes.*;

public class Main
{
	public static void main(String[] args)
	{
		Circle  c1 = new Circle(0, 0, 10, "blue");
		c1.makeVisible(true);

		Circle  c2 = new Circle(10, 10, 10, "green");
		c2.makeVisible(true);
		
		Rectangle r1 = new Rectangle(40, 40, 40, 40, "yellow");
		r1.makeVisible(true);
	}
}