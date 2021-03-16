package game;

import mainwindow.MainWindow;

import javax.swing.*;

public class SunFlower extends Tower{
    SunFlower(int _id, int _x, int _y){
        super(_id, _x, _y, 30, 100);
        cost=200;

        if(alive){
            if(Basic.money<cost){
                alive=false;
                System.out.println("金钱不足以购买太阳花！");
                MainWindow.printer.append("金钱不足以购买太阳花！\n");
            }
            else{
                Basic.money-=cost;
                MainWindow.wealth.setText("金钱:\n    "+Basic.money);
            }
        }

        if(alive){
            synchronized(Basic.battle){
                synchronized(MainWindow.btn_grids){
                    ImageIcon icon = new ImageIcon("./src/Pics/sun_flower_space.png");
                    icon.setImage(icon.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                    MainWindow.btn_grids[x][y].setIcon(icon);
                    Basic.battle[x][y]=3;
                }
            }
        }

    }
    String say(int monster_id){
        String str="太阳花"+id+": 我打了怪物"+monster_id+"生命值"+damage+"点";
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
            synchronized(Basic.monsters){
                //获取最小距离
                Monster monster=null;
                int tmp_i=0;
                for(int i=0;i<Basic.monsters.size();i++){
                    monster = Basic.monsters.get(i);
                    if(monster!=null){
                        int tmp_x=monster.x;
                        int tmp_y=monster.y;
                        if(Math.abs(tmp_x-x)<=3&&Math.abs(tmp_y-y)<=3){
                            monster.life-=damage;
                            say(monster.id);
                        }
                    }
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
