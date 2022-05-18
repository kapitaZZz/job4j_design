package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONToPOJO {
    public static void main(String[] args) {
        JSONObject pentHouse = new JSONObject();
        JSONObject worker = new JSONObject();
        JSONArray typeArray = new JSONArray();
        String[] typeHouse = {"Skyscraper", "TownHouse", "EliteApartments"};

        typeArray.put(typeHouse);

        worker.put("name", "Peter");
        worker.put("experience", 10);

        pentHouse.put("address", "MoscowCity");
        pentHouse.put("stages", "40");
        pentHouse.put("isComplete", "true");
        pentHouse.put("worker", worker);
        pentHouse.put("typeHouse", typeArray);
        System.out.println(pentHouse);
    }
}
