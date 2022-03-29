package bll;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import  dao.AbstractDAO;
import  dao.ClientDAO;
import  model.Client;


public class ClientBll {

    private ClientDAO client;

    public ClientBll(){
        client = new ClientDAO();
    }

    public List<Client> SelectQuery(){
        return client.findAll();
    }

    public Client findByID(int id){
        Client c = client.findByID(id);
        if(c == null){
            throw  new NoSuchElementException("The client with id = " + id + " was not found!");
        }
        return c;
    }

    public void insert(Client c){
        client.insert(c);
    }

    public void update(Client c){
        client.update(c);
    }

    public void delete(Client c){
        client.delete(c);
    }

    public Object[][] getTable(){
        return client.getTable(SelectQuery());
    }

    public Object[] getHeader(){
        return client.getTableHeader();
    }
}
