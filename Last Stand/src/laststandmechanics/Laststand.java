package laststandmechanics;

import javax.swing.*;

public class Laststand extends JFrame
{
    private int difficulty;
    public JFrame frame;
    public Board board;
    public static Laststand Laststand;
    
    public Laststand() 
    {
        frame = new JFrame();
        Object[] options = { "Recruit", "Last Stand", "Veteran", "Martin Mode", "I quit" };
        difficulty = JOptionPane.showOptionDialog(null, "Choose your difficulty: ", "Last Stand", 1, 3, null, options, options[0]);
        
        if (difficulty == 4) 
        {
            System.exit(1);
        }
        init(this.difficulty);
    }
    
    public void init(final int n) 
    {
        board = new Board(n);
        frame.add(this.board);
        frame.setDefaultCloseOperation(3);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Last Stand");
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void reset() 
    {
        frame.remove(board);
        Object[] options = { "Recruit", "Last Stand", "Veteran", "Martin Mode", "I quit" };
        difficulty = JOptionPane.showOptionDialog(null, "Choose your difficulty: ", "Last Stand", 1, 3, null, options, options[0]);
        
        if (difficulty == 4) 
        {
            System.exit(1);
        }
        init(difficulty);
    }
    public static void main(String[] args) 
    {
        Laststand = new Laststand();
    }
}