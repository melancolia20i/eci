package marble;

import shapes.*;
import java.util.Scanner;

public class Main
{
	/* Estas dos variables son las que definiran las dimensiones
	 * para crear el tablero
	 * gridLength: numero de cuadrados
	 * noColors: numero de colores
	 * */
	private static int gridLength;
	private static int noColors;
	
	/* Hace referencia a las dimensiones que tomara dibujar un cuadrado
	 * en el tablero
	 * */
	private static final int sqrtSize = 40;
	
	private static void getDimensions ()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Marble game\ntamaño del tablero: ");
		
		gridLength = sc.nextInt();
		System.out.print("numero de colores: ");
		noColors = sc.nextInt();
		
		/* El primer problema que enfrentamos es que el numero de colores
		 * sea mayor al numero de casillas disponibles
		 **/
		if (noColors >= gridLength)
		{
			System.out.println("No se puede tener mas colores que casillas :(");
			System.exit(0);
		}
	}
	
	public static void main (String [] main)
	{
		getDimensions();
		Canvas.winLength = gridLength * sqrtSize;
		
		Rectangle r = new Rectangle();
	}
}
