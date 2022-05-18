package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;

public class Person implements Serializable {

    private String name;
    private int age;
    private String profession;
    private String phoneNumber;

    public Person(String name, int age, String profession, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", profession='" + profession + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Person person = new Person("Sam", 33, "Agent", "+45-4545-45-45");
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(person);
        }
        try (FileInputStream fis = new FileInputStream(tempFile);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Person personFromFile = (Person) ois.readObject();
            System.out.println(personFromFile);
        }

    }
}
