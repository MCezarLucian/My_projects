package model;

import java.io.Serializable;
import java.util.*;

public class ComposedProduct extends MenuItem implements Serializable {

    private ArrayList<BasedProduct> products;

    public ComposedProduct (String name, ArrayList<BasedProduct> products){
        this.products = products;
        this.name = name;
    }

    public ArrayList<BasedProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<BasedProduct> products) {
        this.products = products;
    }
    public int getFinalPrice(){
        int total = 0;
        for(BasedProduct x : products){

            total = total + x.getPrice();

        }
        return total;
    }

    public double averageRating(){
        double a = 0;
        int cnt = 0;
        for(BasedProduct x : products){
            a = a + x.getRating();
            cnt++;
        }
        return (double) a/cnt;
    }
    public int averageCalories(){
        int a = 0;
        //int cnt = 0;
        for(BasedProduct x : products){
            a = a + x.getCalories();
            //cnt++;
        }
        return (int) a;
    }
    public int averageProteins(){
        int a = 0;
        //int cnt = 0;
        for(BasedProduct x : products){
            a = a + x.getProtein();
            //cnt++;
        }
        return (int) a;
    }
    public int averageFat(){
        int a = 0;
        //int cnt = 0;
        for(BasedProduct x : products){
            a = a + x.getFat();
            //cnt++;
        }
        return (int) a;
    }
    public int averageSodium(){
        int a = 0;
        //int cnt = 0;
        for(BasedProduct x : products){
            a = a + x.getSodium();
            //cnt++;
        }
        return (int) a;
    }
    public int averagePrice(){
        int a = 0;
        //int cnt = 0;
        for(BasedProduct x : products){
            a = a + x.getCalories();
            //cnt++;
        }
        return (int) a;
    }


}
