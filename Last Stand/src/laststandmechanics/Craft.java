package laststandmechanics;

import java.awt.event.*;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Craft
{
    private String craft;
    private int dx;
    private int dy;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;
    private ArrayList missiles;
    private int mode;
    private ImageIcon ii;
    
    public Craft(int difficulty) 
    {
        
        craft    = "Assets/Images/craft.png";
        visible  = true;
        mode     = difficulty;
        ii       = new ImageIcon(this.getClass().getResource(this.craft));
        image    = this.ii.getImage();
        width    = this.image.getWidth(null);
        height   = this.image.getHeight(null);
        missiles = new ArrayList();
        x        = 100;
        y        = 100;
    }
   
    public void move() 
    {
        x = x + dx;
        y = y + dy;
        if (x < 1) 
        {
            x = 970;
        }
        if (x > 970) 
        {
            x = 1;
        }
        if (y < 1) 
        {
            y = 645;
        }
        if (y > 645) 
        {
            y = 1;
        }
    }
    
    public int getX() 
    {
        return x;
    }
    
    public int getY() 
    {
        return y;
    }
    
    public Image getImage() 
    {
        if (!visible) {
            return null;
        }
        return image;
    }
    
    public ArrayList getMissiles() 
    {
        return missiles;
    }
    
    public void setVisible(boolean visible) 
    {
        this.visible = visible;
    }
    
    public boolean isVisible() 
    {
        return visible;
    }
    
    public Rectangle getBounds() 
    {
        return new Rectangle(x, y, width, height);
    }
    
    public void fire(int n) 
    {
    	if(missiles.size() <= n) 
    	{
    		Sound.MISSILESHOT.play();
            missiles.add(new Missile(x + width, y + height / 2));  
    	}
    }
    
    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if(key == 32) 
        {
            int n = 8;
            if (mode == 0) 
            {
                n = 10;
            }
            if (mode == 1) 
            {
                n = 8;
            }
            if (mode == 2) 
            {
                n = 6;
            }
            if (mode == 3) 
            {
                n = 4;
            }
            fire(n);
        }
        
        ImageIcon ii;
        
        if(key == 37) 
        {
            if(mode == 3)
            {
                dx = -2;
            }
            else 
            {
                dx = -1;
            }
            
            ii     = new ImageIcon(this.getClass().getResource("Assets/Images/craftLeft.png"));
            image  = ii.getImage();
            width  = this.image.getWidth(null);
            height = this.image.getHeight(null);
        }
        if (key == 39)
        {
            if (this.mode == 3) 
            {
                dx = 2;
            }
            else 
            {
                dx = 1;
            }
            ii          = new ImageIcon(this.getClass().getResource("Assets/Images/craft.png"));
            this.image  = ii.getImage();
            this.width  = this.image.getWidth(null);
            this.height = this.image.getHeight(null);
        }
        if (key == 38) 
        {
            if (mode == 3) 
            {
                dy = -2;
            }
            else 
            {
                dy = -1;
            }
            ii     = new ImageIcon(this.getClass().getResource("Assets/Images/craftUp.png"));
            image  = ii.getImage();
            width  = this.image.getWidth(null);
            height = this.image.getHeight(null);
        }
        if (key == 40) 
        {
            if (mode == 3) 
            {
                dy = 2;
            }
            else 
            {
                dy = 1;
            }
            ii     = new ImageIcon(this.getClass().getResource("Assets/Images/craftDown.png"));
            image  = ii.getImage();
            width  = this.image.getWidth(null);
            height = this.image.getHeight(null);
        }
        if (key == 82) 
        {
            Laststand.Laststand.reset();
        }
    }
    
    public void keyReleased(final KeyEvent e) 
    {
    	int key = e.getKeyCode();
        ImageIcon ii;
        if (key == 37) 
        {
            dx     = 0;
            ii     = new ImageIcon(this.getClass().getResource("Assets/Images/craft.png"));
            image  = ii.getImage();
            width  = this.image.getWidth(null);
            height = this.image.getHeight(null);
        }
        if (key == 39) 
        {
            dx     = 0;
            ii     = new ImageIcon(this.getClass().getResource("Assets/Images/craft.png"));
            image  = ii.getImage();
            width  = this.image.getWidth(null);
            height = this.image.getHeight(null);
        }
        if (key == 38) 
        {
            dy     = 0;
            ii     = new ImageIcon(this.getClass().getResource("Assets/Images/craft.png"));
            image  = ii.getImage();
            width  = this.image.getWidth(null);
            height = this.image.getHeight(null);
        }
        if (key == 40) 
        {
            dy     = 0;
            ii     = new ImageIcon(this.getClass().getResource("Assets/Images/craft.png"));
            image  = ii.getImage();
            width  = this.image.getWidth(null);
            height = this.image.getHeight(null);
        }
   }
}
