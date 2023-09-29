package users.candidate;

import users.Person;

import java.io.Serializable;
import java.util.Objects;

public class Candidate extends Person {

    /**
     * Number of time people voted for this candidate in the current vote
     */
    private int numberOfVotes;

    /**
     * Pitch of this candidate in the current vote
     */
    private final String pitch; // TODO : pitch can be a text or a video

    public Candidate(String firstName, String lastName, String pitch) {
        super(firstName, lastName);
        this.numberOfVotes = 0;
        this.pitch = pitch;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void incrementNumberOfVote() {
        this.numberOfVotes++;
    }

    public String getPitch() {
        return pitch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Candidate candidate = (Candidate) o;

        if (numberOfVotes != candidate.numberOfVotes) return false;
        return Objects.equals(pitch, candidate.pitch);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + numberOfVotes;
        result = 31 * result + (pitch != null ? pitch.hashCode() : 0);
        return result;
    }
}
