package ru.job4j.serialization.json;

import java.util.Arrays;

public class Planet {
    private final String name;
    private final int systemNumber;
    private final boolean isLivable;
    private final PlanetParameters parameters;
    private final String[] moons;

    public Planet(String name, int systemNumber, boolean isLivable, PlanetParameters parameters, String[] moons) {
        this.name = name;
        this.systemNumber = systemNumber;
        this.isLivable = isLivable;
        this.parameters = parameters;
        this.moons = moons;
    }

    public String getName() {
        return name;
    }

    public int getSystemNumber() {
        return systemNumber;
    }

    public boolean isLivable() {
        return isLivable;
    }

    public PlanetParameters getParameters() {
        return parameters;
    }

    public String[] getMoons() {
        return moons;
    }

    @Override
    public String toString() {
        return "Planet{"
                + "name='" + name + '\''
                + ", systemNumber=" + systemNumber
                + ", isLivable=" + isLivable
                + ", parameters=" + parameters
                + ", moons=" + Arrays.toString(moons)
                + '}';
    }
}