package com.panyukovnn.optional;

import com.panyukovnn.optional.model.Personage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonageUtil {

    public static List<Personage> personages = new ArrayList<>();

    static {
        personages.addAll(Arrays.asList(
                new Personage("Walter", "White", "Genius of chemistry"),
                new Personage("Skyler", "White", "Economist"),
                new Personage("Jesse", "Pinkman", "Seller"),
                new Personage("Hank,", "Schrader", "DEA agent"),
                new Personage("Saul", "Goodman", "Lawyer"),
                new Personage("Gustavo", "Fring", "Meth king"),
                new Personage("Mike", "Ehrmantraut", "Troubleshooter"),
                new Personage("Tuco", "Salamanca", "Drug cartel member")
        ));

        personages.get(0).setMeth(10_000d);
        personages.get(2).setMeth(9000d);
        personages.get(5).setMeth(1_000_000d);
        personages.get(7).setMeth(1000d);
    }
}
