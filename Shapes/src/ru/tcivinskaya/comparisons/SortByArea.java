package ru.tcivinskaya.comparisons;

import ru.tcivinskaya.shape.Shape;

import java.util.Comparator;

public class SortByArea implements Comparator<Shape> {
    public int compare(Shape firstShape, Shape secondShape) {
        return Double.compare(firstShape.getArea(), secondShape.getArea());
    }
}
