package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "planetParameters")
public class PlanetParameters {
    @XmlAttribute
    private double mass;

    @XmlAttribute
    private int diameter;

    public PlanetParameters() { }

    public PlanetParameters(double mass, int diameter) {
        this.mass = mass;
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return "PlanetParameters{"
                + "mass=" + mass
                + "kg, diameter=" + diameter
                + "km}";
    }
}