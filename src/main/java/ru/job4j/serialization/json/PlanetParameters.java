package ru.job4j.serialization.json;

public class PlanetParameters {
    private final double mass;
    private final int diameter;

    public PlanetParameters(double mass, int diameter) {
        this.mass = mass;
        this.diameter = diameter;
    }

    public double getMass() {
        return mass;
    }

    public int getDiameter() {
        return diameter;
    }

    @Override
    public String toString() {
        return "PlanetParameters{"
                + "mass=" + mass
                + "kg, diameter=" + diameter
                + "km}";
    }
}