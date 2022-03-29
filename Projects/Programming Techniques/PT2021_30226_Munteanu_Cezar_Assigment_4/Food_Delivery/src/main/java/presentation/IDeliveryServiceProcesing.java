package presentation;

import model.*;
import model.MenuItem;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface IDeliveryServiceProcesing {
    /**
     *
     * @return our orders
     */
    HashMap<Order, ArrayList<MenuItem>> getOrdersLists();

    /**
     * Add a new order
     * @param order
     * @param items
     */
    void addOrder(Order order, ArrayList<MenuItem> items);

    /**
     * Remove an order
     * @param order
     */
    void removeOrder(Order order);

    /**
     *
     * @return our menu
     */
    HashSet<MenuItem> getMenu();

    /**
     * Add a product in the menu
     * @param menuItem
     */
    void addMenuItem(MenuItem menuItem);

    /**
     * Edit a product from menu
     * @param menuItem
     */
    void editMenuItem(MenuItem menuItem);

    /**
     * Remove a product from menu
     * @param menuItem
     */
    void removeMenuItem(MenuItem menuItem);

    /**
     *
     * @return our users of application
     */
    HashSet<Account> getUsers();

    /**
     * Add a new user
     * @param user
     * @return
     */
    String addUser(Account user);

    /**
     *
     * @return based products
     */
    HashSet<BasedProduct>getBasedProducts();

    /**
     *
     * @return composed products
     */
    HashSet<ComposedProduct> getComposedProducts();

    /**
     * This function import our data from .csv file
     * @throws IOException
     */
    void importProducts() throws IOException;

    /**
     * This function generates reports of how the food delivery works
     * @param max
     * @param min
     * @param val
     * @param aparitii
     * @param vall
     * @param daysNr
     */
    void Reports(int max, int min, int val, int aparitii,int vall, int daysNr);

}
