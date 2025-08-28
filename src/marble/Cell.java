package marble;

import shapes.Rectangle;

public class Cell
{
	/* estos valores establecen la posicion dentro de una matriz de celdas, mas
	 * no la posicion donde la celda sera dibujada en el canva; para obtener esos
	 * valores se debe multiplicar col y row por el mxwidth
	 * */
	private int col;
	private int row;

	/* se refiere a si la celda puede alojar a una canica sin importar
	 * su color, si permissive es true entonces cualquier color puede permanecer aqui,
	 * si es false solo celdas de color 'onlyAccepts' podran
	 * */
	private boolean permissive;
	
	/* este atributo solo tiene sentido si permissive es false y se refiere
	 * al color que debera ser la canica para evitar perder
	 * */
	private String   onlyAccepts;
	
	/* dado que el fondo del juego es blanco haremos uso de esto para no crear
	 * N * N rectangulos, sino mas bien solo los que no son permisivos, si esta
	 * celda es no permisiva entonces el attrb floor tendra un valor; null en
	 * caso contrario
	 *  */
	private Rectangle floor;
	
	/* se refiere si hay una canica la cual esta sobre esta celda, claramente
	 * esto solo puede pasar si la celda es permisiva o si la canica y el
	 * color de la celda son el mismo
	 * */
	private boolean beingPressed;
	
	public Cell (int col, int row)
	{
		this.col = col;
		this.row = row;
		this.permissive  = true;
		this.onlyAccepts = null;
		this.beingPressed = false;
	}
	
	public void turnOnPermissive (String oaccepts, int cellsz)
	{	
		this.permissive = false;
		this.onlyAccepts = oaccepts;
		
		this.floor = new Rectangle(this.row * cellsz, this.col * cellsz, cellsz, cellsz, this.onlyAccepts);
		this.floor.makeVisible(true);
	}
	
	public void setFeelingPressed (boolean pressed)
	{
		this.beingPressed = pressed;
	}
	
	public int getx () { return this.col; }
	public int gety () { return this.row; }
	
	public boolean amPermissive () { return this.permissive; }
	public String onlyAccept () { return this.onlyAccepts; }
	
	public boolean amBeingPressed () { return this.beingPressed; }
}
