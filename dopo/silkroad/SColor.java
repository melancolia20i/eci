/* en este archivo se definen los colores que se van a utilizar
 * para el juego, implementa colores basados en la clase java.awt.Color
 *
 * @author  Hever Barrera ; Juan Diego Patino
 * @version 1
 */

import java.awt.Color;

public enum SColor
{
	SAND (new Color(227, 212, 168)),
	ROAD (new Color(139, 125, 107));

	private Color color;

	SColor (Color color)     { this.color = color; }
	public Color getColor () { return this.color;  }
}
