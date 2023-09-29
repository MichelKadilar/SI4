package io;

import users.candidate.Candidate;

import java.util.List;

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
        System.out.println("Press 1 to display the list of the candidates or Press 2 to begin to put your vote for every candidate");
    }

    public static void displayCandidateList(List<Candidate> candidateList) {
        for (int index = 1; index < candidateList.size(); index++) {
            Candidate candidate = candidateList.get(index);
            System.out.println(index + " : " + candidate.getFirstName() + " " + candidate.getLastName() +
                    " " + candidate.getPitch());
        }
    }

    public static void displayCandidate(int index, List<Candidate> candidateList) {
        Candidate candidate = candidateList.get(index);
        System.out.println(index + " : " + candidate.getFirstName() + " " + candidate.getLastName() +
                " " + candidate.getPitch());
    }

    public static void displayPleaseVote() {
        System.out.println("Please give a value between 0 and 3 included for this candidate");
    }

    public static void displayUserVoteJustInputed(int voteValue, int index, List<Candidate> candidateList) {
        Candidate candidate = candidateList.get(index);
        System.out.println("You voted : " + voteValue + " for : " + candidate.getFirstName() + " " + candidate.getLastName());
    }
}
