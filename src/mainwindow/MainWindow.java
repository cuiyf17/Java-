package mainwindow;

import game.Basic;
import game.MusicThread;
import game.PlanterThread;
import game.StarterThread;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements ActionListener{
    JRadioButton[] btn_mode;
    ButtonGroup btn_group;
    public static JButton[][] btn_grids;
    public static JTextArea printer,carrot_life,monster_epoch,wealth;
    JButton bn_start, bn_pause;
    StarterThread st;
    PlanterThread pt;
    Thread starter;
    Thread planter;
    Thread music_palyer;

    JPanel p1, p2; //创建面板
    Basic game_mode;
    int mode;
    int width,height;
    public static int grid_width;
    public MainWindow(int _mode){
        super("保卫萝卜");
        width=1400;
        height=900;

        MusicThread mt=new MusicThread("./src/Music/BGM.wav");
        music_palyer=new Thread(mt);
        music_palyer.start();

        Container c = getContentPane(); //创建内容面板对象
        p2 = new JPanel();
        p2.setLayout(null);
        c.add(p2);

        game_mode=new game.Basic();
        game_mode.setMode(_mode);
        game_mode.BuildGame();

        grid_width=Math.min((width-300)/ Basic.map[0].length,(height-300)/ Basic.map.length);
        btn_grids = new JButton[game.Basic.map.length][game.Basic.map[0].length];

        printer=new JTextArea("欢迎来到崔晏菲的保卫萝卜世界！\n");
        printer.setBounds((width-600)/2,Basic.map.length*grid_width+10,600,200);
        printer.setEditable(false);
        JScrollPane js=new JScrollPane(printer);
        //分别设置水平和垂直滚动条自动出现
        js.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        js.setBounds((width-600)/2,Basic.map.length*grid_width+10,600,200);
        js.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                if(evt.getAdjustmentType() == AdjustmentEvent.TRACK ) {
                    js.getVerticalScrollBar().setValue(js.getVerticalScrollBar().getModel().getMaximum() - js.getVerticalScrollBar().getModel().getExtent());
                }
            }
        });
        p2.add(js);

        carrot_life=new JTextArea("萝卜生命值:\n    "+Basic.carrot_life);
        carrot_life.setBounds(grid_width*Basic.map[0].length+(width- Basic.map[0].length*grid_width)/2+10,(Basic.map.length/2)*grid_width,grid_width,grid_width);
        carrot_life.setEditable(false);
        p2.add(carrot_life);

        wealth=new JTextArea("金钱:\n    "+Basic.money);
        wealth.setBounds(grid_width*Basic.map[0].length+(width- Basic.map[0].length*grid_width)/2+10,0,grid_width,grid_width);
        wealth.setEditable(false);
        p2.add(wealth);

        monster_epoch=new JTextArea("怪物波数:\n    "+Basic.now_epoch+"/ "+Basic.max_epoch);
        monster_epoch.setBounds((width- Basic.map[0].length*grid_width)/2-10-grid_width,0,grid_width,grid_width);
        monster_epoch.setEditable(false);
        p2.add(monster_epoch);

        bn_start=new JButton("游戏开始");
        bn_start.setBounds((width- Basic.map[0].length*grid_width)/2,Basic.map.length*grid_width+10,(width-600)/2-10-(width- Basic.map[0].length*grid_width)/2,grid_width);
        bn_start.addActionListener(this);
        p2.add(bn_start);

        bn_pause=new JButton("暂停");
        bn_pause.setBounds((width-600)/2+600+10,Basic.map.length*grid_width+10,(width-600)/2-10-(width- Basic.map[0].length*grid_width)/2,grid_width);
        bn_pause.addActionListener(this);
        p2.add(bn_pause);

        btn_mode=new JRadioButton[3];
        btn_mode[0]=new JRadioButton("简单难度");
        btn_mode[1]=new JRadioButton("中等难度");
        btn_mode[2]=new JRadioButton("困难难度");
        btn_group=new ButtonGroup();
        for(int i=0;i<3;i++){
            btn_group.add(btn_mode[i]);
            btn_mode[i].addActionListener(this);
            btn_mode[i].setBounds((width- Basic.map[0].length*grid_width)/2,Basic.map.length*grid_width+20+grid_width+i*30,grid_width,30);
            p2.add(btn_mode[i]);
            if(i==Basic.mode-1){
                btn_mode[i].setSelected(true);
            }
        }


        for(int i=0;i<game.Basic.map.length;i++){
            for(int j=0;j<game.Basic.map[0].length;j++){
                btn_grids[i][j]= new JButton("");
                btn_grids[i][j].setBounds(j*grid_width+(width- Basic.map[0].length*grid_width)/2, i*grid_width, grid_width, grid_width);
                ImageIcon icon = null;
                if(Basic.map[i][j]==0){
                    icon = new ImageIcon("./src/Pics/space.png");
                }
                else if(Basic.map[i][j]==1){
                    icon = new ImageIcon("./src/Pics/road.jpg");
                }
                else if(Basic.map[i][j]==2){
                    icon = new ImageIcon("./src/Pics/stock.png");
                }
                else if(Basic.map[i][j]==3){
                    icon = new ImageIcon("./src/Pics/flag.png");
                }
                else if(Basic.map[i][j]==4){
                    icon = new ImageIcon("./src/Pics/carrot.png");
                }

                icon.setImage(icon.getImage().getScaledInstance(grid_width,grid_width, 0));
                btn_grids[i][j].setIcon(icon);
                btn_grids[i][j].addActionListener(this);
                p2.add(btn_grids[i][j]);
            }
        }


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置窗口关闭动作
        setVisible(true); //设置为可见
        setResizable(false); //禁止调整框架大小
        setSize(width,height); //设置窗口大小
        setLocationRelativeTo(null); //显示到中间
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Basic.not_start){
            if(e.getSource()==bn_start){
                Basic.not_start=false;
                printer.append("游戏开始！\n");
                bn_start.setText("重新开始");
                st = new StarterThread(3,0);
                pt = new PlanterThread();
                starter = new Thread(st);
                planter = new Thread(pt);

                starter.start();
                planter.start();
            }
            if(e.getSource()==btn_mode[0]){
                if(Basic.mode!=1){
                    Basic.endGame=true;
                    int nandu=1;
                    try {
                        if(starter!=null){
                            starter.join();
                        }
                        if(planter!=null){
                            planter.join();
                        }
                        if(music_palyer!=null){
                            music_palyer.join();
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    dispose();
                    new Basic();
                    new MainWindow(nandu);
                }
            }
            else if(e.getSource()==btn_mode[1]){
                if(Basic.mode!=2){
                    Basic.endGame=true;
                    int nandu=2;
                    try {
                        if(starter!=null){
                            starter.join();
                        }
                        if(planter!=null){
                            planter.join();
                        }
                        if(music_palyer!=null){
                            music_palyer.join();
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    dispose();
                    new Basic();
                    new MainWindow(nandu);
                }
            }
            else if(e.getSource()==btn_mode[2]){
                if(Basic.mode!=3){
                    Basic.endGame=true;
                    int nandu=3;
                    try {
                        if(starter!=null){
                            starter.join();
                        }
                        if(planter!=null){
                            planter.join();
                        }
                        if(music_palyer!=null){
                            music_palyer.join();
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    dispose();
                    new Basic();
                    new MainWindow(nandu);
                }
            }
        }
        else
        {
            if(e.getSource()==bn_start){
                Basic.endGame=true;
                int nandu=Basic.mode;
                try {
                    if(starter!=null){
                        starter.join();
                    }
                    if(planter!=null){
                        planter.join();
                    }
                    if(music_palyer!=null){
                        music_palyer.join();
                    }
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                dispose();
                new Basic();
                new MainWindow(nandu);
            }
            else if(e.getSource()==bn_pause){
                if(Basic.not_start){

                }
                else if(Basic.pause){
                    Basic.pause=false;
                    printer.append("游戏继续！\n");
                    bn_pause.setText("暂停");
                }
                else{
                    Basic.pause=true;
                    printer.append("游戏暂停！\n");
                    bn_pause.setText("继续");
                }
            }
            else{
                if(e.getSource()==btn_mode[0]){
                    if(Basic.mode!=1){
                        Basic.endGame=true;
                        int nandu=1;
                        try {
                            if(starter!=null){
                                starter.join();
                            }
                            if(planter!=null){
                                planter.join();
                            }
                            if(music_palyer!=null){
                                music_palyer.join();
                            }
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        dispose();
                        new Basic();
                        new MainWindow(nandu);
                    }
                }
                else if(e.getSource()==btn_mode[1]){
                    if(Basic.mode!=2){
                        Basic.endGame=true;
                        int nandu=2;
                        try {
                            if(starter!=null){
                                starter.join();
                            }
                            if(planter!=null){
                                planter.join();
                            }
                            if(music_palyer!=null){
                                music_palyer.join();
                            }
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        dispose();
                        new Basic();
                        new MainWindow(nandu);
                    }
                }
                else if(e.getSource()==btn_mode[2]){
                    if(Basic.mode!=3){
                        Basic.endGame=true;
                        int nandu=3;
                        try {
                            if(starter!=null){
                                starter.join();
                            }
                            if(planter!=null){
                                planter.join();
                            }
                            if(music_palyer!=null){
                                music_palyer.join();
                            }
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        dispose();
                        new Basic();
                        new MainWindow(nandu);
                    }
                }
                else  if(!Basic.pause){
                    for(int i=0;i<game.Basic.map.length;i++) {
                        for (int j = 0; j < game.Basic.map[0].length; j++) {
                            if(e.getSource()==btn_grids[i][j]&&Basic.map[i][j]==0){
                                ChooseWindowThread cwt=new ChooseWindowThread(i,j);
                                Thread t= new Thread(cwt);
                                t.start();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

}