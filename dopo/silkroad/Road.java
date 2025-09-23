/* esta clase se encarga de contener los objetos tipo Rectangle
 * que representan la carrera (chunks); esto con el fin de no
 * tener que dibujar demasiado y asi salvar tiempo con los renders
 *
 * @author  Hever Barrera ; Juan Diego Patino
 * @version 1
 */

public class Road
{
	/* especifica cuantos chunks maximo deben haber por ventana, tener en cuenta
	 * que este valod fue calculado con respecto a una ventana de 500x500 y un
	 * chunk con tamano 100x100
	 */
	public static final int MAXNOCHUNKS = 17;

	/* especifica que tan grande es un pedazo de tierra (un cuadradado)
	 * donde se pueden poner tiedas y/o robots
	 */
	private static final int _CHUNKSZ = 100;

	/* estas son las coordenadas donde debe ir posicionado cada chunk dentro
	 * de la ventana, tener en cuenta que fueron calculadas con los valores
	 * de nua ventana 500x500 y un chunk 100x100
	 */
	private static final int[][] _CHUNKSLOCS =
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

	/* objetos de la clase Rectangle que representan los chunks, estas intancias
	 * siempre estaran aca, lo unico que cambiara sera su visiblidad si la pagina
	 * actual no necesita de ellas, por ejemplo una pagina que tenga 5 chunks
	 */
	private static Rectangle []_CHUNKS = new Rectangle[MAXNOCHUNKS];

	/* instancia y dibuja todos los cunks que se necesitan para dibujar la primera
	 * pagina. Puede que la primera pagina tenga los Road.MAXNOCHUNKS completos
	 * pero quiza la segunda solo tiene dos, en ese caso lo que se hace es ocultar
	 * los otors MAXNOCHUNKS - 2 chunks, no borrarlos
	 */
	public static void renderFullRoad (final int p0chunks)
	{
		for (int i = 0; i < p0chunks; i++)
		{
			_CHUNKS[i] = new Rectangle(_CHUNKSLOCS[i][0], _CHUNKSLOCS[i][1], _CHUNKSZ, _CHUNKSZ, SColor.ROAD);
			_CHUNKS[i].visibility(true);
		}
	}

	/* dado el mapa de una pagina, esta funcion hara visible/invisible los chunks
	 * que sean necesarios actializar
	 */
	public static void updateRoad (final boolean []map)
	{
		for (int i = 0; i < Road.MAXNOCHUNKS; i++)
		{
			_CHUNKS[i].visibility(map[i]);
		}
	}
}
