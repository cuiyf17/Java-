package game;

public class PlanterThread implements Runnable{
    String name;
    int id;
    public PlanterThread(){
        id=-1;
        name="";
        Basic.tower_builder = new Planter();
    }
    public PlanterThread(int _id, String _name){
        id=_id;
        name=_name;
        Basic.tower_builder = new Planter();
    }
    public void run(){
        Basic.tower_builder.Planting();
    }
}
