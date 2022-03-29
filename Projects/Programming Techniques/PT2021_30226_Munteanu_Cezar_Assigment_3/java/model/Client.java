package model;

public class Client {
    private int id;
    private String FirstName;
    private String LastName;
    private String Adress;
    private String telNumber;

    public Client(int ID, String fName, String lName, String adress, String tNr){
        this.id = ID;
        this.FirstName = fName;
        this.LastName = lName;
        this.Adress = adress;
        this.telNumber = tNr;
    }
    public Client(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
