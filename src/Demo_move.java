import com.sun.org.apache.xpath.internal.operations.String;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * improve the understand about the event handler
 * control the movement of black circle demo
 */

public class Demo_move extends JFrame implements ActionListener{


    Mypanel3 mypanel3  = null;


    public static void main(java.lang.String[] args){
        Demo_move demo_move =new Demo_move();
    }

    public Demo_move(){
        mypanel3 = new Mypanel3();
        this.add(mypanel3);
        this.addKeyListener(mypanel3);
        this.setSize(400,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


class Mypanel3 extends JPanel implements KeyListener{


    int x = 10 ;
    int y = 10 ;
    public void paint(Graphics g){
        super.paint(g);
        g.fillOval(x,y,20,20);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            y++;
        }else if (e.getKeyCode() == KeyEvent.VK_UP){
            y--;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x--;
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            x++;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


