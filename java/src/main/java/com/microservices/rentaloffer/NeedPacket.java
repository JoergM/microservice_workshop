package com.microservices.rentaloffer;

import com.google.gson.Gson;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeedPacket {

    public final String NEED = "car_rental_offer";
    public String id;
    public String member;
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
        message.put("member", member);
        return new Gson().toJson(message);
    }

    public void proposeSolution(Solution solution) {
        solutions.add(solution);
    }

    public List<Solution> getSolutions() {
        return solutions;
    }
}
