import java.awt.event.MouseMotionAdapter;

public class Operatii {

    public Polinom adunare(Polinom A, Polinom B){
        Monom x;
        Polinom rezultat = new Polinom();
        int cntA = A.nrMonoame();//nr de monoame din primul polinom
        int cntB = B.nrMonoame();//nr de monoame din al doilea polinom

        int i = 0 ;
        int j = 0;

        while(i < cntA && j < cntB)
        {
            double coefA = A.getMonom(i).getCoef();
            double coefB = B.getMonom(j).getCoef();

            double powA = A.getMonom(i).getPow();
            double powB = B.getMonom(j).getPow();

            //Daca monoamele au aceeasi putere atunci le adunam coeficientii si adaugam la noul polinom
            if(powA == powB){
                x = new Monom(powA,coefA + coefB);
                i++;
                j++;
            }
            else{
                //Daca puterea primului polinom e mai mare decat cea din al doilea o adaugam direct in polinomul rezultat
                if(powA > powB)
                {
                    x = new Monom(powA, coefA);
                    i++;
                }
                // la fel si pentru al doilea
                else{
                    x = new Monom(powB, coefB);
                    j++;
                }
            }
            rezultat.addMonom(x);
        }
        //daca au mai ramas monoame din primul polinom le adaugam la polinomul final
        while(i < cntA)
        {
            double coefA = A.getMonom(i).getCoef();
            double powA = A.getMonom(i).getPow();
            x = new Monom(powA,coefA);
            i++;
            rezultat.addMonom(x);
        }

        // la fel si pentru al doilea
        while(j < cntB)
        {
            double coefB = B.getMonom(j).getCoef();
            double powB = B.getMonom(j).getPow();
            x = new Monom(powB,coefB);
            j++;
            rezultat.addMonom(x);
        }

        return rezultat;
    }

    public Polinom scadere(Polinom A, Polinom B)
    {

        Polinom C = new Polinom();
        int i =0;
        while (i < B.nrMonoame()){

            C.addMonom(new Monom(B.getMonom(i).getPow(), -B.getMonom(i).getCoef()));
            i++;

        }
        Operatii op = new Operatii();

        return op.adunare(A,C);
    }

    public Polinom inmultire(Polinom A, Polinom B){

        Monom x;
        Polinom C = new Polinom();
        Polinom rezultat = new Polinom() ;
        int cntA = A.nrMonoame();
        int cntB = B.nrMonoame();
        int i = 0;
        int j ;
        while(i < cntA){
            double coefA = A.getMonom(i).getCoef();
            double powA = A.getMonom(i).getPow();
            i++;
            j = 0;
            while(j < cntB){
                double coefB = B.getMonom(j).getCoef();
                double powB = B.getMonom(j).getPow();
                x = new Monom(powA + powB, coefA * coefB);
                C.addMonom(x);
                j++;
            }
        }
        for(i = 0; i < C.nrMonoame(); i++) {
            double s = C.getMonom(i).getCoef();
            for (j = i + 1; j < C.nrMonoame(); j++) {
                if (C.getMonom(i).getPow() == C.getMonom(j).getPow()) {
                    s = s + C.getMonom(j).getCoef();
                    C.deleteMonom(j);
                }
            }
            Monom aux = new Monom(C.getMonom(i).getPow(), s);
            rezultat.addMonom(aux);
        }
        for(i = 0; i < rezultat.nrMonoame() - 1; i++) {
            for (j = i + 1; j < rezultat.nrMonoame(); j++) {
                if (rezultat.getMonom(i).getPow() < rezultat.getMonom(j).getPow()) {
                    rezultat.switchMonom(i, j);
                }
            }
        }
        return rezultat;
    }

    public String impartire(Polinom A, Polinom B){
        Operatii op = new Operatii();//cu ajutorul ei vom apelatii operatiile deja scrise
        String cat = "";//aici vom salva catul impartirii
        String rest = "";//aici vom salva restul impartirii
        Polinom rezultat = new Polinom(); // aici va fi salvat cat-ul sub format Polinom

        while(A.getMonom(0).getPow() >= B.getMonom(0).getPow()){
            Monom imp1 = B.getMonom(0);//Aici salvam primul monom din B
            Monom imp2 = A.getMonom(0);//Aici salvam priul monom din A, aici fiind mai important deoarece acesta se modifica la fieca loop
            Monom c = new Monom(imp2.getPow() - imp1.getPow(), imp2.getCoef() / imp1.getCoef());// c = primul monom din A / primul monom din B

            Polinom aux = new Polinom();//ne luam un polinom auxiliar
            aux.addMonom(c);//adaugam monomul in C
            aux = op.inmultire(B, aux); // aux = c * (Polinom)B;
            rezultat.addMonom(c);//salvam monomul rezultat din impartire si in rezultat la fiecare loop
            A = op.scadere(A, aux) ;//scadem din polinomul A auxiliarul creat
            A.deleteMonom(0);//stergem de fiecare data monomul 0 deoarece acesta prin scadere va fi 0 in fiecare loop
            if(A.nrMonoame() == 0)
                break;
        }

        if(A.eGoala())
            rest = "0";
        else
            rest = A.toString();

        if(rest == "")
            rest = "0";
        cat = rezultat.toString() + " si " + "rest = " + rest;
        return cat;
    }

    public Polinom derivare(Polinom A){
        Polinom rezultat = new Polinom();
        int cntA = A.nrMonoame();
        int i =0;

        while (i < cntA){
            double coefA = A.getMonom(i).getCoef();
            double powA = A.getMonom(i).getPow();
            if(powA != 0){
                Monom aux = new Monom(powA - 1,coefA * powA);
                rezultat.addMonom(aux);
            }
            i++;
        }
        return rezultat;
    }

    public Polinom integrare(Polinom A){
        Polinom rezultat = new Polinom();
        int cntA = A.nrMonoame();
        int i=0;

        while(i < cntA){
            double coefA = A.getMonom(i).getCoef();
            double powA = A.getMonom(i).getPow();
            Monom aux = new Monom(powA + 1, coefA / (powA + 1));
            rezultat.addMonom(aux);
            i++;
        }
        return rezultat;
    }
}
