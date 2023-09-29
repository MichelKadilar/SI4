package users.candidate;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class CandidateRMI {

    public static void rebindUserManagerImpl(Registry registry, String nameOnRegister) throws RemoteException {
        ICandidate objetDistant = new CandidateImpl(0);
        registry.rebind(nameOnRegister, objetDistant);
    }
}
