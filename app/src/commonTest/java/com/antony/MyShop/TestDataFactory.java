package com.antony.MyShop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.antony.myshop.data.model.Product;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static Product makeRibot(String uniqueSuffix) {
        return Product.create(makeProfile(uniqueSuffix));
    }

    public static List<Product> makeListRibots(int number) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            products.add(makeRibot(String.valueOf(i)));
        }
        return products;
    }

    public static Profile makeProfile(String uniqueSuffix) {
        return Profile.builder()
                .setName(makeName(uniqueSuffix))
                .setEmail("email" + uniqueSuffix + "@ribot.co.uk")
                .setDateOfBirth(new Date())
                .setHexColor("#0066FF")
                .setAvatar("http://api.ribot.io/images/" + uniqueSuffix)
                .setBio(randomUuid())
                .build();
    }

    public static Name makeName(String uniqueSuffix) {
        return Name.create("Name-" + uniqueSuffix, "Surname-" + uniqueSuffix);
    }

}