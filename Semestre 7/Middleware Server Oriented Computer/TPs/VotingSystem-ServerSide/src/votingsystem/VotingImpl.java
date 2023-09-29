package votingsystem;

import main.Main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class VotingImpl extends UnicastRemoteObject implements IVoting {
    protected VotingImpl(int portNumber) throws RemoteException {
        super(portNumber);
    }

    @Override
    public boolean sendVote(UUID userId, String otp, int rank, int voteValue) throws RemoteException {

        return ;
    }
}
