import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demo extends JFrame implements ActionListener{

    Mypanel2 mypanel2 = null;

    JButton jb1 = null;
    JButton jb2 = null;
    public static void main(String[] args){
        Demo demo =new Demo();
    }

    public Demo(){
        mypanel2 = new Mypanel2();
        jb1 = new JButton("black");
        jb2 = new JButton("red");
        this.add(mypanel2);
        this.add(jb1,BorderLayout.NORTH);
        this.add(jb2,BorderLayout.SOUTH);
        this.setSize(400,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jb1.addActionListener(this);
        jb1.setActionCommand("black");
        jb2.addActionListener(this);
        jb2.setActionCommand("red");
    }


    // handle event
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("black")){
            System.out.println("you click the black button");
            mypanel2.setBackground(Color.black);
        }else if (e.getActionCommand().equals("red")){
            System.out.println("you click the red button");
            mypanel2.setBackground(Color.red);

        }
    }
}

class Mypanel2 extends JPanel{

    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0,0,200,300);
    }

}
