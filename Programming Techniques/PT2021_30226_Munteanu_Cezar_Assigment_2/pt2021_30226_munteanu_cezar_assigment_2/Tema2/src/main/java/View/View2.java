package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View2 extends JFrame{

    private JLabel p1 = new JLabel("");
    private JTextArea ta1 = new JTextArea("");

    public View2() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.add(p1);
        p1.setLayout(null);
        p1.add(ta1);
        ta1.setBounds(1,1,600,600);
    }
    public void getShow1(String A){ta1.setText(A);}
}
