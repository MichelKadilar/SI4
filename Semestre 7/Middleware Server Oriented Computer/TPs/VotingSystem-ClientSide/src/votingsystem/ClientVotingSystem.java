package votingsystem;

import io.Input;
import io.Output;
import main.MainClient;
import users.candidate.Candidate;
import users.candidate.CandidateManager;
import votingsystem.exceptions.InvalidUserIdException;
import votingsystem.exceptions.InvalidVoteValueException;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientVotingSystem {

    private CandidateManager candidateManager;

    private Map<Candidate, Integer> winners;

    public ClientVotingSystem(List<Candidate> candidateList) {
        this.candidateManager = new CandidateManager(candidateList);
        this.winners = new HashMap<>();
    }

    public void voting(IVoting votingRMI) throws RemoteException, InvalidUserIdException, InvalidVoteValueException {
        int index = 0;
        List<Candidate> candidates = candidateManager.getCandidates();
        while (index < candidates.size()) {
            Output.displayLine();
            Output.displayCandidate(index, candidates);
            Output.displayPleaseVote();
            String voteValue = Input.getNextInputLine();
            int intVoteValue = Integer.parseInt(voteValue);
            if (intVoteValue < VotingConstants.MINIMUM_VOTE_VALUE.getVoteValue() ||
                    intVoteValue > VotingConstants.MAXIMUM_VOTE_VALUE.getVoteValue()) {
                throw new InvalidVoteValueException(intVoteValue); // TODO : maybe just ask to user to retype a vote value ?
            }

            if (votingRMI.sendVote(MainClient.user.getUserId(), "", index, intVoteValue)) { // TODO : take OTP in account
                Output.displayUserVoteJustInputed(intVoteValue, index, candidates);
                index++;
            } else {
                throw new InvalidUserIdException();
            }
        }
        System.out.println(votingRMI.getUserVote(MainClient.user.getUserId(), "").toString()); // TODO : take OTP in account

        if (votingRMI.hasVoteEnded()) {
            Map<Integer, Integer> candidatesFinalResults = votingRMI.getFinalCandidatesRanking(MainClient.user.getUserId(), "");
            if (!candidatesFinalResults.isEmpty()) { // TODO : take OTP in account
                Output.displayFinalRankingResults(candidates, candidatesFinalResults);
                this.addWinnersToMap(candidates, candidatesFinalResults);
                Output.displayWinners(this.winners);
                this.clearWinners();
                this.candidateManager.clearCandidates();
            }
        } else {
            Output.displayElectionHasNotFinished();
        }
    }

    private void addWinnersToMap(List<Candidate> candidates, Map<Integer, Integer> candidatesFinalResults) {
        int maxNbOfVotes = 0;
        for (Integer candidatesRank : candidatesFinalResults.keySet()) {
            int nbOfVotes = candidatesFinalResults.get(candidatesRank);
            if (nbOfVotes > maxNbOfVotes) {
                maxNbOfVotes = nbOfVotes;
            }
            if (nbOfVotes == maxNbOfVotes) {
                this.addWinner(candidates.get(candidatesRank), nbOfVotes);
            }
        }
    }

    private void addWinner(Candidate candidate, Integer nbOfVotes) {
        this.winners.put(candidate, nbOfVotes);
    }

    private void clearWinners() {
        this.winners.clear();
    }
}
