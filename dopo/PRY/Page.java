/* represents a page of the road.
 * A page contains chunks, STORES and ROBOTS
 * associated with a specific PageOrientation.
 *
 * @author  Hever Barrera ; Juan Diego Patiño
 * @version 0.2
 */

import javax.swing.JOptionPane;


public class Page
{
	private static Store   [] STORES;
	private static Robot   [] ROBOTS;
	private static boolean [] SPAWNS;
	private static int        TLENGTH;

	public static void init (final int totalLength)
	{
		STORES = new Store   [totalLength];
		ROBOTS = new Robot   [totalLength];
		SPAWNS = new boolean [totalLength];

		for (int i = 0; i < totalLength; i++)
		{
			STORES[i]      = null;
			ROBOTS[i]      = null;
			SPAWNS[i] = false;
		}

		TLENGTH = totalLength;
	}

	private int             length;
	private int             pageNo;
	private PageOrientation orientation;
	private boolean []      visibleChunks;


	public Page (final int length, final PageOrientation orientation, final int pageNo)
	{
		this.length        = length;
		this.pageNo        = pageNo;
		this.orientation   = orientation;
		this.visibleChunks = new boolean[Chunk.maxPerPage];

		for (int i = orientation.getFrom(), j = 0; i != orientation.getUntil() && j < length; i += orientation.getChange())
		{
			this.visibleChunks[i] = true;
			j++;
		}
	}

	public void changeObjsVisibility (final boolean state)
	{
		final int run = this.pageNo * Chunk.maxPerPage;
		for (int i = run; i < run + this.length; i++)
		{
			if (STORES[i] != null)
			{
				STORES[i].changeVisibility(this.isLocWithinThisPage(i));
			}
			if (ROBOTS[i] != null)
			{
				ROBOTS[i].changeVisibility(this.isLocWithinThisPage(i));
			}
		}
	}

	public void placeStore (final int tenges, final int location)
	{
		if (!this.checkbounds(location))
		{
			return;
		}

		if (STORES[location] != null)
		{
			JOptionPane.showMessageDialog(null, "there's a store already", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		STORES[location] = new Store(tenges, this.orientation.getObjLoc(location), this.isLocWithinThisPage(location));
	}

	public void removeStore (final int location)
	{
		if (!this.checkbounds(location))
		{
			return;
		}

		if (STORES[location] == null)
		{
			JOptionPane.showMessageDialog(null, "there's no store here", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		STORES[location].changeVisibility(false);
		STORES[location] = null;
	}

	public void placeRobot (final int location)
	{
		if (!this.checkbounds(location))
		{
			return;
		}

		if (SPAWNS[location])
		{
			JOptionPane.showMessageDialog(null, "spot used as home previously", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (STORES[location] != null)
		{
			JOptionPane.showMessageDialog(null, "cannot place a robot here, there's a store", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		ROBOTS[location] = new Robot(this.orientation.getObjLoc(location), this.isLocWithinThisPage(location));
		SPAWNS[location] = true;
	}

	public void removeRobot (final int location)
	{
		if (!this.checkbounds(location))
		{
			return;
		}

		if (!SPAWNS[location])
		{
			JOptionPane.showMessageDialog(null, "no robot spawns there", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		ROBOTS[location].changeVisibility(false);
		ROBOTS[location] = null;
		SPAWNS[location] = false;
	}

	public void moveRobot (final int location, final int meters)
	{
		if (!this.checkbounds(location))
		{
			return;
		}

		if (ROBOTS[location] == null)
		{
			JOptionPane.showMessageDialog(null, "no robot is there", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		final int endsup = meters + this.orientation.getObjLoc(ROBOTS[location].getLocation());
		if (endsup < 0 || endsup >= TLENGTH)
		{
			JOptionPane.showMessageDialog(null, "goes outta bounds", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (STORES[endsup] != null && STORES[endsup].getAvailable())
		{
			STORES[endsup].setAsNoLongerAvailable();

		}

		ROBOTS[location].move(meters, false, this.isLocWithinThisPage(endsup));
		ROBOTS[location].updateMoney(meters);
	}

	public void reboot ()
	{
		for (int i = 0; i < Chunk.maxPerPage; i++)
		{
			if (ROBOTS[i] != null)
			{
				ROBOTS[i].move(0, true, this.isLocWithinThisPage(i));
			}
			if (STORES[i] != null)
			{
				STORES[i].resupply();
			}
		}
	}

	public int getLength ()                  { return this.length; }
	public PageOrientation getOrientation () { return this.orientation; }
	public boolean [] getVisibleChunks ()    { return this.visibleChunks; }

	private boolean checkbounds (final int location)
	{
		if (location >= TLENGTH || location < 0)
		{
			JOptionPane.showMessageDialog(null, "location outta bounds", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean isLocWithinThisPage (final int location)
	{
		return (location >= (this.pageNo * Chunk.maxPerPage)) && (location <= (this.pageNo * (Chunk.maxPerPage + 1)));
	}
}
