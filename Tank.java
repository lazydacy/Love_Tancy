public class Tank {
    //坦克ddujaindujialonghuanweishoujimaidongfangyuanqingkelianhua 坐标
    private int x;
    private int y;
    private int direct;//0123-上下左右
    private int speed=4;
    private int health;


    public Tank(int y, int x,int direct) {
        this.y = y;
        this.x = x;
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    //定义坦克移动
    public void moveUp() {
        if(y>=speed){
            y-=speed;
        }
    }
    public void moveDown() {
        if(y<=750-60-speed){
            y+=speed;
        }
    }
    public void moveLeft() {
        if(x>=speed){
            x-=speed;
        }
    }
    public void moveRight() {
        if(x<=1000-60-speed){
            x+=speed;
        }
    }
}
