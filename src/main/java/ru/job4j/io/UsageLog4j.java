package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    public static void main(String[] args) {
        String name = "Sam Fisher";
        int age = 33;
        boolean isMarried = false;
        float height = 1.72f;
        double salary = 45000.50;
        long personId = 41235874513L;
        byte childrenCount = 1;
        short workExperience = 15;
        LOG.debug("User info name : {}, age : {}, height : {}, salary per year : {}, personal ID : {},"
                        + " child(children) : {}, work experience : {}, is married : {}",
                name,
                age,
                height,
                salary,
                personId,
                childrenCount,
                workExperience,
                isMarried);
    }
}
