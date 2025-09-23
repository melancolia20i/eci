/* este enum permite descibir la manera en las que las
 * paginas situan sus chunks, tiendas y robots
 *
 * @author  Hever Barrera ; Juan Diego Patino
 * @version 1
 */

public enum PageOrientation
{
	TOP_LEFT (0, Road.MAXNOCHUNKS, 1),
	CENTER   (Road.MAXNOCHUNKS - 1, -1, -1);

	public static PageOrientation getOrientationBasedOnIndex (final int index)
	{
		return ((index % 2) == 0) ? TOP_LEFT : CENTER;
	}

	private final int starts;
	private final int ends;
	private final int change;

	PageOrientation (final int starts, final int ends, final int change)
	{
		this.starts = starts;
		this.ends   = ends;
		this.change = change;
	}

	public int getStarts () { return this.starts; }
	public int getEnds   () { return this.ends;   }
	public int getChange () { return this.change; }
}
