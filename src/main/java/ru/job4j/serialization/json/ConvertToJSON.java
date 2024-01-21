package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConvertToJSON {
    public static void main(String[] args) {
        final Planet planet = new Planet("Earth", 3, true,
                new PlanetParameters(5.9722e24, 12742), new String[] {"Moon"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(planet));

        final String planetJson =
                "{"
                        + "\"name\":\"Mars\","
                        + "\"systemNumber\":4,"
                        + "\"isLivable\":false,"
                        + "\"parameters\":"
                        + "{"
                        + "\"mass\":6.42e23,"
                        + "\"diameter\":6779"
                        + "},"
                        + "\"moons\":"
                        + "[\"Phobos\",\"Deimos\"]"
                        + "}";
        final Planet anotherPlanet = gson.fromJson(planetJson, Planet.class);
        System.out.println(anotherPlanet);
    }
}