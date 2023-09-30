package io;

import users.candidate.Candidate;

import java.util.List;
import java.util.Map;

public class Output {

    public static void displayConnectionMessage() {
        System.out.println("Please connect to the system by entering your first name and your last name as follows : 'John Doe'");
    }

    public static void displaySuccessfulConnection() {
        System.out.println("You are now connected !");
    }

    public static void displayFailureConnection() {
        System.err.println("Connection have failed ! Please re-enter your credential as follows : 'John Doe'");
    }

    public static void displayChoiceAfterSuccessfulConnection() {
        //Press 2 to begin to put your vote for every candidate
        System.out.println("Press 1 to display the list of the candidates\nPress 2 to register as a voter in the (future) election");
    }

    public static void displayChoiceAfterRegistrationForElection() {

        System.out.println("Press 1 to display the list of the candidates\nPress 2 to begin to put your vote for every candidate");

    }

    public static void displayCandidateList(List<Candidate> candidateList) {
        for (int index = 0; index < candidateList.size(); index++) {
            Candidate candidate = candidateList.get(index);
            System.out.println((index + 1) + " : " + candidate.getFirstName() + " " + candidate.getLastName() +
                    " " + candidate.getPitch());
        }
    }

    public static void displayCandidate(int index, List<Candidate> candidateList) {
        Candidate candidate = candidateList.get(index);
        System.out.println((index + 1) + " : " + candidate.getFirstName() + " " + candidate.getLastName() +
                " " + candidate.getPitch());
    }

    public static void displayCurrentlyNoElection() {
        System.out.println("There is currently no ongoing election. Please try later.");
    }

    public static void displayElectionHasNotFinished() {
        System.out.println("There is currently an ongoing election. Please wait until it is finished to retrieve the final results.");
    }

    public static void displayLine() {
        System.out.println("-------------------------------------------------------------------------");
    }

    public static void displayPleaseVote() {
        System.out.println("Please give a value between 0 and 3 included for this candidate");
    }

    public static void displayUserVoteJustInputed(int voteValue, int index, List<Candidate> candidateList) {
        Candidate candidate = candidateList.get(index);
        System.out.println("You voted : " + voteValue + " for : " + candidate.getFirstName() + " " + candidate.getLastName());
    }

    public static void displayFinalRankingResults(List<Candidate> candidateList, Map<Integer, Integer> candidatesRankAndTheirFinalResults) {
        for (Integer candidatesRank : candidatesRankAndTheirFinalResults.keySet()) {
            Candidate candidate = candidateList.get(candidatesRank);
            Integer nbOfVotes = candidatesRankAndTheirFinalResults.get(candidatesRank);
            Output.displayCandidateResult(candidate, candidatesRank, nbOfVotes);
        }
    }

    private static void displayCandidateResult(Candidate candidate, Integer candidateRank, Integer nbOfVotes) {
        System.out.println("Candidate : " + candidate.getFirstName() + " " + candidate.getLastName() + " of rank " + candidateRank + " has got : " + nbOfVotes + " Votes");
    }

    public static void displayWinners(Map<Candidate, Integer> winners) {
        System.out.println("Winners are :");
        for (Candidate candidate : winners.keySet()) {
            System.out.println(candidate.getFirstName() + " " + candidate.getLastName() + " with : " + winners.get(candidate) + " votes");
        }
        Output.displayCongratulations();
    }

    public static void displayCongratulations() {
        System.out.println("Congratulations !");
    }
}
