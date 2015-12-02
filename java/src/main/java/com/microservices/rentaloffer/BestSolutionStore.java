package com.microservices.rentaloffer;

import com.google.gson.Gson;
import com.google.gson.internal.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import static com.microservices.rentaloffer.SolutionType.ALTERNATIVE;

public class BestSolutionStore implements MessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(BestSolutionStore.class);
    private static Connections connection;
    private final HashMap<String, String> solutionMap;

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
        solutionMap.put(needPacket.id, bestSolution.getSolution());
        logger.info("Stored Best Solution for " + needPacket.id + " " + bestSolution.getSolution());
    }

    private Solution findBestSolution(NeedPacket needPacket) {
        if (needPacket.getSolutions().size() > 0) {
            return needPacket.getSolutions().get(0);
        } else return null;
    }

}
