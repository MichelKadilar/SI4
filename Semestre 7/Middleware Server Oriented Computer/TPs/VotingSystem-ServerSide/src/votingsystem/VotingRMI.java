package votingsystem;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class VotingRMI {

    public static void rebindVotingImpl(Registry registry, String nameOnRegister) throws RemoteException {
        IVoting objetDistant = new VotingImpl(0);
        registry.rebind(nameOnRegister, objetDistant);
    }
}
