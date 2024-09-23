/**
 * 子弹类，定义位置速度等相关属性
 */
public class Shot extends Thread{
    int x;
    int y;
    int speed;
    int direction;
    boolean shotLive=true;
    public Shot(int x, int y, int speed,int direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    @Override
    public void run() {

        while(shotLive){

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch(direction){
                case 0:
                    y-=speed;
                    break;
                case 1:
                    y+=speed;
                    break;
                case 2:
                    x-=speed;
                    break;
                case 3:
                    x+=speed;
                    break;
            }

            if(!(x>=0&&x<=1000&&y>=0&&y<=750)){
                shotLive=false;
                break;
            }


        }
    }
}
