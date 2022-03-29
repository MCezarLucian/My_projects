import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        /*
        Monom a1 = new Monom(2,1);
        //Monom a2 = new Monom(1,-2);
        //Monom a3 = new Monom(0,1);

        Monom b1 = new Monom(2,1);
        //Monom b2 = new Monom(0,2);

        Polinom a = new Polinom();
        a.addMonom(a1);
       // a.addMonom(a2);
        //a.addMonom(a3);

        Polinom b = new Polinom();
        b.addMonom(b1);
        //b.addMonom(b2);

        Operatii x = new Operatii();
        System.out.println(x.impartire(a,b).toString());


         */



        Polinom a = new Polinom();
        Polinom b = new Polinom();
        Polinom c = new Polinom();
        View v = new View();
        Controller d = new Controller(a, b, c, v);

        v.setVisible(true);




    }
}
