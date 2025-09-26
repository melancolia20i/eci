/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author  hever barrera batero ; juan diego patino munoz
 * @version 1
 *
 * Esta clase almacena informacion global (principalmente titulos de ventana)
 * con el fin de mantener las demas clases limpias y organizadas.
 */

import javax.swing.JOptionPane;

public class Misc
{
	/** Titulo principal de la aplicacion. */
	public static final String T0       = "SilkRoad - ICPC's J problem simulator";
	/** Tiempo en milisegundos usado para la pausa al renderizar. */
	public static final int    RENDERMS = 10;

	/**
	 * Muestra un cuadro de dialogo para cambiar la pagina actual.
	 * Solicita al usuario un numero de pagina y lo devuelve como entero.
	 * @return numero de pagina ingresado por el usuario
	 */
	public static int changePageDialog ()
	{   
		return Integer.parseInt(JOptionPane.showInputDialog(
					null,
					String.format("page [0 - %d): ", Road.NOPAGES),
					String.format("%s: changing page (current: %d)", T0, Road.CURPAGE),
					JOptionPane.INFORMATION_MESSAGE
					));
	}

	/**
	 * Muestra un mensaje de error indicando que el numero de pagina dado es invalido.
	 * @param given numero de pagina ingresado por el usuario
	 */
	public static void invalidPageNumberDialog (final int given)
	{
		JOptionPane.showMessageDialog(
				null,
				String.format("invalid page number (%d)", given),
				T0,
				JOptionPane.ERROR_MESSAGE
				);
	}

	/**
	 * Muestra un mensaje de error cuando se recibe una ubicacion invalida
	 * desde BlueJ para colocar un objeto.
	 * @param location ubicacion proporcionada
	 */
	public static void invalidLocationGivenViaBlueJDialogs (final int location)
	{
		JOptionPane.showMessageDialog(
				null,
				String.format("invalid location provided (%d is not between [0, %d])", location, Silkroad.LENGTH - 1),
				T0,
				JOptionPane.ERROR_MESSAGE
				);
	}

	/**
	 * Muestra un mensaje de error cuando se intenta colocar una tienda
	 * en una ubicacion que ya contiene una.
	 * @param location ubicacion donde se intento colocar la tienda
	 */
	public static void invalidLocationToPlaceAStoreAt (final int location)
	{
		JOptionPane.showMessageDialog(
				null,
				String.format("cannot place a store there! there's already one store at location %d", location),
				T0,
				JOptionPane.ERROR_MESSAGE
				);
	}

	/**
	 * Muestra un mensaje de error cuando se intenta eliminar una tienda
	 * en una ubicacion que no tiene ninguna.
	 * @param location ubicacion donde se intento eliminar la tienda
	 */
	public static void invalidLocationToRemoveAStoreAt (final int location)
	{
		JOptionPane.showMessageDialog(
				null,
				String.format("cannot remove a store there! there's no store at location %d", location),
				T0,
				JOptionPane.ERROR_MESSAGE
				);
	}
}

