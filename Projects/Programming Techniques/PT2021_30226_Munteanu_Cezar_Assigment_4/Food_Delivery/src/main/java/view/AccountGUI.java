package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AccountGUI extends JFrame{
    private JPanel accountPanel = new JPanel();
    private JTextField username = new JTextField();
    private JTextField password = new JTextField();
    private JLabel id = new JLabel("Username :");
    private JLabel ps = new JLabel("Password :");
    private JButton Login = new JButton("Login");
    private JButton Register = new JButton("Register");
    private JLabel message = new JLabel();


    public AccountGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,600,600);
        this.setTitle("Login Window");


        // LOGIN WINDOW
        accountPanel.setBounds(0,0,600,600);
        this.add(accountPanel);
        accountPanel.setLayout(null);
        id.setBounds(200,125,200,25);
        accountPanel.add(id);
        username.setBounds(200,150,200,25);
        accountPanel.add(username);
        ps.setBounds(200,190,100,20);
        accountPanel.add(ps);
        password.setBounds(200,210, 200, 25);
        accountPanel.add(password);
        Login.setBounds(200, 250, 90, 25);
        accountPanel.add(Login);
        Register.setBounds(310, 250, 90, 25);
        accountPanel.add(Register);
        message.setBounds(200,275,200,25);
        accountPanel.add(message);


        accountPanel.setVisible(true);

        this.setVisible(true);
    }

    public String getUsername(){return username.getText();}
    public String getPassword(){return password.getText();}
    public void setAccount(String info){message.setText(info);}
    public void LoginListener(ActionListener c){this.Login.addActionListener(c);}
    public void RegisterListener(ActionListener c){this.Register.addActionListener(c);}
}
