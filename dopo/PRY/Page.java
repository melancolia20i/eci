/* represents a page of the road.
 * A page contains chunks, stores and robots
 * associated with a specific PageOrientation.
 *
 * @author  Hever Barrera ; Juan Diego Patiño
 * @version 0.2
 */

import javax.swing.JOptionPane;

public class Page
{
	/* this map will tell us what chunks should be visible for this page
	 * 1 if it should be, 0 otherwise
	 */
	private byte []chunkMap;

	private Store []storesMap;
	private Robot []robotsMap;

	/* since no robot can be spawned in the same spot as other
	 * we need to track where a robot has been placed before
	 */
	private boolean []spotUsedAsHome;

	/* specifies this page length in terms of amount of chunks
	 */
	private int length;

	private PageOrientation or;

	private int pageNumber;

	/* @param thisLength   number of chunks in this page
	 * @param or           orientation of the page
	 */
	public Page (final int thisLength, final PageOrientation or, final int pageNumber)
	{
		this.length         = thisLength;
		this.or             = or;
		this.chunkMap       = new byte[Chunk.maxPerPage];
		this.storesMap      = new Store[Chunk.maxPerPage];
		this.robotsMap      = new Robot[Chunk.maxPerPage];
		this.spotUsedAsHome = new boolean[Chunk.maxPerPage];
		this.pageNumber     = pageNumber;

		for (int i = or.getFrom(), j = 0; i != or.getUntil() && j < thisLength; i += or.getChange())
		{
			this.chunkMap[i] = 1;
			this.spotUsedAsHome[i] = false;
			j++;
		}
	}

	/* changes the visibility of all stores and robots in this page.
	 *
	 * @param state true to show, false to hide
	 */
	public void changeVisibilityOfObjs (final boolean state)
	{
		for (int i = 0; i < Chunk.maxPerPage; i++)
		{
			if (this.storesMap[i] != null)
			{
				this.storesMap[i].changeVisibility(state);
			}
			if (this.robotsMap[i] != null)
			{

				this.robotsMap[i].changeVisibility(state);
			}
		}
	}

	/* creates a store at the given location if it is free.
	 *
	 * @param tenges       resources of the store
	 * @param location     position within this page
	 */
	public void createStore (final int tenges, final int location)
	{
		if (!this.checkBounds(location)) { return; }

		final int loc = this.or.getObjLoc(location);
		if (this.storesMap[loc] != null)
		{
			JOptionPane.showMessageDialog(null, "There's a store already", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.storesMap[loc] = new Store(tenges, loc);
	}

	/* removes a store at the given location if there's a store there.
	 *
	 * @param location     position within this page
	 */
	public void removeStore (final int location)
	{
		if (!this.checkBounds(location)) { return; }

		final int loc = this.or.getObjLoc(location);
		if (this.storesMap[loc] == null)
		{
			JOptionPane.showMessageDialog(null, "There's no store there", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		this.storesMap[loc].changeVisibility(false);
		this.storesMap[loc] = null;
	}

	/* creates a new robot in the location given, be aware that no robot can be placed
	 * in the same home spot as other, no robot can be placed where there's a store
	 *
	 * @param location     location given
	 */
	public void createRobot (final int location)
	{
		if (!this.checkBounds(location)) { return; }

		final int loc = this.or.getObjLoc(location);
		if (this.spotUsedAsHome[loc])
		{
			JOptionPane.showMessageDialog(null, "Spot used as home previously", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (this.storesMap[loc] != null)
		{
			JOptionPane.showMessageDialog(null, "Cannot place a robot here, there's a store", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		this.robotsMap[loc] = new Robot(loc);
		this.spotUsedAsHome[loc] = true;	
	}

	/* removes a robot at the given home location, the user cannot give  the
	 * current position of the robot but instead the location where it spawns
	 *
	 * @param home         the position where the robot spawn
	 */
	public void removeRobot (final int home)
	{
		if (!this.checkBounds(home)) { return; }

		final int loc = this.or.getObjLoc(home);
		if (!this.spotUsedAsHome[loc])
		{
			JOptionPane.showMessageDialog(null, "No robot spawns there", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		this.robotsMap[loc].changeVisibility(false);
		this.robotsMap[loc] = null;
		this.spotUsedAsHome[loc] = false;
	}

	public void moveRobot (final int location, final int meters)
	{
		if (!this.checkBounds(location)) { return; }

		final int loc = this.or.getObjLoc(location);
		if (this.robotsMap[loc] == null)
		{
			JOptionPane.showMessageDialog(null, "No robot is there", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		final int terminal = meters + this.or.getObjLoc(this.robotsMap[loc].getLocation());

		if (terminal < 0 && this.pageNumber == 0)
		{
			JOptionPane.showMessageDialog(null, "cannot move robot further; first page limit reached", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (terminal >= this.length && this.pageNumber == (Silkroad.NOPAGES - 1))
		{
			JOptionPane.showMessageDialog(null, "cannot move robot further; last page limit reached", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (terminal >= 0 && terminal < this.length)
		{
			this.robotsMap[loc].move(meters, false);
		}

		if (terminal < 0)
		{
			System.out.println("back");
		}

		if (terminal >= this.length)
		{
			System.out.println("forward");
		}
	}

	public int getLength ()             { return this.length; }
	public byte []getChunkMap ()        { return this.chunkMap; }
	public PageOrientation getPageOr () { return this.or; }

	/* removes a store at the given location if there's a store there.
	 *
	 * @param location     location given
	 * @return             if the location is within bounds
	 */
	private boolean checkBounds (final int location)
	{
		if (location >= this.length)
		{
			JOptionPane.showMessageDialog(null, "Store outta bounds", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
}

