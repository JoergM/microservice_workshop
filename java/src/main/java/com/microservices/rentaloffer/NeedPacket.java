package com.microservices.rentaloffer;

import java.rmi.server.UID;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.Gson;

public class NeedPacket {

    public final String NEED = "car_rental_offer";
    public String id;
    private final List<Solution> solutions = new ArrayList<>();

    public NeedPacket() {
        id = new UID().toString();
    }

    public String toJson() {
        Map<String, Object> message = new HashMap<>();
        message.put("json_class", NeedPacket.class.getName());
        message.put("need", NEED);
        message.put("solutions", solutions);
        message.put("id", id);
        return new Gson().toJson(message);
    }

    public void proposeSolution(Solution solution) {
        solutions.add(solution);
    }

    public List<Solution> getSolutions() {
        return solutions;
    }
}
