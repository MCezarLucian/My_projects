public class Monom {

    private double pow;//puterea monomului
    private double coef;//coeficientul monomului

    Monom(double pow, double coef) {
        this.pow = pow;
        this.coef = coef;
    }

    //metoda ce returneaza puterea
    public double getPow(){
        return this.pow;
    }

    //metoda ce returneaza coeficientul
    public double getCoef(){
        return this.coef;
    }
}
