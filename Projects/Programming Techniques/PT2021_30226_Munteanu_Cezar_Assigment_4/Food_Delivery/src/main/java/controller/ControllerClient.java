package controller;

import model.Account;
import model.BasedProduct;
import model.ComposedProduct;
import model.Order;
import presentation.DeliveryServiceProcesing;
import view.ClientGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControllerClient {

    int ordernr=0;
    ClientGUI c;
    DeliveryServiceProcesing d;
    int clientID;

    public ControllerClient(ClientGUI c, int clientID, DeliveryServiceProcesing d){
        this.d = d;
        this.clientID = clientID;
        this.c = c;

        c.ViewListener(new ViewListener());
        c.SearchListener(new SearchListener());
        c.OrderListener(new OrderListener());
    }

    public class ViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("PAI");
            String s = null;
            for(MenuItem x : d.getMenu()){
                s = s + x.getName() + "\n";
            }
            c.MenuSet(s);
        }
    }


    public class SearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String s  = "";
            if(c.getName().compareTo("") > 0){
                //System.out.println("SAL");
                for(MenuItem x : d.getMenu()){
                    if(x.getName().compareTo(c.getName()) == 0){
                        s = s + x.getName();
                       // System.out.println(s);
                        break;
                    }
                }
            }
            else{
                if(c.getRating().compareTo("") > 0){
                    double rating = Double.parseDouble(c.getRating());
                    for(MenuItem x : d.getMenu()){
                        if(x instanceof BasedProduct){
                            if(((BasedProduct) x).getRating() == rating){
                                s = s + x.getName() + "\n";
                            }
                        }
                        if(x instanceof ComposedProduct){
                            if(((ComposedProduct) x).averageRating() == rating){
                                s = s + x.getName() + "\n";
                            }
                        }
                    }
                }
                else{
                    if(c.getCalories().compareTo("") > 0){
                        int cal = Integer.parseInt(c.getCalories());
                        for(MenuItem x : d.getMenu()){
                            if(x instanceof BasedProduct){
                                if(((BasedProduct) x).getCalories() == cal){
                                    s = s + x.getName() + "\n";
                                }
                            }
                            if(x instanceof ComposedProduct){
                                if(((ComposedProduct) x).averageCalories() == cal){
                                    s = s + x.getName() + "\n";
                                }
                            }
                        }
                    }
                    else{
                        if(c.getProteins().compareTo("") > 0){
                            int prot = Integer.parseInt(c.getProteins());
                            for(MenuItem x : d.getMenu()){
                                if(x instanceof BasedProduct){
                                    if(((BasedProduct) x).getProtein() == prot){
                                        s = s + x.getName() + "\n";
                                    }
                                }
                                if(x instanceof ComposedProduct){
                                    if(((ComposedProduct) x).averageProteins() == prot){
                                        s = s + x.getName() + "\n";
                                    }
                                }
                            }
                        }
                        else{
                            if(c.getFat().compareTo("") > 0){
                                int fat = Integer.parseInt(c.getFat());
                                for(MenuItem x : d.getMenu()){
                                    if(x instanceof BasedProduct){
                                        if(((BasedProduct) x).getFat() == fat){
                                            s = s + x.getName() + "\n";
                                        }
                                    }
                                    if(x instanceof ComposedProduct){
                                        if(((ComposedProduct) x).averageFat() == fat){
                                            s = s + x.getName() + "\n";
                                        }
                                    }
                                }
                            }
                            else{
                                if(c.getSodium().compareTo("") >  0){
                                    int sod = Integer.parseInt(c.getSodium());
                                    for(MenuItem x : d.getMenu()){
                                        if(x instanceof BasedProduct){
                                            if(((BasedProduct) x).getSodium() == sod){
                                                s = s + x.getName() + "\n";
                                            }
                                        }
                                        if(x instanceof ComposedProduct){
                                            if(((ComposedProduct) x).averageSodium() == sod){
                                                s = s + x.getName() + "\n";
                                            }
                                        }
                                    }
                                }
                                else{
                                    if(c.getPrice().compareTo("") > 0){
                                        int pr = Integer.parseInt(c.getPrice());
                                        for(MenuItem x : d.getMenu()){
                                            if(x instanceof BasedProduct){
                                                if(((BasedProduct) x).getPrice() == pr){
                                                    s = s + x.getName() + "\n";
                                                }
                                            }
                                            if(x instanceof ComposedProduct){
                                                if(((ComposedProduct) x).averagePrice() == pr){
                                                    s = s + x.getName() + "\n";
                                                }
                                            }
                                        }
                                }
                            }
                        }
                    }

                }
            }
        }

            c.MenuSet(s);

    }

}
    public class OrderListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = c.getOrder();
            ArrayList<model.MenuItem> itms = new ArrayList<model.MenuItem>();
            int total = 0;
            //ArrayList<String> sir = new ArrayList<String>();
            String[] sir = s.split("\n");
            for(String aux : sir){
                //System.out.println(aux);
                for(MenuItem x : d.getMenu()){
                    if(x.getName().compareTo(aux) == 0){
                        itms.add((model.MenuItem) x);
                        if(x  instanceof BasedProduct){
                            total = total + ((BasedProduct) x).getPrice();
                            ((BasedProduct) x).setSituation(((BasedProduct) x).getSituation() + 1);
                        }
                        if(x instanceof ComposedProduct){
                            total = total + ((ComposedProduct) x).getFinalPrice();
                            ((ComposedProduct) x).setSituation(((ComposedProduct) x).getSituation() + 1);
                        }
                    }
                }
            }
            for (Account x : d.getUsers()){
                if(clientID == x.getId()){
                    x.setAparitii(x.getAparitii() + 1);
                }
            }
            ordernr ++;
            Order o = new Order(ordernr, clientID, LocalDateTime.now(),total);
            d.addOrder(o, itms);
            System.out.println(o.toString());
            for(model.MenuItem i : itms){
                System.out.println(i.toString());
            }

        }
    }
}
