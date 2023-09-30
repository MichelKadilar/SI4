package io;

public class Output {

    public static void displayAdminChoice() {
        System.out.println("If you want to start a vote, press 'S'. You can put an end to it with the 'E' key.");
    }

    public static void displayTheVoteHasBeenStarted() {
        System.out.println("The vote has been started");
    }

    public static void displayNoVoters(){
        System.out.println("There is no registered voter in the election you want to start");
    }
}
