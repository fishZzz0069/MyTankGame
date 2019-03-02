import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * java draw Tank
 *
*/




public class MyTankGame extends JFrame{

    Mypanel mp = null;


    public MyTankGame(){
        mp = new Mypanel();
        Thread t = new Thread(mp);
        t.start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(400,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        MyTankGame myTankGame = new MyTankGame();
    }

}



// define a class for drawing
class Mypanel extends JPanel implements KeyListener,Runnable{
    //覆盖Jpanel 的 paint方法
    //Graphics 是绘图的重要类，你可以把它理解成一只画笔
    // define my tank
    Hero hero = null;

    Vector<EnemyTank> ets = new Vector<>();
    int enSize = 3;

    Vector<Bomb> bombs = new Vector<>();

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;


    public Mypanel(){
        hero = new Hero(100,100);
        hero.setColor(0);
        for ( int i =0 ; i < enSize ; i++){
            EnemyTank et = new EnemyTank((i+1)*50,0);
            et.setColor(1);
            et.setDirect(2);


            Thread t = new Thread(et);
            t.start();

            // add shots to enemy tank
            Shot s = new Shot(et.getX() + 10 ,et.getY() + 30,et.getDirect());

            et.ss.add(s);
            Thread t2 = new Thread(s);
            t2.start();

            ets.add(et);

        }

        try {
            image1 = ImageIO.read(new File("/Users/zsy/Downloads/Tank/src/bomb_1.gif"));
            image2 = ImageIO.read(new File("/Users/zsy/Downloads/Tank/src/bomb_2.gif"));
            image3 = ImageIO.read(new File("/Users/zsy/Downloads/Tank/src/bomb_3.gif"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        // three images change become a bomb
        //image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        //image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        //image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }
    public void paint(Graphics g){
        //1 调用父类函数完成初始化
        super.paint(g);
        g.fillRect(0,0,400,300);
//        g.setColor(Color.red);
//        g.drawOval(10,10,30,30);
//        g.setColor(Color.CYAN);
//        g.draw3DRect(10,10,40,60,true);
//
//        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/test.png"));
//        g.drawImage(image,90,90,200,150,this);
//        g.drawString("祖国外岁",100,100);

        g.setColor(Color.YELLOW);
        if (hero.isAlive) {
            this.drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), hero.getColor());
        }
        for (int  i = 0 ; i< ets.size() ; i++){

            EnemyTank et  = ets.get(i);

            if (et.isAlive) {
                this.drawTank(et.getX(), et.getY(), g, et.getDirect(), et.getColor());
                for ( int j = 0; j < et.ss.size() ; j ++){
                    Shot enemyShot = et.ss.get(j);
                    if (enemyShot.isLive){
                        g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1, false);
                    }else {
                        et.ss.remove(enemyShot);
                    }
                }
            }
        }

        for (int i = 0 ; i < bombs.size() ; i++){
            Bomb b = bombs.get(i);
            if (b.life > 6){
                g.drawImage(image1,b.x,b.y,30,30,this);
            }else if (b.life >3){
                g.drawImage(image2,b.x,b.y,30,30,this);

            }else {
                g.drawImage(image3,b.x,b.y,30,30,this);

            }

            b.lifeDown();

            // bomb's hp equals zero
            if (b.life == 0){
                bombs.remove(b);
            }
        }


        if (this.hero.shots!=null ){
            for (int i = 0 ; i < hero.shots.size() ; i++) {
                Shot s = hero.shots.get(i);
                if (s.isLive) {
                    g.draw3DRect(s.x, s.y, 1, 1, false);
                }else {
                    hero.shots.remove(s);
                }

            }
        }


    }

    public void hitEnemyTank(){
        for (int i = 0; i <hero.shots.size() ; i++){
            Shot myshot = hero.shots.get(i);
            if (myshot.isLive){
                for (int j = 0;j <ets.size();j++){
                    EnemyTank et = ets.get(j);
                    if (et.isAlive){


                        this.hitTank(myshot,et);
                    }
                }
            }
        }


    }

    public void hitMe(){

        for (int i = 0 ; i < this.ets.size() ; i++){
            EnemyTank et = ets.get(i);
            for (int j =0 ; j < et.ss.size() ; j++){
                Shot enemtShot = et.ss.get(j);
                if (hero.isAlive){
                    if (this.hitTank(enemtShot,hero)){


                    }
                }
            }
        }
    }

    // is bullet touch the tank
    public boolean hitTank(Shot s,Tank et){
        boolean b2=false;



        switch (et.direct){
            case 0:
            case 2:
                if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30){
                    s.isLive = false;
                    et.isAlive = false;
                    b2 = true;
                    Bomb b = new Bomb(et.x,et.y);
                    bombs.add(b);

                }
                break;
            case 1:
            case 3:
                if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20){
                    s.isLive = false;
                    et.isAlive = false;
                    b2 = true;
                    Bomb b = new Bomb(et.x,et.y);
                    bombs.add(b);
                }

        }
        return b2;
    }

    public void drawTank(int x, int y ,Graphics g , int direct , int type){

        switch (type){
            case 0:
            g.setColor(Color.YELLOW);
            break;
            case 1:
            g.setColor(Color.CYAN);
            break;
        }


        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 5, 30, true);
                g.fill3DRect(x + 15, y, 5, 30, true);
                g.fill3DRect(x + 5, y + 5, 10, 20, true);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y);
                break;
            case 1:
                g.fill3DRect(x, y, 30, 5,false);
                g.fill3DRect(x, y+15, 30, 5, false);
                g.fill3DRect(x+5, y+5, 20, 10, false);
                g.fillOval(x+10, y+5, 10, 10);
                g.drawLine(x+15, y+10, x+30, y+10);
                break;
            case 2:
                g.fill3DRect(x, y, 5, 30,false);
                g.fill3DRect(x+15,y , 5, 30,false);
                g.fill3DRect(x+5,y+5 , 10, 20,false);
                g.fillOval(x+5, y+10, 10, 10);
                g.drawLine(x+10, y+15, x+10, y+30);
                break;
            case 3:
                g.fill3DRect(x, y, 30, 5,false);
                g.fill3DRect(x, y+15, 30, 5, false);
                g.fill3DRect(x+5, y+5, 20, 10, false);
                g.fillOval(x+10, y+5, 10, 10);
                g.drawLine(x+15, y+10, x, y+10);
                break;
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W){
            this.hero.setDirect(0);
            this.hero.moveUp();

        }else if (e.getKeyCode() == KeyEvent.VK_D){
            this.hero.setDirect(1);
            this.hero.moveRight();
        }else if (e.getKeyCode() == KeyEvent.VK_S){
            this.hero.setDirect(2);
            this.hero.mobeDown();
        }else if (e.getKeyCode() == KeyEvent.VK_A){
            this.hero.setDirect(3);
            this.hero.moveLeft();
        }else if (e.getKeyCode() == KeyEvent.VK_J){

            if (this.hero.shots.size() <=4) {
                this.hero.shotEnemy();
            }

        }



        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.hitEnemyTank();

            this.hitMe();




            this.repaint();
        }
    }
}


