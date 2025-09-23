/* esta clase es donde se guarda la informacion de lo que pasa
 * en diferentes tramos el mapa
 *
 * @author  Hever Barrera ; Juan Diego Patino
 * @version 1
 */

import javax.swing.JOptionPane;

public class Page
{
	/* indica cuatas paginas han sido creadas, su valor se establece
	 * por primera y unica vez en el metodo 'createAllPages'
	 */
	private static int _NOPAGES;

	public static Page createAllPages ()
	{
		final int up2 = (Silkroad.ROADLENGTH <= Road.MAXNOCHUNKS) ? (Silkroad.ROADLENGTH - 1) : (Road.MAXNOCHUNKS - 1);
		final Page p0 = new Page(0, up2, up2 + 1, 0, null, null, PageOrientation.TOP_LEFT);

		_NOPAGES = 1;
		if (up2 == (Silkroad.ROADLENGTH - 1))
		{
			Road.renderFullRoad(Silkroad.ROADLENGTH);
			return p0;
		}

		int chunksleft = Silkroad.ROADLENGTH - Road.MAXNOCHUNKS;
		int gf         = Road.MAXNOCHUNKS;
		int orindx     = 1;
		Page lastpage  = p0;

		while (chunksleft > 0)
		{
			final int newlen = Math.min(chunksleft, Road.MAXNOCHUNKS);

			Page pn = new Page(gf, gf + newlen - 1, newlen, orindx, null, lastpage, PageOrientation.getOrientationBasedOnIndex(orindx));
			lastpage.setNextpage(pn);

			chunksleft -= newlen;
			gf         += newlen;
			lastpage    = pn;

			_NOPAGES++;
			orindx++;
		}

		Road.renderFullRoad(Road.MAXNOCHUNKS);
		return p0;
	}

	public static Page changePage (final Page currentpage)
	{
		final int currentpageno = currentpage.getpageNo();
		final String title      = String.format("%s - cambio de pagina (actual: %d)", Silkroad.TITLE, currentpageno + 1);

		final int no = Integer.parseInt(JOptionPane.showInputDialog(
			null,
			String.format("pagina [1-%d]:", _NOPAGES),
			title,
			JOptionPane.INFORMATION_MESSAGE
		)) - 1;

		if (no == currentpageno)
		{
			return currentpage;
		}

		if ((no + 1) > _NOPAGES || no <= 0)
		{
			JOptionPane.showMessageDialog(null, "esa pagina no existe :(", title, JOptionPane.ERROR_MESSAGE);
			return currentpage;
		}

		final boolean needsnext = (no > currentpageno);
		Page aux = currentpage;

		do
		{
			if (needsnext)
			{
				aux = aux.getNextpage();
			}
			else
			{
				aux = aux.getPrevpage();
			}
		} while ((aux.getpageNo() != no) && (aux != null));

		return (aux == null) ? currentpage : aux;
	}

	/* goesfrom   : indice global del primer chunk que se renderiza en esta pagina (inclusivo)
	 * goesup     : indice global del ultimo chunk que se renderiza en esta pagina (inclusivo)
	 * roadlength : numero de chunks en esta pagina (goesup - goesfrom + 1)
	 * no         : numero de pagina
	 * next       : pagina siguiente
	 * prev       : pagina previa
	 * orientation: orientation page
	 */
	private int             goesfrom;
	private int             goesup;
	private int             roadlength;
	private int             no;
	private Page            next;
	private Page            prev;
	private PageOrientation orientation;
	private boolean []      chunkmap;

	private Page (final int gf, final int gu, final int length, final int no, final Page next, final Page prev, final PageOrientation orientation)
	{
		this.goesfrom    = gf;
		this.goesup      = gu;
		this.roadlength  = length;
		this.no          = no;
		this.next        = next;
		this.prev        = prev;
		this.orientation = orientation;
		this.chunkmap    = new boolean [Road.MAXNOCHUNKS];

		for (int i = orientation.getStarts(), j = 0; i != orientation.getEnds(); i += orientation.getChange())
		{
			this.chunkmap[i] = (j++ < this.roadlength);
		}
	}

	public void displayPageContents ()
	{
		Road.updateRoad(this.chunkmap);
	}

	public void setNextpage (final Page next) { this.next = next; }
	public void setPrevpage (final Page prev) { this.prev = prev; }

	public Page getNextpage ()                { return this.next; }
	public Page getPrevpage ()                { return this.prev; }

	public int getpageNo ()                   { return this.no;   }
}
