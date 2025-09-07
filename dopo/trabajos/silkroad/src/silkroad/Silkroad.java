package silkroad;

import shapes.*;

class Store
{
	private int tenges;
	private int location;
	
	public Store (int tenges, int location)
	{
		this.tenges = tenges;
		this.location = location;
	}
	
}

class Robot
{	
}

public class Silkroad
{
	/* Este es el numero maximo que se puede ingresar para crear
	 * una carretera. Es 17 dado que la ventana es 500x500 y cada
	 * road chunk es de 100x100
	 * */
	public static final int maxRoadLength = 17;

	/* Cada pedazo de carretera es un rectangulo de 100x100
	 * */
	private final int roadChunkSize = 100;
	
	/* Esta matriz establece donde se deben colocar las placas del Road
	 * dentro de la ventana
	 * */
	private static final int [][]placeChunkAt =
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
	

	private int profit;
	private int roadLength;
	
	public Silkroad (int length)
	{
		this.roadLength = length;

		for (int i = 0; i < this.roadLength; i++)
		{
			int row = placeChunkAt[i][0], col = placeChunkAt[i][1];
			new Rectangle(row, col, roadChunkSize, roadChunkSize, ColorType.ROAD).makeVisible(true);;
		}
	
	}

	public void placeStore (int location, int tenges)
	{
		
	}
	
	public void removeStore (int location)
	{
	}
	
	public void moveRobot (int location, int meters)
	{
	}
	
	public void resupplyStores ()
	{
	}
	
	public void returnRobots ()
	{
	}
	
	public void reboot ()
	{
	}
	
	public int profit ()
	{
		return this.profit;
	}
	
	public int[][] stores ()
	{
		return null;
	}
	
	public int[][] robots ()
	{
		return null;
	}
	
	public void makeVisible ()
	{
	}
	
	public void makeInvisble ()
	{
	}
	
	public void finish ()
	{
	}
	
	public void ok ()
	{
	}
}
