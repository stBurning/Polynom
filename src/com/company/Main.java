package com.company;

public class Main {

    public static void main(String[] args) {
        Polynom p = new Polynom(new double[]{-10, 2, -3, -1});
        System.out.println(p);
        Polynom p1 = new Polynom(new double[]{0, 0, 0});
        System.out.println(p1);
        Polynom p2 = new Polynom(new double[]{1, 0, 0});
        System.out.println(p2);
        Polynom p3 = new Polynom(new double[]{0, 1, 0});
        System.out.println(p3);
        Polynom p4 = new Polynom(new double[]{0, 0, 1});
        System.out.println(p4);
        Polynom p5 = new Polynom(new double[]{-1, 0, 0});
        System.out.println(p5);
        Polynom p6 = new Polynom(new double[]{0, -1, 0});
        System.out.println(p6);
        Polynom p7 = new Polynom(new double[]{0, 0, -1});
        System.out.println(p7);
        System.out.println(p.invoke(3));
    }
}
