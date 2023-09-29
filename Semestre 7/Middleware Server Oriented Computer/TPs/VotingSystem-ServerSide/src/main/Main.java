package main;

import users.PersonManager;
import users.user.User;

import java.rmi.RemoteException;

public class Main {

    public static PersonManager<User> userConnectionManager;

    public static void main(String[] args) throws RemoteException {
        userConnectionManager = new PersonManager<>("Users.txt");
        SetupRMI.setupRMI();

    }
}
