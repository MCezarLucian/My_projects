package controller;

import model.*;
import connection.*;
import bll.*;
import connection.ConnectionDB;
import controller.Controller;
import dao.*;
import view.*;

public class MainClass {
    public static void main(String [] args){

        View view = new View();
        Controller controller = new Controller(view);

    }
}
