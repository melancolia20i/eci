/* archivo principal desde donde se ejecuta el codigo
 * y se realizan las posibles operaciones (debe ser
 * ejecutado con BlueJ dado que este proyecto no tiene
 * metodo main)
 *
 * @author  Hever Barrera ; Juan Diego Patino
 * @version 1
 */


public class Silkroad
{
	public static final String TITLE      = "silkroad - DOPO";
	public static int          ROADLENGTH = 0;

	private Page page;

	/* primer constructor: dada una longitud (entera no negativa) se creara
	 * una ruta de seda (el numero de paginas dependera de `length`
	 */
	public Silkroad (final int length)
	{
		ROADLENGTH = length;
		this.page  = Page.createAllPages();
		this.page.displayPageContents();
	}

	public Silkroad (final int [][]days)
	{
	}

	public void changePage ()
	{
		this.page = Page.changePage(this.page);
		this.page.displayPageContents();
	}

	public void placeStore (final int location, final int tenges)
	{
	}
}
