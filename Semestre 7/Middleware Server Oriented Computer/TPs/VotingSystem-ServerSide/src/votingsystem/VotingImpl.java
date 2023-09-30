package votingsystem;

import votingsystem.exceptions.InvalidUserIdException;
import votingsystem.exceptions.TwoTimeVoteException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.Map;
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
            boolean voteSuccess = votingSystemManager.getVoteManager().addVoteForCandidate(userId, rank, voteValue);
            try {
                if (votingSystemManager.getVoteManager().hasVoterVotedForEveryCandidate(userId)) {
                    votingSystemManager.getVoteManager().updateVoterHasVoted(userId);
                }
                return voteSuccess;
            } catch (TwoTimeVoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean registerToVote(UUID userId, String otp) throws RemoteException { //TODO : take OTP in account
        return votingSystemManager.addVoter(userId);
    }

    @Override
    public boolean hasVoteStarted() throws RemoteException {
        return this.votingSystemManager.hasVotedBegan();
    }

    @Override
    public boolean hasVoteEnded() throws RemoteException {
        return this.votingSystemManager.hasVoteEnded();
    }

    @Override
    public Vote getUserVote(UUID userId, String otp) throws RemoteException { // TODO : take OTP in account
        return this.votingSystemManager.getVoteManager().getUserVote(userId);
    }

    @Override
    public Map<Integer, Integer> getFinalCandidatesRanking(UUID userId, String otp) throws RemoteException { // TODO : take OTP in account
        if (this.votingSystemManager.hasVoteEnded()) {
            return this.votingSystemManager.getFinalRankingSystem().getFinalRanking();
        } else return Collections.emptyMap();
    }
}
