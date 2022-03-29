package model;

import java.io.Serializable;

public abstract class MenuItem extends java.awt.MenuItem implements Serializable {

    public String name;
    public int situation = 0;

    public String getName() {
        return name;
    }

    public int getSituation() {
        return situation;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }

    public void setName(String name) {
        this.name = name;
    }
}
