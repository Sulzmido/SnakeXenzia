package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {
    
    public Rectangle[] box;
    private Vector2D displacement;
    public int sLength;
    private Food sFood;
    public int foodEaten;
    private GamePanel sGp;
    private int sWidth;
    private int sHeight;
    
    public Snake(GamePanel gp,Food food,int PWIDTH,int PHEIGHT){
        
        sGp=gp;
        sFood=food;
        sWidth=PWIDTH;
        sHeight=PHEIGHT;
        box=new Rectangle[arraySize];
        displacement=new Vector2D(0,0);
        initArray();       
        sLength=3;
        foodEaten=0;
    }
    
    private Color bodyColor=Color.WHITE;
    private Color headColor=Color.PINK;
    private Color partition=Color.BLACK;
    
    public void draw(Graphics g) {
        
        for(int i=0;i<sLength;i++){     
                        
            if(i==0){
                
                g.setColor(headColor);
                g.fillOval(box[i].x,box[i].y,box[i].height,box[i].width);
                continue;
            }
            g.setColor(bodyColor);
            g.fillOval(box[i].x,box[i].y,box[i].height,box[i].width);
            g.setColor(partition);
            g.drawOval(box[i].x,box[i].y,box[i].height,box[i].width);
        }
    }

    public void update() {
        
        if(keyPressed){
            
            for(int i=(sLength-1);i>0;i--){
                box[i].x=box[i-1].x;
                box[i].y=box[i-1].y;
            }
        }
        
        box[0].x+=displacement.getX();
        box[0].y+=displacement.getY();
        
        checkHead();
    }
    
    private boolean keyPressed=false;
    public void move(int keycode){
        
        switch(keycode){
            case (KeyEvent.VK_UP):
                if(displacement.getX()==0 && displacement.getY()==bLength) break;                
                displacement.setX(0);
                displacement.setY(-bLength);
            break;
            case KeyEvent.VK_DOWN:
                if(displacement.getX()==0 && displacement.getY()==-bLength) break;
                displacement.setX(0);
                displacement.setY(bLength);
            break;
            case KeyEvent.VK_LEFT:
                if(displacement.getX()==bLength && displacement.getY()==0) break;
                displacement.setX(-bLength);
                displacement.setY(0);
            break;
            case KeyEvent.VK_RIGHT:
                if(displacement.getX()==-bLength && displacement.getY()==0) break;
                displacement.setX(bLength);
                displacement.setY(0);
            break;
        }
        update();
        keyPressed=true;
    }

    private final int bLength=10;
    private final int arraySize=100;
    private void initArray() {
        
        for(int i=0;i<arraySize;i++){
            
            if(i==0){
                box[i]=new Rectangle(0,0,bLength,bLength);
                continue;
            }
            box[i]=new Rectangle(sWidth,sHeight,bLength,bLength);
            
        }
    }
    
    private void foodEaten(int x,int y){
        //System.out.println(x+"-"+sFood.pFood.x+" "+y+"-"+sFood.pFood.y);
        if(x==sFood.pFood.x && y==sFood.pFood.y){
            sFood.respawnFood();
            if(sLength<arraySize) sLength++;
            foodEaten++;
        }
    }

    private void checkHead() {
        
        foodEaten(box[0].x,box[0].y);
        checkHeadInScreen();   
        checkHeadOnBody();
    }

    private void checkHeadInScreen() {
        
        if(box[0].y<0){
            box[0].y=(sHeight-bLength);
        }
        if(box[0].x<0){
            box[0].x=(sWidth-bLength);
        }
        if(box[0].x>=sWidth){
            box[0].x=0;
        }
        if(box[0].y>=sHeight){
            box[0].y=0;
        }       
    }

    private void checkHeadOnBody() {
              
        for(int i=1;i<sLength;i++){
            if((box[i].y==box[0].y) && (box[i].x==box[0].x)){
                sGp.gameOver=true;
                break;
            }
        }
    }
 
}
