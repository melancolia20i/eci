/* enumeration class PageOrientation - defines how chunks
 * are oriented inside a page (from top-left forward or
 * from center backward).
 *
 * @author  Hever Barrera ; Juan Diego Patiño
 * @version 0.2v
 */

public enum PageOrientation
{
	TOP_LEFT (0, Chunk.maxPerPage, 1),
	CENTER   (Chunk.maxPerPage - 1, -1, -1);

	private final int from;
	private final int until;
	private final int change;

	/* constructor that defines a page orientation.
	 *
	 * @param from         starting index
	 * @param until        ending index
	 * @param change       increment/decrement step
	 */
	PageOrientation (final int from, final int until, final int change)
	{
		this.from   = from;
		this.until  = until;
		this.change = change;
	}

	/* @return             starting index
	 */
	public int getFrom ()
	{ 
		return this.from; 
	}

	/* @return             ending index
	 */
	public int getUntil () 
	{ 
		return this.until; 
	}

	/* @return             step size (direction of iteration)
	 */
	public int getChange () 
	{ 
		return this.change; 
	}

	/* returns the location of an object depending on the orientation.
	 *
	 * @param location     index of the object within the page
	 * @return             adjusted index based on orientation
	 */
	public int getObjLoc (final int location)
	{
		if (this.from == 0)
		{
			return location;
		}
		return Chunk.maxPerPage - location - 1;
	}

	/* returns the orientation for a given page index.
	 * Even pages are oriented TOP_LEFT, odd pages CENTER.
	 *
	 * @param pageIndex    index of the page
	 * @return             PageOrientation for that page
	 */
	public static PageOrientation getOrientationBasedOnPageIndex (int pageIndex)
	{
		return ((pageIndex % 2) == 0) ? TOP_LEFT : CENTER;
	}
}

