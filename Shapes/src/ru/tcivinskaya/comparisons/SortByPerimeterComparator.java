package ru.tcivinskaya.comparisons;

import ru.tcivinskaya.shapes.Shape;

import java.util.Comparator;

public class SortByPerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}
