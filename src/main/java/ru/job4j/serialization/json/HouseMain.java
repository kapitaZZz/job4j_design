package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HouseMain {

    public static void main(String[] args) {
        final House house = new House("Red_Square", 25, false, new Worker("Ivan", 15),
                new String[]{"Skyscraper", "TownHouse", "EliteApartments"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(house));

        final String houseJson = "{"
                + "\"address\":Red_Square,"
                + "\"stages\":35,"
                + "\"isComplete\":false,"
                + "\"worker\":"
                + "{"
                + "\"name\":Ivan,"
                + "\"experience\":15"
                + "},"
                + "\"typeHouse\":"
                + "[\"Skyscraper\",\"TownHouse\",\"EliteApartments\"]"
                + "}";

        final House houseMod = gson.fromJson(houseJson, House.class);
        System.out.println(houseMod);
    }
}
