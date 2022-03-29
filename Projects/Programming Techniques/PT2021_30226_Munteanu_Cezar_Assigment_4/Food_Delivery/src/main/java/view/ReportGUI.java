package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ReportGUI extends JFrame {

    private JPanel report = new JPanel();
    private JTextField min = new JTextField();
    private JTextField max = new JTextField();
    private JTextField ordered = new JTextField();
    private JTextField placed = new JTextField();
    private JTextField highValue = new JTextField();
    private JTextField day = new JTextField();

    private JLabel minv = new JLabel("Val min:");
    private JLabel maxv = new JLabel("Val max:");
    private JLabel ord = new JLabel("Ordered More:");
    private JLabel pl = new JLabel("Placed:");
    private JLabel hv = new JLabel("High Value:");
    private JLabel d = new JLabel("Day:");

    private JButton rep = new JButton("Generate");


    public ReportGUI(){
        this.setBounds(0,0,300,300);
        this.setTitle("Report Panel");

        report.setBounds(0,0,620,680);
        report.setLayout(null);
        this.add(report);

        minv.setBounds(20,20,100,25);
        maxv.setBounds(20,55,100,25);
        ord.setBounds(20,90,100,25);
        pl.setBounds(20,125,100,25);
        hv.setBounds(20,160,100,25);
        d.setBounds(20,195,100,25);
        min.setBounds(120,20,100,25);
        max.setBounds(120,55,100,25);
        ordered.setBounds(120,90,100,25);
        placed.setBounds(120,125,100,25);
        highValue.setBounds(120,160,100,25);
        day.setBounds(120,195,100,25);
        rep.setBounds(120,220,100,25);
        report.add(minv);
        report.add(maxv);
        report.add(ord);
        report.add(pl);
        report.add(hv);
        report.add(d);
        report.add(min);
        report.add(max);
        report.add(ordered);
        report.add(placed);
        report.add(highValue);
        report.add(day);
        report.add(rep);

        report.setVisible(true);
        this.setVisible(true);
    }

    public void generateListener(ActionListener c){this.rep.addActionListener(c);}
    public int minText(){return Integer.parseInt(min.getText());}
    public int maxText(){return Integer.parseInt(max.getText());}
    public int orderedText(){return Integer.parseInt(ordered.getText());}
    public int placedText(){return Integer.parseInt(placed.getText());}
    public int hvText(){return Integer.parseInt(highValue.getText());}
    public int dayText(){return Integer.parseInt(day.getText());}



}
