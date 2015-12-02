package com.microservices.rentaloffer;

/**
 * Created by joerg on 02.12.15.
 */
public class Solution {

    SolutionType type;

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    String solution;

    public SolutionType getType() {
        return type;
    }

    public void setType(SolutionType type) {
        this.type = type;
    }
}
