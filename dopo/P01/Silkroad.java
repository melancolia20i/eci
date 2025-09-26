/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author	hever barrera batero ; juan diego patino munoz
 * @version	1
 *
 * Provides the starting point of the project, recall this project
 * must be run from BlueJ, which implies there's no main funcion.
 *
 * Do not confuse this file with 'Road', this class provides the
 * controls of the simulation
 */

public class Silkroad
{
	/**
	 * specifies the road's length given at the beginning
	 * of the execution
	 */
	public static int LENGTH;

	/**
	 * indicates if the last operation made was successfully
	 * completed
	 */
	private boolean ok;

	public Silkroad (final int length)
	{
		Silkroad.LENGTH = length;
		Road.createRoad();
	}

	public Silkroad (final int[][] days)
	{
	}

	public void changePage ()
	{
		final int pageno = Misc.changePageDialog();
		if ((pageno < 0) || (pageno >= Road.NOPAGES))
		{
			Misc.invalidPageNumberDialog(pageno);
			return;
		}
		Road.changePageVisual(pageno);
	}

	public void placeStore (final int location, final int tenges)
	{
		if (location < 0 || location >= LENGTH)
		{
			Misc.invalidLocationGivenViaBlueJDialogs(location);
			return;
		}

		this.ok = Road.placeStore(location, tenges);
		if (!this.ok)
		{
			Misc.invalidLocationToPlaceAStoreAt(location);
			return;
		}
	}

	public void removeStore (final int location)
	{
		if (location < 0 || location >= LENGTH)
		{
			Misc.invalidLocationGivenViaBlueJDialogs(location);
			return;
		}

		this.ok = Road.removeStore(location);
		if (!this.ok)
		{
			Misc.invalidLocationToRemoveAStoreAt(location);
			return;
		}
	}

}
