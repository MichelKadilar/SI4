package menuchoice;

import io.Input;
import io.Output;
import main.MainClient;
import users.candidate.Candidate;
import users.candidate.ICandidate;
import votingsystem.ClientVotingSystem;
import votingsystem.IVoting;
import votingsystem.exceptions.InvalidUserIdException;
import votingsystem.exceptions.InvalidVoteValueException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class MenuChoiceManager {

    public static void choiceAfterSuccessfulConnection() throws MalformedURLException, NotBoundException, RemoteException, InvalidUserIdException, InvalidVoteValueException {
        int choiceInputIntValue;
        do {
            Output.displayChoiceAfterSuccessfulConnection();
            String choiceAfterSuccessfulConnection = Input.getNextInputLine();
            choiceInputIntValue = Integer.parseInt(choiceAfterSuccessfulConnection);
            ICandidate candidateManager = (ICandidate) Naming.lookup("rmi://localhost:2001/UM");
            List<Candidate> candidateList = candidateManager.getCandidateList();
            switch (choiceInputIntValue) {
                case 1: { // 1 is for displaying the list of the candidates of the election
                    Output.displayCandidateList(candidateList);
                    break;
                }
                case 2: // 2 is for register for a future election going to start
                    IVoting votingRMI = (IVoting) Naming.lookup("rmi://localhost:2001/V");
                    if (!votingRMI.hasVoteStarted()) {
                        votingRMI.registerToVote(MainClient.user.getUserId(), "");
                        choiceAfterRegisteringForElection(votingRMI);
                        return;
                    } else {
                        Output.displayCurrentlyNoElection();
                        choiceInputIntValue = 0; // to not quit the system when there is no election
                        break;
                    }
                default:
                    break;
            }
        } while (choiceInputIntValue != 2);
    }

    public static void choiceAfterRegisteringForElection(IVoting votingRMI) throws MalformedURLException, InvalidUserIdException, NotBoundException, RemoteException, InvalidVoteValueException {
        do {
            Output.displayChoiceAfterRegistrationForElection();
            String choiceAfterSuccessfulConnection = Input.getNextInputLine();
            int choiceInputIntValue = Integer.parseInt(choiceAfterSuccessfulConnection);
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
                    if (votingRMI.hasVoteStarted() && !votingRMI.hasVoteEnded()) {
                        clientVotingSystem.voting(votingRMI);
                        return;
                    } else {
                        Output.displayCurrentlyNoElection();
                        break;
                    }
                default:
                    break;
            }
        } while (true);
    }

    //TODO : quit the system or wait for a future election ?

}
