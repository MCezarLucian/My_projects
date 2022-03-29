package data;

import model.BasedProduct;
import model.MenuItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.nio.*;
import java.util.stream.Collectors;

public class Reader {

    public HashSet<MenuItem> ReadFromFile (String filePath) throws IOException {

        HashSet<MenuItem> hashSet = new HashSet<MenuItem>();
        HashSet<String> title = new HashSet<String>();
        List<String> stringList = Files.lines(Paths.get(filePath))
                .collect(Collectors.toList());

        stringList.remove(0);
        stringList.forEach(s ->{

            BasedProduct product = new BasedProduct();

            String[] aux = s.split(",");

            if(title.add(aux[0]) == true){
                product.setName(aux[0]);
                product.setRating(Double.parseDouble(aux[1]));
                product.setCalories(Integer.parseInt(aux[2]));
                product.setProtein(Integer.parseInt(aux[3]));
                product.setFat(Integer.parseInt(aux[4]));
                product.setSodium(Integer.parseInt(aux[5]));
                product.setPrice(Integer.parseInt(aux[6]));
                hashSet.add(product);
            }
        });
        return hashSet;
    }
}
