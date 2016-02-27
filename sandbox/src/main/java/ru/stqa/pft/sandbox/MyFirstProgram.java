package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {

		hello("world");
		hello("user");
		hello("Alexei");

		Square s = new Square(8);
		System.out.println("The area of square with side " + s.l +" = " +s.area());

		Rectangle r = new Rectangle(4,6);
		System.out.println("The area of rectangle with side " + r.a +" and " +r.b +" = " +r.area());

	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

}
