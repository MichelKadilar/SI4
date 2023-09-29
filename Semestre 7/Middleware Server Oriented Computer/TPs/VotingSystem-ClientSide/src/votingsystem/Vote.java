package votingsystem;

import java.util.HashMap;
import java.util.Map;

public class Vote {

    /**
     * First integer key is for candidate rank
     * Second integer value is for the vote value given by one user
     */
    private final Map<Integer, Integer> votes;

    public Vote() {
        this.votes = new HashMap<>();
    }

    public boolean addVote(int rank, int voteValue) {
        if (votes.containsKey(rank)) return false; // voted two times for the same candidate, so error
        else {
            votes.put(rank, voteValue);
            return true;
        }
    }

    public Map<Integer, Integer> getVotes() {
        return votes;
    }

    public void clearVotes() {
        this.votes.clear();
    }

    @Override
    public String toString() {
        String str = "";
        for (Integer candidateRank : votes.keySet()) {
            str += "Rank : " + candidateRank + " vote given : " + votes.get(candidateRank) + "\n";
        }
        return str;
    }
}
