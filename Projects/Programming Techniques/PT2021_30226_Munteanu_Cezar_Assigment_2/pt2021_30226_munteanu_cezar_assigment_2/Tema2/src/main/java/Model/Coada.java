package Model;

import java.util.ArrayList;
import java.util.concurrent.*;


public class Coada extends Thread{

    private BlockingQueue<Client> clients = new LinkedBlockingQueue<>();
    //private ArrayList<Client> clients = new ArrayList<Client>();
    private int currPoz;
    private int poz ;
    private int dim = 0;
    private int waitingTime = 0;
    private boolean trueValue = true;
    Client waitingClient ;

    public void setTrueValue(boolean x){this.trueValue = x;};
    /*
    public int getCurrPoz(){
        return clients.get(0).getServTime();
    }*/

    public int getPoz(){
        return this.poz;
    }
    public void setCurrPoz(int curr){
        this.currPoz = curr;
    }
    public void setPoz(int poz){
        this.poz = poz;
    }
    public void addWaitingTime(Client x){this.waitingTime = this.waitingTime + x.getServTime();}
    /*
    public void updateCurrPoz(int curr){
        clients.get(0).setServTime(curr);
    }*/
    public int getWaitingTime(){return this.waitingTime;}
    public void addClient(Client client){
        clients.add(client);
        dim++;
        waitingTime += client.getServTime();
    }
    public void removeClient(){
        clients.remove(0);
    }
    /*
    public Client getClient(int nr){
        return clients.element(nr);
    }*/
    public int getSize(){
        return clients.size();
    }
    public void setWaitingClient(Client x){this.waitingClient = x;}
    public synchronized void run(){
        while(trueValue){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread exception!");
                }
                if(waitingTime <= 0){
                    waitingTime = 1;
                }
                waitingTime--;
                if(!clients.isEmpty()) {
                    clients.peek().minServTime();
                    if (clients.peek().getServTime() == 0) {
                        clients.poll();
                    }
                }
            }
    }
    public String toString(){
        int a = 0;
        String aux = "";
        /*
        while(a < this.getSize()){
            aux = aux + "(" + this.getClient(a).getID() + ", " +
                    this.getClient(a).getArrTime() + ", " +
                    this.getClient(a).getServTime() + ");" ;
            a++;
        }*/

        for(Client x : clients){
            aux = aux + "(" + x.getID() + ", " +
                    x.getArrTime() + ", " +
                    x.getServTime() + ");" ;
        }
        System.out.println();
        if(this.getSize() == 0) {
            aux = aux + "Closed";
        }
        return aux;
    }
    /*
    public void printCoada(){
        int a = 0;
        while(a < this.getSize()){
            System.out.print("Serv:" + this.getClient(a).getServTime() + " " +
                    "Arr:" + this.getClient(a).getArrTime() + " " +
                    "ID:" + this.getClient(a).getID() + "     ");
            a++;
        }
        System.out.println();
        if(this.getSize() == 0) {
            System.out.println("View.Coada Nula");
        }
    }*/
    public int getSum(){
        int sum = 0;

        if(this.getSize() == 0){
            return 0;
        }
        for(Client x : clients){
            sum += x.getServTime();
        }
        return sum;
    }

}
