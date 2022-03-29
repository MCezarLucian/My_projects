package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeGUI extends JFrame{

    private JPanel employee = new JPanel();
    private JButton delivery = new JButton("Delivery");
    private JTextField afis = new JTextField();
    private JLabel mes = new JLabel("Orders : ");

    public EmployeeGUI(){
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,400,400);
        this.setTitle("Employee Panel");
        employee.setBounds(0,0,400,400);
        this.add(employee);
        employee.setLayout(null);

        delivery.setBounds(150,30,100,25);
        employee.add(delivery);
        mes.setBounds(20,70,100,25);
        employee.add(mes);
        afis.setBounds(20,100,350,240);
        employee.add(afis);


        employee.setVisible(true);
        this.setVisible(true);
    }

    public void setAfis(String s){afis.setText(s);}
    public void deliveryListener(ActionListener c){this.delivery.addActionListener(c);}
}
