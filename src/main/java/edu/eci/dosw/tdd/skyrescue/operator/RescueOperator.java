package edu.eci.dosw.tdd.skyrescue.operator;

public class RescueOperator {

    private final String id;
    private final String name;

    public RescueOperator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}