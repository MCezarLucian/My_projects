package view;

import javax.swing.*;
import javax.swing.text.TabableView;

public class productTable extends JFrame {

    JTable t = new JTable();
    String[] cName = {"Name"};
    JScrollPane s = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    public productTable(Object[][] o){
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,600,600);
        this.setTitle("Menu");



        t = new JTable(o, cName);
        this.add(s);
        this.add(t);

        this.setVisible(true);
    }
}
