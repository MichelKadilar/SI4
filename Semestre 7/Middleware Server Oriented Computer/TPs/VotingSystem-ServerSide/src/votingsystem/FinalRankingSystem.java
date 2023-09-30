package votingsystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FinalRankingSystem {

    public Map<Integer, Integer> finalRanking; // candidateRank, nb of votes


    public FinalRankingSystem(List<Integer> candidatesRank) {
        this.finalRanking = new HashMap<>();
        buildFinalRankingMap(candidatesRank);
    }

    private void buildFinalRankingMap(List<Integer> candidatesRank) {
        for (Integer candidateRank : candidatesRank) {
            this.finalRanking.put(candidateRank, 0);
        }
    }

    private void addOneVoterVotes(Map<Integer, Integer> votes) {
        for (Integer candidateRank : votes.keySet()) {
            this.finalRanking.put(candidateRank, this.finalRanking.get(candidateRank) +
                    votes.get(candidateRank));
        }
    }

    public void fillFinalRankingMap(Map<UUID, Vote> votersVotes) {
        for (UUID uuid : votersVotes.keySet()) {
            Vote vote = votersVotes.get(uuid);
            this.addOneVoterVotes(vote.getVotes());
        }
    }

    public Map<Integer, Integer> getFinalRanking() {
        return finalRanking;
    }
}
