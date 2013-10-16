package laststandmechanics;

import javax.swing.*;
import java.awt.*;

public class Missile
{
    private int x;
    private int y;
    private Image image;
    private Image image1;
    boolean visible;
    private int width;
    private int height;
    private final int BOARD_WIDTH = 1020;
    private final int MISSILE_SPEED = 2;
    
    public Missile(int x, int y) 
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("Assets/Images/missile.png"));
        image   = ii.getImage();
        visible = true;
        width   = this.image.getWidth(null);
        height  = this.image.getHeight(null);
        this.x  = x;
        this.y  = y;
    }
    
    public Image getImage() 
    {
        return image;
    }
    
    public int getX() 
    {
        return x;
    }
    
    public int getY() 
    {
        return y;
    }
    
    public boolean isVisible() 
    {
        return visible;
    }
    
    public void setVisible(Boolean visible) 
    {
        this.visible = visible.booleanValue();
    }
    
    public Rectangle getBounds() 
    {
        return new Rectangle(x, y, width, height);
    }
    
    public void move() 
    {
        x = x + 2;
        if (x > 1020) 
        {
            visible = false;
        }
    }
}