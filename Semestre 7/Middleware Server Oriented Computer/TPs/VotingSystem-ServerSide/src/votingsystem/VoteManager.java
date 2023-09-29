package votingsystem;

import io.FileManager;
import main.Main;
import users.candidate.Candidate;
import users.user.User;
import votingsystem.exceptions.InvalidUserIdException;
import votingsystem.exceptions.TwoTimeVoteException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class VoteManager {

    private List<Candidate> candidates;

    private Map<UUID, Boolean> haveVotersVotedMap;
    private List<User> voters;

    public VoteManager() {
        buildCandidatesList();
        buildHaveVotersVotedMap();
    }

    private void buildCandidatesList() {
        this.candidates = (List<Candidate>) FileManager.createList("Candidates.txt");
    }

    private void buildHaveVotersVotedMap() {
        this.haveVotersVotedMap = new HashMap<>();
        for (User user : Main.userConnectionManager.getPersonList()) {
            addVoterToMap(user.getUserId());
        }
    }

    public void clearCandidatesList() {
        this.candidates.clear();
    }

    public void clearHaveVotersVotedMap() {
        this.haveVotersVotedMap.clear();
    }

    public void addVoterToMap(UUID userId) {
        this.haveVotersVotedMap.put(userId, Boolean.FALSE);
    }

    public boolean haveEveryVoterVoted() {
        if (this.haveVotersVotedMap.size() > 1) {
            for (Boolean b : this.haveVotersVotedMap.values()) {
                if (b.equals(Boolean.FALSE)) return false;
            }
        }
        return true;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public boolean hasVoterVoted(UUID userId) throws InvalidUserIdException {
        if (this.haveVotersVotedMap.containsKey(userId)) {
            return this.haveVotersVotedMap.get(userId);
        } else throw new InvalidUserIdException();
    }

    public void updateVoterHasVoted(UUID userId) throws TwoTimeVoteException, InvalidUserIdException {
        if (!this.haveVotersVotedMap.containsKey(userId)) {
            throw new InvalidUserIdException();
        } else {
            if (Boolean.TRUE.equals(this.haveVotersVotedMap.get(userId))) throw new TwoTimeVoteException();
            else {
                this.haveVotersVotedMap.replace(userId, Boolean.TRUE);
            }
        }
    }

    public boolean hasVoterVotedForEveryCandidate(UUID userId) throws InvalidUserIdException, TwoTimeVoteException {
        if (!this.haveVotersVotedMap.containsKey(userId)) {
            throw new InvalidUserIdException();
        } else {
            if (this.haveVotersVotedMap.get(userId).equals(Boolean.TRUE)) throw new TwoTimeVoteException();
            else {
                for (User user : voters) {
                    if (user.getUserId().equals(userId)) {
                        return user.getVote().getVotes().size() == this.candidates.size();
                    }
                }
            }
        }
    }
}
