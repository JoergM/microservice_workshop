package com.microservices.rentaloffer;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class BestSolutionStore implements MessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(BestSolutionStore.class);
    private static Connections connection;
    private final HashMap<String, Solution> solutionMap;

    public static void main(String[] args) {
        String host = args[0];
        String port = args[1];

        connection = new Connections(host, port);
        connection.deliveryLoop(new BestSolutionStore());
    }

    public BestSolutionStore() {
        solutionMap = new HashMap<>();
    }

    public void handle(String message) {
        NeedPacket needPacket = new Gson().fromJson(message, NeedPacket.class);

        Solution bestSolution = findBestSolution(needPacket);
        solutionMap.put(needPacket.id, bestSolution);
        logger.info("Stored Best Solution for " + needPacket.id + " " + bestSolution.getSolutionDescription());
    }

    private Solution findBestSolution(NeedPacket needPacket) {
        Solution currentSolution = solutionMap.get(needPacket.id);
        if (currentSolution == null) {
            return needPacket.getSolutions().get(0);
        }

        Solution newSolution = needPacket.getSolutions().get(0);

        if (newSolution.getValue() > currentSolution.getValue()) {
            return newSolution;
        } else {
            return currentSolution;
        }
    }

}
