/**
 * Write a description of class Page here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.swing.JOptionPane;

public class Page
{
	/* This map will tell us what chunks should be visible for this page
	 * 1 if it should be, 0 otherwise
	 * */
	private byte []chunkMap;

	private Store []storesMap;
	private Robot []robotsMap;

	/* Specifies this page length in terms of amount of chunks
	 * */
	private int length;

	private PageOrientation or;

	public Page (final int thisLength, final PageOrientation or)
	{
		this.length    = thisLength;
		this.or        = or;
		this.chunkMap  = new byte[Chunk.maxPerPage];
		this.storesMap = new Store[Chunk.maxPerPage];

		for (int i = or.getFrom(), j = 0; i != or.getUntil() && j < thisLength; i += or.getChange())
		{
			this.chunkMap[i]  = 1;
			j++;
		}
	}

	public void changeVisibilityOfObjs (final boolean state)
	{
		for (int i = 0; i < this.length; i++)
		{
			if (this.storesMap[i] != null)
			{
				this.storesMap[i].changeVisibility(state);
			}
		}
	}

	public void createStore (final int tenges, final int location)
	{
		if (location >= this.length)
		{
			JOptionPane.showMessageDialog(null, "Store outta bounds", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		final int loc = this.or.getObjLoc((location));
		if (this.storesMap[loc] != null)
		{
			JOptionPane.showMessageDialog(null, "There's a store already", Main.TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.storesMap[loc] = new Store(tenges, loc);
	}

	public int getLength ()             { return this.length; }
	public byte []getChunkMap ()        { return this.chunkMap; }
	public PageOrientation getPageOr () { return this.or; }
}
