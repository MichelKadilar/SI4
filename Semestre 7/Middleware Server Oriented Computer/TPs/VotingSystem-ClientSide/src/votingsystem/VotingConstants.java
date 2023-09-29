package votingsystem;

public enum VotingConstants {

    MINIMUM_VOTE_VALUE(0), MAXIMUM_VOTE_VALUE(3);

   private final int voteValue;

    VotingConstants(int voteValue){
        this.voteValue = voteValue;
    }

    public int getVoteValue() {
        return voteValue;
    }
}
