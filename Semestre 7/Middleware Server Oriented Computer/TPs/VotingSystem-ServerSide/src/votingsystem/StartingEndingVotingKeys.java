package votingsystem;

public enum StartingEndingVotingKeys {
    STARTING_VOTE('S'), ENDING_VOTE('E');

    private char key;

    StartingEndingVotingKeys(char key) {
        this.key = key;
    }

    public char getKey() {
        return key;
    }
}
