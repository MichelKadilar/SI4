package users.exceptions;

public class InvalidRankValueException extends Exception {
    public InvalidRankValueException(){
        super("Rank can't be inferior to 1");
    }
}
