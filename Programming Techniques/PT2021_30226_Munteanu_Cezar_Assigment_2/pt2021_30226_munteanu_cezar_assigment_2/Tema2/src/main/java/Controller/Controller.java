package Controller;

import Model.Client;
import Model.Generator;
import Model.Coada;
import Model.App;
import View.View2;
import View.View1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {

    App app ;
    View1 view = new View1();

    public Controller(){
        this.view.addListenerAdauga1(new ListenerAdauga1());
    }

    class ListenerAdauga1 implements ActionListener{

        public void actionPerformed(ActionEvent e){
            int arrMax = Integer.parseInt(view.getArrMax());
            int arrMin = Integer.parseInt(view.getArrMin());
            int servMax = Integer.parseInt(view.getServMax());
            int servMin = Integer.parseInt(view.getServMin());
            int ClientsNr = Integer.parseInt(view.getClientsNr());
            int simTime = Integer.parseInt(view.getSimTime());
            int queuesNr = Integer.parseInt(view.getQueuesNr());
            app = new App(arrMax, arrMin, servMax, servMin, ClientsNr, simTime, queuesNr);
            app.start();

        }
    }

}
