package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Worker;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "house")
@XmlAccessorType(XmlAccessType.FIELD)
public class House {
    @XmlAttribute
    private String address;
    @XmlAttribute
    private int stages;
    @XmlAttribute
    private boolean isComplete;
    @XmlElement
    private Worker worker;
    @XmlElementWrapper(name = "typeHouse")
    @XmlElement(name = "type")
    private String[] typeHouse;

    public House() {
    }

    public House(String address, int stages, boolean isComplete, Worker worker, String[] typeHouse) {
        this.address = address;
        this.stages = stages;
        this.isComplete = isComplete;
        this.worker = worker;
        this.typeHouse = typeHouse;
    }

    @Override
    public String toString() {
        return "House{"
                + "address='" + address + '\''
                + ", stages=" + stages
                + ", isComplete=" + isComplete
                + ", worker=" + worker
                + ", typeHouse=" + Arrays.toString(typeHouse)
                + "}";
    }

    public static void main(String[] args) throws JAXBException {
        final House house = new House("Tverskaya_Street", 25, true, new Worker("concierge", 5),
                new String[]{"Skyscraper", "TownHouse", "EliteApartments"});

        JAXBContext context = JAXBContext.newInstance(House.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(house, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
