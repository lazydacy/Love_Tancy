import javax.swing.*;

public class TankGame extends JFrame {

    MyPanel mp=null;
    public static void main(String[] args) throws InterruptedException {
        new TankGame();
    }
    public TankGame() throws InterruptedException {
        mp=new MyPanel();
        Thread thread=new Thread(mp);
        thread.start();
        add(mp);
        addKeyListener(mp);
        setSize(1014,786);
        setTitle("坦克大战by杜大龙");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
