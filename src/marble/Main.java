package marble;

import shapes.*;
import javax.swing.*;

public class Main
{
	private static int N;
	private static int M;
	
	private static void getGameSettings ()
	{
		/* Dado que todo el proyecto es con ventanas tambien deberiamos
		 * pedir los datos con ventanas... Codigo generado por chatGPT,
		 * modificado por nostros
		 * */
		String n_ = JOptionPane.showInputDialog("N:");
		String m_ = JOptionPane.showInputDialog("M:");
		
		N = Integer.parseInt(n_);
		M = Integer.parseInt(m_);
		
		/* El primer proble que surgue es saber si los valores tienen sentido,
		 * es decir, no puede haber M fichas si M >= N, pero como hay M fichas
		 * plus M espacios para cada ficha la condicion deberia ser
		 * 2M <= N*N
		 * M <= N*N/2
		 * */
		if (M > (int) (N * N / 2))
		{
			JOptionPane.showMessageDialog(null, "Espacio minimo invalido", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}	
	}
	
	public static void main(String[] args)
	{
		getGameSettings();
	}
}