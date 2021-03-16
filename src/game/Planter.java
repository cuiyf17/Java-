package game;

public class Planter {
    int num;
    public Planter(){
        num=0;
    }
    Planter(int _num){
        num=_num;
    }
    void Planting(){
        int x=2;
        int y=0;
        for(int i=0;i<num&&Basic.endGame==false;i++){
            Plant(i,x,y+i,"SunFlower");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Remove(x+i,y+i);
        }

    }

    public void Plant(int id, int x, int y, String name){
        if(x>=0&&x<Basic.map.length&&y>=0&&y<Basic.map[0].length){
            TowerThread tt=new TowerThread(id,x,y,name);
            Thread t=new Thread(tt);
            t.start();
        }
    }
    public void Remove(int x, int y){
        Tower tmp=null;
        synchronized(Basic.towers){
            for(int i=0;i<Basic.towers.size();i++){
                tmp=Basic.towers.get(i);
                if(tmp!=null){
                    if(tmp.x==x&&tmp.y==y){
                        tmp.close();
                        break;
                    }
                }
            }
        }
    }
}
