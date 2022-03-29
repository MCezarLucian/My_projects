
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private Polinom model1;
    private Polinom model2;
    private Polinom model3;
    private View view;

    public Controller(Polinom model1, Polinom model2, Polinom model3, View view){
        this.model1 = model1;
        this.model2 = model2;
        this.model3 = model3;
        this.view = view;
        this.view.addListenerAdauga1(new ListenerAdauga1());
        this.view.addListenerAdauga2(new ListenerAdauga2());

        this.view.addListenerAduna(new ListenerAduna());
        this.view.addListenerScade(new ListenerScade());
        this.view.addListenerInmulteste(new ListenerInmulteste());
        this.view.addListenerImparte(new ListenerImparte());
        this.view.addListenerDeriveaza(new ListenerDeriveaza());
        this.view.addListenerIntegreaza(new ListenerIntegreaza());
        this.view.addListenerCurata(new ListenerCurata());


    }



    class ListenerAdauga1 implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String coefString1, powString1;
            coefString1 = view.getCoef1();
            powString1 = view.getPow1();
            double coef1 = Double.parseDouble(coefString1);
            double pow1 = Double.parseDouble(powString1);
            model1.addMonom(new Monom(pow1,coef1));
            view.getShow1(model1);

        }
    }

    class ListenerAdauga2 implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String coefString2, powString2;
            coefString2 = view.getCoef2();
            powString2 = view.getPow2();
            double coef2 = Double.parseDouble(coefString2);
            double pow2 = Double.parseDouble(powString2);
            model2.addMonom(new Monom(pow2,coef2));
            view.getShow2(model2);
        }
    }

    class ListenerAduna implements ActionListener{

        public void actionPerformed(ActionEvent e){
            Operatii op = new Operatii();
            model3 = op.adunare(model1, model2);
            view.getShow3(model3.toString());
        }
    }

    class ListenerScade implements ActionListener{

        public void actionPerformed(ActionEvent e){
            Operatii op = new Operatii();
            model3 = op.scadere(model1, model2);
            view.getShow3(model3.toString());
        }
    }
    class ListenerInmulteste implements ActionListener{

        public void actionPerformed(ActionEvent e){
            Operatii op = new Operatii();
            model3 = op.inmultire(model1, model2);
            view.getShow3(model3.toString());
        }
    }

    class ListenerImparte implements ActionListener{

        public void actionPerformed(ActionEvent e){
            Operatii op = new Operatii();
            String s = op.impartire(model1, model2);
            view.getShow3(s);
        }
    }

    class ListenerDeriveaza implements ActionListener{

        public void actionPerformed(ActionEvent e){
            Operatii op = new Operatii();
            model3 = op.derivare(model1);
            view.getShow3(model3.toString());
        }
    }

    class ListenerIntegreaza implements ActionListener{

        public void actionPerformed(ActionEvent e){
            Operatii op = new Operatii();
            model3 = op.integrare(model1);
            view.getShow3(model3.toString());
        }
    }

    class ListenerCurata implements ActionListener{

        public void actionPerformed(ActionEvent e){
            model1 = new Polinom();
            model2 = new Polinom();
            model3 = new Polinom();
            view.getShow1(model1);
            view.getShow2(model2);
            view.getShow3(model3.toString());
        }
    }


}
