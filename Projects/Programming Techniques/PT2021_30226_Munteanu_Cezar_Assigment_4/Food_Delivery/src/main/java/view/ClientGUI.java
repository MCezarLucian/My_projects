package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {

    private JPanel client = new JPanel();
    private JButton view = new JButton("View");
    private JButton search = new JButton("Search");
    private JButton order = new JButton("Order");

    private JLabel sb = new JLabel("Search by:");
    private JTextField name = new JTextField();
    private JTextField rating =new JTextField();
    private JTextField calories = new JTextField();
    private JTextField protein = new JTextField();
    private JTextField fat = new JTextField();
    private JTextField sodium = new JTextField();
    private JTextField price = new JTextField();
    private JTextField newName = new JTextField();

    private JLabel nm = new JLabel("Name:");
    private JLabel rt = new JLabel("Rating:");
    private JLabel cal = new JLabel("Calories:");
    private JLabel pr = new JLabel("Proteins:");
    private JLabel ft= new JLabel("Fat:");
    private JLabel sd = new JLabel("Sodium:");
    private JLabel ps = new JLabel("Price:");

    private JTextArea menu = new JTextArea();
    private JTextArea result = new JTextArea();

    public ClientGUI(){
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,620,680);
        this.setTitle("Client Panel");

        client.setBounds(0,0,620,680);
        client.setLayout(null);
        this.add(client);

        view.setBounds(20,20,150,25);
        search.setBounds(20,55,150,25);
        sb.setBounds(20,90, 70,25);
        nm.setBounds(90,90,60,25);
        rt.setBounds(90,125,60,25);
        cal.setBounds(90,160,60,25);
        pr.setBounds(90,195,60,25);
        ft.setBounds(90,230,60,25);
        sd.setBounds(90,265,60,25);
        ps.setBounds(90,300,60,25);
        name.setBounds(150,90,100,25);
        rating.setBounds(150,125,100,25);
        calories.setBounds(150,160,100,25);
        protein.setBounds(150,195,100,25);
        fat.setBounds(150,230,100,25);
        sodium.setBounds(150,265,100,25);
        price.setBounds(150,300,100,25);
        menu.setBounds(300,20,280,600);
        order.setBounds(20, 340, 150,25);
        result.setBounds(20,370,260,250);

        client.add(order);
        client.add(result);
        client.add(menu);
        client.add(view);
        client.add(search);
        client.add(sb);
        client.add(name);
        client.add(nm);
        client.add(rt);
        client.add(cal);
        client.add(pr);
        client.add(ft);
        client.add(sd);
        client.add(ps);
        client.add(rating);
        client.add(calories);
        client.add(protein);
        client.add(fat);
        client.add(sodium);
        client.add(price);


        client.setVisible(true);
        this.setVisible(true);
    }


    public String getName(){return name.getText();}
    public String getRating(){return rating.getText();}
    public String getCalories(){return calories.getText();}
    public String getProteins(){return protein.getText();}
    public String getFat(){return fat.getText();}
    public String getSodium(){return sodium.getText();}
    public String getPrice(){return price.getText();}

    public void ViewListener(ActionListener c){this.view.addActionListener(c);}
    public void SearchListener(ActionListener c){this.search.addActionListener(c);}
    public void OrderListener(ActionListener c){this.order.addActionListener(c);}
    public void MenuSet(String s){menu.setText(s);}
    public String getOrder(){return result.getText();}

}
