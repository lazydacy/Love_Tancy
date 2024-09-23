import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.util.Vector;

/**
 *坦克大战绘图区域
 */

public class MyPanel extends JPanel implements KeyListener,Runnable {

    //定义我的坦克
    Hero hero=null;
    //定义敌方坦克
    Vector<EnemyTank> enemyTanks=new Vector<>();
    //定义爆炸图片
    Vector<Bomb> bombs=new Vector<>();

    //定义6张爆炸图片
    Image[] images=new Image[6];

    //定义坦克位置文件
    //String filePath ="src:\\location.txt";
    //new BufferedWriter(new FileWriter(filePath,true));

    //定义初始敌方坦克数量
    int enemyTankSize=3;


    //初始化所需数据
    public MyPanel( ) throws InterruptedException {
        //初始化敌方坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank=new EnemyTank(0,300*(i+1),1);
            enemyTank.setEnemyTanks(enemyTanks);
            Thread thread =new Thread(enemyTank);
            thread.start();
            enemyTanks.add(enemyTank);
        }

        //我方坦克
        hero=new Hero(500,690,0);
        //hero.setSpeed(10);

        //导入爆炸图片
        MediaTracker tracker = new MediaTracker(this);
        for (int i=0;i<images.length;i++) {
            images[i]=Toolkit.getDefaultToolkit().getImage(getClass().getResource("bomb_"+(i+1)+".png"));
            tracker.addImage(images[i],0);
            tracker.waitForAll();
        }
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);

        if(hero!=null){
            drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),0);
            //判断我方子弹是否击中
            for (int i=0;i<hero.shots.size();i++){
                Shot shot=hero.shots.get(i);
                if(!shot.shotLive){
                    hero.shots.remove(i);
                }
                drawShot(shot.x,shot.y,g,0);
                //先判断是否有敌方坦克被击中，将被击中的踢出去，和画坦克写在一起会导致闪烁
                for (int j = 0; j < enemyTankSize; j++) {
                    EnemyTank enemyTank =enemyTanks.get(j);
                    if (getShot(shot,enemyTank)) {
                        shot.shotLive = false;
                        enemyTanks.remove(j);
                        //hero.shots.remove(i);
                        enemyTankSize--;
                    }
                }
            }
        }

        //画敌方子弹
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank =enemyTanks.get(i);
            if(enemyTank.shot!=null&&enemyTank.shot.shotLive){
                drawShot(enemyTank.shot.x,enemyTank.shot.y,g,1);
            }
            //判断是否被敌方子弹击中
            if(hero!=null&&getShot(enemyTank.shot,hero)){
                enemyTank.shot.shotLive=false;
                hero=null;
            };
        }


        //画出敌方坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank =enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
        }

        //画爆炸
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb =bombs.get(i);
            if(bomb.live!=0){
                g.drawImage(images[bomb.live-1],bomb.x,bomb.y,60,60,this);
            }
            bomb.lifeDown();
            if(!bomb.isLive){
                bombs.remove(i);
            }
        }
        //调用画坦克的方法，因为其他地方还有可能用到
    }

//画坦克
    public void drawTank(int x,int y,Graphics g,int direct,int type ){
        switch(type){
            //己方坦克
            case 0:
                g.setColor(Color.cyan);
                break;
            //敌方坦克
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        switch(direct){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y,x+20,y+30);
                break;
            case 1://向下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+60,x+20,y+30);
                break;
            case 2://向左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
            case 3://向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
        }

    }
//画子弹
    public  void drawShot(int x,int y,Graphics g,int type){
        switch(type){
            //己方坦克
            case 0:
                g.setColor(Color.cyan);
                break;
            //敌方坦克
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        g.draw3DRect(x,y,2,2,false);

    }
    //被子弹击中事件
    public boolean getShot(Shot shot,Tank Tank){
        switch (Tank.getDirect()){
            case 0:
            case 1:
                if(shot.x>Tank.getX()&&shot.x<Tank.getX()+40&&shot.y>Tank.getY()&&shot.y< Tank.getY()+60){
                    Bomb bomb = new Bomb(Tank.getX(), Tank.getY());
                    bombs.add(bomb);
                    return true;
                }
                break;
            case 2:
            case 3:
                if(shot.x>Tank.getX()&&shot.x<Tank.getX()+60&&shot.y> Tank.getY()&&shot.y< Tank.getY()+40){
                    Bomb bomb = new Bomb(Tank.getX(), Tank.getY());
                    bombs.add(bomb);
                    return true;
                }
                break;
        }
        return false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按键按下事件
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        if(hero!=null){
            switch(e.getKeyCode()){
                //忘了写break了，switch穿透
                case KeyEvent.VK_UP:
                    hero.moveUp();
                    hero.setDirect(0);
                    break;
                case KeyEvent.VK_DOWN:
                    hero.moveDown();
                    hero.setDirect(1);
                    //System.out.println(hero.getY());
                    break;
                case KeyEvent.VK_LEFT:
                    hero.moveLeft();
                    hero.setDirect(2);
                    break;
                case KeyEvent.VK_RIGHT:
                    hero.moveRight();
                    hero.setDirect(3);
                    //System.out.println(hero.getX());
                    break;
            }

            if(e.getKeyCode()==KeyEvent.VK_J){
                //System.out.println("英雄发射子弹了");
                hero.heroShot();
            }
            //&&(hero.shot==null|| !hero.shot.shotLive)
            this.repaint();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    //绘图多线程
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("调用了绘图多线程");
            this.repaint();
        }

    }
}
