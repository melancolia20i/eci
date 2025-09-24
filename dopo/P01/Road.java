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

class Chunk
{
	private Store [] stores;
	private Robot [] robots;

	private int id;

	public Chunk (final int id)
	{
		this.id = id;
	}
}

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
	private static final int _maxNoTerrainLength = 17;
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

	public static void createRoad ()
	{
		_terrain  = new Rectangle[_maxNoTerrainLength];
		_fullroad = new Chunk[Silkroad.LENGTH];

		for (int i = 0; i < Silkroad.LENGTH; i++)
		{
			_fullroad[i] = new Chunk(i);
		}

		for (int i = 0; i < Math.min(_maxNoTerrainLength, Silkroad.LENGTH); i++)
		{
			System.out.println(i);
			_terrain[i] = new Rectangle(_terraincoords[i][0], _terraincoords[i][1], _terrainSqrSize, _terrainSqrSize, SColor.road);
			_terrain[i].changevisibility(true);

		}
	}
}
