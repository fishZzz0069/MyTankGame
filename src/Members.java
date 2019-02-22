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

    public Tank(int x,int y){
        this.x = x;
        this.y = y;
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