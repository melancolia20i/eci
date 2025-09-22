/**
 * represents the main silkroad game logic.
 * manages pages, chunks, stores, and switching between pages.
 *
 * @author  Hever Barrera ; Juan Diego Patiño
 * @version 0.2v
 */

import javax.swing.JOptionPane;


public class Silkroad
{
	public static int NO_PAGES;

	private int     length;
	private int     currentnopage;
	private Page [] pages;
	private int     totalMoney;

	public Silkroad (final int length)
	{
		NO_PAGES           = (length + Chunk.maxPerPage - 1) / Chunk.maxPerPage;
		this.length        = length;
		this.currentnopage = 0;

		Page.init(length);
		this.createPages(length);
		this.totalMoney = 0;
	}

	public void changePage (int to)
	{
		to = to - 1;
		if (this.currentnopage == to)
		{
			JOptionPane.showMessageDialog(null, "That one is the current page...", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		this.pages[this.currentnopage].changeObjsVisibility(false);

		final Page page = this.pages[to];
		Chunk.renderChunks(page.getOrientation(), page.getVisibleChunks());

		this.currentnopage = to;
		this.pages[this.currentnopage].changeObjsVisibility(true);
	}

	public void placeStore (final int tenges, final int location)
	{
		this.totalMoney += tenges;
		this.pages[this.currentnopage].placeStore(tenges, location - 1);
	}

	public void removeStore (final int location)
	{
		this.pages[this.currentnopage].removeStore(location - 1);
	}

	public void placeRobot (final int location)
	{
		this.pages[this.currentnopage].placeRobot(location - 1);
	}

	public void removeRobot (final int location)
	{
		this.pages[this.currentnopage].removeRobot(location - 1);
	}

	public void moveRobot (final int location, final int meters)
	{
		this.pages[this.currentnopage].moveRobot(location - 1, meters);
	}

	private void createPages (int length)
	{
		this.pages = new Page[NO_PAGES];
		for (int i = 0; i < NO_PAGES; i++)
		{
			final PageOrientation orientation = PageOrientation.getOrientationBasedOnPageIndex(i);

			if (length >= Chunk.maxPerPage)
			{
				this.pages[i] = new Page(Chunk.maxPerPage, orientation, i);
			}
			else
			{
				this.pages[i] = new Page(length, orientation, i);
			}
			length -= Chunk.maxPerPage;
		}
		Chunk.renderForTheFirstTime(this.pages[0].getLength());
	}

	public static void updateProgressBar ()
	{
		for (int i = 0; i < Page.TLENGTH; i++)
		{
		}
	}
}

