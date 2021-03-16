package game;

public class StarterThread implements Runnable{

    public StarterThread(int start_x, int start_y){
        super();
        Basic.monster_producer = new Starter(start_x,start_y,Basic.max_epoch,5,15);
    }
    public void run(){
        Basic.monster_producer.Produce();
    }
}
