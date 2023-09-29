package users.candidate;

import io.FileManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CandidateImpl extends UnicastRemoteObject implements ICandidate {
    public CandidateImpl(int portNumber) throws RemoteException {
        super(portNumber);
    }

    @Override
    public List<Candidate> getCandidateList() throws RemoteException {
        return FileManager.createCandidateListFromFile();
    }
}
