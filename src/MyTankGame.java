import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


    public Mypanel(){
        hero = new Hero(100,100);
        hero.setColor(0);
        for ( int i =0 ; i < enSize ; i++){
            EnemyTank et = new EnemyTank((i+1)*50,0);
            et.setColor(1);
            et.setDirect(2);
            ets.add(et);
        }
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
        this.drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),hero.getColor());
        for (int  i = 0 ; i< ets.size() ; i++){
            this.drawTank(ets.get(i).getX(),ets.get(i).getY(),g,ets.get(i).getDirect(),ets.get(i).getColor());
        }

        if (this.hero.shot!=null  ){
            g.draw3DRect(this.hero.shot.x, this.hero.shot.y, 1, 1,false);
        }


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
            this.hero.shotEnemy();

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
            this.repaint();
        }
    }
}


