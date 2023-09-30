package menuchoice;

import io.Input;
import io.Output;
import main.MainClient;
import users.candidate.Candidate;
import users.candidate.ICandidate;
import votingsystem.ClientVotingSystem;
import votingsystem.IVoting;
import votingsystem.exceptions.InvalidUserIdException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class MenuChoiceManager {

    public static void choiceAfterSuccessfulConnection() throws MalformedURLException, NotBoundException, RemoteException, InvalidUserIdException {
        int choiceInputIntValue = 0;
        do {
            Output.displayChoiceAfterSuccessfulConnection();
            String choiceAfterSuccessfulConnection = Input.getNextInputLine();
            for (ChoiceValues choice : ChoiceValues.values()) {
                choiceInputIntValue = Integer.parseInt(choiceAfterSuccessfulConnection);
                if (choice.getChoiceValue() == choiceInputIntValue) {
                    ICandidate candidateManager = (ICandidate) Naming.lookup("rmi://localhost:2001/UM");
                    List<Candidate> candidateList = candidateManager.getCandidateList();
                    switch (choiceInputIntValue) {
                        case 1: { // 1 is for displaying the list of the candidates of the election
                            Output.displayCandidateList(candidateList);
                            break;
                        }
                        case 2: // register for the current election if there is one, or for a future election if there isn't one
                            Output.displayCandidateList(candidateList);
                            IVoting votingRMI = (IVoting) Naming.lookup("rmi://localhost:2001/V");
                            votingRMI.registerToVote(MainClient.user.getUserId(), "");
                            choiceAfterRegisteringForElection(votingRMI);
                            return;
                        default:
                            break;
                    }
                }
            }
        } while (choiceInputIntValue != 2);
    }

    public static void choiceAfterRegisteringForElection(IVoting votingRMI) throws MalformedURLException, InvalidUserIdException, NotBoundException, RemoteException {
        do {
            Output.displayChoiceAfterSuccessfulConnection();
            String choiceAfterSuccessfulConnection = Input.getNextInputLine();
            for (ChoiceValues choice : ChoiceValues.values()) {
                int choiceInputIntValue = Integer.parseInt(choiceAfterSuccessfulConnection);
                if (choice.getChoiceValue() == choiceInputIntValue) {
                    ICandidate candidateManager = (ICandidate) Naming.lookup("rmi://localhost:2001/UM");
                    List<Candidate> candidateList = candidateManager.getCandidateList();
                    switch (choiceInputIntValue) {
                        case 1: { // 1 is for displaying the list of the candidates of the election
                            Output.displayCandidateList(candidateList);
                            break;
                        }
                        case 2: // 2 is for voting for all the candidates, one after another
                            Output.displayCandidateList(candidateList);
                            ClientVotingSystem clientVotingSystem = new ClientVotingSystem(candidateList);
                            if (votingRMI.hasVoteStarted()) {
                                clientVotingSystem.voting(votingRMI);
                                return;
                            } else {
                                Output.displayCurrentlyNoElection();
                                break;
                            }
                        default:
                            break;
                    }
                }
            }
        } while (true);
    }

    //TODO : quit the system or wait for a future election ?

}
