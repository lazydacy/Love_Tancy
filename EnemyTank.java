import java.util.Random;
import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    // Vector<String> enemyShots=new Vector<>();
    Shot shot = null;
    //设定前进的距离
    int time = 0;

    //定义全部敌方坦克数组，用于遍历防止折叠
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //判断是否重叠,重叠返回true
    public Boolean isOverlap() {
        for (int i = 0; i < this.enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            int dir = enemyTank.getDirect();
            if (enemyTank != this) {
                switch (this.getDirect()) {
                    case 0:
                        switch (dir) {
                            //敌方坦克是上下位置两种情况
                            case 0:
                            case 1:
                                //判断左上点是否碰到
                                if (enemyTank.getX() <= this.getX()
                                        && enemyTank.getX() + 40 >= this.getX()
                                        && enemyTank.getY() <= this.getY()
                                        && enemyTank.getY() + 60 >= this.getY()
                                ) {
                                    return true;
                                }
                                //判断右上方点是否碰到
                                if (enemyTank.getX() <= this.getX() + 40
                                        && enemyTank.getX() + 40 >= this.getX() + 40
                                        && enemyTank.getY() <= this.getY()
                                        && enemyTank.getY() + 60 >= this.getY()
                                ) {
                                    return true;
                                }

                                //敌方坦克是左右位置两种情况
                            case 2:
                            case 3:
                                //判断左上点是否碰到
                                if (enemyTank.getX() <= this.getX()
                                        && enemyTank.getX() + 60 >= this.getX()
                                        && enemyTank.getY() <= this.getY()
                                        && enemyTank.getY() + 40 >= this.getY()
                                ) {
                                    return true;
                                }
                                //判断右上方点是否碰到
                                if (enemyTank.getX() <= this.getX() + 40
                                        && enemyTank.getX() + 60 >= this.getX() + 40
                                        && enemyTank.getY() <= this.getY()
                                        && enemyTank.getY() + 40 >= this.getY()
                                ) {
                                    return true;
                                }
                        }
                    case 1:
                        switch (dir) {
                            //敌方坦克是上下位置两种情况
                            case 0:
                            case 1:
                                //判断左下点是否碰到
                                if (enemyTank.getX() <= this.getX()
                                        && enemyTank.getX() + 40 >= this.getX()
                                        && enemyTank.getY() <= this.getY() + 60
                                        && enemyTank.getY() + 60 >= this.getY() + 60
                                ) {
                                    return true;
                                }
                                //判断右下方点是否碰到
                                if (enemyTank.getX() <= this.getX() + 40
                                        && enemyTank.getX() + 40 >= this.getX() + 40
                                        && enemyTank.getY() <= this.getY() + 60
                                        && enemyTank.getY() + 60 >= this.getY() + 60
                                ) {
                                    return true;
                                }

                                //敌方坦克是左右位置两种情况
                            case 2:
                            case 3:
                                //判断左下点是否碰到
                                if (enemyTank.getX() <= this.getX()
                                        && enemyTank.getX() + 60 >= this.getX()
                                        && enemyTank.getY() <= this.getY() + 60
                                        && enemyTank.getY() + 40 >= this.getY() + 60
                                ) {
                                    return true;
                                }
                                //判断右上方点是否碰到
                                if (enemyTank.getX() <= this.getX() + 40
                                        && enemyTank.getX() + 60 >= this.getX() + 40
                                        && enemyTank.getY() <= this.getY() + 60
                                        && enemyTank.getY() + 40 >= this.getY() + 60
                                ) {
                                    return true;
                                }
                        }
                    case 2:
                        switch (dir) {
                            //敌方坦克是上下位置两种情况
                            case 0:
                            case 1:
                                //判断左上点是否碰到
                                if (enemyTank.getX() <= this.getX()
                                        && enemyTank.getX() + 40 >= this.getX()
                                        && enemyTank.getY() <= this.getY()
                                        && enemyTank.getY() + 60 >= this.getY()
                                ) {
                                    return true;
                                }
                                //判断右上方点是否碰到
                                if (enemyTank.getX() <= this.getX()
                                        && enemyTank.getX() + 40 >= this.getX()
                                        && enemyTank.getY() <= this.getY() + 40
                                        && enemyTank.getY() + 60 >= this.getY() + 40
                                ) {
                                    return true;
                                }

                                //敌方坦克是左右位置两种情况
                            case 2:
                            case 3:
                                //判断左上点是否碰到
                                if (enemyTank.getX() <= this.getX()
                                        && enemyTank.getX() + 60 >= this.getX()
                                        && enemyTank.getY() <= this.getY()
                                        && enemyTank.getY() + 40 >= this.getY()
                                ) {
                                    return true;
                                }
                                //判断右上方点是否碰到
                                if (enemyTank.getX() <= this.getX()
                                        && enemyTank.getX() + 60 >= this.getX()
                                        && enemyTank.getY() <= this.getY() + 40
                                        && enemyTank.getY() + 40 >= this.getY() + 40
                                ) {
                                    return true;
                                }
                        }
                    case 3:
                        switch (dir) {
                            //敌方坦克是上下位置两种情况
                            case 0:
                            case 1:
                                //判断左上点是否碰到
                                if (enemyTank.getX() <= this.getX() + 60
                                        && enemyTank.getX() + 40 >= this.getX() + 60
                                        && enemyTank.getY() <= this.getY()
                                        && enemyTank.getY() + 60 >= this.getY()
                                ) {
                                    return true;
                                }
                                //判断右上方点是否碰到
                                if (enemyTank.getX() <= this.getX() + 60
                                        && enemyTank.getX() + 40 >= this.getX() + 60
                                        && enemyTank.getY() <= this.getY() + 40
                                        && enemyTank.getY() + 60 >= this.getY() + 40
                                ) {
                                    return true;
                                }

                                //敌方坦克是左右位置两种情况
                            case 2:
                            case 3:
                                //判断左上点是否碰到
                                if (enemyTank.getX() <= this.getX() + 60
                                        && enemyTank.getX() + 60 >= this.getX() + 60
                                        && enemyTank.getY() <= this.getY()
                                        && enemyTank.getY() + 40 >= this.getY()
                                ) {
                                    return true;
                                }
                                //判断右上方点是否碰到
                                if (enemyTank.getX() <= this.getX() + 60
                                        && enemyTank.getX() + 60 >= this.getX() + 60
                                        && enemyTank.getY() <= this.getY() + 40
                                        && enemyTank.getY() + 40 >= this.getY() + 40
                                ) {
                                    return true;
                                }
                        }

                }
            }


        }
        return false;
    }

    public EnemyTank(int x, int y, int direct) {
        super(x, y, direct);
    }

    @Override
    public void run() {
        Random random = new Random();

        //随机方向
        while (true) {
            //生成子弹
            if (shot == null || !shot.shotLive) {
                switch (getDirect()) {
                    case 0:
                        shot = new Shot(getX() + 20, getY(), 10, getDirect());
                        break;
                    case 1:
                        shot = new Shot(getX() + 20, getY() + 60, 10, getDirect());
                        break;
                    case 2:
                        shot = new Shot(getX(), getY() + 20, 10, getDirect());
                        break;
                    case 3:
                        shot = new Shot(getX() + 60, getY() + 20, 10, getDirect());
                        break;
                }
                new Thread(shot).start();
            }

            //shot.start();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //随机前近距离
            if (time > random.nextInt(20, 80)||isOverlap()) {
                //随机方向随机移动
                this.setDirect(random.nextInt(4));
                time = 0;
            }

            switch (this.getDirect()) {
                    case 0 -> this.moveUp();
                    case 1 -> this.moveDown();
                    case 2 -> this.moveLeft();
                    case 3 -> this.moveRight();
            }

            this.time++;

            //判断是否到达边界
            if (!(this.getX() > 0 && this.getX() < 1000 - 60 - this.getSpeed() && this.getY() > 0 && this.getY() < 750 - 60 - this.getSpeed())) {
                this.setDirect(random.nextInt(4));
            }
        }
    }
}
