package users.candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateManager {

    private List<Candidate> candidates;

    public CandidateManager(List<Candidate> candidates) {
        this.candidates = new ArrayList<>(candidates);
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void clearCandidates() {
        this.candidates.clear();
    }
}
