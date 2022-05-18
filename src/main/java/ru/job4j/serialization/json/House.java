package ru.job4j.serialization.json;

import java.util.Arrays;

public class House {

    private String address;
    private int stages;
    private boolean isComplete;
    private Worker worker;
    private String[] typeHouse;

    public House(String address, int stages, boolean isComplete, Worker worker, String[] typeHouse) {
        this.address = address;
        this.stages = stages;
        this.isComplete = isComplete;
        this.worker = worker;
        this.typeHouse = typeHouse;
    }

    @Override
    public String toString() {
        return "House{"
                + "address='" + address + '\''
                + ", stages=" + stages
                + ", isComplete=" + isComplete
                + ", worker=" + worker
                + ", typeHouse=" + Arrays.toString(typeHouse)
                + '}';
    }
}
