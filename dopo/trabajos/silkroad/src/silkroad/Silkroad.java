package silkroad;

import shapes.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Store
{
	private static ColorType [][] styles =
	{
		{ColorType.C1_F,  ColorType.C1_T},
		{ColorType.C2_F,  ColorType.C2_T},
		{ColorType.C3_F,  ColorType.C3_T},
		{ColorType.C4_F,  ColorType.C4_T},
		{ColorType.C5_F,  ColorType.C5_T},
		{ColorType.C6_F,  ColorType.C6_T},
		{ColorType.C7_F,  ColorType.C7_T},
		{ColorType.C8_F,  ColorType.C8_T},
		{ColorType.C9_F,  ColorType.C9_T},
		{ColorType.C10_F, ColorType.C10_T},
		{ColorType.C11_F, ColorType.C11_T},
		{ColorType.C12_F, ColorType.C12_T},
		{ColorType.C13_F, ColorType.C13_T},
		{ColorType.C14_F, ColorType.C14_T},
		{ColorType.C15_F, ColorType.C15_T},
		{ColorType.C16_F, ColorType.C16_T},
		{ColorType.C17_F, ColorType.C17_T},
	};	
	
	/* tanto como la fachada (rectangulo) y el techo (triangulo)
	 * compartiran el tamano de 25 unidades
	 * */
	private int dimensions = 25;
	
	private int tenges;
	private boolean available;
	private int location;
	
	private Rectangle fachada;
	private Triangle techo;
	
	public Store (int tenges, int location)
	{
		this.tenges = tenges;
		this.location = location;
		
		this.putInTheMap();
	}	
	
	private void putInTheMap ()
	{	
		ColorType cf = styles[this.location][0];
		ColorType ct = styles[this.location][1];	
		
		int frow = 0, fcol = 0, trow = 0, tcol = 0;
		int startRow = Silkroad.chunksXY[location][0], startCol = Silkroad.chunksXY[location][1];

		if (this.location <= 4)
		{	
			frow = startRow + dimensions * 3;
			fcol = 0;
			
			trow = startRow + dimensions * 2;
			tcol = dimensions / 2;
		}
		else if (this.location <= 7)
		{
			frow = startRow + dimensions * 3;
			fcol = startCol + dimensions;

			trow = startRow + dimensions * 2;
			tcol = fcol + (dimensions / 2);
		}
		else if (this.location <= 12)
		{	
			frow = startRow + dimensions * 3;
			fcol = startCol + dimensions * 3;
			
			trow = frow - dimensions;
			tcol = fcol + (dimensions / 2);
		}
		else if (this.location <= 14)
		{
			frow = startRow + dimensions;
			fcol = startCol + dimensions;
			
			trow = startRow;
			tcol = fcol + (dimensions / 2);
		}
		else if (this.location <= 16)
		{
			frow = startRow + dimensions * 2;
			fcol = startCol;
			
			trow = startRow + dimensions;
			tcol = startCol + (dimensions / 2);
		}
		
		fachada = new Rectangle(frow, fcol, dimensions, dimensions, cf);	
		techo = new Triangle(trow,tcol, dimensions, dimensions, ct);
		
		fachada.makeVisible(true);
		techo.makeVisible(true);
	}
	
	public void killMe ()
	{	
		this.fachada.makeVisible(false);
		this.techo.makeVisible(false);
	}
	
	public void reSupply ()
	{
		if (this.available == true) { return; }
		this.available = true;
	}
}

class Robot
{	
}

public class Silkroad
{
	/* Este es el numero maximo que se puede ingresar para crear
	 * una carretera. Es 17 dado que la ventana es 500x500 y cada
	 * road chunk es de 100x100
	 * */
	public static final int maxRoadLength = 17;

	/* Cada pedazo de carretera es un rectangulo de 100x100
	 * */
	public static final int roadChunkSize = 100;

	/* Esta matriz establece donde se deben colocar las placas del Road
	 * dentro de la ventana
	 * */
	public static final int [][]chunksXY =
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
	

	private int profit;
	private int roadLength;
	
	private int noStores = 0;
	private Store []gapsAvailableForStores = new Store[maxRoadLength];
	
	private static final String title = "SilkRoad";
	
	public Silkroad (int length)
	{
		this.roadLength = length;

		for (int i = 0; i < this.roadLength; i++)
		{
			int row = chunksXY[i][0], col = chunksXY[i][1];
			new Rectangle(row, col, roadChunkSize, roadChunkSize, ColorType.ROAD).makeVisible(true);;
		}
		
		for (int i = 0; i < maxRoadLength; i++)
		{
			this.gapsAvailableForStores[i] = null;
		}
	}

	public void placeStore (int location, int tenges)
	{
		if (this.gapsAvailableForStores[location] != null)
		{
			JOptionPane.showMessageDialog(null, "cannot place a store at that location, there's one already", title, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Store store = new Store(tenges, location);	
		this.gapsAvailableForStores[location] = store;		
		this.noStores++;
	}
	
	public void removeStore (int location)
	{
		if (this.gapsAvailableForStores[location] == null)
		{
			JOptionPane.showMessageDialog(null, "cannot delete a store at that location, there's nothing there", title, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.gapsAvailableForStores[location].killMe();
		this.gapsAvailableForStores[location] = null;
	}
	
	public void moveRobot (int location, int meters)
	{
	}
	
	public void resupplyStores ()
	{
		for (int i = 0; i < this.roadLength; i++)
		{
			this.gapsAvailableForStores[i].reSupply();
		}
	}
	
	public void returnRobots ()
	{
	}
	
	public void reboot ()
	{
	}
	
	public int profit ()
	{
		return this.profit;
	}
	
	public int[][] stores ()
	{
		return null;
	}
	
	public int[][] robots ()
	{
		return null;
	}
	
	public void makeVisible ()
	{
	}
	
	public void makeInvisble ()
	{
	}
	
	public void finish ()
	{
	}
	
	public void ok ()
	{
	}
}
