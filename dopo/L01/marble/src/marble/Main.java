package marble;

import shapes.*;
import javax.swing.*;

import java.util.Random;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame
{
	/* Esta variable hace referencia a el tamano que se requiere
	 * para dibujar un cuadrado, tambien equivale al diametro de
	 * una canica
	 * */
	private static int mxwidth = 40;

	private static int N;
	private static int M;
	
	private static Cell[][] board;
	private static int noSolved = 0;
	
	final private static String[] colorstrings =
	{
		"red",
		"magenta",
		"yellow",
		"orange",
		"green",
		"blue",
		"gray",
		"pink",
		"cyan",
	};
	
	private static List<Circle> canicas = new ArrayList<>();
	
	private static void getGameSettings ()
	{
		/* Dado que todo el proyecto es con ventanas tambien deberiamos
		 * pedir los datos con ventanas... Codigo generado por chatGPT,
		 * modificado por nostros
		 * */
		String n_ = JOptionPane.showInputDialog("N [2, ]:");
		String m_ = JOptionPane.showInputDialog("M [1,9]:");
		
		N = Integer.parseInt(n_);
		M = Integer.parseInt(m_);

		/* Hay 11 colores, de esos 11 no usaremos ni el blanco ni el negro
		 * para fichas, lo cual nos deja con 9 colores, tambien se debe
		 * comprobar que no hayan mas colores de los que realmente tenemos
		 * */
		if (M > 9)
		{
			JOptionPane.showMessageDialog(null, "Valor invalido para M", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		if (N < 2)
		{
			JOptionPane.showMessageDialog(null, "Valor invalido para N", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		/* El primer proble que surgue es saber si los valores tienen sentido,
		 * es decir, no puede haber M fichas si M >= N, pero como hay M fichas
		 * plus M espacios para cada ficha la condicion deberia ser
		 * 2M <= N*N
		 * M <= N*N/2
		 * */
		if (M > Math.floor(N * N / 2))
		{
			JOptionPane.showMessageDialog(null, "Espacio minimo invalido", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		Canvas.winsize = mxwidth * N;
	}
	
	private static void createBoardBackEnd ()
	{	
		board = new Cell[N][N];

		/* lo primero que queremos hacer es el tablero basico, sin ningun tipo
		 * de color ni nada, solo una representacion
		 * */
		for (int row = 0; row < N; row++)
		{
			for (int col = 0; col < N; col++)
			{
				board[row][col] = new Cell(row, col);
			}
		}	
		
		Random rand = new Random();
		/* ahora queremos marcar algunas celdas como no permisivas (aun no hemos
		 * incluido las canicas, solo celdas)
		 * */
		for (int m = 0; m < M; m++)
		{
			int rrow = rand.nextInt(N);
			int rcol = rand.nextInt(N);
			
			if (board[rrow][rcol].amPermissive() == false) { m--; continue; }
			board[rrow][rcol].turnOnPermissive(colorstrings[m], mxwidth);
		}
		
		/* ahora hacemos lo mismo que arriba solo que esta vez queremos ubicar
		 * las canicas mas no los slots
		 * */
		for (int m = 0; m < M; m++)
		{		
			int rrow = rand.nextInt(N);
			int rcol = rand.nextInt(N);
			
			Cell cell = board[rrow][rcol];

			/* si queremos colocar una canica en cualquier lado primero debemos
			 * asegurarnos de que no haya un slot ahi y ademas que no haya otra
			 * canica puesta previamente
			 * */
			if (cell.amPermissive() == false || cell.amBeingPressed() == true)
			{
				m--;
				continue;
			}
			
			Circle marble = new Circle(rcol * mxwidth, rrow * mxwidth, mxwidth, colorstrings[m], rrow, rcol);
			marble.makeVisible(true);
			
			canicas.add(marble);
			board[rrow][rcol].setFeelingPressed(true);
		}

		/* XXX: borrar este bucle cuando ya no sea necesario
		 * */
		for (int row = 0; row < N; row++)
		{
			for (int col = 0; col < N; col++)
			{
				System.out.print(board[row][col].amPermissive() + "\t\t");
			}
			System.out.println();
		}	
	}

	private static void handleMotion (int dm, char dp)
	{
		for (int i = 0; i < canicas.size(); i++)
		{		
			Circle canica = canicas.get(i);				
			
			if (canica.alreadySolved() == true)
			{
				continue;
			}
			
			int x = canica.get_table_col(), y = canica.get_table_row();
			int old_x = x, old_y = y;
			
			if (dp == 'y') { y += dm; }
			else { x += dm; }
			
			/* si alguna de estas condiciones pasa significa que estamos al borde
			 * del tablero lo cual nos deberis impedir movernos
			 * */
			if (x == -1 || x == N || y == -1 || y == N)
			{
				continue;
			}

			Cell celda = board[y][x];	
			
			/* si la celda contiene una canica actualmente, entonces no
			 * se puede colocar una mas ahi
			 * */
			if (celda.amBeingPressed() == true)
			{
				System.out.println("hola");
				continue;
			}
				
			/* actualizamos la posicion dentro del tablero y la posicion de la canica
			 * en la pantalla del juego
			 * */
			canica.set_table_col(x);
			canica.set_table_row(y);
			canica.update_position(y * mxwidth, x * mxwidth);	
			
			celda.setFeelingPressed(true);
			if (celda.amPermissive() == false)
			{
				String canicaColor = canica.getColor();
				if (canicaColor != celda.onlyAccept())
				{
					JOptionPane.showMessageDialog(null, "Has hecho un mal movimiento", "Has perdido :(", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
				
				/* eliminamos esta canica de la lista y del visual del juego
				 * */
				canica.i_am_already_paired();
				noSolved++;
				
				celda.getFloor().makeVisible(false);
				celda.set_as_permissive();
				celda.setFeelingPressed(false);
			}
			
			board[old_y][old_x].setFeelingPressed(false);
		}
		
		if (noSolved == M)
		{
			JOptionPane.showMessageDialog(null, "Has resuleto este tablero!", "Felicidades", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
	
	public static void getKeyPressed (char key)
	{
		if (key != 'w' && key != 'e' && key != 's' && key != 'n')
		{
			return;
		}
		
		/* dm: diferencial de movimiento -1 o 1
		 * dp: diferencial de posicion x o y
		 * */
		int dm = 0;
		char dp = 0;

		if (key == 's' || key == 'n') { dp = 'y'; }
		else if (key == 'w' || key == 'e') { dp = 'x'; }
		
		if (key == 'w' || key == 'n')      { dm = 1; }
		else if (key == 'e' || key == 's') { dm = -1; }	
		
		handleMotion(dm, dp);	
	}
		
	public static void main(String[] args)
	{
		getGameSettings();
		createBoardBackEnd();
	}	
}
