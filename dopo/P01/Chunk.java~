/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author  hever barrera batero ; juan diego patino munoz
 * @version 1
 *
 * Esta clase permite almacenar informacion sobre los fragmentos (chunks)
 * generados por el archivo Road.java. Ese archivo guarda multiples
 * instancias de esta clase para registrar la informacion de la simulacion.
 */

import java.util.List;
import java.util.ArrayList;

public class Chunk
{
	private PageOrientation orientedby;
	private Store           store;
	private int             globalId;
	private int             internalId;
	private boolean         displayed;

	/**
	 * este objeto hace referencia al robot que se creo en este chunk
	 * con el objetivo de que cuando pase un dia el robot vuleva aca
	 */
	private Robot           robot;

	/**
	 * este queue hace referencia a todos los robos que estan actualmente
	 * en este chunk
	 */
	private List<Robot>    robots;

	/**
	 * Constructor de la clase Chunk.
	 * Inicializa un nuevo fragmento con su orientacion, identificador global,
	 * identificador interno y estado de visibilidad.
	 * @param orientation orientacion del fragmento en la pagina
	 * @param nochunk identificador global del fragmento
	 * @param show indica si el fragmento se muestra al inicio
	 */
	public Chunk (final PageOrientation orientation, final int nochunk, final boolean show)
	{
		this.orientedby = orientation;
		this.globalId   = nochunk;
		this.internalId = nochunk % Road.MAXNOTERRAINLENGTH;
		this.store      = null;
		this.displayed  = show;
		this.robot      = null;
		this.robots     = new ArrayList<>();
	}

	/**
	 * Crea e inaugura una nueva tienda (Store) asociada a este fragmento.
	 * La tienda se coloca segun la orientacion y el id interno del fragmento.
	 * @param tenges parametro que define la configuracion inicial de la tienda
	 */
	public void inaugurateStore (final int tenges)
	{
		this.store = new Store(
			tenges,
			this.orientedby.getModifiedIndexBasedOnInternalId(this.internalId),
			this.displayed
		);
	}

	/**
	 * Cierra la tienda asociada al fragmento (si existe) y elimina su referencia.
	 */
	public void closeStore ()
	{
		this.store.changevisibility(false);
		this.store = null;
	}

	/**
	 * Crea un nuevo robot (Robot) asociada a este fragmento.
	 * El robot se coloca segun la orientacion y el id interno del fragmento.
	 */
	public void placeRobot ()
	{
		this.robot = new Robot(
			this.globalId,
			this.orientedby.getModifiedIndexBasedOnInternalId(this.internalId),
			this.displayed
		);
		this.robots.add(this.robot);
	}

	/**
	 * Elimina la instancia de este chunk con respecto a su robot, quiere decir
	 * que ningun robot spawneara aca hasta que se cree uno nuevo
	 */
	public void killRobot ()
	{
		this.robot.changevisibility(false);
		this.robot = null;
	}

	/**
	 * Esta funcion solo se llama desde el metodo 'killRobot' ya que lo que hace esta
	 * funcion es eliminar toda referencia que se tenga del robot que se quiere borrar
	 * @param positionInQueue posicion en la que se encuentra el robot en este chunk
	 */
	public void colateralKill (final int positionInQueue)
	{
		this.robots.remove(positionInQueue);
	}

	/**
	 * Cambia el estado de visibilidad del fragmento y de la tienda
	 * asociada si esta existe.
	 * @param to nuevo estado de visibilidad (true para visible, false para oculto)
	 */
	public void changevisibility (final boolean to)
	{
		this.displayed = to;
		if (this.store != null)
		{
			this.store.changevisibility(to);
		}

		for (int i = 0; i < this.robots.size(); i++)
		{
			this.robots.get(i).changevisibility(to);
		}
	}

	/**
	 * Devuelve la tienda asociada al fragmento.
	 * @return objeto Store o null si no hay tienda
	 */
	public Store getStore () { return this.store; }

	/**
	 * Devuelve el robot asociada al fragmento.
	 * @return objeto Robot o null si no hay robot
	 */
	public Robot getRobot () { return this.robot; }
}

