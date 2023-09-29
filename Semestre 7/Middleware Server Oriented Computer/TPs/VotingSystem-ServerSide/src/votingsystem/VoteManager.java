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

    private Map<Integer, Candidate> candidates;

    private Map<UUID, Boolean> haveVotersVotedMap;
    private final Map<UUID, Vote> voterVotes;

    public VoteManager() {
        voterVotes = new HashMap<>();
        buildCandidatesList();
        buildHaveVotersVotedMap();

    }

    private void buildCandidatesList() {
        this.candidates = new HashMap<>();
        List<Candidate> candidatesList = (List<Candidate>) FileManager.createList("Candidates.txt");
        int rank = 0;
        for (Candidate candidate : candidatesList) {
            candidates.put(rank, candidate);
        }
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

    public boolean addVoterToMap(UUID userId) {
        User user = findUserByUUID(userId);
        if (user == null) return false;
        else {
            this.voterVotes.put(userId, new Vote());
            this.haveVotersVotedMap.put(userId, Boolean.FALSE);
            return true;
        }
    }

    public User findUserByUUID(UUID userId) {
        for (User user : Main.userConnectionManager.getPersonList()) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public boolean addVoteForCandidate(UUID userId, int candidateRank, int userVoteValue) {
        if (this.haveVotersVotedMap.get(userId).equals(Boolean.TRUE)) return false;
        else {
            if (this.voterVotes.containsKey(userId) && this.voterVotes.get(userId) != null) {
                return this.voterVotes.get(userId).addVote(candidateRank, userVoteValue);
            } else return false;
        }
    }

    public boolean haveEveryVoterVoted() {
        if (this.haveVotersVotedMap.size() > 1) {
            for (Boolean b : this.haveVotersVotedMap.values()) {
                if (b.equals(Boolean.FALSE)) return false;
            }
        }
        return true;
    }

    public Map<Integer, Candidate> getCandidates() {
        return candidates;
    }

    public boolean hasVoterAlreadyVoted(UUID userId) throws InvalidUserIdException {
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
                if (this.voterVotes.get(userId).getVotes().size() == this.candidates.size()) {
                    this.updateVoterHasVoted(userId);
                    return true;
                }
            }
        }
        return false;
    }

    public Vote getUserVote(UUID userId) {
        if (this.voterVotes.containsKey(userId)) {
            return this.voterVotes.get(userId);
        }
        return null;
    }

    public Map<UUID, Boolean> getHaveVotersVotedMap() {
        return haveVotersVotedMap;
    }

    public Map<UUID, Vote> getVoterVotes() {
        return voterVotes;
    }
}
