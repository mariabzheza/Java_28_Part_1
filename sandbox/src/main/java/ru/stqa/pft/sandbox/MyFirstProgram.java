package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		Point p1 = new Point(5,4);
		Point p2 = new Point(2,3);
		System.out.println("The distance between points (" +p1.x+","+p1.y+") and (" +p2.x+","+p2.y+ ") = "
				+Point.distance(p1,p2));
	}

}
