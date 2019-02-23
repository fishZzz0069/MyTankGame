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

    public Tank(int x,int y){
        this.x = x;
        this.y = y;
    }

    // fire
    public void shotEnemy(){


        switch (this.direct){
            case 0:
                shot = new Shot(x+10,y,0);
                break;
            case 1:
                shot = new Shot(x+30,y+10,1);
                break;
            case 2:
                shot = new Shot(x+10,y+30,2);
                break;
            case 3:
                shot = new Shot(x,y+10,3);
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


class EnemyTank extends Tank{

    public EnemyTank(int x, int y) {
        super(x, y);
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