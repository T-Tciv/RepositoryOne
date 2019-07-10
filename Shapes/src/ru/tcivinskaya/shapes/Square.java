package ru.tcivinskaya.shapes;

import ru.tcivinskaya.shape.Shape;

public class Square implements Shape {
    private double length;

    public Square(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return length;
    }

    public double getHeight() {
        return length;
    }

    public double getArea() {
        return length * length;
    }

    public double getPerimeter() {
        return length * 4;
    }

    @Override
    public String toString() {
        return "Квадрат, длина стороны = " + length;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(length);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Square comparedSquare = (Square) object;

        return length == comparedSquare.length;
    }
}
