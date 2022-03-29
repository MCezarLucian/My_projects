package controller;

import data.Serialization;
import model.Account;
import presentation.DeliveryServiceProcesing;
import view.AccountGUI;
import view.AdministratorGUI;
import view.ClientGUI;
import view.EmployeeGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;

public class ControllerAccount {

    AccountGUI accountGUI ;
    DeliveryServiceProcesing d = new DeliveryServiceProcesing();
    int ClientID;

    public ControllerAccount(AccountGUI a){
        this.accountGUI = a;

        Serialization ser = new Serialization();
        d = (DeliveryServiceProcesing) ser.read("menu.txt");



        accountGUI.LoginListener(new LoginListener());
        accountGUI.RegisterListener(new RegisterListener());

        ser.update(d, "menu.txt");
    }

    public class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            HashSet<Account> users = d.getUsers();
            String username = accountGUI.getUsername();
            String password = accountGUI.getPassword();
            for (Account x : users) {
                System.out.println(username + "    " + x.getUsername());
                if (x.getUsername().compareTo(username) == 0 && x.getPassword().compareTo(password) == 0) {
                    ClientID = x.getId();
                    if (x.getRole() == 0) {//Admin
                        AdministratorGUI admin = new AdministratorGUI();
                        ControllerAdministrator adminControl = new ControllerAdministrator(admin, d);
                    }
                    if (x.getRole() == 1) {
                        ClientGUI client = new ClientGUI();
                        ControllerClient clientControl = new ControllerClient(client, ClientID, d);

                    }
                    if (x.getRole() == 2) {
                        EmployeeGUI employee = new EmployeeGUI();

                        ControllerEmployee employeeControl = new ControllerEmployee(employee, d);
                        d.addObserver(employeeControl);
                    }
                    //accountGUI.dispose();
                } else {
                    accountGUI.setAccount("You need to register!");
                }
            }

        }
    }

    public class RegisterListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = accountGUI.getUsername();
                String password = accountGUI.getPassword();
                Random rand = new Random();
                d.addUser(new Account(rand.nextInt(200), username, password, 1));
            }
    }


}
