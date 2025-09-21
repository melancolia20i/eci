/**
 * Write a description of class Chunk here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Chunk
{
	/* Specifies the maximum number of chunks (terrain squares) available
	 * per page */
	public final static int maxPerPage = 17;

	/* Stores the actual objects, these objects are going to be used
	 * for all pages, what is going to be changed per page is the number
	 * of chunks visible
	 * */
	private static Rectangle [] chunks;

	/* These are the coords where each chunk should be placed within the
	 * JFrame
	 *
	 * NOTE: this calculations were made for a 500x500 (Canva.winsize)
	 * window, if that size changes then this shall change too
	 * */
	private static final int [][]locations =
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

	private static final int sz = 100;

	/* this will draw the chunks in the JFrame window, once they're
	 * draw they will never be erased only hidden if needed
	 * */
	public static void renderForTheFirstTime (final int total)
	{
		chunks = new Rectangle[maxPerPage];
		for (int i = 0; i < total; i++)
		{
			chunks[i] = new Rectangle(locations[i][0], locations[i][1], sz, sz, Colors.ROAD);
			chunks[i].changeVisibility(true);
		}
	}

	public static void renderChunks (final PageOrientation or, final byte [] map)
	{
		for (int i = or.getFrom(); i != or.getUntil(); i += or.getChange())
		{
			chunks[i].changeVisibility(map[i] == 1 ? true : false);
		}
	}
}
