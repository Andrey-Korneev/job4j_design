package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "planet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Planet {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private int systemNumber;

    @XmlAttribute
    private boolean isLivable;
    private PlanetParameters parameters;

    @XmlElementWrapper(name = "moons")
    @XmlElement(name = "moon")
    private String[] moons;

    public Planet() { }

    public Planet(String name, int systemNumber, boolean isLivable, PlanetParameters parameters, String[] moons) {
        this.name = name;
        this.systemNumber = systemNumber;
        this.isLivable = isLivable;
        this.parameters = parameters;
        this.moons = moons;
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