package ru.stqa.pft.sandbox;

/**
 * Created by maria on 04.03.2016.
 */
public class Equality {

    public static void main(String[] args) {
        String s1 = "firefox";
        String s2 = new String(s1);
        String s3 = "firefox";

        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1==s3);
    }

}
