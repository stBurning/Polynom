package com.company;

public class Main {

    public static void main(String[] args) {
        Polynom p = new Polynom(new double[]{0.5, 1, 2});
        System.out.println(p.plus(p));
    }
}
