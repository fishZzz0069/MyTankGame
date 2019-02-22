import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * java draw Tank
 *
*/




public class MyTankGame extends JFrame{

    Mypanel mp = null;


    public MyTankGame(){
        mp = new Mypanel();
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
class Mypanel extends JPanel implements KeyListener{
    //覆盖Jpanel 的 paint方法
    //Graphics 是绘图的重要类，你可以把它理解成一只画笔
    // define my tank
    Hero hero = null;

    public Mypanel(){
        hero = new Hero(100,100);
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
        drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);


    }

    public void drawTank(int x, int y ,Graphics g , int direct , int type){

        switch (type){
            case 0:
            g.setColor(Color.YELLOW);
            case 1:
            g.setColor(Color.CYAN);
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
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


