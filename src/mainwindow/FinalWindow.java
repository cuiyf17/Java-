package mainwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalWindow extends JFrame implements ActionListener{
    JButton btn_exit;
    public FinalWindow(String state){
        super(state);
        int width=300;
        int height=300;
        Container c =getContentPane();
        c.setLayout(null);
        btn_exit=new JButton("确定");
        btn_exit.setBounds(100,200,100,50);
        btn_exit.addActionListener(this);
        c.add(btn_exit);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //设置窗口关闭动作
        setVisible(true); //设置为可见
        setResizable(false); //禁止调整框架大小
        setSize(width,height); //设置窗口大小
        setLocationRelativeTo(null); //显示到中间
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_exit){
            dispose();
        }
    }
}
