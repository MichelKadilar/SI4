package votingsystem.exceptions;

public class InvalidUserIdException extends Exception {
    public InvalidUserIdException() {
        super("User id is invalid");
    }
}
