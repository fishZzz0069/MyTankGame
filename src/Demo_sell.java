import com.sun.tools.corba.se.idl.StringGen;

public class Demo_sell {


    public static void main(java.lang.String[] args){
        TicketWindow tw1 = new TicketWindow();
//        TicketWindow tw2 = new TicketWindow();
//        TicketWindow tw3 = new TicketWindow();
        Thread t1 = new Thread(tw1);
        Thread t2 = new Thread(tw1);
        Thread t3 = new Thread(tw1);
        t1.start();
        t2.start();
        t3.start();

    }
}


class TicketWindow implements Runnable{

    private int nums = 2000;


    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                if (nums > 0) {
                    //show the ticket info
                    System.out.println(Thread.currentThread().getName() + "is selling" + nums + "ticket");
                    nums--;
                } else {
                    // stop selling
                    break;
                }
            }

        }

    }
}