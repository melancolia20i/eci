/* this class represents  a set of terrain chunks (squares) that make up
 * the visble map inside the game
 *
 * chunks are small squares units positioned in a fixed grid inside a JFrame
 * window that can be shown or hidden depending on the page and map config
 *
 * @author  Hever Barrera ; Juan Diego Patiño
 * @version 0.2v
 */
public class Chunk
{
	/* maximum number of chunks (terrain squares) per page.
	 */
	public static final int maxPerPage = 17;

	/* stores all chunk objects. These are created once and reused
	 * across all pages; visibility is toggled depending on the page.
	 */
	private static Rectangle[] chunks;

	/**
	 * Coordinates for each chunk (x, y position in pixels).
	 * These values assume a 500x500 window (Canva.winsize).
	 * If the window size changes, these coordinates must be updated.
	 */
	private static final int[][] locations =
	{
		{0,     0},
		{100,   0},
		{200,   0},
		{300,   0},
		{400,   0},
		{400, 100},
		{400, 200},
		{400, 300},
		{400, 400},
		{300, 400},
		{200, 400},
		{100, 400},
		{0  , 400},
		{0,   300},
		{0,   200},
		{100, 200},
		{200, 200},
	};

	/* default size (width and height) of each chunk in pixels.
	 */
	private static final int sz = 100;

	/* renders the chunks for the first time. This creates
	 * Rectangle objects and makes them visible.
	 *
	 * @param total        number of chunks to initialize
	 */
	public static void renderForTheFirstTime(final int total)
	{
		chunks = new Rectangle[maxPerPage];
		for (int i = 0; i < total; i++)
		{
			chunks[i] = new Rectangle( locations[i][0], locations[i][1], sz, sz, Colors.ROAD);
			chunks[i].changeVisibility(true);
		}
	}

	/* updates the visibility of the chunks based on orientation
	 * and a map array.
	 *
	 * @param or           the orientation of the page (iteration order)
	 * @param map          a byte array where {1 = visible}, {0 = hidden}
	 */
	public static void renderChunks(final PageOrientation or, final byte[] map)
	{
		for (int i = or.getFrom(); i != or.getUntil(); i += or.getChange())
		{
			chunks[i].changeVisibility(map[i] == 1);
		}
	}
}

