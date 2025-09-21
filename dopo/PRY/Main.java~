/**
 * This class only takes tne road length and checks its value, then it calls
 * the real backend of the project (this is the starting point)
 *
 * @author Hever Barrera ; Juan Diego Patiño
 * @version 0.2v
 */

import javax.swing.JOptionPane;

public class Main
{
	final public static String TITLE = "silkroad";

	private int roadLength;

	public Main () { this.init(); }

	/**
	 * This method inits all what we need in order to run
	 * the project
	 *
	 * @param     nothing
	 * @return    nothing
	 */
	private void init ()
	{
		this.roadLength = Integer.parseInt(JOptionPane.showInputDialog(null, "road length: ", TITLE, JOptionPane.INFORMATION_MESSAGE));
		if (this.roadLength <= 0)
		{
			JOptionPane.showMessageDialog(null, "please provide an actual length", TITLE, JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		new Silkroad(this.roadLength);
	}
}
