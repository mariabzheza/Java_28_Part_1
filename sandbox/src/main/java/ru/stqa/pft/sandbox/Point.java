package ru.stqa.pft.sandbox;

/**
 * Created by maria on 22.02.2016.
 */
public class Point {
    double x;
    double y;
    Point (double x, double y){
        this.x = x;
        this.y = y;
    }
    public static double distance (Point p1, Point p2){
        double rez;
        rez = Math.sqrt( Math.pow((p2.x-p1.x),2) + Math.pow((p2.y-p1.y),2) );
        return rez;
    }
}
