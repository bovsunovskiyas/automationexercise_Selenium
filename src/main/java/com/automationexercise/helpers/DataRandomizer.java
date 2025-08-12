package com.automationexercise.helpers;

import com.automationexercise.models.Gender;
import com.github.javafaker.Faker;
import com.github.javafaker.File;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class DataRandomizer {

    public static final Faker faker = new Faker();

    public static String getRandomUuid(){
        UUID uuid = UUID.randomUUID();
        String randomPart = uuid.toString().substring(0,4);
        return randomPart + "_";
    }

    public static String getRandomEmail(){
        UUID uuid = UUID.randomUUID();
        String randomPart = uuid.toString().substring(0,4);
        return randomPart+ "_" + faker.internet().emailAddress();
    }

    public static String getRandomPassword(){
        return faker.internet().password(true);
    }

    public static String getRandomFullName(){
        return faker.name().fullName();
    }

    public static String getRandomFirstName(){
        return faker.name().firstName();
    }

    public static String getRandomLastName(){
        return faker.name().lastName();
    }

    public static String getRandomCompany(){
        return faker.company().name();
    }

    public static String getRandomAddress1(){
        return faker.address().fullAddress();
    }

    public static String getRandomAddress2() {
        return faker.address().secondaryAddress();
    }

    public static String getRandomCountry(){
        return faker.address().country();
    }

    public static String getRandomState(){
        return faker.address().state();
    }

        public static String getRandomCity(){
        return faker.address().city();
    }
    public static String getRandomZipcode(){
        return faker.address().zipCode();
    }

    public static String getRandomPhone(){
        return faker.phoneNumber().cellPhone();
    }

    public static Gender getRandomTitle(){
        Gender[] genders = Gender.values();
        return genders[new Random().nextInt(genders.length)];
    }

    public static String[] getRandomBirthDate(){
        Date birthday = faker.date().birthday(18,70);
        LocalDate localDate = birthday.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        String formattedDate = localDate.format(formatter);
        return formattedDate.split(" ");
    }

    public static String getRandomSubject(){
        return faker.app().author();
    }

    public static String getRandomMessage(){
        return faker.medical().diseaseName();
    }






}
