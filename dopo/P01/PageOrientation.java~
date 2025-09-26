/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author	hever barrera batero ; juan diego patino munoz
 * @version	1
 *
 * Provides a way to understand how pages toggle their orientation depending
 * on if they're even or odd page numbers
 *
 * even page numbers: starts from the left top corner of the screen
 * odd page numbers : starts from the center of the screen
 */

public enum PageOrientation
{
	EVEN (0, Road.MAXNOTERRAINLENGTH, 1),
	ODD  (Road.MAXNOTERRAINLENGTH - 1, -1, -1);

	public static PageOrientation orientationOf (final int pageno)
	{
		return ((pageno % 2) == 0) ? EVEN : ODD;
	}

	private final int starts;
	private final int ends;
	private final int delta;

	PageOrientation (final int starts, final int ends, final int delta)
	{
		this.starts = starts;
		this.ends   = ends;
		this.delta  = delta;
	}

	public int getModifiedIndexBasedOnInternalId (final int id)
	{
		if (this == EVEN)
		{
			return id;
		}
		return Road.MAXNOTERRAINLENGTH - id - 1;
	}

	public int getstarts () { return this.starts; }
	public int getends   () { return this.ends;   }
	public int getdelta  () { return this.delta;  }
}
