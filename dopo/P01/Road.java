/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author	hever barrera batero ; juan diego patino munoz
 * @version	1
 *
 * Provides the class from where all the logic and information of the
 * simulation is stored
 *
 * Do not confuse this file with 'Silkroad', this class provides the
 * simulation's backend
 */

public class Road
{
	/**
	 * this array stores the whole road and all of its information, since we can
	 * place a store at position say 1000 while being at position 0 we need a way
	 * to access all chunks in a quick way, so we need to make all this info static
	 * to the class
	 */
	private static Chunk [] _fullroad;

	/**
	 * this array stores the objects made in order to give the ilussion of ground, these
	 * are the brown squares drawn in the screen and where robots and stores will be
	 * placed
	 */
	private static Rectangle [] _terrain;

	/**
	 * indicates the maximum number of terrain squares that can be draw at once within
	 * the window; it's 17 since we have a 500x500 window and each terrain chunk is
	 * a 100x100 square, plus the form of the road is a spiral which leave us with the
	 * following visual:
	 *
	 *	T.TTT
	 *	T.T.T
	 *	T.T.T
	 *	T...T
	 *	TTTTT
	 *
	 * as you can see each T represets a place where a chunk can be placed and the dot
	 * empty space which gives the ilusion of spiral
	 */
	public  static final int MAXNOTERRAINLENGTH  = 17;
	private static final int _terrainSqrSize     = 100;

	/**
	 * since the terrain chunks do not move during the execution of the simulation we
	 * can compute their positions within the window in order to make the simulator
	 * faster
	 */
	private static final int[][] _terraincoords =
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

	/**
	 * _chunk0i: specifies the index of the first chunk shown in the current page (inclusive)
	 * _chunkNi: specifies the index of the last chunk show in the current page   (exclusive)
	 */
	private static int _chunk0i;
	private static int _chunkNi;

	/**
	 * in order to make all map visible we need to implement the concept of a 'page' which
	 * is in short a range of Chunks
	 */
	public static int NOPAGES = 1;
	public static int CURPAGE = 0;

	public static void createRoad ()
	{
		_terrain  = new Rectangle[MAXNOTERRAINLENGTH];
		_fullroad = new Chunk[Silkroad.LENGTH];

		final PageOrientation orientations[] =
		{
			PageOrientation.EVEN,
			PageOrientation.ODD
		};

		for (int i = 0, j = 0; i < Silkroad.LENGTH; i++)
		{
			if (((i % MAXNOTERRAINLENGTH) == 0) && (i != 0))
			{
				j = 1 - j;
			}

			/**
			 * al inicio todo lo que sea menor de MAXNOTERRAINLENGTH pertenece
			 * a la primera pagina, por ende estos chunks deberan ser visibles
			 */
			_fullroad[i] = new Chunk(orientations[j], i, (i < MAXNOTERRAINLENGTH));
		}

		final int minwinterrgen = Math.min(MAXNOTERRAINLENGTH, Silkroad.LENGTH);
		for (int i = 0; i < minwinterrgen; i++)
		{
			_terrain[i] = new Rectangle(_terraincoords[i][0], _terraincoords[i][1], _terrainSqrSize, _terrainSqrSize, SColor.road);
			_terrain[i].changevisibility(true);

		}

		NOPAGES  = (int) Math.ceil((double) Silkroad.LENGTH / MAXNOTERRAINLENGTH);
		_chunk0i = 0;
		_chunkNi = minwinterrgen - 1;
	}

	public static void changePageVisual (final int pageno)
	{
		if (pageno == CURPAGE)
		{
			return;
		}	

		/**
		 * telling all the chunks from the current page (no updated page) not
		 * to render its stuff since they're not longer gonna be visible
		 */
		for (int i = _chunk0i; i != _chunkNi; i++) { _fullroad[i].changevisibility(false); }

		_chunk0i = pageno * MAXNOTERRAINLENGTH;
		_chunkNi = pageno * MAXNOTERRAINLENGTH;
		CURPAGE  = pageno;
		
		_chunkNi += Math.min(MAXNOTERRAINLENGTH, Silkroad.LENGTH - _chunkNi);

		displayTerrainBasedOnThisPage();
		for (int i = _chunk0i; i != _chunkNi; i++) { _fullroad[i].changevisibility(true); }
	}

	public static boolean placeStore (final int location, final int tenges)
	{
		if (_fullroad[location].getStore() != null)
		{
			return false;
		}

		_fullroad[location].inaugurateStore(tenges);
		return true;
	}

	private static void displayTerrainBasedOnThisPage ()
	{
		final PageOrientation or = PageOrientation.orientationOf(CURPAGE);
		int lim = (_chunkNi - _chunk0i);

		if (or == PageOrientation.ODD)
		{
			lim = MAXNOTERRAINLENGTH - lim;
		}


		for (int i = or.getstarts(); i != or.getends(); i += or.getdelta())
		{
			if (or == PageOrientation.ODD)
			{
				_terrain[i].changevisibility((i < lim) ? false : true);
			}
			else if (or == PageOrientation.EVEN)
			{
				_terrain[i].changevisibility((i > lim) ? false : true);
			}
		}
	}
}
