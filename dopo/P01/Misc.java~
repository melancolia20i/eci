/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author	hever barrera batero ; juan diego patino munoz
 * @version	1
 *
 * Provides a class which stores global information (mostly window titles) in
 * order to keep all classes clean
 */

import javax.swing.JOptionPane;

public class Misc
{
	public static final String T0       = "SilkRoad - ICPC's J problem simulator";
	public static final int    RENDERMS = 10;


	public static int changePageDialog ()
	{	
		return Integer.parseInt(JOptionPane.showInputDialog(
			null,
			String.format("page [0 - %d): ", Road.NOPAGES),
			String.format("%s: changing page (current: %d)", T0, Road.CURPAGE),
			JOptionPane.INFORMATION_MESSAGE
		));
	}

	public static void invalidPageNumberDialog (final int given)
	{
		JOptionPane.showMessageDialog(
			null,
			String.format("invalid page number (%d)", given),
			T0,
			JOptionPane.ERROR_MESSAGE
		);
	}

	public static void invalidLocationGivenViaBlueJDialogs (final int location)
	{
		JOptionPane.showMessageDialog(
			null,
			String.format("invalid location provided (%d is not between [0, %d])", location, Silkroad.LENGTH - 1),
			T0,
			JOptionPane.ERROR_MESSAGE
		);
	}

	public static void invalidLocationToPlaceAStoreAt (final int location)
	{
		JOptionPane.showMessageDialog(
			null,
			String.format("cannot place a store there! there's already one store at location %d", location),
			T0,
			JOptionPane.ERROR_MESSAGE
		);
	}

	public static void invalidLocationToRemoveAStoreAt (final int location)
	{
		JOptionPane.showMessageDialog(
			null,
			String.format("cannot remove a store there! there's no store at location %d", location),
			T0,
			JOptionPane.ERROR_MESSAGE
		);
	}
}
