import controller.ControllerAccount;
import data.Reader;
import data.Serialization;
import model.Account;
import model.BasedProduct;
import model.MenuItem;
import model.Order;
import presentation.DeliveryServiceProcesing;
import view.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.String;

public class main {
    public static void main(String args[]) throws IOException {

        /*
        DeliveryServiceProcesing d = new DeliveryServiceProcesing();
        Account a = new Account(1,"a1","a1",0);
        Account a2 = new Account(1,"a2","a2",2);
        d.importProducts();
        d.addUser(a);
        d.addUser(a2);
        HashSet<MenuItem> aux = d.getMenu();
        for(MenuItem i : aux){
            System.out.println(i.getName());
        }
        Serialization s = new Serialization();
        s.update(d,"menu.txt");

         */

       AccountGUI view = new AccountGUI();
       ControllerAccount c = new ControllerAccount(view);


    }

}
