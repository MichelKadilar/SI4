package votingsystem.exceptions;

import votingsystem.VotingConstants;

public class InvalidVoteValueException extends Exception {

    public InvalidVoteValueException(int voteValue){
        super("The vote value : " + voteValue + "is invalid. Vote value can only be between " +
                VotingConstants.MINIMUM_VOTE_VALUE.getVoteValue() + " and " +
                VotingConstants.MAXIMUM_VOTE_VALUE.getVoteValue());
    }
}
