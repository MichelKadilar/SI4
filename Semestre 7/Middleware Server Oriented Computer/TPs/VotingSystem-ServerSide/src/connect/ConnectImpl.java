package connect;

import main.Main;
import users.user.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;

public class ConnectImpl extends UnicastRemoteObject implements IConnect {

    public ConnectImpl(int numberPort) throws RemoteException {
        super(numberPort);
    }

    /**
     * Server-side : Get the sign-in credentials sent by the client
     *
     * @param username first name and last name of the user
     * @throws RemoteException
     */
    @Override
    public UUID sendCredentials(String username) throws RemoteException {
        //System.out.println(username); // TODO : delete
        String[] info = username.split(" ");


        List<User> userList = Main.userConnectionManager.getPersonList();
        int userIndexInList = Main.userConnectionManager.findPersonInList(info[0], info[1]);
        if (userIndexInList != -1) {
            // connection OK
            return userList.get(userIndexInList).getUserId();
        }
        return null;
    }


}
