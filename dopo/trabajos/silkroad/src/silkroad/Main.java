package silkroad;

import shapes.*;

public class Main
{
	/* Este es el numero maximo que se puede ingresar para crear
	 * una carretera. Es 17 dado que la ventana es 500x500 y cada
	 * road chunk es de 100x100
	 * */
	private static final int maxRoadLength = 17;
	private static final int roadChunkSize = 100;
	
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
	
	private static int roadLength;
	
	public static void main (String []args)
	{
		roadLength = 17;
		for (int i = 0; i < roadLength; i++)
		{
			int row = placeChunkAt[i][0], col = placeChunkAt[i][1];
			new Rectangle(row, col, roadChunkSize, roadChunkSize, ColorType.ROAD).makeVisible(true);;
		}
	}
	
	public static void getKeyPressed (char key)
	{
		
	}
	
}
