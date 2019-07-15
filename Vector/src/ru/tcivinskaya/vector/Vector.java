package ru.tcivinskaya.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) throws IllegalAccessException {
        if (n <= 0) {
            throw new IllegalAccessException("n должно быть больше нуля");
        }

        this.components = new double[n];
    }

    public Vector(Vector otherVector) {
        this.components = Arrays.copyOf(otherVector.components, otherVector.getSize());
    }

    public Vector(double[] components) {
        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int n, double[] components) throws IllegalAccessException {
        if (n <= 0) {
            throw new IllegalAccessException("n должно быть больше нуля");
        }

        this.components = Arrays.copyOf(components, n);
    }

    public double[] getComponents() {
        return components;
    }

    public void setComponents(double[] components) {
        this.components = components;
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        String stringVector = Arrays.toString(components);
        return "{ " + stringVector.substring(1, stringVector.length() - 1) + " }";
    }

    // Пункт a
    public Vector makeVectorsAddition(Vector otherVector) {
        if (getSize() < otherVector.getSize()) {
            components = Arrays.copyOf(components, otherVector.getSize());
        }

        for (int i = 0; i < otherVector.getSize(); ++i) {
            components[i] += otherVector.components[i];
        }

        return this;
    }

    // Пункт b
    public Vector makeVectorsSubtraction(Vector otherVector) {
        if (getSize() < otherVector.getSize()) {
            components = Arrays.copyOf(components, otherVector.getSize());
        }

        for (int i = 0; i < otherVector.getSize(); ++i) {
            components[i] -= otherVector.components[i];
        }

        return this;
    }

    // Пункт c
    public Vector makeScalarMultiplication(double scalar) {
        for (int i = 0; i < getSize(); ++i) {
            components[i] *= scalar;
        }

        return this;
    }

    // Пункт d
    public Vector turnVector() {
        for (int i = 0; i < getSize(); ++i) {
            components[i] *= -1;
        }

        return this;
    }

    // Пункт e
    public double getLength() {
        double squareSum = 0;
        for (double component : components) {
            squareSum += component * component;
        }

        return Math.sqrt(squareSum);
    }

    // Пункт f
    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(double component, int index) {
        if (index >= getSize()){
            components = Arrays.copyOf(components, index + 1);
        }

        components[index] = component;
    }

    // Пункт g
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Vector otherVector = (Vector) object;

        return getSize() == otherVector.getSize() && Arrays.equals(components, otherVector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }

    public static Vector makeVectorsAddition(Vector vector1, Vector vector2) throws IllegalAccessException {
        Vector vectorSum = new Vector(Math.max(vector1.getSize(), vector2.getSize()));

        for (int i = 0; i < Math.max(vector1.getSize(), vector2.getSize()); ++i) {
            if (i < vector1.getSize()) {
                vectorSum.components[i] += vector1.components[i];
            }

            if (i < vector2.getSize()) {
                vectorSum.components[i] += vector2.components[i];
            }
        }

        return vectorSum;
    }

    public static Vector makeVectorsSubtraction(Vector vector1, Vector vector2) throws IllegalAccessException {
        Vector vectorSubtraction = new Vector(Math.max(vector1.getSize(), vector2.getSize()));

        for (int i = 0; i < Math.max(vector1.getSize(), vector2.getSize()); ++i) {
            if (i < vector1.getSize()) {
                vectorSubtraction.components[i] += vector1.components[i];
            }

            if (i < vector2.getSize()) {
                vectorSubtraction.components[i] -= vector2.components[i];
            }
        }

        return vectorSubtraction;
    }

    public static double makeScalarProduct(Vector vector1, Vector vector2) throws IllegalAccessException {
        double scalarProduct = 0;

        for (int i = 0; i < Math.min(vector1.getSize(), vector2.getSize()); ++i) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}
