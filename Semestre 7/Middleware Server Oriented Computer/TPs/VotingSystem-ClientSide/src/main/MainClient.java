package main;

import connect.ConnectionManager;
import connect.exceptions.BadCredentialsException;
import users.user.User;

public class MainClient {

    public static User user;

    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.connect();

    }
}
