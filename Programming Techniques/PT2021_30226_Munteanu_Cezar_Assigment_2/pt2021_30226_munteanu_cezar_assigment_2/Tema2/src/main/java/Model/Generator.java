package Model;

import Model.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Generator {

    int ClientsNr;
    int arrMax;
    int arrMin;
    int servMax;
    int servMin;
    int id = 0;
    private BlockingQueue<Client> clients = new LinkedBlockingQueue<>();

    Generator(int arrMax, int arrMin, int servMax, int servMin, int ClientsNr, BlockingQueue<Client> clients){
        this.arrMax = arrMax;
        this.arrMin = arrMin;
        this.servMax = servMax;
        this.servMin = servMin;
        this.ClientsNr = ClientsNr;
        this.clients = clients;
    }

    public void Generate(){

        ArrayList<Client> cl = new ArrayList<Client>();
        while(!clients.isEmpty()){
            cl.add(clients.peek());
            clients.poll();
        }

        Random random = new Random();
        int copy = this.ClientsNr;
        //System.out.println(copy);

        while(copy > 0){

            int arrRandom = random.nextInt((this.arrMax - this.arrMin) + 1) + this.arrMin;
            int servRandom = random.nextInt((this.servMax - this.servMin) + 1) + this.servMin;
            this.id++;
            int idRandom = this.id;
            cl.add(new Client(idRandom, arrRandom, servRandom));
            copy-- ;
        }
        Collections.sort(cl);
        for(Client x : cl){
            clients.add(x);
        }

    }
    public BlockingQueue<Client> getClients(){

        //Collections.sort(clients);
        for(Client a : clients) {
            System.out.println(a.getID() + " " + a.getArrTime() + " " + a.getServTime());
        }
        return this.clients;
    }


}
