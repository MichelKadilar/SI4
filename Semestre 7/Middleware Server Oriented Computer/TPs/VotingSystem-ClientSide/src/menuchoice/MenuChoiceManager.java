package menuchoice;

import io.Input;
import io.Output;
import users.candidate.Candidate;
import users.candidate.ICandidate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class MenuChoiceManager {

    public static void choiceAfterSuccessfulConnection() throws MalformedURLException, NotBoundException, RemoteException {
        do {
            Output.displayChoiceAfterSuccessfulConnection();
            String choiceAfterSuccessfulConnection = Input.getNextInputLine();
            for (ChoiceValues choice : ChoiceValues.values()) {
                int choiceInputIntValue = Integer.parseInt(choiceAfterSuccessfulConnection);
                if (choice.getChoiceValue() == choiceInputIntValue) {
                    switch (choiceInputIntValue) {
                        case 1: { // 1 is for displaying the list of the candidates of the election
                            ICandidate candidateManager = (ICandidate) Naming.lookup("rmi://localhost:2001/UM");
                            List<Candidate> candidateList = candidateManager.getCandidateList();
                            Output.displayCandidateList(candidateList);
                            break;
                        }
                        case 2: // 2 is for voting for all the candidates, one after another

                            return; //TODO
                        default:
                            break;
                    }
                }
            }
        } while (true);
    }

}
