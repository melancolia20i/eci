package silkroad;

import shapes.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	/* positiones exactas de donde se deben colocar las casas
	 * dependiendo su posicion en el mapa
	 * {fachada_row, fachada_col, techo_row, techo_col}
	 * 
	 * valores calculados en: 9c642e5 git
	 * */
	private static int [][]locations =
	{
		{75, 0, 50, 12},
		{175, 0, 150, 12},
		{275, 0, 250, 12},
		{375, 0, 350, 12},
		{475, 0, 450, 12},
		{475, 125, 450, 137},
		{475, 225, 450, 237},
		{475, 325, 450, 337},
		{475, 475, 450, 487},
		{375, 475, 350, 487},
		{275, 475, 250, 487},
		{175, 475, 150, 487},
		{75, 475, 50, 487},
		{25, 325, 0, 337},
		{25, 225, 0, 237},
		{150, 200, 125, 212},
		{250, 200, 225, 212},
	};
	
	/* tanto como la fachada (rectangulo) y el techo (triangulo)
	 * compartiran el tamano de 25 unidades
	 * */
	private int dimensions = 25;
	
	/* tenges: numbero de tenges que esta tienda tiene y por el cual se reabastece
	 * available: true si ningun robot ha venido, false si ya pasaron por aca
	 * location: position en el mapa [0, 17)
	 * */
	private int tenges;
	private boolean available;
	private int location;
	
	private Rectangle fachada;
	private Triangle techo;
	
	public Store (int tenges, int location)
	{
		this.tenges = tenges;
		this.location = location;
		this.available = true;
		
		this.putInTheMap();
	}	
	
	private void putInTheMap ()
	{
		int frow = locations[this.location][0], fcol = locations[this.location][1];
		int trow = locations[this.location][2], tcol = locations[this.location][3];
			
		fachada = new Rectangle(frow, fcol, dimensions, dimensions, styles[this.location][0]);
		techo = new Triangle(trow, tcol, dimensions, dimensions, styles[this.location][1]);
		
		fachada.makeVisible(true);
		techo.makeVisible(true);
	}
	
	public void reSupply ()
	{
		if (this.available == true) { return; }
		this.available = true;
	}

	public void visibility (boolean action)
	{
		if (action == true)
		{
			this.fachada.makeVisible(true);
			this.techo.makeVisible(true);
		}
		else
		{
			this.fachada.makeVisible(false);
			this.techo.makeVisible(false);
		}
	}

	public void noLongerAvailable ()
	{
		this.available = false;
	}

	public int getTenges ()
	{
		return this.tenges;
	}
	
	public boolean getAvailable ()
	{
		return this.available;
	}

	public void killMe ()
	{
		this.fachada.makeVisible(false);
		this.techo.makeVisible(false);
	}
}

class Robot
{	
	private ColorType styles[] =
	{
		ColorType.R1,
		ColorType.R2,
		ColorType.R3,
		ColorType.R4,
		ColorType.R5,
		ColorType.R6,
		ColorType.R7,
		ColorType.R8,
		ColorType.R9,
		ColorType.R10,
		ColorType.R11,
		ColorType.R12,
		ColorType.R13,
		ColorType.R14,
		ColorType.R15,
		ColorType.R16,
		ColorType.R17,
	};
	
	private int [][]locations =
	{
		{50, 50},
		{150, 50},
		{250, 50},
		{350, 50},
		{450, 50},
		{450, 150},
		{450, 250},
		{450, 350},
		{450, 450},
		{350, 450},
		{250, 450},
		{150, 450},
		{50, 450},
		{50, 350},
		{50, 250},
		{150, 250},
		{250, 250}
	};

	private final int dimensions = 25;
	private final int origin;

	private int location;	
	private int money;
	
	private Circle body;
	
	public Robot (int location)
	{
		this.location = location;
		this.origin = location;
		this.putInTheMap();
	}
	
	private void putInTheMap ()
	{
		int row = locations[this.location][0], col = locations[this.location][1];
		
		this.body = new Circle(row, col, this.dimensions, styles[this.location]);
		this.body.makeVisible(true);
	}
	
	public void move (int by, boolean origin)
	{
		if (origin == true)
		{
			this.location = this.origin;
			this.money = 0;
		}
		else
		{
			this.location += by;
		}
		this.body.update_position(locations[this.location][0], locations[this.location][1]);
	}
	
	public int getMoneyBitch ()
	{
		return this.money;
	}
	
	public void visibility (boolean action)
	{
		if (action == true)
		{
			this.body.makeVisible(true);
		}
		else
		{
			this.body.makeVisible(false);
		}
	}

	public void takeMoneyFromStore (int amount)
	{
		this.money += amount;
	}

	
	public void killMe ()
	{
		body.makeVisible(false);
	}
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

	private static final String title = "SilkRoad";

	private int profit;
	private int total;
	private int roadLength;
	
	private Store []stores = new Store[maxRoadLength];
	private HashMap<Integer, List<Robot>> robots;
	
	private int [] robotplaced;
	
	private boolean success;

	public Silkroad (int length)
	{
		this.roadLength = length;

		for (int i = 0; i < this.roadLength; i++)
		{
			int row = chunksXY[i][0], col = chunksXY[i][1];
			new Rectangle(row, col, roadChunkSize, roadChunkSize, ColorType.ROAD).makeVisible(true);;
		}
		
		this.robots = new HashMap<Integer, List<Robot>> ();
		this.robotplaced = new int[maxRoadLength];
		
		for (int i = 0; i < maxRoadLength; i++)
		{
			this.stores[i] = null;
			this.robots.put(i, new ArrayList<Robot> ());
			this.robotplaced[i] = 0;
		}
		
		this.profit = 0;
		this.total  = 0;
		this.success = true;	
	}

	public void placeStore (int location, int tenges)
	{
		if (this.stores[location] != null)
		{
			JOptionPane.showMessageDialog(null, "cannot place a store at that location, there's one already", title, JOptionPane.ERROR_MESSAGE);
			this.success = false;
			return;
		}
		if (this.robots.get(location).size() != 0)
		{
			JOptionPane.showMessageDialog(null, "cannot place a store at that location, there's a robot there", title, JOptionPane.ERROR_MESSAGE);
			this.success = false;
			return;
		}
		
		this.total += tenges;	
		Store store = new Store(tenges, location);	

		this.stores[location] = store;		

		this.updateBar();
		this.success = true;
		
		System.out.println("added: " + this.total);
	}
	
	public void removeStore (int location)
	{
		if (this.stores[location] == null)
		{
			JOptionPane.showMessageDialog(null, "cannot delete a store at that location, there's no store there", title, JOptionPane.ERROR_MESSAGE);
			this.success = false;
			return;
		}
		
		this.total -= this.stores[location].getTenges();
		
		this.stores[location].killMe();
		this.stores[location] = null;

		this.updateBar();
		this.success = true;

		System.out.println("removed: " + this.total);
	}
	
	public void placeRobot (int location)
	{
		if (this.robotplaced[location] == 1)
		{
			JOptionPane.showMessageDialog(null, "cannot place a robot at that location, a robot was placed there already", title, JOptionPane.ERROR_MESSAGE);
			this.success = false;
			return;
		}
		if (this.stores[location] != null)
		{
			JOptionPane.showMessageDialog(null, "cannot place a robot at that location, there's a store there", title, JOptionPane.ERROR_MESSAGE);
			this.success = false;
			return;
		}

		Robot robot = new Robot(location);
		this.robotplaced[location] = 1;
		
		this.robots.get(location).add(robot);
		this.success = true;
	}
	
	public void removeRobot (int location)
	{
		if (this.robots.get(location).size() == 0)
		{
			JOptionPane.showMessageDialog(null, "cannot delete a robot at that location, there's no robot there", title, JOptionPane.ERROR_MESSAGE);
			this.success = false;
			return;
		}
		
		Robot r = this.robots.get(location).removeFirst();
		r.killMe();

		this.robotplaced[location] = 0;
		this.success = true;
	}
	
	public void moveRobot (int location, int meters)
	{
		if (this.robots.get(location).size() == 0)
		{
			JOptionPane.showMessageDialog(null, "cannot move robot at that location, there's no robot there", title, JOptionPane.ERROR_MESSAGE);
			this.success = false;
			return;
		}
		
		int newloc = location + meters;
		
		Robot robot = this.robots.get(location).removeFirst();
		robot.move(meters, false);
		
		robots.get(newloc).add(robot);
		this.success = true;
		
		if (this.stores[newloc] != null && this.stores[newloc].getAvailable() == true)
		{
			Store store = this.stores[newloc];

			robot.takeMoneyFromStore(store.getTenges());
			store.noLongerAvailable();
			
			this.profit += store.getTenges();
			this.updateBar();
		}
	}
	
	public void resupplyStores ()
	{
		for (int i = 0; i < maxRoadLength; i++)
		{
			if (this.stores[i] != null)
			{
				this.stores[i].reSupply();
				this.total += this.stores[i].getTenges();
			}
		}
		
		this.updateBar();
		this.success = true;
	}
	
	public void returnRobots ()
	{
		for (int i = 0; i < maxRoadLength; i++)
		{
			int nrbs = this.robots.get(i).size();
			if (nrbs == 0) { continue; }

			for (int j = 0; j < nrbs; j++)
			{	
				this.robots.get(i).get(j).move(0, true);
			}
		}
		this.success = true;
	}
	
	public void reboot ()
	{
		this.resupplyStores();
		this.returnRobots();
		
		this.profit = 0;
		this.total = 0;
		this.success = true;
		
		this.updateBar();
	}
	
	public int profit ()
	{
		JOptionPane.showMessageDialog(null, "Total profit so far is " + this.profit, title, JOptionPane.INFORMATION_MESSAGE);
		this.success = true;
		return this.profit;
	}
	
	public int[][] stores ()
	{
		int[][] stores = new int[maxRoadLength][2];

		for (int i = 0; i < maxRoadLength; i++)
		{
			if (this.stores[i] != null)
			{
				stores[i] = new int[] {i, this.stores[i].getTenges()};
			}
		}
		this.success = true;
		return stores;
	}
	
	public int[][] robots ()
	{
		int[][] robots = new int[maxRoadLength][2];
		
		int k = 0;
		for (int i = 0; i < maxRoadLength; i++)
		{
			int nrbs = this.robots.get(i).size();
			if (nrbs == 0) { continue; }
			
			for (int j = 0; j < nrbs; j++)
			{
				Robot r = this.robots.get(i).get(j);
				robots[k + j]  = new int[] {i, r.getMoneyBitch()};
				
				k++;
			}
		}	
		this.success = true;
		return robots;
	}
	
	public void makeVisible ()
	{
		for (int i = 0; i < maxRoadLength; i++)
		{
			int nrbs = this.robots.get(i).size();
			if (nrbs != 0)
			{
				for (int j = 0; j < nrbs; j++)
				{
					this.robots.get(i).get(j).visibility(true);
				}
			}
			if (this.stores[i] != null)
			{
				this.stores[i].visibility(true);
			}
		}
		this.success = true;
	}
	
	public void makeInvisble ()
	{
		for (int i = 0; i < maxRoadLength; i++)
		{
			int nrbs = this.robots.get(i).size();
			if (nrbs != 0)
			{
				for (int j = 0; j < nrbs; j++)
				{
					this.robots.get(i).get(j).visibility(false);
				}
			}
			if (this.stores[i] != null)
			{
				this.stores[i].visibility(false);
			}
		}
		this.success = true;
	}
	
	public void finish ()
	{
		this.success = true;
		System.exit(0);
	}
	
	public boolean ok ()
	{
		if (this.success == true)
		{
			JOptionPane.showMessageDialog(null, "succeed", title, JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "failed", title, JOptionPane.ERROR_MESSAGE);
		}
		return this.success;
	}	
	
	private void updateBar ()
	{
		int percentage = (int) (((double) this.profit / this.total) * 100);
		Canvas.updateBar(percentage);
	}
}