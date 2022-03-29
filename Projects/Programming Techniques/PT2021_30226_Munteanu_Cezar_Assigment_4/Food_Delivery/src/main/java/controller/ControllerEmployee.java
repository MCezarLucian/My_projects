package controller;

import model.MenuItem;
import model.Order;
import presentation.DeliveryServiceProcesing;
import view.EmployeeGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ControllerEmployee implements Observer {

    EmployeeGUI em  ;
    DeliveryServiceProcesing d ;

    public ControllerEmployee(EmployeeGUI em, DeliveryServiceProcesing d){
        this.d = d;
        this.em = em;

        em.deliveryListener(new deliveryListener());

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Merge Ghita!");
    }

    public class deliveryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("adasd");
            String s="";
            int cnt =0 ;
            HashMap<Order, ArrayList<MenuItem>> o = d.getOrdersLists();
            System.out.println(o);
            for(Order x : o.keySet() ){
                System.out.println("ASA");
                System.out.println(x.getOrderID());
                    s += "Order ID:" + x.getOrderID() + " Client ID:" + x.getClientID() + "\n";
                }
            em.setAfis(s);
            }




        }
}
