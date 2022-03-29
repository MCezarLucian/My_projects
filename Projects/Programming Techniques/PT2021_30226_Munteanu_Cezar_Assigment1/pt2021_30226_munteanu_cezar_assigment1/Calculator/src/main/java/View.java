import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class View extends JFrame{


    private JTextField tf1 = new JTextField("");
    private JTextField tf2 = new JTextField("");
    private JTextField tf3 = new JTextField("");
    private JTextField tf4 = new JTextField("");
    private JButton b1 = new JButton("Adauga");
    private JButton b2 = new JButton("Adauga");
    private JButton b3 = new JButton("Adunare");
    private JButton b4 = new JButton("Scadere");
    private JButton b5 = new JButton("Inmultire");
    private JButton b6 = new JButton("Impartire");
    private JButton b7 = new JButton("Derivare");
    private JButton b8 = new JButton("Integrare");
    private JButton b9 = new JButton("CLEAR");
    private JTextArea ta1 = new JTextArea("");
    private JTextArea ta2 = new JTextArea("");
    private JTextArea ta3 = new JTextArea("");
    private JTextArea ta4 = new JTextArea("Coeficient:");
    private JTextArea ta5 = new JTextArea("Exponent:");
    private JTextArea ta6 = new JTextArea("Coeficient:");
    private JTextArea ta7 = new JTextArea("Exponent:");

    View(){

        this.setBounds(528,450,528,450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(600,600);
        this.setVisible(true);
        p1.setBounds( 528,450,528,450);
        this.add(p1);
        p1.setLayout(null);

        ta4.setBounds(8,8,60,20);
        p1.add(ta4);
        tf1.setBounds(68,8,50,20);
        p1.add(tf1);
        ta5.setBounds(8, 50, 60,20);
        p1.add(ta5);
        tf2.setBounds(68,50,50,20);
        p1.add(tf2);
        b1.setBounds(120,30,80,20);
        p1.add(b1);
        ta1.setBounds(220,30,250,20);
        p1.add(ta1);
        ta6.setBounds(8,100,60,20);
        p1.add(ta6);
        tf3.setBounds(68,100,50,20);
        p1.add(tf3);
        ta7.setBounds(8, 142, 60,20);
        p1.add(ta7);
        tf4.setBounds(68,142,50,20);
        p1.add(tf4);
        b2.setBounds(120,120,80,20);
        p1.add(b2);
        ta2.setBounds(220,120,250,20);
        p1.add(ta2);
        b3.setBounds(8,200,100,20);
        p1.add(b3);
        b4.setBounds(108,200,100,20);
        p1.add(b4);
        b5.setBounds(208,220,100,20);
        p1.add(b5);
        b6.setBounds(8,220,100,20);
        p1.add(b6);
        b7.setBounds(108,220,100,20);
        p1.add(b7);
        b8.setBounds(208,200,100,20);
        p1.add(b8);
        ta3.setBounds(8, 270, 380,20);
        p1.add(ta3);
        b9.setBounds(408,270,100,20);
        p1.add(b9);


    }

    public String getCoef1(){return tf1.getText(); }
    public String getPow1(){return tf2.getText(); }
    public String getCoef2(){return tf3.getText(); }
    public String getPow2(){return tf4.getText(); }

    public void getShow1(Polinom A){ta1.setText(A.toString());}
    public void getShow2(Polinom A){ta2.setText(A.toString());}
    public void getShow3(String A){ta3.setText(A);}

    void addListenerAdauga1(ActionListener l1){ b1.addActionListener(l1); }
    void addListenerAdauga2(ActionListener l2){ b2.addActionListener(l2); }
    void addListenerAduna(ActionListener l3){ b3.addActionListener(l3); }
    void addListenerScade(ActionListener l4){ b4.addActionListener(l4); }
    void addListenerInmulteste(ActionListener l5){ b5.addActionListener(l5); }
    void addListenerImparte(ActionListener l6){ b6.addActionListener(l6); }
    void addListenerDeriveaza(ActionListener l7){ b7.addActionListener(l7); }
    void addListenerIntegreaza(ActionListener l8){ b8.addActionListener(l8); }
    void addListenerCurata(ActionListener l9){ b9.addActionListener(l9); }





}
