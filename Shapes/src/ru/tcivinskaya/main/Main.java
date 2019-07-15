package ru.tcivinskaya.main;

import ru.tcivinskaya.comparisons.SortByAreaComparator;
import ru.tcivinskaya.comparisons.SortByPerimeterComparator;
import ru.tcivinskaya.shapes.Shape;
import ru.tcivinskaya.shapes.Circle;
import ru.tcivinskaya.shapes.Rectangle;
import ru.tcivinskaya.shapes.Square;
import ru.tcivinskaya.shapes.Triangle;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Square(10), new Square(5), new Triangle(1, 2, 1, 5, 3, 2), new Square(3), new Circle(1), new Rectangle(6, 10)};

        System.out.printf("Информация о фигуре с наибольшей площадью:%n%s%n", findMaximalAreaShape(shapes));
        System.out.printf("Информация о фигуре со вторым по величине периметром:%n%s%n", findSecondLargestPerimeterShape(shapes));
    }

    private static Shape findMaximalAreaShape(Shape[] shapes) {
        Shape[] sortedShapesArray = Arrays.copyOf(shapes, shapes.length);

        Arrays.sort(sortedShapesArray, new SortByAreaComparator());

        return sortedShapesArray[sortedShapesArray.length - 1];
    }

    private static Shape findSecondLargestPerimeterShape(Shape[] shapes) {
        Shape[] sortedShapesArray = Arrays.copyOf(shapes, shapes.length);

        Arrays.sort(sortedShapesArray, new SortByPerimeterComparator());

        return sortedShapesArray[sortedShapesArray.length - 2];
    }
}
