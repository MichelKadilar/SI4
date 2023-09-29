package votingsystem;

import votingsystem.exceptions.InvalidUserIdException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class VotingImpl extends UnicastRemoteObject implements IVoting {

    private VotingSystemManager votingSystemManager = new VotingSystemManager();

    protected VotingImpl(int portNumber) throws RemoteException {
        super(portNumber);
    }

    @Override
    public boolean sendVote(UUID userId, String otp, int rank, int voteValue) throws RemoteException, InvalidUserIdException {
        if (!votingSystemManager.getVoteManager().hasVoterAlreadyVoted(userId) &&
                (votingSystemManager.getVoteManager().findUserByUUID(userId) != null)) {

            return votingSystemManager.getVoteManager().addVoteForCandidate(userId, rank, voteValue);
        }
        return false;
    }

    @Override
    public boolean registerToVote(UUID userId, String otp) throws RemoteException { //TODO : take OTP in account
        return votingSystemManager.addVoter(userId);
    }

    @Override
    public Vote getUserVote(UUID userId, String otp) throws RemoteException { // TODO : take OTP in account
        return this.votingSystemManager.getVoteManager().getUserVote(userId);
    }
}
