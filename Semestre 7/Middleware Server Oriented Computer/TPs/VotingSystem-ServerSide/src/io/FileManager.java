package io;

import users.Person;
import users.candidate.Candidate;
import users.exceptions.InvalidRankValueException;
import users.user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    public static List<? extends Person> createList(String filename) {
        if (filename.equals("Users.txt")) {
            return createUserListFromFile();
        } else if (filename.equals("Candidates.txt")) {
            return createCandidateListFromFile();
        } else {
            return new ArrayList<>();
        }
    }

    private static List<Candidate> createCandidateListFromFile() {
        List<Candidate> candidates = new ArrayList<>();
        File file = new File("Candidates.txt");

        try (Scanner scanner = new Scanner(file)) {

            //now read the file line by line...
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] info = line.split(" ");
                candidates.add(new Candidate(info[0], info[1], info[2]));
            }
            return candidates;
        } catch (FileNotFoundException e) { //TODO : do not interrupt the program but just ignore unreadable candidates
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static List<User> createUserListFromFile() {
        List<User> users = new ArrayList<>();
        File file = new File("Users.txt");

        try (Scanner scanner = new Scanner(file)) {

            //now read the file line by line...
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] info = line.split(" ");
                users.add(new User(info[0], info[1]));
            }
            return users;
        } catch (FileNotFoundException e) { //TODO : do not interrupt the program but just ignore unreadable candidates
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
