package connect;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface IConnect extends Remote {
    public UUID sendCredentials(String username) throws RemoteException;
}
