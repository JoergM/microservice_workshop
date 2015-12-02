package com.microservices.rentaloffer;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class MembershipService implements MessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(MembershipService.class);
    private static Connections connection;

    public static void main(String[] args) {
        String host = args[0];
        String port = args[1];

        connection = new Connections(host, port);
        connection.deliveryLoop(new MembershipService());
    }

    public void handle(String message) {
        NeedPacket needPacket = new Gson().fromJson(message, NeedPacket.class);

        if (needPacket.member == null && needPacket.getSolutions().isEmpty()) {
            needPacket.member = new Random().nextBoolean() ? "Y" : "N";
            if (needPacket.member == "Y") {
                connection.publish(needPacket.toJson());
            } else {
                needPacket.proposeSolution(new Solution("Offer Membership", 100));
                connection.publish(needPacket.toJson());
            }
        }


    }

}
