package laststandmechanics;

import javax.swing.*;
import java.awt.*;

public class Alien
{
    private String craft;
    private String craft2;
    private String craft3;
    private String craft4;
    private String level;
    private int x;
    private double y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;
    private int hp;
    private int mode;
    private int fluc;
    
    public Alien(int x, int y, String level, int difficulty) 
    {
        craft  = "alien.png";
        craft2 = "alien2.png";
        craft3 = "alien3.png";
        craft4 = "alien4.png";
        mode   = difficulty;
        
        if (this.mode == 2) 
        {
            fluc = 40;
        }
        if (this.mode == 3) 
        {
            fluc = 50;
        }
        this.level = level;
        ImageIcon ii = null;
        if (level.equals("1")) 
        {
            ii = new ImageIcon(this.getClass().getResource(this.craft));
            hp = 1;
        }
        if (level.equals("2")) 
        {
            ii = new ImageIcon(this.getClass().getResource(this.craft2));
            hp = 1;
        }
        if (level.equals("3")) 
        {
            ii = new ImageIcon(this.getClass().getResource(this.craft3));
            hp = 1;
        }
        if (level.equals("4")) 
        {
            ii = new ImageIcon(this.getClass().getResource(this.craft4));
            hp = 49;
        }
        image   = ii.getImage();
        width   = this.image.getWidth(null);
        height  = this.image.getHeight(null);
        visible = true;
        this.x  = x;
        this.y  = (double)y;
    }
    
    public void move() 
    {
        int n = 1;
        int m = 1;
        int o = 1;
        int p = 1;
        if (mode == 0) 
        {
            n = 1;
            m = 1;
            o = 1;
        }
        if (mode == 1) 
        {
            n = 1;
            m = 2;
            o = 3;
        }
        if (mode == 2) 
        {
            n = 2;
            m = 3;
            o = 4;
            p = 2;
        }
        if (mode == 3) 
        {
            n = 3;
            m = 4;
            o = 5;
            p = 3;
        }
        if (level.equals("1")) 
        {
            if (x < -10) 
            {
                x = 1020;
            }
            x = x - n;
            if (y < -10.0) 
            {
                y = 680.0;
            }
            if (y > 680.0) 
            {
                y = 1.0;
            }
            if (mode == 2 || mode == 3) 
            {
                y = y + Math.sin(Math.toDegrees((double)(this.x / this.fluc)));
            }
        }
        else if (level.equals("2")) 
        {
            if (x < -10) 
            {
                x = 1020;
            }
            x = x - m;
            if (y < -10.0) 
            {
                y = 680.0;
            }
            if (y > 680.0) 
            {
                y = 1.0;
            }
            if (mode == 2 || mode == 3) 
            {
                y = y + Math.sin(Math.toDegrees((double)(this.x / this.fluc)));
            }
        }
        else if (level.equals("3")) 
        {
            if (x < -10) 
            {
                x = 1020;
            }
            x = x - o;
            if (y < -10.0) 
            {
                y = 680.0;
            }
            if (y > 680.0) 
            {
                y = 1.0;
            }
            if (mode == 2 || mode == 3) 
            {
                y = y + Math.sin(Math.toDegrees((double)(this.x / this.fluc)));
            }
        }
        else if (level.equals("4")) 
        {
            if (x < -200) 
            {
                x = 1040;
            }
            x = x - p;
            if (y < -10.0) 
            {
                y = 680.0;
            }
            if (y > 680.0) 
            {
                y = 1.0;
            }
            if (mode == 2 || mode == 3) 
            {
                y = y + Math.sin(Math.toDegrees((double)(this.x / this.fluc)));
            }
        }
        else 
        {
            if (x < 0) 
            {
                x = 1020;
            }
            x = x - 2;
        }
    }
    
    public int getX() 
    {
        return x;
    }
    
    public double getY() 
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
    
    public Image getImage() 
    {
        return this.image;
    }
    
    public Rectangle getBounds() 
    {
        return new Rectangle(x, (int)y, width, height);
    }
    
    public void setHP(int x) 
    {
        hp = x;
    }
    
    public int getHP() 
    {
        return hp;
    }
}