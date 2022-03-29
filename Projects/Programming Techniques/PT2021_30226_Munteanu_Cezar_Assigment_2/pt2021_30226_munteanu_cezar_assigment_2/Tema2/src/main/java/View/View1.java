package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View1 extends JFrame {
    private JLabel p1 = new JLabel("");
    private JButton b1 = new JButton("Adauga");
    private JTextField tf1 = new JTextField("");
    private JTextField tf2 = new JTextField("");
    private JTextField tf3 = new JTextField("");
    private JTextField tf4 = new JTextField("");
    private JTextField tf5 = new JTextField("");
    private JTextField tf6 = new JTextField("");
    private JTextField tf7 = new JTextField("");
    private JTextArea ta1 = new JTextArea("Arrival Max:");
    private JTextArea ta2 = new JTextArea("Arrival Min:");
    private JTextArea ta3 = new JTextArea("Service Max:");
    private JTextArea ta4 = new JTextArea("Service Min:");
    private JTextArea ta5 = new JTextArea("Clients number:");
    private JTextArea ta6 = new JTextArea("Simulation Time:");
    private JTextArea ta7 = new JTextArea("Queues number:");

    public View1(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(250,300);
        this.setVisible(true);
        this.add(p1);
        p1.setLayout(null);
        p1.setBounds( 528,450,528,450);
        p1.add(b1);
        b1.setBounds(10,10,80,30);
        p1.add(ta1);
        ta1.setBounds(10, 45, 100, 20);
        p1.add(tf1);
        tf1.setBounds(115, 45, 100, 20);
        p1.add(ta2);
        ta2.setBounds(10, 70, 100, 20);
        p1.add(tf2);
        tf2.setBounds(115, 70, 100, 20);
        p1.add(ta3);
        ta3.setBounds(10, 95, 100, 20);
        p1.add(tf3);
        tf3.setBounds(115, 95, 100, 20);
        p1.add(ta4);
        ta4.setBounds(10, 120, 100, 20);
        p1.add(tf4);
        tf4.setBounds(115, 120, 100, 20);
        p1.add(ta5);
        ta5.setBounds(10, 145, 100, 20);
        p1.add(tf5);
        tf5.setBounds(115, 145, 100, 20);
        p1.add(ta6);
        ta6.setBounds(10, 170, 100, 20);
        p1.add(tf6);
        tf6.setBounds(115, 170, 100, 20);
        p1.add(ta7);
        ta7.setBounds(10, 195, 100, 20);
        p1.add(tf7);
        tf7.setBounds(115, 195, 100, 20);
    }


    public String getArrMax(){return tf1.getText();}
    public String getArrMin(){return tf2.getText();}
    public String getServMax(){return tf3.getText();}
    public String getServMin(){return tf4.getText();}
    public String getClientsNr(){return tf5.getText();}
    public String getSimTime(){return tf6.getText();}
    public String getQueuesNr(){return  tf7.getText();}

    public void addListenerAdauga1(ActionListener l1){ b1.addActionListener(l1); }


}
