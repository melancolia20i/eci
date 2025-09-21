/* this class is only meant to be used if BlueJ is not used, even though
 * it wouldn't work since the program does provide controls to make the
 * game work
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

	/* this method inits all what we need in order to run
	 * the project
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
