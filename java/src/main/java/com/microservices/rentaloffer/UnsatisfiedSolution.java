package com.microservices.rentaloffer;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.microservices.rentaloffer.SolutionType.UNSATISFIED;

public class UnsatisfiedSolution implements MessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(UnsatisfiedSolution.class);
    private static Connections connection;

    public static void main(String[] args) {
        String host = args[0];
        String port = args[1];

        connection = new Connections(host, port);
        connection.deliveryLoop(new UnsatisfiedSolution());
    }

    public void handle(String message) {
        NeedPacket needPacket = new Gson().fromJson(message, NeedPacket.class);

        if (doesNotAlreadyContainSolution(needPacket)) {
            Solution solution = new Solution();
            solution.setType(UNSATISFIED);
            solution.setSolution("Unsatisfied");

            needPacket.proposeSolution(solution);
            connection.publish(needPacket.toJson());
        }

    }

    private boolean doesNotAlreadyContainSolution(NeedPacket needPacket) {
        List<Solution> solutions = needPacket.getSolutions();

        for (Solution solution : solutions) {
            if (solution.getType() == UNSATISFIED) {
                return false;
            }
        }
        return true;
    }

}
