package main;

import connect.ConnectionManagerRMI;
import users.candidate.CandidateRMI;
import votingsystem.VotingRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class SetupRMI {

    public static final int RMIPort = 2001;

    public static void setupRMI() throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(RMIPort);
        ConnectionManagerRMI.rebindConnectImpl(reg, "C");
        CandidateRMI.rebindUserManagerImpl(reg, "UM");
        VotingRMI.rebindVotingImpl(reg, "V");
    }
}
