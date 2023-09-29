package votingsystem;

import io.Input;
import io.Output;

import java.util.UUID;

public class VotingSystemManager {

    private final VoteManager voteManager;

    public VotingSystemManager() {
        this.voteManager = new VoteManager();
    }

    public void manageVote() {
        boolean isKeyRight = false;
        do {
            Output.displayAdminChoice();
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
        Output.displayTheVoteHasBeenStarted();
        while (!this.voteManager.haveEveryVoterVoted()) {
        }
    }

    public boolean addVoter(UUID userId) {
        return voteManager.addVoterToMap(userId);
    }

    public VoteManager getVoteManager() {
        return voteManager;
    }
}
