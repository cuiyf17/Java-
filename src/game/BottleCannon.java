package game;

import mainwindow.MainWindow;

import javax.swing.*;

public class BottleCannon extends Tower{
    BottleCannon(int _id, int _x, int _y){
        super(_id, _x, _y, 20, 200);
        cost=100;
        if(alive){
            if(Basic.money<cost){
                alive=false;
                System.out.println("金钱不足以购买瓶子炮！");
                MainWindow.printer.append("金钱不足以购买瓶子炮！\n");
            }
            else{
                Basic.money-=cost;
                MainWindow.wealth.setText("金钱:\n    "+Basic.money);
            }
        }

        if(alive){
            synchronized(Basic.battle){
                synchronized(MainWindow.btn_grids){
                    ImageIcon icon = new ImageIcon("./src/Pics/bottle_cannon_space.png");
                    icon.setImage(icon.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                    MainWindow.btn_grids[x][y].setIcon(icon);
                    Basic.battle[x][y]=2;
                }

            }
        }

    }
    String say(int monster_id){
        String str="瓶子炮"+id+": 我打了怪物"+monster_id+"生命值"+damage+"点";
        MainWindow.printer.append(str+"\n");
        System.out.println(str);
        return str;
    }
    @Override
    void attack(){
        while(Basic.endGame==false&&alive){
            while(Basic.pause==true&&Basic.endGame==false){
                try {
                    Thread.sleep(0,1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int min_distance=0x7fffffff;
            synchronized(Basic.monsters){
                //获取最小距离
                Monster min_monster=null;
                int tmp_i=0;
                for(int i=0;i<Basic.monsters.size();i++){
                    int tmp_x=Basic.monsters.get(i).x;
                    int tmp_y=Basic.monsters.get(i).y;
                    int tmp_dist=(x-tmp_x)*(x-tmp_x)+(y-tmp_y)*(y-tmp_y);
                    if(tmp_dist<min_distance){
                        min_distance=tmp_dist;
                        min_monster=Basic.monsters.get(i);
                        tmp_i=i;
                    }
                }
                if(min_monster!=null){
                    min_monster.life-=damage;
                    say(min_monster.id);
                }

            }
            if(Basic.endGame==false&&alive){
                try {
                    Thread.sleep(100000/speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
