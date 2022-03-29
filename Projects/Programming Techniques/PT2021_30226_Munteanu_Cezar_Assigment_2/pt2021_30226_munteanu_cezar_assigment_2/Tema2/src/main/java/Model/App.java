package Model;

import View.View2;
import View.View1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class App extends Thread {

    private ArrayList<Coada> cozi = new ArrayList<Coada>();
    private int nrCozi = 0;
    private BlockingQueue<Client> clients = new LinkedBlockingQueue<>();
    private int nrClienti = 0;
    private int simTime = 0;
    private String fileName="test.txt";
    PrintWriter writer = null;
    View2 view = new View2();
    public App(int arrMax, int arrMin, int servMax, int servMin, int nrClients, int simTime, int nrCozi){

        this.nrCozi = nrCozi;
        //this.view = view;
        this.nrClienti = nrClients;
        this.simTime = simTime;
        //System.out.println(this.nrClienti);
        Generator generator = new Generator(arrMax, arrMin, servMax, servMin, this.nrClienti, clients);
        generator.Generate();
        //generator.getClients();

    }
    public void setNrClienti(int x){this.nrClienti = x;}
    public void setNrCozi(int x){this.nrCozi = x;}
    public int getNrCozi(){return this.nrCozi;}
    public void generareCozi(){
        for(int i = 0 ; i < nrCozi; i++){
            Coada q = new Coada();
            cozi.add(q);
        }
    }
    public void startCozi(){
        for(int i = 0; i < nrCozi; i++){
            cozi.get(i).start();
        }
    }
    public void stopCozi(){
        for(int i = 0; i < nrCozi; i++){
            cozi.get(i).setTrueValue(false);
        }
    }
    public synchronized void run(){

        try{
            writer = new PrintWriter(fileName);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        BlockingQueue<Client> lista = clients;
        generareCozi();
        startCozi();
        int time = 0;
        int peakHour = 0 ;
        int peakMax = 0;
        int waitingAvg = 0;
        int servAvg = 0;
        while(time <= simTime){
            String aux="";
            aux = aux + "Time " + time + "\n";
            writer.println("Time " + time);
            aux = aux + "Waiting clients: ";
            writer.print("Waiting clients: ");
            for(Client a : lista){
                writer.print(a.printClient());
                aux = aux + a.printClient();
                if(time == a.getArrTime()){
                    //View.Coada min = new View.Coada();
                    int val = 100000;
                    int index = 0;
                    int p = 0;
                    servAvg += a.getServTime();
                    int wt = 0;
                    for(Coada x : cozi){
                        if(x.getWaitingTime() < val){
                            index = p;
                            val = x.getWaitingTime();
                        }
                        wt += x.getWaitingTime();
                        p++;
                    }
                    waitingAvg = waitingAvg + wt / nrCozi;
                    cozi.get(index).addClient(a);
                    //clients.remove(a);
                }
            }
            writer.println();
            aux = aux + "\n";
            int ind = 0;
            for(Coada x : cozi){
                ind ++;
                writer.println("Queue " + ind + " : " + x.toString());
                aux = aux + "Queue " + ind + " : " + x.toString() + "\n";
                if(x.getSize() > peakMax){
                    peakMax = x.getSize();
                    peakHour = time;
                }
            }

            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int copyT=time;

            clients.removeIf(c->c.getArrTime() == copyT);
            if(time == simTime){
                view.getShow1(aux + "\n" + "Average Waiting time : " + waitingAvg/simTime + "\n" + "Average Service time : " + servAvg/nrClienti + "\n" + "Peak Hour : " + peakHour);
            }
            else{
                view.getShow1(aux);
            }
            time++;
            //System.out.println(aux);
            //view.getShow1(aux);
        }
        stopCozi();
        writer.println("Average Waiting time : " + waitingAvg/simTime);
        writer.println("Average Service time : " + servAvg/nrClienti);
        writer.println("Peak Hour : " + peakHour);
        writer.close();
    }



}
