package com.microservices.rentaloffer;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class AlternativeSolution implements MessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(AlternativeSolution.class);
    private static Connections connection;
    private Random random = new Random();

    public static void main(String[] args) {
        String host = args[0];
        String port = args[1];

        connection = new Connections(host, port);
        connection.deliveryLoop(new AlternativeSolution());
    }

    public void handle(String message) {
        NeedPacket needPacket = new Gson().fromJson(message, NeedPacket.class);

        if (needPacket.getSolutions().size() == 0) {
            Solution solution = new Solution();
            solution.setSolutionDescription("Alternative");
            solution.setValue(random.nextInt(100));

            needPacket.proposeSolution(solution);
            connection.publish(needPacket.toJson());
        }

    }

}
