package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Generator {
    public File file;
    public static int cnt = 1;
    public static Object object = new Object();
    public static Generator instance;
    public PrintWriter printer;

    public Generator(){
        file = new File("reports.txt");
        try {
            printer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Generator getInstance(){
        if(instance == null){
            System.out.println(" ");
            synchronized (object){
                ////////////////////
                System.out.println(" ");
                if(instance == null){
                    ////////////////////////
                    System.out.println(" ");
                    instance = new Generator();
                }
            }
        }
        return instance;
    }



    public void write(String string){
        printer.println(string);
        cnt = cnt + 1;
    }

    public void close(boolean x){
        if(x == true){
            printer.close();
        }
    }
}
