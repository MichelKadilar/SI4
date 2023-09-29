package menuchoice;

import io.Input;
import io.Output;
import users.candidate.Candidate;
import users.candidate.ICandidate;
import votingsystem.ClientVotingSystem;
import votingsystem.exceptions.InvalidUserIdException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class MenuChoiceManager {

    public static void choiceAfterSuccessfulConnection() throws MalformedURLException, NotBoundException, RemoteException, InvalidUserIdException {
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
                            clientVotingSystem.voting();
                            return; //TODO
                        default:
                            break;
                    }
                }
            }
        } while (true);
    }

}
