package ru.tcivinskaya.main;

import ru.tcivinskaya.comparisons.SortByArea;
import ru.tcivinskaya.comparisons.SortByPerimeter;
import ru.tcivinskaya.shape.Shape;
import ru.tcivinskaya.shapes.Circle;
import ru.tcivinskaya.shapes.Rectangle;
import ru.tcivinskaya.shapes.Square;
import ru.tcivinskaya.shapes.Triangle;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Square(10), new Square(5), new Triangle(1, 2, 1, 5, 3, 2), new Square(3), new Circle(1), new Rectangle(6, 10)};

        System.out.printf("Информация о фигуре с наибольшей площадью:%n%s%n", findMaximalArea(shapes));
        System.out.printf("Информация о фигуре со вторым по величине периметром:%n%s%n", findSecondLargestPerimeter(shapes));
    }

    private static String findMaximalArea(Shape[] shapes) {
        Shape[] sortedShapesArray = Arrays.copyOf(shapes, shapes.length);

        Arrays.sort(sortedShapesArray, new SortByArea());

        return sortedShapesArray[sortedShapesArray.length - 1].toString();
    }

    private static String findSecondLargestPerimeter(Shape[] shapes) {
        Shape[] sortedShapesArray = Arrays.copyOf(shapes, shapes.length);

        Arrays.sort(sortedShapesArray, new SortByPerimeter());

        return sortedShapesArray[sortedShapesArray.length - 2].toString();
    }
}
