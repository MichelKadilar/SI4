package votingsystem;

import io.Input;
import io.Output;
import main.MainClient;
import users.candidate.Candidate;
import users.candidate.CandidateManager;
import votingsystem.exceptions.InvalidUserIdException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ClientVotingSystem {

    private CandidateManager candidateManager;

    public ClientVotingSystem(List<Candidate> candidateList) {
        this.candidateManager = new CandidateManager(candidateList);
    }

    public void voting() throws MalformedURLException, NotBoundException, RemoteException, InvalidUserIdException {
        IVoting votingRMI = (IVoting) Naming.lookup("rmi://localhost:2001/V");

        int index = 1;
        List<Candidate> candidates = candidateManager.getCandidates();
        while (index < candidates.size()) {
            Output.displayCandidate(index, candidates);
            Output.displayPleaseVote();
            String voteValue = Input.getNextInputLine();
            int intVoteValue = Integer.parseInt(voteValue);
            if (intVoteValue < VotingConstants.MINIMUM_VOTE_VALUE.getVoteValue() ||
                    intVoteValue > VotingConstants.MAXIMUM_VOTE_VALUE.getVoteValue()) {
                throw new InvalidUserIdException(); // TODO : maybe just ask to user to retype a vote value ?
            }
            votingRMI.sendVote(MainClient.user.getUserId(), "", index, intVoteValue); // TODO : take OTP in account
            Output.displayUserVoteJustInputed(intVoteValue, index, candidates);
            index++;
        }
        System.out.println(votingRMI.getUserVote(MainClient.user.getUserId(), "").toString()); // TODO : take OTP in account
    }

    public CandidateManager getCandidateManager() {
        return candidateManager;
    }
}
