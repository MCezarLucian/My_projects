package Model;


public class Client implements Comparable {

    private int ID;
    private int arrTime;
    private int servTime;

    Client(int ID, int arr, int serv){
        this.ID = ID;
        this.arrTime = arr;
        this.servTime = serv;
    }
    public void setAll(int ID, int arr, int serv){
        this.ID = ID;
        this.arrTime = arr;
        this.servTime = serv;
    }
    public int getID(){
        return ID;
    }
    public int getArrTime(){
        return arrTime;
    }
    public int getServTime(){
        return servTime;
    }
    public void minServTime(){this.servTime--;}
    public void setID(int ID){
        this.ID = ID;
    }
    public void setArrTime(int arr){
        this.arrTime = arr;
    }
    public void setServTime(int serv){
        this.servTime = serv;
    }
    @Override
    public int compareTo(Object o) {
        Client aux = (Client) o;
        if(this.getArrTime() > aux.getArrTime())
        {
            return 1;
        }
        else if(this.getArrTime() == aux.getArrTime())
            return 0;
        else
            return -1;
    }
    public String printClient(){

        return "(" + this.ID + ", " + this.arrTime + ", " + this.servTime + ");";
    }
}
