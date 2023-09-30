package connect;

import connect.exceptions.BadCredentialsException;
import io.Input;
import io.Output;
import main.MainClient;
import menuchoice.MenuChoiceManager;
import users.user.User;
import votingsystem.exceptions.InvalidUserIdException;
import votingsystem.exceptions.InvalidVoteValueException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.UUID;

public class ConnectionManager {
    public void connect() {
        Output.displayConnectionMessage();
        boolean isUserConnected = false;
        while (!isUserConnected) {
            String clientCredentials = Input.getNextInputLine();

            try {
                // Here we specify the port number to use for the RMI registry
                IConnect connect = (IConnect) Naming.lookup("rmi://localhost:2001/C");
                UUID userId = connect.sendCredentials(clientCredentials);
                if (userId != null) {
                    isUserConnected = true;
                    String[] userInfo = clientCredentials.split(" ");
                    fillUserInfo(userInfo[0], userInfo[1], userId);
                    Output.displaySuccessfulConnection();
                    MenuChoiceManager.choiceAfterSuccessfulConnection();
                } else {
                    try {
                        throw new BadCredentialsException();
                    } catch (BadCredentialsException e) {
                        Output.displayFailureConnection();
                    }
                }
            } catch (MalformedURLException e) {
                System.err.println("Exception starting RMI registry:");
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (NotBoundException e) {
                System.err.println("Exception starting RMI registry:");
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            } catch (InvalidUserIdException e) {
                throw new RuntimeException(e);
            } catch (InvalidVoteValueException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void fillUserInfo(String firstName, String lastName, UUID userId) {
        MainClient.user = new User(firstName, lastName, userId);
    }
}
