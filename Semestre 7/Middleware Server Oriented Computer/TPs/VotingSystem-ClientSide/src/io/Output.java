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
        for (Candidate candidate : candidateList) {
            System.out.println(candidate.getRank() + " : " + candidate.getFirstName() + " " + candidate.getLastName() + " " + candidate.getPitch());
        }
    }
}
