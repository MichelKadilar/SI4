package users.candidate;

import users.Person;
import users.exceptions.InvalidRankValueException;

import java.io.Serializable;
import java.util.Objects;

public class Candidate implements Serializable, Person {

    /**
     * Rank of this candidate in the current vote
     */
    private final int rank;

    /**
     * Number of time people voted for this candidate in the current vote
     */
    private int numberOfVotes;

    /**
     * first name of the candidate
     */
    private final String firstName; //TODO : name not separated in two fields

    /**
     * last name of the candidate
     */
    private final String lastName;

    /**
     * Pitch of this candidate in the current vote
     */
    private final String pitch; // TODO : pitch can be a text or a video

    public Candidate(String firstName, String lastName, String pitch, int rank) throws InvalidRankValueException {

        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfVotes = 0;
        this.pitch = pitch;
        if (rank < 1) { // rank given by the system incrementing automatically
            throw new InvalidRankValueException();
        }
        this.rank = rank;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void incrementNumberOfVote() {
        this.numberOfVotes++;
    }

    public int getRank() {
        return rank;
    }

    public String getPitch() {
        return pitch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        if (rank != candidate.rank) return false;
        if (numberOfVotes != candidate.numberOfVotes) return false;
        if (!Objects.equals(firstName, candidate.firstName)) return false;
        if (!Objects.equals(lastName, candidate.lastName)) return false;
        return Objects.equals(pitch, candidate.pitch);
    }

    @Override
    public int hashCode() {
        int result = rank;
        result = 31 * result + numberOfVotes;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (pitch != null ? pitch.hashCode() : 0);
        return result;
    }
}
