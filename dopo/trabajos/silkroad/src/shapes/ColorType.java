package shapes;
import java.awt.Color;

public enum ColorType
{
	BLACK   (new Color(225, 219, 178)),
	WHITE    (new Color(60, 56, 54)),
	RED     (new Color(204, 36, 26)),
	GREEN   (new Color(152, 151, 26)),
	YELLOW  (new Color(215, 153, 33)),
	BLUE    (new Color(69, 133, 136)),
	PURPLE  (new Color(177, 98, 134)),
	AQUA    (new Color(104, 157, 106)),
	ORANGE  (new Color(214, 93, 14)),
	GROUND  (new Color(194, 178, 128));
	
	private Color color;
	
	ColorType (Color color)
	{
		this.color = color;
	}
	
	public Color getColor ()
	{
		return this.color;
	}
}
