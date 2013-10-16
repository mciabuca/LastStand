package laststandmechanics;

import java.text.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private Craft craft;
    private ArrayList aliens;
    
    private boolean ingame;
    private boolean level1;
    private boolean level2;
    private boolean level3;
    private boolean level4;
    private boolean won;
    
    private int B_WIDTH;
    private int B_HEIGHT;
    private int mode;
    private int totalAliensKilled;
    private int[][] pos;
    
    private String level;
    
    private long startTime;
    private long endTime;
    
    public Board(int difficulty) 
    {
        mode = difficulty;
        this.addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        ingame = true;
        level1 = true;
        level  = "1";
        level2 = false;
        level3 = false;
        level4 = false;
        won    = false;
        
        
        setSize(1000, 700);
        startTime = System.currentTimeMillis();
        craft     = new Craft(mode);
        
        initAliens(20);
        Sound.MAINMUSIC.loop();
        
        timer = new Timer(5, this);
        timer.start();
    }
    
    public Craft getCraft() 
    {
        return this.craft;
    }
    
    public int getRandomXPos() 
    {
        Random random = new Random();
        return random.nextInt(1001) + 1000;
    }
    
    public int getRandomYPos() 
    {
        return new Random().nextInt(620);
    }
    
    public void addNotify()
    {
        super.addNotify();
        this.B_WIDTH  = this.getWidth();
        this.B_HEIGHT = this.getHeight();
    }
    
    public void initAliens(int amount) 
    {
        this.aliens = new ArrayList();
        for (int i = 0; i < amount; i++) 
        {
            if (this.level4) 
            {
                this.aliens.add(new Alien(this.getRandomXPos(), 200, this.level, this.mode));
            }
            else 
            {
                this.aliens.add(new Alien(this.getRandomXPos(), this.getRandomYPos(), this.level, this.mode));
            }
        }
    }
    
    public void paint( Graphics g) 
    {
        super.paint(g);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.craft.getImage(), this.craft.getX(), this.craft.getY(), this);
        if (this.ingame) 
        {
            this.setBackground(Color.BLACK);
            ArrayList ms = craft.getMissiles();
            for (int i = 0; i < ms.size(); i++) 
            {
                Missile m = (Missile)ms.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
            for (int i = 0; i < this.aliens.size(); ++i) 
            {
                Alien a = (Alien)this.aliens.get(i);
                if (a.isVisible()) 
                {
                    g2d.drawImage(a.getImage(), a.getX(), (int)a.getY(), this);
                }
                g2d.setColor(Color.WHITE);
                g2d.drawString(new StringBuilder("Aliens left: ").append(this.aliens.size()).toString(), 5, 15);
                g2d.setColor(Color.YELLOW);
                g2d.drawString("Level: " + this.level, 100, 15);
                g2d.setColor(Color.GREEN);
                g2d.drawString("Instructions: ARROWS to move, SPACE to Shoot. GOOD LUCK!!!", 300, 15);
            }
        }
        else if (this.won) 
        {
        	DecimalFormat df = new DecimalFormat("0.00");
            String msg       = "Game Over: You WON!!!";
            String msg1      = "Would you care to play again?";
            String msg2      = "Tap 'R' to reset.";
            String msg3      = "You lasted a Total Time of: " + df.format((double)(this.endTime - this.startTime) * 0.001) + " seconds";
            this.aliens.clear();
            Font small = new Font("Helvetica", 1, 14);
            FontMetrics metr = this.getFontMetrics(small);
            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (this.B_WIDTH - metr.stringWidth(msg)) / 2, this.B_HEIGHT / 2);
            g.setColor(Color.MAGENTA);
            g.drawString(msg3, (this.B_WIDTH - metr.stringWidth(msg3)) / 2, this.B_HEIGHT / 2 + 20);
            g.setColor(Color.YELLOW);
            g.drawString(msg1, (this.B_WIDTH - metr.stringWidth(msg1)) / 2, this.B_HEIGHT / 2 + 40);
            g.setColor(Color.RED);
            g.drawString(msg2, (this.B_WIDTH - metr.stringWidth(msg2)) / 2, this.B_HEIGHT / 2 + 60);
        }
        else 
        {
        	DecimalFormat df = new DecimalFormat("0.00");
        	String msg       = "Game Over: You LOST.";
            String msg1      = "Would you care to play again?";
            String msg2      = "Tap 'R' to reset.";
            String msg3      = "You lasted a Total Time of: " + df.format((double)(this.endTime - this.startTime) * 0.001) + " seconds and you killed " + this.totalAliensKilled + " Aliens.";
            this.aliens.clear();
            Font small       = new Font("Helvetica", 1, 14);
            FontMetrics metr = this.getFontMetrics(small);
            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (this.B_WIDTH - metr.stringWidth(msg)) / 2, this.B_HEIGHT / 2);
            g.setColor(Color.MAGENTA);
            g.drawString(msg3, (this.B_WIDTH - metr.stringWidth(msg3)) / 2, this.B_HEIGHT / 2 + 20);
            g.setColor(Color.YELLOW);
            g.drawString(msg1, (this.B_WIDTH - metr.stringWidth(msg1)) / 2, this.B_HEIGHT / 2 + 40);
            g.setColor(Color.RED);
            g.drawString(msg2, (this.B_WIDTH - metr.stringWidth(msg2)) / 2, this.B_HEIGHT / 2 + 60);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void actionPerformed( ActionEvent e) 
    {
        if (this.aliens.size() == 0) 
        {
            if (this.level1) 
            {
                this.level1 = false;
                this.level2 = true;
                this.level  = "2";
                this.initAliens(30);
            }
            else if (this.level2) 
            {
                this.level2 = false;
                this.level3 = true;
                this.level  = "3";
                this.initAliens(40);
            }
            else if (this.level3) 
            {
                this.level3 = false;
                this.level4 = true;
                this.level  = "4";
                this.initAliens(1);
            }
        }
        ArrayList ms = craft.getMissiles();
        for (int i = 0; i < ms.size(); i++) 
        {
            final Missile m = (Missile)ms.get(i);
            if (m.isVisible()) 
            {
                m.move();
            }
            else 
            {
                ms.remove(i);
            }
        }
        for (int i = 0; i < aliens.size(); i++) 
        {
            final Alien a = (Alien)this.aliens.get(i);
            if (a.isVisible())
            {
                a.move();
            }
            else 
            {
                this.aliens.remove(i);
            }
        }
        craft.move();
        this.checkCollisions();
        this.repaint();
    }
    
    public void checkCollisions() 
    {
        final Rectangle r3 = this.craft.getBounds();
        Rectangle r2;
        for (int i = 0; i < this.aliens.size(); i++) 
        {
            Alien a = (Alien)this.aliens.get(i);
            r2      = a.getBounds();
            if (r3.intersects(r2)) 
            {
                Sound.CRAFTDEATH.play();
                this.craft.setVisible(false);
                this.ingame  = false;
                this.endTime = System.currentTimeMillis();
            }
        }
        ArrayList ms = this.craft.getMissiles();
        for (int j = 0; j < ms.size(); j++) 
        {
            Missile m    = (Missile)ms.get(j);
            Rectangle r1 = m.getBounds();
            int k        = 0;
            while (k < aliens.size()) 
            {
                Alien a2 = (Alien)this.aliens.get(k);
                r2       = a2.getBounds();
                if (r1.intersects(r2)) 
                {
                    Sound.ALIENHIT.play();
                    if (a2.getHP() <= 0) 
                    {
                        a2.setVisible(Boolean.valueOf(false));
                        m.setVisible(Boolean.valueOf(false));
                        this.totalAliensKilled = this.totalAliensKilled + 1;
                        if (this.level4) 
                        {
                            this.ingame  = false;
                            this.won     = true;
                            this.craft.setVisible(false);
                            this.endTime = System.currentTimeMillis();
                        }
                    }
                    a2.setHP(a2.getHP() - 1);
                    m.setVisible(Boolean.valueOf(false));
                }
                k++;
            }
        }
    }
   class TAdapter extends KeyAdapter
    {
    	public void keyReleased(KeyEvent e) 
    	{
    		craft.keyReleased(e);
    	}
    
    	public void keyPressed( KeyEvent e) 
    	{
    		craft.keyPressed(e);
    	}
    }
}

