/* represents a robot agent that moves on the map.
 * a robot is drawn as a circle; it has an origin (home) index,
 * an amount of money, and a current location index on the page.
 *
 * @author  Hever Barrera ; Juan Diego Patiño
 * @version 0.2v
 */

public class Robot
{
	/* color palette used to render robot bodies.
	 * indexed by location so robots have varied appearances.
	 */
	private static final Colors styles[] =
	{
		Colors.R1,
		Colors.R2,
		Colors.R3,
		Colors.R4,
		Colors.R5,
		Colors.R6,
		Colors.R7,
		Colors.R8,
		Colors.R9,
		Colors.R10,
		Colors.R11,
		Colors.R12,
		Colors.R13,
		Colors.R14,
		Colors.R15,
		Colors.R16,
		Colors.R17,
	};

	/* fixed coordinates (row, col) for each location index (0..16).
	 * these correspond to the chunk layout used by Chunk/Store/etc.
	 */
	private static final int [][]locations =
	{
		{50,  50},
		{150, 50},
		{250, 50},
		{350, 50},
		{450, 50},
		{450, 150},
		{450, 250},
		{450, 350},
		{450, 450},
		{350, 450},
		{250, 450},
		{150, 450},
		{50,  450},
		{50,  350},
		{50,  250},
		{150, 250},
		{250, 250}
	};

	private static final int sz = 25;

	private Circle body;
	private int    origin;
	private int    money;
	private int    location;

	public Robot (final int location, final boolean show)
	{
		this.location  = location;
		this.origin    = location;
		this.placeInChunk(show);
	}

	/* toggles robot visibility
	 *
	 * @param state        true to show, false to hide
	 */
	public void changeVisibility (final boolean state)
	{
		this.body.changeVisibility(state);
	}

	/* moves the position of the robot by 'by' chunks
	 *
	 * @param by           number of chunks to move
	 * @param reset        indicates if the robot has to go back to its spawn spot
	 */
	public void move (final int by, final boolean reset, final boolean show)
	{
		if (reset)
		{
			this.location = this.origin;
			this.money    = 0;
		}
		else
		{
			this.location += by;
			this.location %= Chunk.maxPerPage;
		}
		this.body.updatePosition(locations[this.location][0], locations[this.location][1], show);
	}

	public int getLocation () { return this.location; }

	/* places the robot in its chunk and initializes its body
	 */
	private void placeInChunk (final boolean show)
	{
		final int row = locations[this.location][0];
		final int col = locations[this.location][1];

		this.body = new Circle(row, col, sz, styles[this.location]);
		this.body.changeVisibility(show);
	}
}
