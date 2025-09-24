/**
 *         __ __ __                        __ 
 * .-----.|__|  |  |--.----.-----.---.-.--|  |
 * |__ --||  |  |    <|   _|  _  |  _  |  _  |
 * |_____||__|__|__|__|__| |_____|___._|_____|
 *
 * @author	hever barrera batero ; juan diego patino munoz
 * @version	1
 *
 * Provides different colors by using the java.awt.Color class in order
 * to make the simulator more colorful and no that boring
 */

import java.awt.Color;

public enum SColor
{
	sand (new Color(227, 212, 168)),
	road (new Color(139, 125, 107));

	private final Color self;

	SColor (final Color color) { this.self = color; }
	public Color getcolor ()   { return this.self; }
}
