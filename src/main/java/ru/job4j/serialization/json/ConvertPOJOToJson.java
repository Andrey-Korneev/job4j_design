package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConvertPOJOToJson {
    public static void main(String[] args) {
        JSONObject jsonPlanetParameters = new JSONObject("{\"mass\":6.42e23, \"diameter\":6779}");
        List<String> list = new ArrayList<>();
        list.add("Phobos");
        list.add("Deimos");
        JSONArray jsonMoons = new JSONArray(list);

        final Planet planet = new Planet("Mars", 4, false,
                new PlanetParameters(6.42e23, 6779), new String[] {"Phobos", "Deimos"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", planet.getName());
        jsonObject.put("systemNumber", planet.getSystemNumber());
        jsonObject.put("isLivable", planet.isLivable());
        jsonObject.put("parameters", jsonPlanetParameters);
        jsonObject.put("moons", jsonMoons);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(planet));
    }
}