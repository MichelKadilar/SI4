package votingsystem;

import votingsystem.exceptions.InvalidUserIdException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

public interface IVoting extends Remote {
    public boolean sendVote(UUID userId, String otp, int rank, int voteValue) throws RemoteException, InvalidUserIdException;

    public boolean registerToVote(UUID userId, String otp) throws RemoteException;

    public boolean hasVoteStarted() throws RemoteException;

    public boolean hasVoteEnded() throws RemoteException;

    public Vote getUserVote(UUID userId, String otp) throws RemoteException;

    public Map<Integer, Integer> getFinalCandidatesRanking(UUID userId, String otp) throws RemoteException;
}
