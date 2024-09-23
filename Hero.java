import java.util.Vector;

public class Hero extends Tank{

    Shot shot=null;

    Vector<Shot> shots =new Vector<>();


    public Hero(int x, int y,int direct){
        super(x,y,direct);
    }
    //调用射击类
    public void heroShot(){
        if(shots.size()==5) {
                return;
        }

        switch(getDirect()){
            case 0:
                shot=new Shot(getX()+20,getY(),10,getDirect());
                break;
            case 1:
                shot=new Shot(getX()+20,getY()+60,10,getDirect());

                break;
            case 2:
                shot=new Shot(getX(),getY()+20,10,getDirect());

                break;
            case 3:
                shot=new Shot(getX()+60,getY()+20,10,getDirect());
                break;
        }
        shots.add(shot);
        shot.start();
    }


}
