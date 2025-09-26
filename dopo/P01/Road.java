/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author  hever barrera batero ; juan diego patino munoz
 * @version 1
 *
 * Esta clase almacena toda la logica e informacion de la simulacion.
 *
 * Nota: no confundir con la clase Silkroad. Road provee el backend
 * de la simulacion.
 */

public class Road
{
	/**
	 * Arreglo que almacena todos los chunks de la carretera.
	 */
	private static Chunk [] _fullroad;

	/**
	 * Arreglo que almacena los rectangulos que simulan el terreno.
	 */
	private static Rectangle [] _terrain;

	/**
	 * Numero maximo de cuadrados de terreno que se pueden dibujar
	 * a la vez en la ventana.
	 */
	public static final int MAXNOTERRAINLENGTH = 17;

	/** Tamano en pixeles de cada cuadrado de terreno (100x100).
	 */
	private static final int _terrainSqrSize = 100;

	/**
	 * Coordenadas precalculadas de cada cuadrado del terreno dentro
	 * de la ventana. Permite optimizar el renderizado.
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

	/**
	 * Indice del primer chunk visible en la pagina actual.
	 */
	private static int _chunk0i;

	/**
	 * Indice del ultimo chunk visible en la pagina actual.
	 */
	private static int _chunkNi;

	/**
	 * Numero total de paginas del mapa.
	 */
	public static int NOPAGES = 1;

	/**
	 * Numero de pagina actual.
	 */
	public static int CURPAGE = 0;

	/**
	 * Crea la carretera inicial con todos sus chunks y rectangulos de terreno.
	 */
	public static void createRoad ()
	{
		_terrain  = new Rectangle[MAXNOTERRAINLENGTH];
		_fullroad = new Chunk[Silkroad.LENGTH];

		final PageOrientation orientations[] =
		{
			PageOrientation.EVEN,
			PageOrientation.ODD
		};

		for (int i = 0, j = 0; i < Silkroad.LENGTH; i++)
		{
			if (((i % MAXNOTERRAINLENGTH) == 0) && (i != 0))
			{
				j = 1 - j;
			}

			_fullroad[i] = new Chunk(orientations[j], i, (i < MAXNOTERRAINLENGTH));
		}

		final int minwinterrgen = Math.min(MAXNOTERRAINLENGTH, Silkroad.LENGTH);
		for (int i = 0; i < minwinterrgen; i++)
		{
			_terrain[i] = new Rectangle(_terraincoords[i][0], _terraincoords[i][1], _terrainSqrSize, _terrainSqrSize, SColor.road);
			_terrain[i].changevisibility(true);
		}

		NOPAGES  = (int) Math.ceil((double) Silkroad.LENGTH / MAXNOTERRAINLENGTH);
		_chunk0i = 0;
		_chunkNi = minwinterrgen - 1;
	}

	/**
	 * Cambia la pagina visualizada en pantalla.
	 * @param pageno numero de la nueva pagina
	 */
	public static void changePageVisual (final int pageno)
	{
		if (pageno == CURPAGE)
		{
			return;
		}

		// Ocultar los chunks de la pagina actual
		for (int i = _chunk0i; i != _chunkNi; i++) { _fullroad[i].changevisibility(false); }

		// Calcular rango de chunks de la nueva pagina
		_chunk0i = pageno * MAXNOTERRAINLENGTH;
		_chunkNi = pageno * MAXNOTERRAINLENGTH;
		CURPAGE  = pageno;
		_chunkNi += Math.min(MAXNOTERRAINLENGTH, Silkroad.LENGTH - _chunkNi);

		// Dibujar terreno y chunks de la nueva pagina
		displayTerrainBasedOnThisPage();
		for (int i = _chunk0i; i != _chunkNi; i++) { _fullroad[i].changevisibility(true); }
	}

	/**
	 * Intenta colocar una tienda en la posicion indicada.
	 * @param location posicion del chunk
	 * @param tenges identificador de la tienda
	 * @return true si se coloco la tienda, false si ya existia una
	 */
	public static boolean placeStore (final int location, final int tenges)
	{
		if (_fullroad[location].getStore() != null)
		{
			return false;
		}

		_fullroad[location].inaugurateStore(tenges);
		return true;
	}

	/**
	 * Intenta eliminar una tienda en la posicion indicada.
	 * @param location posicion del chunk
	 * @return true si se elimino, false si no habia tienda
	 */
	public static boolean removeStore (final int location)
	{
		if (_fullroad[location].getStore() == null)
		{
			return false;
		}
		_fullroad[location].closeStore();
		return true;
	}

	/**
	 * Muestra el terreno dependiendo de la pagina actual
	 * y su orientacion (par o impar).
	 */
	private static void displayTerrainBasedOnThisPage ()
	{
		final PageOrientation or = PageOrientation.orientationOf(CURPAGE);
		int lim = (_chunkNi - _chunk0i);

		if (or == PageOrientation.ODD)
		{
			lim = MAXNOTERRAINLENGTH - lim;
		}

		for (int i = or.getstarts(); i != or.getends(); i += or.getdelta())
		{
			if (or == PageOrientation.ODD)
			{
				_terrain[i].changevisibility((i < lim) ? false : true);
			}
			else if (or == PageOrientation.EVEN)
			{
				_terrain[i].changevisibility((i > lim) ? false : true);
			}
		}
	}
}

