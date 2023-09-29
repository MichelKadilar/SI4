package votingsystem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface IVoting extends Remote {
    public boolean sendVote(UUID userId, String otp, int rank, int voteValue) throws RemoteException;
}
