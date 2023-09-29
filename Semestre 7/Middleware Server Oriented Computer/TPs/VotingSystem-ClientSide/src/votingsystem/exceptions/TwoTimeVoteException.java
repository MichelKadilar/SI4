package votingsystem.exceptions;

public class TwoTimeVoteException extends Exception {

    public TwoTimeVoteException() {
        super("User can't vote two times for the same candidate");
    }
}
