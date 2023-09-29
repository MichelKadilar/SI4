package users.candidate;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICandidate extends Remote {
    public List<Candidate> getCandidateList() throws RemoteException;
}
