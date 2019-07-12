package ru.tcivinskaya.comparisons;

import ru.tcivinskaya.shapes.Shape;

import java.util.Comparator;

public class SortByAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}


