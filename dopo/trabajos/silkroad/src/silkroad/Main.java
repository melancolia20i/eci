package silkroad;

import javax.swing.JOptionPane;

public class Main
{
	private static Silkroad silk;
	private static int length;
	
	private static String locationBoundsMsg;
	
	public static void main (String []args)
	{
		// TODO: change this
		length = 17;
		
		if (length > Silkroad.maxRoadLength)
		{	
		}

		displayControls();
		locationBoundsMsg = "location [1, " + length + "]";
		silk = new Silkroad(length);
	}
	
	public static void getKeyPressed (char key)
	{
		switch (key)
		{
			case 's': placeStore(); break;
			case 'd': deleteStore(); break;
		}
	}
	
	private static void displayControls ()
	{
		System.out.println("silkroad - controls");
		System.out.println("  s: place a new store");
		System.out.println("  d: delete a store");
		System.out.println("  r: place a new robot");
		System.out.println("  e: erase a robot");
		System.out.println("  m: move a robot");
		System.out.println("  R: resupply stores");
		System.out.println("  b: return robots");
		System.out.println("  x: reboot");
		System.out.println("  p: profit");
		System.out.println("  L: list stores");
		System.out.println("  l: list robots");
		System.out.println("  V: make visible");
		System.out.println("  I: make invisible");
		System.out.println("  f: finish");
		System.out.println("  o: ok");
	}
	
	private static void placeStore ()
	{
		final String title = "placing a new store";

		int location = Integer.parseInt(JOptionPane.showInputDialog(null, locationBoundsMsg, title, JOptionPane.INFORMATION_MESSAGE));
		int tenges   = Integer.parseInt(JOptionPane.showInputDialog(null, "tenges [1, ]", title, JOptionPane.INFORMATION_MESSAGE));

		if (location > length || location == 0)
		{
			JOptionPane.showMessageDialog(null, "please provide an actual location", title, JOptionPane.ERROR_MESSAGE);
			return;
		}	
		if (tenges <= 0)
		{
			JOptionPane.showMessageDialog(null, "please provide an actual amount of money", title, JOptionPane.ERROR_MESSAGE);
			return;
		}	
		silk.placeStore(location - 1, tenges);
	}
	
	private static void deleteStore ()
	{
		final String title = "deleting a store";
		int location = Integer.parseInt(JOptionPane.showInputDialog(null, locationBoundsMsg, title, JOptionPane.INFORMATION_MESSAGE));		

		if (location > length || location == 0)
		{
			JOptionPane.showMessageDialog(null, "please provide an actual location", title, JOptionPane.ERROR_MESSAGE);
			return;
		}
		silk.removeStore(location - 1);
	}
}
