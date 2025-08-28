package marble;

public class Cell
{
	/* Hay dos tipos de celdas, las que pueden usarse como
	 * suelo normal y cualquier tipo de canica puede pasar
	 * por ahi (permissive = true) y luego esta las que solo
	 * permiten un color (permissive = false).
	 * 
	 * si permissive == true, el atributo color no debera
	 * usarse
	 * 
	 * si una celda es permisiva y la canica correspondiente
	 * llega a la celda, entonces esta celda dejara de ser
	 * permisiva para convertirse en una celda normal
	 * */
	private boolean permissive;
	private String   color;	
	private int      xpos, ypos;
	
	public Cell (int ypos, int xpos)
	{
		this.ypos = ypos;
		this.xpos = xpos;
	}
	
	public void setAsPermissive (String color)
	{
		this.permissive = true;
		this.color = color;
	}
	
	public boolean amIPermissive ()
	{
		return this.permissive;
	}
	
	public String permissiveKind ()
	{
		return this.color;
	}
}
