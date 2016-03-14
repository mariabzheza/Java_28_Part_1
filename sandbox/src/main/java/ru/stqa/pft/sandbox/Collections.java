package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maria on 11.03.2016.
 */
public class Collections {

    public static void main(String[] args) {

        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

        for (String l : languages) {
            System.out.println("We want to learn " + l);
        }

    }
}
