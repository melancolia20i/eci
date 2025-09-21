/**
 * Enumeration class PageOrientation - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */

public enum PageOrientation
{
	TOP_LEFT (0, Chunk.maxPerPage, 1),
	CENTER   (Chunk.maxPerPage - 1, -1, -1);

	private final int from;
	private final int until;
	private final int change;

	PageOrientation (final int from, final int until, final int change)
	{
		this.from   = from;
		this.until  = until;
		this.change = change;
	}

	public int getFrom   () { return this.from;   }
	public int getUntil  () { return this.until ; }
	public int getChange () { return this.change; }

	public int getObjLoc (final int location)
	{
		if (this.from == 0)
		{
			return location;
		}
		return Chunk.maxPerPage - location - 1;
	}

	public static PageOrientation getOrientationBasedOnPageIndex (int pageIndex)
	{
		return ((pageIndex % 2) == 0) ? TOP_LEFT : CENTER;
	}
}
