
/**
 * Write a description of class Triangle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;

public class Triangle
{
    final private static int VERTICES = 3;

    private int     row;
    private int     col;
    private int     height;
    private int     width;
    private Colors  color;
    private boolean visible;

    public Triangle (final int row, final int col, final int height, final int width, final Colors color)
    {
        this.row     = row;
        this.col     = col;
        this.width   = width;
        this.height  = height;
        this.color   = color;
        this.visible = false;
    }

    public void changeVisibility (final boolean status)
    {
        if (status)
        {
            this.visible = true;
            this.draw();
        }
        else
        {
            this.erase();
            this.visible = false;
        }
    }

    private void draw ()
    {
        if (!this.visible) { return; }
        Canva canvas = Canva.getCanva();

        int []xs = { this.col, this.col + (this.width  / 2), this.col - (this.width  / 2) };
        int []ys = { this.row, this.row + this.height      , this.row + this.height       };

        canvas.draw(this, this.color, new Polygon(xs, ys, VERTICES));
        canvas.wait(10);
    }

    private void erase ()
    {
        if (!this.visible) { return; }
        Canva canvas = Canva.getCanva();
        canvas.erase(this);
    }
}
