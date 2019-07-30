package ru.tcivinskaya.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n должно быть больше нуля");
        }

        this.components = new double[n];
    }

    public Vector(Vector otherVector) {
        this.components = Arrays.copyOf(otherVector.components, otherVector.getSize());
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("длинна массива должна быть больше нуля");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("n должно быть больше нуля");
        }

        this.components = Arrays.copyOf(components, n);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        String stringVector = Arrays.toString(components);
        return "{ " + stringVector.substring(1, stringVector.length() - 1) + " }";
    }

    public Vector add(Vector otherVector) {
        if (getSize() < otherVector.getSize()) {
            components = Arrays.copyOf(components, otherVector.getSize());
        }

        for (int i = 0; i < otherVector.getSize(); ++i) {
            components[i] += otherVector.components[i];
        }

        return this;
    }

    public Vector subtract(Vector otherVector) {
        if (getSize() < otherVector.getSize()) {
            components = Arrays.copyOf(components, otherVector.getSize());
        }

        for (int i = 0; i < otherVector.getSize(); ++i) {
            components[i] -= otherVector.components[i];
        }

        return this;
    }

    public Vector makeScalarMultiplication(double scalar) {
        double measurementError = 1.0e-10;

        for (int i = 0; i < getSize(); ++i) {
            if (Math.abs(components[i]) > measurementError) {
                components[i] *= scalar;
            }
        }

        return this;
    }

    public Vector turnVector() {
        double measurementError = 1.0e-10;

        for (int i = 0; i < getSize(); ++i) {
            if (Math.abs(components[i]) > measurementError) {
                components[i] *= -1;
            }
        }

        return this;
    }

    public double getLength() {
        double squareSum = 0;
        for (double component : components) {
            squareSum += component * component;
        }

        return Math.sqrt(squareSum);
    }

    public double getComponent(int index) {
        if (index >= getSize() || index < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс компоненты вектора");
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index >= getSize() || index < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс компоненты вектора");
        }

        components[index] = component;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Vector otherVector = (Vector) object;

        return Arrays.equals(components, otherVector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vectorSum = new Vector(vector1);

        return vectorSum.add(vector2);
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        Vector vectorSubtraction = new Vector(vector1);

        return vectorSubtraction.subtract(vector2);
    }

    public static double makeScalarProduct(Vector vector1, Vector vector2) {
        double scalarProduct = 0;
        int lessVectorSize = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < lessVectorSize; ++i) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}
