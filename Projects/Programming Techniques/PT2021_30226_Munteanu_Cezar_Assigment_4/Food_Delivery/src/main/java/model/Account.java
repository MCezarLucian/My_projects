package model;

import java.io.Serializable;

public class Account implements Serializable {

    int id;
    String username;
    String password;
    int role; // 0 - Admin // 1 - Client // 2 - Employee
    int aparitii = 0;


    public Account(int id, String username, String password, int role)  {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getAparitii() {
        return aparitii;
    }

    public void setAparitii(int aparitii) {
        this.aparitii = aparitii;
    }
}
