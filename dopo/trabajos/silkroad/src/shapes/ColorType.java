package shapes;
import java.awt.Color;

public enum ColorType
{
	SAND  (new Color(227, 212, 168)),
	ROAD  (new Color(139, 125, 107)),
	
	C1_F (new Color(228, 214, 170)),
	C1_T (new Color(150, 120, 90)),
	C2_F (new Color(210, 190, 160)),
	C2_T (new Color(110, 90, 70)),
	C3_F (new Color(193, 154, 107)),
	C3_T (new Color(120, 80, 50)),
	C4_F (new Color(180, 140, 100)),
	C4_T (new Color(90, 60, 40)),
	C5_F (new Color(240, 235, 220)),
	C5_T (new Color(160, 140, 120)),
	C6_F (new Color(235, 230, 210)),
	C6_T (new Color(120, 100, 80)),
	C7_F (new Color(200, 200, 200)),
	C7_T (new Color(100, 100, 100)),
	C8_F (new Color(160, 160, 160)),
	C8_T (new Color(60, 60, 60)),
	C9_F (new Color(150, 140, 130)),
	C9_T (new Color(50, 50, 50)),
	C10_F (new Color(120, 110, 100)),
	C10_T (new Color(30, 30, 30)),
	C11_F (new Color(245, 220, 200)),
	C11_T (new Color(160, 100, 70)),
	C12_F (new Color(230, 210, 200)),
	C12_T (new Color(120, 80, 60)),
	C13_F (new Color(210, 225, 220)),
	C13_T (new Color(90, 110, 100)),
	C14_F (new Color(200, 210, 230)),
	C14_T (new Color(80, 90, 120)),
	C15_F (new Color(185, 175, 140)),
	C15_T (new Color(100, 90, 60)),
	C16_F (new Color(170, 160, 120)),
	C16_T (new Color(70, 60, 40)),
	C17_F (new Color(160, 120, 90)),
	C17_T (new Color(60, 40, 30));
	
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
