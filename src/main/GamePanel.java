package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    
    //GamePanel's width and height
    private final int PHEIGHT=480;
    private final int PWIDTH=640;
   
    //panels constructor
    private Snake snake;
    private Food food;
    public GamePanel() {
        
        super();
        setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
        setFocusable(true);
        requestFocus();
        setUpMouse();
        setUpKey();
        food=new Food(PWIDTH,PHEIGHT);
        snake=new Snake(this,food,PWIDTH,PHEIGHT);
        food.init(snake);      
    }        
    
    //addNotify is called when panel is added to frame and visible
    private Thread thread;
    @Override
    public void addNotify(){
        super.addNotify();
        startThread();
    }
    
    //run method in thread (Game Loop)
    // boolean in while loop in run
    private volatile boolean running=false;
    @Override
    public void run(){
        
        running=true;
        while(running){
            gameUpdate();
            gameRender();
            paintScreen();
            
            try{
                thread.sleep(50);
            }catch(Exception e){}
        }
        
    }
    
    // this method is called when JFrame is closing
    public void stop() {
        running=false;
    }
    
    // update if game is not paused and game is not over 
    volatile boolean gameOver=false;
    private void gameUpdate() {

        if(!isPaused && !gameOver){
            snake.update();
        }
    }
    
    //gameRender uses img as buffer
    //paints into img
    private Image img;
    private final Color background=Color.BLACK;
    private void gameRender() {

        img=createImage(PWIDTH,PHEIGHT);
        Graphics g=img.getGraphics();
        g.setColor(background);
        g.fillRect(0,0,PWIDTH,PHEIGHT);
        snake.draw(g);
        food.draw(g);
        if(gameOver){
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));
            g.drawString("Game Over! Score: "+(snake.foodEaten*5),200,200);
        }
        g.dispose();
    }
    
    //paintScreen paints buffer to screen
    //gets the Graphics of panel and paints the buffer image
    private void paintScreen() {

        Graphics g=this.getGraphics();
        g.drawImage(img,0,0,null);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    private volatile boolean isPaused=false;
    public void pause() {
        isPaused=true;
    }

    public void resume() {
        isPaused=false;
    }
    
    
    //method binds action to mouse input on JPanel
    private void setUpMouse() {
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                JOptionPane.showMessageDialog(null,e.getX()+" : "+e.getY());
            }
        });
        
    }
    
    //method bind action to key input on JPanel
    private void setUpKey() {
        
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyChar()=='p') pause();
                if(e.getKeyChar()=='l') resume();
                if(!isPaused){
                    int keycode=e.getKeyCode();
                    snake.move(keycode);
                }
            }
        });
    }
    
    //called by addNotify to start game loop
    private void startThread() {
        
        if(thread==null){
            thread=new Thread(this);
            thread.start();
        }        
    }   
    
}
