public class Bomb {
    //炸弹坐标
    int x;
    int y;
    //爆炸生命周期，依次判断放哪个图片
    int live=6;
    boolean isLive=true;

    public Bomb(int x, int y) {
        this.y = y;
        this.x = x;
    }

    //生命周期减少方法
    public void lifeDown() {
        if(this.live>0) {
            this.live--;
        }else{
            isLive=false;
        }
    }
}
