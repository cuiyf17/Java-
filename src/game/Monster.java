package game;

import mainwindow.FinalWindow;
import mainwindow.MainWindow;

import javax.swing.*;

public class Monster {

    public int id;
    public int life;
    public int speed;
    int[][] map;
    int[][] visited;
    int x,y;
    Monster(int _id, int[][] _map){
        id=_id;
        life=100;
        speed=200*Basic.mode;
        map=_map;
        visited=new int[map.length][map[0].length];
        for(int i=0;i< visited.length;i++){
            for(int j=0;j<visited[0].length;j++){
                visited[i][j]=0;
            }
        }
    }
    String say(){
        String str = "怪物"+id+": 我正在("+x+", "+y+")";
        System.out.println(str);
        return str;
    }

    int showLife(){
        return life;
    }
    int showSpeed(){
        return speed;
    }
    boolean isKilled(){
        return life<=0?true:false;
    }
    int[] location(){
        return new int[]{x,y};
    }
    void run(int start_x, int start_y){
        visited[start_x][start_y]=1;
        x=start_x;
        y=start_y;
        synchronized(Basic.battle){
            synchronized(MainWindow.btn_grids){
                ImageIcon monster_road=new ImageIcon("./src/Pics/monster_road.png");
                monster_road.setImage(monster_road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                Basic.battle[x][y]=1;
                MainWindow.btn_grids[x][y].setIcon(monster_road);
            }

        }

        while(map[x][y]!=4&&life>0&&Basic.endGame==false){
            while(Basic.pause==true&&Basic.endGame==false){
                try {
                    Thread.sleep(0,1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(map[x][y]!=4&&life>0&&Basic.endGame==false){
                try {
                    Thread.sleep((long) (50000/speed));
                } catch (InterruptedException e) {
                    continue;
                }
            }
            else{
                break;
            }
            if(map[x][y]!=4&&life>0&&Basic.endGame==false){
                say();

                if(x+1< map.length){
                    if((map[x+1][y]==1||map[x+1][y]==4)&&visited[x+1][y]==0){
                        synchronized(Basic.battle){
                            synchronized(MainWindow.btn_grids){
                                ImageIcon road=new ImageIcon("./src/Pics/road.jpg");
                                road.setImage(road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                ImageIcon monster_road=new ImageIcon("./src/Pics/monster_road.png");
                                monster_road.setImage(monster_road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                MainWindow.btn_grids[x][y].setIcon(road);
                                Basic.battle[x][y]=0;
                                x=x+1;
                                y=y;
                                visited[x][y]=1;
                                Basic.battle[x][y]=1;
                                if(map[x][y]==4){
                                    monster_road=new ImageIcon("./src/Pics/monster_carrot.png");
                                    monster_road.setImage(monster_road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                }
                                MainWindow.btn_grids[x][y].setIcon(monster_road);
                            }

                        }
                    }
                }
                if(y-1>=0){
                    if((map[x][y-1]==1||map[x][y-1]==4)&&visited[x][y-1]==0){
                        synchronized(Basic.battle){
                            synchronized(MainWindow.btn_grids){
                                ImageIcon road=new ImageIcon("./src/Pics/road.jpg");
                                road.setImage(road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                ImageIcon monster_road=new ImageIcon("./src/Pics/monster_road.png");
                                monster_road.setImage(monster_road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                MainWindow.btn_grids[x][y].setIcon(road);
                                Basic.battle[x][y]=0;
                                x=x;
                                y=y-1;
                                visited[x][y]=1;
                                Basic.battle[x][y]=1;
                                if(map[x][y]==4){
                                    monster_road=new ImageIcon("./src/Pics/monster_carrot.png");
                                    monster_road.setImage(monster_road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                }
                                MainWindow.btn_grids[x][y].setIcon(monster_road);
                            }

                        }
                    }
                }
                if(y+1<map[0].length){
                    if((map[x][y+1]==1||map[x][y+1]==4)&&visited[x][y+1]==0){
                        synchronized(Basic.battle){
                            synchronized(MainWindow.btn_grids){
                                ImageIcon road=new ImageIcon("./src/Pics/road.jpg");
                                road.setImage(road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                ImageIcon monster_road=new ImageIcon("./src/Pics/monster_road.png");
                                monster_road.setImage(monster_road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                MainWindow.btn_grids[x][y].setIcon(road);
                                Basic.battle[x][y]=0;
                                x=x;
                                y=y+1;
                                visited[x][y]=1;
                                Basic.battle[x][y]=1;
                                if(map[x][y]==4){
                                    monster_road=new ImageIcon("./src/Pics/monster_carrot.png");
                                    monster_road.setImage(monster_road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                }
                                MainWindow.btn_grids[x][y].setIcon(monster_road);
                            }

                        }
                    }
                }
                if(x-1>=0){
                    if((map[x-1][y]==1||map[x-1][y]==4)&&visited[x-1][y]==0){
                        synchronized(Basic.battle){
                            synchronized(MainWindow.btn_grids){
                                ImageIcon road=new ImageIcon("./src/Pics/road.jpg");
                                road.setImage(road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                ImageIcon monster_road=new ImageIcon("./src/Pics/monster_road.png");
                                monster_road.setImage(monster_road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                MainWindow.btn_grids[x][y].setIcon(road);
                                Basic.battle[x][y]=0;
                                x=x-1;
                                y=y;
                                visited[x][y]=1;
                                Basic.battle[x][y]=1;
                                if(map[x][y]==4){
                                    monster_road=new ImageIcon("./src/Pics/monster_carrot.png");
                                    monster_road.setImage(monster_road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                                }
                                MainWindow.btn_grids[x][y].setIcon(monster_road);
                            }

                        }
                    }
                }
            }else{
                break;
            }

            if(map[x][y]!=4&&life>0&&Basic.endGame==false){
                try {
                    Thread.sleep(50000/speed);
                } catch (InterruptedException e) {
                    continue;
                }
            }
            else{
                break;
            }
        }
        if(life<=0){
            System.out.println("怪物"+id+"死亡。");
            Basic.money+=10;
            MainWindow.wealth.setText("金钱:\n    "+Basic.money);
            System.out.println("金钱为：------------------------------------"+Basic.money);
            synchronized(Basic.battle){
                synchronized(MainWindow.btn_grids){
                    ImageIcon road=null;
                    if(map[x][y]==4){
                        road =new ImageIcon("./src/Pics/carrot.png");
                        road.setImage(road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                    }
                    else{
                        road=new ImageIcon("./src/Pics/monster_die.png");
                        road.setImage(road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                    }
                    Basic.battle[x][y]=0;
                    MainWindow.btn_grids[x][y].setIcon(road);
                }
            }
        }
        else if(map[x][y]==4){
            Basic.carrot_life--;
            MainWindow.carrot_life.setText("萝卜生命值:\n    "+Basic.carrot_life);
            if(Basic.carrot_life<=0){
                Basic.endGame=true;
                System.out.println("萝卜被怪物"+id+"扣除了1点生命值。");
                System.out.println("游戏失败！");
                MainWindow.printer.append("游戏失败！\n");
                new FinalWindow("游戏失败！");
            }
            else{
                synchronized(MainWindow.btn_grids){
                    ImageIcon road=null;
                    road =new ImageIcon("./src/Pics/carrot.png");
                    road.setImage(road.getImage().getScaledInstance(MainWindow.grid_width,MainWindow.grid_width, 0));
                    MainWindow.btn_grids[x][y].setIcon(road);
                    System.out.println("萝卜被怪物"+id+"扣除了1点生命值。");
                }
            }

        }
    }

}
