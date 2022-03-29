package Controller;

import Controller.Controller;
import Model.Client;
import Model.Generator;
import Model.Coada;
import Model.App;

public class Main {

    public static void main(String[] args){
        /*
        //System.out.println("AAAA");
        ArrayList<View.Client> clients = new ArrayList<View.Client>();
        Model.Generator generator = new Model.Generator(5, 3, 4, 1,5, clients);
        generator.Generate();
        generator.getClients();
        View.Coada coada = new View.Coada();
       // System.out.println("AAAA");
        for(View.Client a:clients){
            System.out.println(a.getID() + " " + a.getArrTime() + " " + a.getServTime());
            coada.addClient(a);

        }

        coada.start();
        //System.out.println(coada.getWaitingTime());
        */
        //View.View2 v = new View.View2();
        //View.App ap = new View.App(40, 2, 7, 1, 50, 60, 5);
        //ap.start();


        Controller c = new Controller();
        //View.View1 v = new View.View1();

    }
}
