package votingsystem.exceptions;

import votingsystem.VoteValueConstants;

public class InvalidVoteValueException extends Exception {

    public InvalidVoteValueException(int voteValue){
        super("The vote value : " + voteValue + "is invalid. Vote value can only be between " +
                VoteValueConstants.MINIMUM_VOTE_VALUE.getVoteValue() + " and " +
                VoteValueConstants.MAXIMUM_VOTE_VALUE.getVoteValue());
    }
}
