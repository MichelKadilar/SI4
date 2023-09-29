package connect;

import main.Main;
import users.user.User;
import votingsystem.VotingSystemManager;

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
        VotingSystemManager votingSystemManager = new VotingSystemManager();
        System.out.println(username);
        String[] info = username.split(" ");


        List<User> userList = Main.userConnectionManager.getPersonList();
        int userIndexInList = userList.indexOf(new User(info[0], info[1]));
        if (userIndexInList != -1) {
            // connection OK
            UUID userid = UUID.randomUUID();
            userList.get(userIndexInList).setUserId(userid);
            votingSystemManager.addVoter(userid);
            return userid;
        }
        return null;
    }


}
