package ru.tcivinskaya.main;

import ru.tcivinskaya.vector.Vector;


public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Vector vector = new Vector(3);
        vector.setComponent(-3, 0);
        vector.setComponent(2, 1);
        vector.setComponent(1, 2);

        Vector vector1 = new Vector(new double[]{1, 2});

        Vector vector2 = new Vector(3, new double[]{2, 3, 1, 4});

        Vector vector3 = new Vector(vector1);
        vector3.setComponent(4, 2);

        System.out.printf("У нас есть четыре вектора с компонентами: %s, %s, %s, %s%n", vector, vector1, vector2, vector3);

        System.out.println();

        System.out.printf("При сложении векторов с компонентами %s и %s получается вектор с компоненатами ", vector, vector1);
        System.out.println(vector.makeVectorsAddition(vector1));
        System.out.printf("При вычитании вектора с компонентами %s из вектора с компонентами %s получается вектор с компоненатами ", vector1, vector);
        System.out.println(vector.makeVectorsSubtraction(vector1));

        System.out.println();

        System.out.printf("При сложении векторов с компонентами %s и %s получается вектор с компоненатами ", vector2, vector3);
        System.out.println(Vector.makeVectorsAddition(vector2, vector3));
        System.out.printf("При вычитании из него вектора с компонентами %s получается вектор с компоненатами ", vector2);
        System.out.println(Vector.makeVectorsSubtraction(Vector.makeVectorsAddition(vector2, vector3), vector2));
        System.out.printf("Скалярное произведение векторов с компонентами %s, %s = %f%n", vector2, vector3, Vector.makeScalarProduct(vector2, vector3));

        System.out.println();

        vector.setComponent(0, 2);
        System.out.printf("Длина вектора с компонентами %s = %f, размерность = %d%n", vector, vector.getLength(), vector.getSize());
        int scalar = 5;
        System.out.printf("При умножении этого вектора на скаляр (%d) получается вектор с компонентами %s%n", scalar, vector.makeScalarMultiplication(scalar));
        System.out.println("Развернём этот вектор");
        vector.turnVector();
        int index = 1;
        System.out.printf("Тогда его компонента с индексом %d будет равна %f%n", index, vector.getComponent(index));
    }
}

