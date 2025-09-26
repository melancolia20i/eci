/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author  hever barrera batero ; juan diego patino munoz
 * @version 1
 *
 * Esta clase representa el punto de inicio del proyecto. 
 * El proyecto debe ser ejecutado desde BlueJ, lo cual implica
 * que no existe un metodo main tradicional.
 *
 * Nota: no confundir con la clase Road, ya que Silkroad provee
 * los controles de la simulacion.
 */

public class Silkroad
{
	/** Longitud total de la carretera, especificada al inicio. */
	public static int LENGTH;

	/** Indica si la ultima operacion fue realizada con exito. */
	private boolean ok;

	/**
	 * Constructor que inicializa la simulacion con una carretera
	 * de la longitud dada.
	 * @param length longitud de la carretera
	 */
	public Silkroad (final int length)
	{
		Silkroad.LENGTH = length;
		Road.createRoad();
	}

	/**
	 * Constructor alternativo que recibe informacion de dias.
	 * Actualmente no implementado.
	 * @param days matriz con datos de dias
	 */
	public Silkroad (final int[][] days)
	{
	}

	/**
	 * Cambia la pagina visible en la simulacion, pidiendo el
	 * numero de pagina al usuario.
	 */
	public void changePage ()
	{
		final int pageno = Misc.changePageDialog();
		if ((pageno < 0) || (pageno >= Road.NOPAGES))
		{
			Misc.invalidPageNumberDialog(pageno);
			return;
		}
		Road.changePageVisual(pageno);
	}

	/**
	 * Intenta colocar una tienda en la posicion indicada.
	 * @param location posicion del chunk
	 * @param tenges identificador de la tienda
	 */
	public void placeStore (final int location, final int tenges)
	{
		if (location < 0 || location >= LENGTH)
		{
			Misc.invalidLocationGivenViaBlueJDialogs(location);
			return;
		}

		this.ok = Road.placeStore(location, tenges);
		if (!this.ok)
		{
			Misc.invalidLocationToPlaceAStoreAt(location);
			return;
		}
	}

	/**
	 * Intenta remover una tienda en la posicion indicada.
	 * @param location posicion del chunk
	 */
	public void removeStore (final int location)
	{
		if (location < 0 || location >= LENGTH)
		{
			Misc.invalidLocationGivenViaBlueJDialogs(location);
			return;
		}

		this.ok = Road.removeStore(location);
		if (!this.ok)
		{
			Misc.invalidLocationToRemoveAStoreAt(location);
			return;
		}
	}
}

