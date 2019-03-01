import java.awt.*;
import java.util.Vector;

public class Members {

}
class Tank{
    //表示tank的横坐标
    int x = 0;
    //坦克的纵坐标
    int y = 0;

    //0 up 1 down 2 right 3 left
    int direct  = 0 ;

    /*
    speed of tank
     */
    int speed = 1;

    int color = 0 ;

    Shot shot = null;

    boolean isAlive = true;

    Vector<Shot> shots = new Vector<>();

    public Tank(int x,int y){
        this.x = x;
        this.y = y;
    }

    // fire
    public void shotEnemy(){


        switch (this.direct){
            case 0:
                shot = new Shot(x+10,y,0);
                shots.add(shot);
                break;
            case 1:
                shot = new Shot(x+30,y+10,1);
                shots.add(shot);
                break;
            case 2:
                shot = new Shot(x+10,y+30,2);
                shots.add(shot);
                break;
            case 3:
                shot = new Shot(x,y+10,3);
                shots.add(shot);
                break;
        }
        Thread t = new Thread(shot);
        t.start();

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

class Hero extends Tank{




    public Hero(int x, int y) {
        super(x, y);
    }



    // Tank up move
    public void moveUp(){
        y -= speed;
    }

    public void moveRight(){
        x += speed;
    }

    public void mobeDown(){
        y += speed;
    }

    public void moveLeft(){
        x -= speed;
    }



}


class EnemyTank extends Tank implements Runnable{


    boolean isLive = true;

     int times = 0;

    Vector<Shot> ss = new Vector<>();

    // add shots

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {


        while (true){


            try {
                Thread.sleep(50);
            }catch (Exception e){
                e.printStackTrace();
            }

            switch (this.direct){
                case 0:
                    for (int i =0 ; i < 30 ; i++) {
                        if (y > 0){
                           y -= speed;
                        }

                        try {
                            Thread.sleep(50);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i =0 ; i < 30 ; i++) {
                        if (x<300) {
                            x += speed;
                        }

                        try {
                            Thread.sleep(50);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    break;
                case 2:
                    for (int i = 0; i < 30 ; i++) {
                        if (y<300) {
                            y += speed;
                        }

                        try {
                            Thread.sleep(50);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0;i < 30 ; i++) {
                        if (x>0) {
                            x -= speed;
                        }

                        try {
                            Thread.sleep(50);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
            }
//            if (ss.size() < 1 ){
//                Shot s = new Shot(this.x,this.y,this.direct);
//            }

            times ++;
            if(times%2==0)
            {
                if(isLive)
                {
                    if(ss.size()<5)
                    {
                        //System.out.println("et.ss.size()<5="+et.ss.size());
                        Shot s=null;

                        switch(direct)
                        {
                            case 0:
                                s=new Shot(x+10,y,0);
                                ss.add(s);
                                break;
                            case 1:
                                s=new Shot(x+30,y+10,1);
                                ss.add(s);
                                break;
                            case 2:
                                s=new Shot(x+10,y+30,2);
                                ss.add(s);
                                break;
                            case 3:
                                s=new Shot(x,y+10,3);
                                ss.add(s);
                                break;
                        }

                        Thread t=new Thread(s);
                        t.start();
                    }
                }
            }

            this.direct = (int)(Math.random()*4);
            if (!this.isLive){
                // if this tank is dead , break
                break;
            }




        }




    }
}

class Shot implements Runnable{

    int x;
    int y;
    int direct;
    int speed = 1;

    boolean isLive = true;

    public Shot(int x , int y , int direct){
        this.x = x;
        this.y = y;
        this.direct = direct;
    }


    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 0:
                    y-=speed ;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;
            }
            System.out.println("x:"+x );
            if (x<0 || x>400 || y<0 || y>300){
                this.isLive = false;
                break;
            }
        }
    }
}

class Bomb {
    // define x of bomb
    int x,y ;
    // the Hp of Bomb
    int life = 9;
    boolean isLive = true;
    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }
    //reduce Hp
    public void lifeDown(){
        if (life > 0){
            life--;
        }else {
            this.isLive = false;
        }
    }



}