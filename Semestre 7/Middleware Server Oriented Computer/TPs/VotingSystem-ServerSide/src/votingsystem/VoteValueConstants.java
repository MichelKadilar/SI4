package votingsystem;

public enum VoteValueConstants {

    MINIMUM_VOTE_VALUE(0), MAXIMUM_VOTE_VALUE(3);

   private final int voteValue;

    VoteValueConstants(int voteValue){
        this.voteValue = voteValue;
    }

    public int getVoteValue() {
        return voteValue;
    }
}
