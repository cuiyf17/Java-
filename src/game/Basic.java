package game;

import java.util.ArrayList;

//battle用来记录地图上的怪物和敌人，1表示怪物，2表示瓶子炮，3表示太阳花
//map用来表示地图，0表示空地，1表示路，2表示障碍，3表示起点，4表示终点“萝卜”
public class Basic {

    int space;
    int road;
    int stock;
    int start;
    int end;
    public static boolean endGame;
    public static int[][] map;
    public static int[][] battle;
    public static ArrayList<Monster> monsters;
    public static ArrayList<Tower> towers;
    public static Starter  monster_producer;
    public static Planter tower_builder;
    public static int start_x;
    public static int start_y;
    public static int end_x;
    public static int end_y;
    public static int carrot_life;
    public static int money;
    public static int mode;
    public static int max_epoch;
    public static int now_epoch;
    public static boolean not_start;
    public static boolean pause;
    public static int tower_num;
    public Basic(){
        space=0;
        road=1;
        stock=2;
        start=3;
        end=4;

        start_x=0;
        start_y=0;
        end_x=0;
        end_y=0;
        money=100;
        carrot_life=10;
        mode=0;
        max_epoch=10;
        now_epoch=0;
        not_start=true;
        pause=false;
        endGame=false;
        monsters = new ArrayList<Monster>();
        towers = new ArrayList<Tower>();
        tower_num=0;
        monster_producer=null;
        tower_builder=null;
    }
    public void setMode(int x){
        mode=x;
    }
    public void BuildGame(){
        if(mode==1){
            int[][] _map = {{0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0},
                            {3,1,1,1,1,1,1,1,1,1,4},
                            {0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0}};
            start_x=3;
            start_y=0;
            end_x=3;
            end_y=10;
            map=_map;
            battle = new int[map.length][map[0].length];
            mode=1;
            for(int i=0;i<battle.length;i++){
                for(int j=0;j<battle[0].length;j++){
                    battle[i][j]=0;
                }
            }
        }
        else if(mode==2){
            int[][] _map = {{2,2,2,2,2,2,2,2,2,2,2},
                            {3,0,0,0,0,0,1,1,1,1,1},
                            {1,0,1,1,1,0,1,0,0,0,1},
                            {1,0,1,0,1,0,1,1,1,0,4},
                            {1,0,1,0,1,0,0,0,1,0,0},
                            {1,1,1,0,1,1,1,1,1,0,0},
                            {2,2,2,2,2,2,2,2,2,2,2}};
            start_x=1;
            start_y=0;
            end_x=3;
            end_y=10;
            map=_map;
            battle = new int[map.length][map[0].length];
            mode=2;
            for(int i=0;i<battle.length;i++){
                for(int j=0;j<battle[0].length;j++){
                    battle[i][j]=0;
                }
            }
        }
        else if(mode==3){
            int[][] _map = {{2,2,2,2,2,2,2,2,2,2,2},
                            {3,1,1,1,1,1,1,1,1,2,2},
                            {2,2,2,2,2,2,2,2,1,2,2},
                            {1,1,1,1,1,1,1,1,1,2,4},
                            {1,0,0,0,0,0,0,0,0,2,1},
                            {1,2,1,1,1,2,1,1,1,2,1},
                            {1,1,1,2,1,1,1,2,1,1,1}};
            start_x=1;
            start_y=0;
            end_x=3;
            end_y=10;
            map=_map;
            mode=3;
            battle = new int[map.length][map[0].length];
            for(int i=0;i<battle.length;i++){
                for(int j=0;j<battle[0].length;j++){
                    battle[i][j]=0;
                }
            }
        }
    }
}
