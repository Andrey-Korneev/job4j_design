package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class ConvertXmlToPOJO {
    public static void main(String[] args) throws Exception {
        Planet planet = new Planet("Mars", 4, false,
                new PlanetParameters(6.42e23, 6779), new String[] {"Phobos", "Deimos"});

        JAXBContext context = JAXBContext.newInstance(Planet.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(planet, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Planet result = (Planet) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}