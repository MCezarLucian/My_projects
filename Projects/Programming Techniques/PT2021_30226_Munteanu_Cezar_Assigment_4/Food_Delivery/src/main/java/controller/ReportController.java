package controller;

import presentation.DeliveryServiceProcesing;
import view.ReportGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportController {

    ReportGUI r ;
    DeliveryServiceProcesing d;

    public ReportController(ReportGUI r, DeliveryServiceProcesing d){
        this.d= d;
        this.r = r;

        r.generateListener(new generateListener());
    }

    public class generateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            d.Reports(r.minText(), r.maxText(), r.orderedText(), r.placedText(), r.hvText(), r.dayText());
        }
    }

}
