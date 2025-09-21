/* this class handles the concept of a store which is a instance
 * of a rectangle an cicrle with some tenges (money)
 *
 * @author  Hever Barrera ; Juan Diego Patino
 * @version 0.2v
 */

public class Store
{
	/* each store has a different style (styles may repeat
	 * per pages since we do not want to create a very big number
	 * of styles) (the style cycle repeats itself every Chunk.maxPerPage stores)
	 */
	private static final Colors [][] styles =
	{
		{Colors.C1_F , Colors.C1_T },
		{Colors.C2_F , Colors.C2_T },
		{Colors.C3_F , Colors.C3_T },
		{Colors.C4_F , Colors.C4_T },
		{Colors.C5_F , Colors.C5_T },
		{Colors.C6_F , Colors.C6_T },
		{Colors.C7_F , Colors.C7_T },
		{Colors.C8_F , Colors.C8_T },
		{Colors.C9_F , Colors.C9_T },
		{Colors.C10_F, Colors.C10_T},
		{Colors.C11_F, Colors.C11_T},
		{Colors.C12_F, Colors.C12_T},
		{Colors.C13_F, Colors.C13_T},
		{Colors.C14_F, Colors.C14_T},
		{Colors.C15_F, Colors.C15_T},
		{Colors.C16_F, Colors.C16_T},
		{Colors.C17_F, Colors.C17_T},
	};

	/* just as we did in Chunk class we compute the positions where each
	 * store should be placed at so we do not waste time recomputing for
	 * every single one of them
	 */
	private static final int [][]locations =
	{
		{75,  0,   50,  12},
		{175, 0,   150, 12},
		{275, 0,   250, 12},
		{375, 0,   350, 12},
		{475, 0,   450, 12},
		{475, 125, 450, 137},
		{475, 225, 450, 237},
		{475, 325, 450, 337},
		{475, 475, 450, 487},
		{375, 475, 350, 487},
		{275, 475, 250, 487},
		{175, 475, 150, 487},
		{75,  475, 50,  487},
		{25,  325, 0,   337},
		{25,  225, 0,   237},
		{150, 200, 125, 212},
		{250, 200, 225, 212},
	};

	/* each store starts with a fixed number of tenges, we need to resupply
	 * the store by the same amount
	 */
	private final int tenges;

	/* when a robot comes to this store, it will take the money so this
	 * store will no longer be available until next day
	 */
	private boolean available; 

	/* index chosen for this store [0 - 16] (index of locations)
	*/
	private int locatedAt;

	private static final int sz = 25;

	private Rectangle fachada;
	private Triangle  techo;

	/* @param tenges       initial amount of money the store has
	 * @param location     location index of the store
	 */
	public Store (final int tenges, final int location)
	{
		this.tenges    = tenges;
		this.locatedAt = location;
		this.available = true;

		this.placeInChunk();
	}

	/* toggles store visibility
	 *
	 * @param state        true to show, false to hide
	 */
	public void changeVisibility (final boolean state)
	{
		this.fachada.changeVisibility(state);
		this.techo.changeVisibility(state);
	}

	/* places the store in its chunk and initializes its shapes
	 */
	private void placeInChunk ()
	{
		final int f_row = locations[this.locatedAt][0];
		final int f_col = locations[this.locatedAt][1];

		final int t_row = locations[this.locatedAt][2];
		final int t_col = locations[this.locatedAt][3];

		this.fachada = new Rectangle(f_row, f_col, sz, sz, styles[this.locatedAt][0]);
		this.techo   = new Triangle(t_row, t_col, sz, sz, styles[this.locatedAt][1]);

		this.fachada.changeVisibility(true);
		this.techo.changeVisibility(true);
	}
}

