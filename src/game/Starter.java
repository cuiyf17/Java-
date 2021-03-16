package game;

import mainwindow.FinalWindow;
import mainwindow.MainWindow;

public class Starter {
    int x,y;
    int max_epoch;
    int min_monster,max_monster;
    int monster_id;
    int epoch;
    Starter(int _x, int _y, int _max_epoch, int _min_monster, int _max_monster){
        x=_x;
        y=_y;
        max_epoch=_max_epoch;
        min_monster=_min_monster*Basic.mode;
        max_monster=_max_monster*Basic.mode;
        epoch=0;
    }
    public int nowEpoch(){
        return epoch+1;
    }
    void Produce(){
        while(Basic.endGame==false&&epoch<max_epoch){
            int num=min_monster+epoch*(max_monster-min_monster)/max_epoch;
            //一波怪物
            Thread[] ts=new Thread[num];
            Basic.now_epoch=nowEpoch();
            MainWindow.monster_epoch.setText("怪物波数:\n    "+Basic.now_epoch+"/ "+Basic.max_epoch);
            for(int i=0;i<num&&Basic.endGame==false;i++){
                while(Basic.pause==true&&Basic.endGame==false){
                    try {
                        Thread.sleep(0,1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //每1.5秒产生一个怪物
                MonsterThread mt = new MonsterThread(monster_id,Basic.start_x,Basic.start_y);
                monster_id++;
                ts[i]=new Thread(mt);
                ts[i].start();
                try {
                    Thread.sleep((long) (1500/Math.sqrt(Math.sqrt(Basic.mode))));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<num;i++){
                try {
                    if(ts[i]!=null){
                        ts[i].join();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            epoch++;
            Basic.now_epoch=nowEpoch();
            MainWindow.monster_epoch.setText("怪物波数:\n    "+Basic.now_epoch+"/ "+Basic.max_epoch);
            if(Basic.endGame==false){
                System.out.println("第"+epoch+"波清零了！");
            }

        }
        if(epoch==max_epoch&&Basic.endGame==false){
            System.out.println("通关了！");
            MainWindow.printer.append("游戏胜利！\n");
            Basic.endGame=true;
            new FinalWindow("游戏胜利！");
        }
    }
}
