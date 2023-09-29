package connect;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class ConnectionManagerRMI {

    public static void rebindConnectImpl(Registry registry, String nameOnRegister) throws RemoteException {
        IConnect objetDistant = new ConnectImpl(0);
        registry.rebind(nameOnRegister, objetDistant);
    }
}
