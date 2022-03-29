package controller;

import model.BasedProduct;
import model.ComposedProduct;
import model.MenuItem;
import presentation.DeliveryServiceProcesing;
import view.AdministratorGUI;
import view.ReportGUI;
import view.productTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControllerAdministrator {

    AdministratorGUI a ;
    DeliveryServiceProcesing d;

    public ControllerAdministrator(AdministratorGUI a, DeliveryServiceProcesing d){
        this. a = a;
        this.d = d;
        a.ImportListener(new ImportListener());
        a.AddListener(new AddListener());
        a.RemoveListener(new RemoveListener());
        a.EditListener(new EditListener());
        a.CreateListener(new CreateListener());
        a.ViewListener(new ViewListener());
        a.ReportListener(new ReportListener());
    }

    public class ImportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                d.importProducts();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = a.getName();
            for(BasedProduct x : d.getBasedProducts()){
                if(x.getName().compareTo(name) == 0){
                    d.addMenuItem(x);
                    break;
                }
            }
            for(ComposedProduct y : d.getComposedProducts()) {
                if (y.getName().compareTo(name) == 0) {
                    d.addMenuItem(y);
                    break;
                }
            }
        }
    }

    public class RemoveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = a.getName();
            for(MenuItem x : d.getMenu()){
                if(x.getName().compareTo(name) == 0){
                    d.removeMenuItem(x);
                    break;
                }
            }

        }
    }

    public class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            BasedProduct b;
            String name = a.getName();
            for(MenuItem x : d.getMenu()){
                if(x.getName().compareTo(name) == 0){
                    b = new BasedProduct(a.getName(),Double.parseDouble(a.getRating()),Integer.parseInt(a.getCalories()),Integer.parseInt(a.getProteins()),Integer.parseInt(a.getFat()),Integer.parseInt(a.getSodium()),Integer.parseInt(a.getPrice()));
                    d.editMenuItem(b);
                    break;
                }
            }

        }
    }

    public class CreateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            d.addMenuItem(new BasedProduct(a.getName(),Double.parseDouble(a.getRating()),Integer.parseInt(a.getCalories()),Integer.parseInt(a.getProteins()),Integer.parseInt(a.getFat()),Integer.parseInt(a.getSodium()),Integer.parseInt(a.getPrice())));

        }
    }

    public class ViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] objects = new Object[50000][10000];
            int cnt = 0;
            for(MenuItem x : d.getMenu()){
                objects[cnt][0] = x.getName();
                cnt++;
            }

            productTable t = new productTable(objects);

            //a.MenuSet(s);
        }
    }

    public class ReportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ReportGUI r = new ReportGUI();
            ReportController rep = new ReportController(r, d);
        }
    }
}
