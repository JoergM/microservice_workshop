package com.microservices.rentaloffer;

/**
 * Created by joerg on 02.12.15.
 */
public class Solution {

    String solutionDescription;
    int value;

    public Solution() {
    }

    public Solution(String solutionDescription, int value) {
        this.solutionDescription = solutionDescription;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

}
