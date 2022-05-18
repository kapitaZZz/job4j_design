package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Worker;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        House house = new House("Tverskaya_Street", 25, true, new Worker("concierge", 5),
                new String[]{"Skyscraper", "TownHouse", "EliteApartments"});

        JAXBContext context = JAXBContext.newInstance(House.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(house, writer);
            xml = writer.toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            House houseNew = (House) unmarshaller.unmarshal(reader);
            System.out.println(houseNew);
        }
    }
}
