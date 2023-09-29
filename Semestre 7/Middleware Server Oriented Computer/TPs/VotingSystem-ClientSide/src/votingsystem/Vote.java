package votingsystem;

import users.candidate.Candidate;
import votingsystem.exceptions.InvalidVoteValueException;
import votingsystem.exceptions.TwoTimeVoteException;

import java.util.HashMap;
import java.util.Map;

public class Vote {

    private final Map<Candidate, Integer> votes;

    public Vote() {
        this.votes = new HashMap<>();
    }

    private void addVote(Candidate candidate, int voteValue) throws InvalidVoteValueException, TwoTimeVoteException {
        if (voteValue >= VotingConstants.MINIMUM_VOTE_VALUE.getVoteValue() &&
                voteValue <= VotingConstants.MAXIMUM_VOTE_VALUE.getVoteValue()) {
            if (votes.containsKey(candidate)) throw new TwoTimeVoteException();
            else {
                votes.put(candidate, voteValue);
            }
        } else throw new InvalidVoteValueException(voteValue);
    }

    public Map<Candidate, Integer> getVotes() {
        return votes;
    }

    public void clearVotes(){
        this.votes.clear();
    }
}
