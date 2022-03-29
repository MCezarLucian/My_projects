import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testProject {

    Polinom p1 = new Polinom();
    Polinom p2 = new Polinom();

    Polinom myRez = new Polinom();
    Polinom crRez = new Polinom();

    @Test
    public void testSuma()
    {
        Operatii op = new Operatii();

        //4x^3 + x^2 + 3
        p1.addMonom(new Monom(3,4));
        p1.addMonom(new Monom(2,1));
        p1.addMonom(new Monom(0,3));

        //2x^3 + x + 2
        p2.addMonom(new Monom(3,2));
        p2.addMonom(new Monom(1,1));
        p2.addMonom(new Monom(0,2));

        myRez = op.adunare(p1, p2);

        //6x^3 + x^2 + x + 5
        crRez.addMonom(new Monom(3, 6));
        crRez.addMonom(new Monom(2, 1));
        crRez.addMonom(new Monom(1, 1));
        crRez.addMonom(new Monom(0, 5));

        Assertions.assertEquals(myRez.toString(), crRez.toString());

    }

    @Test
    public void testDiferenta()
    {

        Operatii op = new Operatii();

        //4x^3 + x^2 + 3
        p1.addMonom(new Monom(3,4));
        p1.addMonom(new Monom(2,1));
        p1.addMonom(new Monom(0,3));

        //2x^3 + x + 2
        p2.addMonom(new Monom(3,2));
        p2.addMonom(new Monom(1,1));
        p2.addMonom(new Monom(0,2));

        myRez = op.scadere(p1, p2);

        //2x^3 + x^2 - x + 1
        crRez.addMonom(new Monom(3, 2));
        crRez.addMonom(new Monom(2, 1));
        crRez.addMonom(new Monom(1, -1));
        crRez.addMonom(new Monom(0, 1));

        Assertions.assertEquals(myRez.toString(), crRez.toString());

    }

    @Test
    public void testProdus()
    {
        Operatii op = new Operatii();

        //4x^3 + x^2 + 3
        p1.addMonom(new Monom(3,4));
        p1.addMonom(new Monom(2,1));
        p1.addMonom(new Monom(0,3));

        //2x^3 + x + 2
        p2.addMonom(new Monom(3,2));
        p2.addMonom(new Monom(1,1));
        p2.addMonom(new Monom(0,2));

        myRez = op.inmultire(p1, p2);

        //8x^6 + 2x^5 + 4x^4 + 15x^3 + 2x^2 + 3x + 6
        crRez.addMonom(new Monom(6, 8));
        crRez.addMonom(new Monom(5, 2));
        crRez.addMonom(new Monom(4, 4));
        crRez.addMonom(new Monom(3, 15));
        crRez.addMonom(new Monom(2, 2));
        crRez.addMonom(new Monom(1, 3));
        crRez.addMonom(new Monom(0, 6));

        Assertions.assertEquals(myRez.toString(), crRez.toString());

    }

    @Test
    public void testDivide()
    {
        Operatii op = new Operatii();
        String myStringRez;
        String crStringRez;

        //4x^3 + x^2 + 3
        p1.addMonom(new Monom(3,4));
        p1.addMonom(new Monom(2,1));
        p1.addMonom(new Monom(0,3));

        //2x^3 + x + 2
        p2.addMonom(new Monom(3,2));
        p2.addMonom(new Monom(1,1));
        p2.addMonom(new Monom(0,2));

        myStringRez = op.impartire(p1, p2);

        crStringRez = "2 si rest = x^2-2x-1";

        Assertions.assertEquals(myStringRez, crStringRez);

    }

    @Test
    public void testDerivare()
    {
        Operatii op = new Operatii();

        //4x^3 + x^2 + 3
        p1.addMonom(new Monom(3,4));
        p1.addMonom(new Monom(2,1));
        p1.addMonom(new Monom(0,3));

        String crStringRez = "12x^2+2x";
        myRez = op.derivare(p1);

        Assertions.assertEquals(myRez.toString(),crStringRez);

    }

    @Test
    public void testIntegrare()
    {
        Operatii op = new Operatii();

        //4x^3 + 3x^2 + 3
        p1.addMonom(new Monom(3,4));
        p1.addMonom(new Monom(2,3));
        p1.addMonom(new Monom(0,3));

        String crStringRez = "x^4+x^3+3x";
        myRez = op.integrare(p1);

        Assertions.assertEquals(myRez.toString(),crStringRez);

    }
}
