package main;

public class Vector2D {

    private int x;
    private int y;
       
    public Vector2D(int x,int y){
        this.x=x;
        this.y=x;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y=y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void add(Vector2D v){
        x+=v.getX();
        y+=v.getY();
    }
    
    public void multiply(int i){
        x*=i;
        y*=i;
    }
}
