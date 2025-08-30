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
			
			Circle marble = new Circle(rcol * mxwidth, rrow * mxwidth, mxwidth, colorstrings[m]);
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
	
	public static void main(String[] args)
	{
		getGameSettings();
		createBoardBackEnd();
	}	
}
