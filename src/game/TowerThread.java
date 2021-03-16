package game;

public class TowerThread implements Runnable{
    Tower tower;
    int x,y;
    TowerThread(int id, int _x, int _y, String name){
        super();
        if(name.equals("BottleCannon")){
            tower = new BottleCannon(id,_x,_y);
            x=_x;
            y=_y;
            if(tower.isAlive()){
                System.out.println("瓶子炮"+id+"在("+x+", "+y+")建成");
            }
        }
        else if(name.equals("SunFlower")){
            tower = new SunFlower(id,_x,_y);
            x=_x;
            y=_y;
            if(tower.isAlive()){
                System.out.println("太阳花"+id+"在("+x+", "+y+")建成");
            }

        }
        else{
            tower=null;
        }
    }
    public void run(){
        if(tower.isAlive()){
            synchronized(Basic.towers){
                Basic.towers.add(tower);
            }
            tower.attack();
            synchronized(Basic.towers){
                Basic.towers.remove(tower);
            }
        }
    }

}
