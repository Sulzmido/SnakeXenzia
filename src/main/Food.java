package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Food {
    
    private int fWIDTH;
    private int fHEIGHT;
    private Random rnd;
    public Food(int PWIDTH,int PHEIGHT){
        
        fWIDTH=PWIDTH;
        fHEIGHT=PHEIGHT;
        rnd=new Random();
        pFood=new Point();
        pFood.x=rndMultiple(fWIDTH);
        pFood.y=rndMultiple(fHEIGHT);
    }
    
    private int rndMultiple(int bound){
        int num;
        while(true){
            num=Math.abs(rnd.nextInt()%bound);
            if(num%foodSize==0) return num;
        }
    }
    
    public Point pFood;
    private final int foodSize=10;
    private final Color foodColor=Color.RED;
    public void draw(Graphics g) {
        
        g.setColor(foodColor);       
        g.fillOval(pFood.x,pFood.y,foodSize,foodSize);
    }
    
    public void respawnFood(){
        
        pFood.x=rndMultiple(fWIDTH);
        pFood.y=rndMultiple(fHEIGHT);
        for(int i=0;i<fSnake.sLength;i++){
            
            if(pFood.x==fSnake.box[i].x && pFood.y==fSnake.box[i].y){
                respawnFood();
            }
        }
    }

    private Snake fSnake;
    public void init(Snake snake) {
        fSnake=snake;
    }
}
