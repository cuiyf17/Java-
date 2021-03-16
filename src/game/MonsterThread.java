package game;

public class MonsterThread implements Runnable{
    Monster monster;
    int x,y;
    MonsterThread(int id, int start_x, int start_y){
        super();
        monster=new Monster(id, Basic.map);
        x=start_x;
        y=start_y;
    }

    public void run(){
        synchronized(Basic.monsters){
            Basic.monsters.add(monster);
        }
        monster.run(x,y);
        synchronized(Basic.monsters){
            Basic.monsters.remove(monster);
        }
    }
}
