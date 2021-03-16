package mainwindow;

import game.Basic;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChooseWindow extends JFrame implements ActionListener{
    int width;
    int height;
    int x,y;
    JButton[] btn_towers;
    JPanel p;
    public ChooseWindow(int _x, int _y){
        super("建造面板");
        width=200;
        height=330;
        x=_x;
        y=_y;

        Container c = getContentPane(); //创建内容面板对象
        p = new JPanel();
        p.setLayout(null);
        c.add(p);

        btn_towers=new JButton[3];
        for(int i=0;i<3;i++){
            btn_towers[i]=new JButton();
            btn_towers[i].setBounds(0, i*100, 200, 100);
            ImageIcon icon;
            if(i==0){
                icon=new ImageIcon("./src/Pics/bottle_cannon.png");
                btn_towers[i].setText("瓶子炮 100");
            }
            else if(i==1){
                icon=new ImageIcon("./src/Pics/sun_flower.png");
                btn_towers[i].setText("太阳花 200");
            }
            else{
                icon=new ImageIcon("./src/Pics/remove.png");
                btn_towers[i].setText("移除炮塔");
            }
            icon.setImage(icon.getImage().getScaledInstance(100,100, 0));
            btn_towers[i].setIcon(icon);
            btn_towers[i].addActionListener(this);
            p.add(btn_towers[i]);
        }


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //设置窗口关闭动作
        setVisible(true); //设置为可见
        setResizable(false); //禁止调整框架大小
        setSize(width,height); //设置窗口大小
        setLocationRelativeTo(null); //显示到中间
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(Basic.not_start||Basic.endGame||Basic.pause)){
            if(e.getSource()==btn_towers[0]){
                if(Basic.money>=100){
                    Basic.tower_builder.Plant(Basic.tower_num,x,y,"BottleCannon");
                    Basic.tower_num++;
                    dispose();
                }
                else{
                    MainWindow.printer.append("金钱不足！\n");
                }
            }
            else if(e.getSource()==btn_towers[1]){
                if(Basic.money>=200){
                    Basic.tower_builder.Plant(Basic.tower_num,x,y,"SunFlower");
                    Basic.tower_num++;
                    dispose();
                }
                else{
                    MainWindow.printer.append("金钱不足！\n");
                }
            }
            else if(e.getSource()==btn_towers[2]){
                Basic.tower_builder.Remove(x,y);
                dispose();
            }
        }

    }
}
