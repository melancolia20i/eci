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
	/* since the program can have more than Chunk.maxPerPage chunks we need a way
	 * to store more than that amount, we are going to handle it with the concept
	 * of a page
	 */
	private Page []pages;

	private int length;
	private int currentPage;

	public static int NOPAGES;

	/* @param length       total length of the road in chunks
	 */
	public Silkroad (final int length)
	{
		NOPAGES     = (length + Chunk.maxPerPage - 1) / Chunk.maxPerPage;

		this.length      = length;
		this.pages       = new Page[NOPAGES];
		this.currentPage = 0;

		this.createPages(length);
	}

	/* changes the current page to another page index
	 *
	 * @param to           page number (1-based index)
	 */
	public void changePage (int to)
	{
		to = to - 1;
		if (this.currentPage == to)
		{
			JOptionPane.showMessageDialog(
				null, "That one is the current page...", Main.TITLE, JOptionPane.ERROR_MESSAGE
			);
			return;
		}

		this.pages[this.currentPage].changeVisibilityOfObjs(false);

		final Page ncurr = this.pages[to];
		Chunk.renderChunks(ncurr.getPageOr(), ncurr.getChunkMap());

		this.currentPage = to;
		this.pages[this.currentPage].changeVisibilityOfObjs(true);
	}

	/* places a store in the current page
	 *
	 * @param tenges       resources of the store
	 * @param location     location index (1-based)
	 */
	public void placeStore (final int tenges, final int location)
	{
		this.pages[this.currentPage].createStore(tenges, location - 1);
	}

	/* removes a store in the current page
	 *
	 * @param location     location index (1-based)
	 */
	public void removeStore (final int location)
	{
		this.pages[this.currentPage].removeStore(location - 1);
	}

	/* places a robot in the current page
	 *
	 * @param location     location index (1-based)
	 */
	public void placeRobot (final int location)
	{
		this.pages[this.currentPage].createRobot(location - 1);
	}

	/* removes a robot in the current page
	 *
	 * @param home     location index (1-based)
	 */
	public void removeRobot (final int home)
	{
		this.pages[this.currentPage].removeRobot(home - 1);
	}

	/* moves a robot given its current location and the meters
	 *
	 * @param location     location where the robot to move is currently in
	 * @param meters       number of chunks to move (> 0 forward, < 0 backwards)
	 */
	public void moveRobot (final int location, final int meters)
	{
		this.pages[this.currentPage].moveRobot(location - 1, meters);
	}

	/* creates all pages of the road and initializes the first one
	 *
	 * @param length       total road length in chunks
	 */
	private void createPages (int length)
	{
		/* sets how long is the road for each page and displays
		 * the first page
		 */
		for (int i = 0; i < NOPAGES; i++)
		{
			final PageOrientation or = PageOrientation.getOrientationBasedOnPageIndex(i);

			if (length >= Chunk.maxPerPage)
			{
				this.pages[i] = new Page(Chunk.maxPerPage, or, i);
			}
			else if (length > 0)
			{
				this.pages[i] = new Page(length, or, i);
			}
			length -= Chunk.maxPerPage;
		}
		Chunk.renderForTheFirstTime(this.pages[0].getLength());
	}
}

