package ru.tcivinskaya.main;

import ru.tcivinskaya.vector.Vector;


public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Vector vector = new Vector(new double[]{1, 2, 3, 6});
        System.out.println(vector);

        vector.setComponent(4, 8);
        System.out.println(vector);

        Vector vector1 = new Vector(7, new double[]{1, 2, 3, 6});

        System.out.println(vector.makeVectorsAddition(vector1));
        System.out.println(vector.makeVectorsSubtraction(vector1));

        Vector vector2 = new Vector(7);
        Vector vector3 = new Vector(vector);
    }
}

