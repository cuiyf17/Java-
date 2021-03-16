package mainwindow;

public class ChooseWindowThread implements Runnable{
    int x,y;
    ChooseWindowThread(int _x, int _y){
        x=_x;
        y=_y;
    }
    public void run(){
        ChooseWindow window=new ChooseWindow(x,y);

    }
}
