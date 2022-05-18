package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    public static void main(String[] args) {
        String name = "Sam Fisher";
        int age = 33;
        String job = "CIA";
        String birthDate = "21.09.1988";
        String birtCountry = "UK";
        boolean isMarried = false;
        boolean driverLicense = true;
        LOG.debug("User info name : {}, age : {}, work place at : {}, birthdate : {}, has driver license : {},"
                        + " birt country : {}, is married : {}",
                name,
                age,
                job,
                birthDate,
                driverLicense,
                birtCountry,
                isMarried);
    }
}
