package presentation;


import data.Reader;
import data.Serialization;
import model.*;
import model.MenuItem;
import view.ClientGUI;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryServiceProcesing extends Observable implements IDeliveryServiceProcesing,Serializable {

    private HashSet<MenuItem> menu = new HashSet<MenuItem>();
    private HashSet<Account> user = new HashSet<Account>();
    private HashMap<Order, ArrayList<MenuItem>> orderList = new HashMap<Order, ArrayList<MenuItem>>();

    /**
     * Instantiates a Delivery service
     */

    public DeliveryServiceProcesing(){
    }

    /**
     *
     * @return A list of orders.
     */
    @Override
    public HashMap<Order, ArrayList<MenuItem>> getOrdersLists() {
        return orderList;
    }

    /**
     * Add an order in the HashMap
     * @param order an order
     * @param items an product/menu item
     */
    @Override
    public void addOrder(Order order, ArrayList<MenuItem> items) {
        assert order != null : "Try to add null order";
        assert items != null;
        orderList.put(order, items);
        setChanged();
        notifyObservers(order.toString());

    }


    /**
     * Remove an order
     * @param order which we want to remove
     */
    @Override
    public void removeOrder(Order order) {
        assert  order != null : "Try to remove null order";
        orderList.remove(order);
    }

    /**
     *
     * @return our menu
     */
    @Override
    public HashSet<MenuItem> getMenu() {
        return menu;
    }

    /**
     * Add a product in the menu
     * @param menuItem
     */
    @Override
    public void addMenuItem(MenuItem menuItem) {
        assert menuItem != null : "Try to add null item in menu";
        menu.add(menuItem);
    }

    /**
     * Edit a product from menu
     * @param menuItem
     */
    @Override
    public void editMenuItem(MenuItem menuItem) {
        assert menuItem != null : "Try to edit a with a null value";
        for(MenuItem x : menu){
            if(x.name == menuItem.name){
                x = menuItem;
                break;
            }
        }
    }

    /**
     * Remove a product from menu
     * @param menuItem
     */
    @Override
    public void removeMenuItem(MenuItem menuItem) {
        assert menuItem != null : "Try to remove a null menu item";
        menu.remove(menuItem);
    }

    /**
     *
     * @return all users of the application
     */
    @Override
    public HashSet<Account> getUsers() {
        return user;
    }

    /**
     * Add a new user
     * @param newUser
     * @return A string which we'll display if the new account can be created
     */
    @Override
    public String addUser(Account newUser) {
        String valid = null;
        assert user != null : "try to add null user";
        for(Account x : user){
            if(x.getUsername() == newUser.getUsername()){
                valid = "This username already exists!";
                return valid;
            }
        }
        valid = "Account created!";
        user.add(newUser);
        return valid;
    }

    /**
     *
     * @return based products
     */
    @Override
    public HashSet<BasedProduct> getBasedProducts() {
        Serialization ser = new Serialization();
        HashSet<BasedProduct> bProduct;
        bProduct = new HashSet<>();
        bProduct = (HashSet<BasedProduct>) ser.read("BasedProducts.txt");
        return bProduct;
    }

    /**
     *
     * @return composed products
     */
    @Override
    public HashSet<ComposedProduct> getComposedProducts() {
        Serialization ser = new Serialization();
        HashSet<ComposedProduct> cProduct;
        cProduct = new HashSet<>();
        cProduct = (HashSet<ComposedProduct>) ser.read("ComposedProducts.txt");
        return cProduct;
    }

    /**
     * This functions import our data from .csv file
     * @throws IOException
     */
    @Override
    public void importProducts() throws IOException {
        Reader read = new Reader();
        HashSet <MenuItem> set = read.ReadFromFile("src//products.csv");
        for(MenuItem i : set){
            menu.add(i);
        }

    }

    /**
     * The fucntion which generates our reports
     * @param max
     * @param min
     * @param val
     * @param aparitii
     * @param vall
     * @param daysNr
     */
    @Override
    public void Reports(int max, int min, int val, int aparitii, int vall, int daysNr) {
        assert min < 0 : "Wrong min!";
        assert max < 0 : "Wrong max!";

        Generator.getInstance().write("The Reports are :");
        //1st
        Map<Order, ArrayList<MenuItem>> orders = orderList.entrySet().stream()
                .filter(hMap -> hMap.getKey().getOrderDate().getHour() >= min)
                .filter(hMap -> hMap.getKey().getOrderDate().getHour() <= max)
                .collect(Collectors.toMap(hMap -> hMap.getKey(), hMap -> hMap.getValue()));

        ArrayList<Order> ord = new ArrayList<>();
        for(Order i : orders.keySet()){
            ord.add(i);
        }
        Generator.getInstance().write("-Orders with time interval wanted:");
        for(Order x : ord){
            Generator.getInstance().write(x.toString());
        }

        assert val < 0 ;
        //2nd
        List<MenuItem> prod = menu.stream()
                .filter(pr -> pr.getSituation() >= val)
                .collect(Collectors.toList());

        Generator.getInstance().write("Products which was ordered more than a value:");
        for(MenuItem x : prod){
            Generator.getInstance().write(x.getName());
        }


        assert vall < 0;
        assert aparitii < 0;
        //3th
        Generator.getInstance().write("Clients that ordered more than a value:");

        List<Account> client = user.stream()
                .filter( cl -> cl.getAparitii() >= aparitii)
                .collect(Collectors.toList());

        List<Account> rez = new ArrayList<>();

        for(Account i : client){
            Map <Order, List<MenuItem>> order = orderList.entrySet().stream()
                    .filter(hm -> hm.getKey().getClientID() == i.getId())
                    .filter(hm -> hm.getKey().getTotal() >= vall)
                    .collect(Collectors.toMap(hm -> hm.getKey(), hm -> hm.getValue()));

            if(order.size() > 0){
                rez.add(i);
            }
        }

        rez = rez.stream()
                .distinct()
                .collect(Collectors.toList());


        for(Account i : rez){
          Generator.getInstance().write(i.toString());
        }

        assert daysNr < 0;

        Generator.getInstance().write("\n");
        Generator.getInstance().write("Products ordered in a specified day:");

        orderList.entrySet().forEach(e ->{
            System.out.println(e.getKey()+ " - " + e.getValue());
        });

        Map<Order, List<MenuItem>> rezv2 = orderList.entrySet().stream()
                .filter(m -> m.getKey().getOrderDate().getDayOfWeek().equals(daysNr))
                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue()));
        System.out.println("Dorim afisare");
        System.out.println("adasdka");

        List<MenuItem> rez4 = new ArrayList<>();
        for(Order i : orders.keySet()){
            rez4.addAll(rezv2.get(i));
        }

        System.out.println("dorim citire");
        rez4 = rez4.stream()
                .distinct()
                .collect(Collectors.toList());

        for(MenuItem i : rez4){
            Generator.getInstance().write(i.getName());
        }
        Generator.getInstance().close(true);

    }
}
