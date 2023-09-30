package votingsystem;

import io.Input;
import io.Output;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class VotingSystemManager {

    private final VoteManager voteManager;

    private final FinalRankingSystem finalRankingSystem;

    private boolean hasVoteEnded;

    private boolean hasVotedBegan;

    public VotingSystemManager() {
        this.voteManager = new VoteManager();
        Set<Integer> candidatesRank = this.voteManager.getCandidates().keySet();
        finalRankingSystem = new FinalRankingSystem(new ArrayList<>(candidatesRank)); // because ranks are unique, this cast should work
        hasVotedBegan = false;
        this.hasVoteEnded = false;

        new Thread(this::manageVote).start();

    }


    public void manageVote() {
        boolean isKeyRight = false;
        Output.displayAdminChoice();
        do {
            String startVoteStringKey = Input.getNextInputLine();
            for (StartingEndingVotingKeys key : StartingEndingVotingKeys.values()) {
                char startKey = startVoteStringKey.charAt(0);
                if (key.getKey() == startKey) {
                    isKeyRight = true;
                    if (startKey == 'S') {
                        this.startVote();
                    }
                }
            }
        } while (!isKeyRight);
    }

    public void startVote() {
        this.hasVotedBegan = true;
        this.hasVoteEnded = false;
        Output.displayTheVoteHasBeenStarted();
        while (!this.voteManager.haveEveryVoterVoted()) {
        }
        this.finalRankingSystem.fillFinalRankingMap(this.voteManager.getVoterVotes());
        this.hasVoteEnded = true;
        this.hasVotedBegan = false;
    }

    public boolean addVoter(UUID userId) {
        return voteManager.addVoterToMap(userId);
    }

    public VoteManager getVoteManager() {
        return voteManager;
    }

    public FinalRankingSystem getFinalRankingSystem() {
        return finalRankingSystem;
    }

    public boolean hasVoteEnded() {
        return hasVoteEnded;
    }

    public boolean hasVotedBegan() {
        return hasVotedBegan;
    }
}
