package ru.job4j.serialization.json;

public class Worker {
    private String name;
    private int experience;

    public Worker(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Worker{"
                + "name='" + name + '\''
                + ", experience=" + experience
                + '}';
    }
}
