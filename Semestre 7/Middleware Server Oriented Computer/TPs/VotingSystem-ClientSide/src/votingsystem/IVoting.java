package votingsystem;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVoting extends Remote {
    public boolean sendVote(int rank, int voteValue) throws RemoteException; // TODO : add two parameters : users OTP and id
}
