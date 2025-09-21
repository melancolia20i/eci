/**
 * Write a description of class silkroad here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.swing.JOptionPane;

public class Silkroad
{
	/* Since the program can have more than Chunk.maxPerPage chunks we need a way
	 * to store more than that amount, we are going to handle it with the concept
	 * of a page
	 * */
	private Page []pages;

	private int length;
	private int nopages;
	private int currentPage;

	public Silkroad (final int length)
	{
		this.length      = length;
		this.nopages     = (length + Chunk.maxPerPage - 1) / Chunk.maxPerPage;
		this.pages       = new Page[this.nopages];
		this.currentPage = 0;

		this.createPages(length);
	}

	public void changePage (int to)
	{
		to = to - 1;
		if (this.currentPage == to)
		{
			JOptionPane.showMessageDialog(null, "That one is the current page...", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		this.pages[this.currentPage].changeVisibilityOfObjs(false);

		final Page ncurr = this.pages[to];
		Chunk.renderChunks(ncurr.getPageOr(), ncurr.getChunkMap());

		this.currentPage = to;
		this.pages[this.currentPage].changeVisibilityOfObjs(true);
	}

	public void placeStore (final int tenges, final int location)
	{
		this.pages[this.currentPage].createStore(tenges, location - 1);
	}

	private void createPages (int length)
	{
		/* Sets how long is the road for each page and displays
		 * the first page
		 * */
		for (int i = 0; i < this.nopages; i++)
		{
			final PageOrientation or = PageOrientation.getOrientationBasedOnPageIndex(i);

			if (length >= Chunk.maxPerPage)
			{
				this.pages[i] = new Page(Chunk.maxPerPage, or);
			}
			else if (length > 0)
			{
				this.pages[i] = new Page(length, or);
			}
			length -= Chunk.maxPerPage;
		}
		Chunk.renderForTheFirstTime(this.pages[0].getLength());
	}
}
