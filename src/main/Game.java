package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class Game extends JFrame implements WindowListener{
    
    public static void main(String[] args){
       
        Game game=new Game();
        
    }
    
    private GamePanel gp;
    public Game(){
        
        super("Test Game");
        addWindowListener(this);
        gp=new GamePanel();
        setContentPane(gp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);     
        setVisible(true);
        
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        gp.stop();
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {
       gp.pause();
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        gp.resume();
    }

    @Override
    public void windowActivated(WindowEvent e) {
        gp.resume();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
       gp.pause();
    }
}
