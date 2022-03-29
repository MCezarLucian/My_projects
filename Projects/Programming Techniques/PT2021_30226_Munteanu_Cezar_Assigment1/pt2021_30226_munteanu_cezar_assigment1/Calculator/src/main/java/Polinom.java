
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polinom {

    private List<Monom> elemente = new ArrayList<Monom>();//lista de monoame
    private int nrElemente = 0;//nr de monoame


    //metoda de adaugare monom la polinom
    public void addMonom(Monom element){
        elemente.add(element);
        nrElemente++;
    }

    public void switchMonom(int i, int j){
        Collections.swap(elemente, i, j);
    }

    public int nrMonoame(){
        return nrElemente;
    }

    //metoda de stergere monom din polinom
    public void deleteMonom(int element){
        elemente.remove(element);
        nrElemente--;
    }

    //metoda de stergere polinom(curatare)
    public void reset(){
        elemente.clear();
        nrElemente = 0;
    }

    //metoda de verificare daca polinomul are sau nu elemente
    public boolean eGoala(){
        if (elemente.isEmpty())
            return true;
        else
            return false;
    }

    //metoda de obtine un monom de pe pozitia i din polinom
    public Monom getMonom(int i){
        return elemente.get(i);
    }

    //metoda prin care returnam polinomul ca string
    public String toString(){

        String afis = "";
        int cnt = 0;

        if (elemente.isEmpty())
            afis = "0";
        else{
            for( Monom x : elemente){
                double coef = x.getCoef();
                if(x.getCoef() == 0)
                    continue;
                if(coef != 1 && coef != -1) {
                    if(cnt != 0)
                    {
                        if(coef > 0)
                        {
                            if(coef > (int)coef)
                                afis = afis + "+" + String.format("%.2f", coef);
                            else
                                afis = afis + "+" + (int)coef;
                        }
                        else{
                            if(coef < (int)coef)
                                afis = afis + String.format("%.2f", coef);
                            else
                                afis = afis + (int)coef;
                        }
                    }
                    else {
                        if(coef > 0)
                        {
                            if(coef > (int)coef)
                                afis = afis + String.format("%.2f", coef);
                            else
                                afis = afis + (int)coef;
                        }
                        else{
                            if(coef < (int)coef)
                                afis = afis + String.format("%.2f", coef);
                            else
                                afis = afis + (int)coef;
                        }
                        cnt++;
                    }
                }
                else
                {
                    if(coef == -1){if(x.getPow() == 0)
                        afis = afis + "-1";else afis = afis + "-";}
                    else
                        if(cnt != 0 && x.getPow() == 0){
                        afis = afis + "+1";
                        }
                        else   if(cnt!=0) afis = afis + "+";
                        //else{if(coef == 1)afis = afis + "1";}

                }
                if(x.getPow()  == 1)
                    afis = afis + "x";
                if(x.getPow() >= 2)
                    afis = afis + "x^" + (int)x.getPow();

                cnt++;
            }
        }
        return afis;
    }


}
