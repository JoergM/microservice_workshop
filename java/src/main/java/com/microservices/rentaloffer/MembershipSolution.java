package com.microservices.rentaloffer;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class MembershipSolution implements MessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(MembershipSolution.class);
    private static Connections connection;
    private Random random = new Random();

    public static void main(String[] args) {
        String host = args[0];
        String port = args[1];

        connection = new Connections(host, port);
        connection.deliveryLoop(new MembershipSolution());
    }

    public void handle(String message) {
        NeedPacket needPacket = new Gson().fromJson(message, NeedPacket.class);

        if (needPacket.getSolutions().size() == 0 && "Y".equals(needPacket.member)) {
            Solution solution = new Solution();
            solution.setSolutionDescription("Membership Offer");
            solution.setValue(random.nextInt(100) + 100); //this is a good value :)

            needPacket.proposeSolution(solution);
            connection.publish(needPacket.toJson());
        }

    }

}
