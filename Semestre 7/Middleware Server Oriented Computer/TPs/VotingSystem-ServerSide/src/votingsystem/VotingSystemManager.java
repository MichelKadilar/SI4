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

    private boolean hasVoteBegan;

    public VotingSystemManager() {
        this.voteManager = new VoteManager();
        Set<Integer> candidatesRank = this.voteManager.getCandidates().keySet();
        this.finalRankingSystem = new FinalRankingSystem(new ArrayList<>(candidatesRank)); // because ranks are unique, this cast should work
        this.hasVoteBegan = false;
        this.hasVoteEnded = false;

        new Thread(this::manageVote).start();
    }


    public void manageVote() {
        boolean isKeyRight = false;
        Output.displayAdminChoice();
        do {
            String startVoteStringKey = Input.getNextInputLine();
            for (StartingEndingVotingKeys key : StartingEndingVotingKeys.values()) {
                char charKey = startVoteStringKey.charAt(0);
                if (key.getKey() == charKey) {
                    isKeyRight = true;
                    if (charKey == 'S') {
                        if (this.voteManager.isThereVoters()) {
                            this.hasVoteEnded = false;
                            this.hasVoteBegan = true;
                            this.startVote();
                        } else {
                            Output.displayNoVoters();
                            isKeyRight = false;
                        }
                    } /*else if (charKey == 'E') {
                        this.hasVoteEnded = true;
                        this.hasVotedBegan = false;
                        this.finalRankingSystem.fillFinalRankingMap(this.voteManager.getVoterVotes());
                    }*/
                }
            }
        } while (!isKeyRight);
    }

    public void startVote() {
        Output.displayTheVoteHasBeenStarted();
        while (!this.voteManager.haveEveryVoterVoted()) { // && !hasVoteEnded
        }
        this.hasVoteEnded = true;
        this.hasVoteBegan = false;
        this.finalRankingSystem.fillFinalRankingMap(this.voteManager.getVoterVotes());
    }

    public boolean addVoter(UUID userId) {
        if (!this.hasVoteBegan) {
            return voteManager.addVoterToMap(userId);
        } else return false;
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
        return hasVoteBegan;
    }
}
