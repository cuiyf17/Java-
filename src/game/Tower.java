package game;

import mainwindow.MainWindow;

import javax.swing.*;

public class Tower {
    int num=0;
    public int damage;
    public int speed;
    int x,y;
    int id;
    boolean alive;
    public int cost;
    Tower(){
        id=0;
        damage=0;
        speed=0;
        x=0;
        y=0;
        alive=true;
        cost=0;
    }
    boolean isAlive(){
        return alive;
    }
    Tower(int _id, int _x, int _y, int _damage, int _speed){
        num++;
        synchronized(Basic.battle){
            if(Basic.battle[_x][_y]!=0&&Basic.map[_x][_y]!=0){
                alive=false;
                System.out.println("("+_x+", "+y+")非空！无法放置炮塔！");
                MainWindow.printer.append("("+_x+", "+y+")非空！无法放置炮塔！\n");
            }
            else{

                id=_id;
                x=_x;
                y=_y;
                damage=_damage;
                speed=_speed;
                alive=true;
                cost=0;
            }

        }
    }
    void attack(){

    }
    void close(){
        alive=false;
        System.out.println("炮塔"+id+"已移除("+x+", "+y+")。");
        //MainWindow.printer.append("炮塔"+id+"已移除("+x+", "+y+")。\n");
        synchronized(Basic.battle){
            synchronized(MainWindow.btn_grids){
                ImageIcon icon = new ImageIcon("./src/Pics/space.png");
                icon.setImage(icon.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                MainWindow.btn_grids[x][y].setIcon(icon);
                Basic.battle[x][y]=0;
            }
            Basic.battle[x][y]=0;
        }
    }

}
