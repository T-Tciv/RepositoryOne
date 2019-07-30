package ru.tcivinskaya.main;

import ru.tcivinskaya.matrix.Matrix;
import ru.tcivinskaya.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(2, 2);
        Matrix matrix1 = new Matrix(new double[][]{{0, 2}, {3, 4}});

        Matrix matrix2 = new Matrix(new Vector[]{new Vector(new double[]{5, 6}), new Vector(new double[]{7, 8})});
        Matrix matrix3 = new Matrix(matrix2);

        System.out.println("Получение размеров матрицы");
        System.out.println(matrix);
        System.out.println(matrix.getRowsCount());
        System.out.println(matrix.getColumnsCount());

        //Задание вектора-строки матрицы
        matrix.setRow(0, new Vector(new double[]{9, 10}));

        System.out.println("Получение вектора-строки матрицы");
        System.out.println(matrix);
        System.out.println(matrix.getRow(0));

        System.out.println("Получение вектора-столбца матрицы");
        System.out.println(matrix);
        System.out.println(matrix.getColumn(0));

        System.out.println("Транспонирование матрицы");
        matrix = new Matrix(matrix1);
        System.out.println(matrix);
        System.out.println(matrix.transpose());

        System.out.println("Умножение матрицы на скаляр");
        matrix = new Matrix(matrix1);
        System.out.println(matrix);
        System.out.println(matrix.multiplyByScalar(-2));

        System.out.println("Получение определителя матрицы");
        matrix = new Matrix(new double[][]{{2, 1, 2}, {0, -1, 3}, {-6, 5, 4}});
        System.out.println(matrix);
        System.out.println(matrix.getDeterminant());

        matrix = new Matrix(new double[][]{{10, 2}, {1.5, 4}});
        System.out.println(matrix);
        System.out.println(matrix.getDeterminant());

        System.out.println("Умножение матрицы на вектор-столбец");
        matrix = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Vector vectorColumn = new Vector(new double[]{5, 6});
        System.out.println(matrix);
        System.out.println(vectorColumn);
        System.out.println(matrix.makeVectorColumnMultiplication(vectorColumn));

        System.out.println("Сложение матриц (два метода)");
        matrix = new Matrix(new double[][]{{1, 2}, {3, 4}});
        matrix1 = new Matrix(new double[][]{{5, 6}, {7, 8}});
        System.out.println(matrix);
        System.out.println(matrix1);
        System.out.println(Matrix.sumMatrix(matrix, matrix1));
        System.out.println(matrix.sumMatrix(matrix1));

        System.out.println("Вычитание матриц (два метода)");
        matrix = new Matrix(new double[][]{{1, 2}, {3, 4}});
        matrix1 = new Matrix(new double[][]{{5, 6}, {7, 8}});
        System.out.println(matrix);
        System.out.println(matrix1);
        System.out.println(Matrix.subtractMatrix(matrix, matrix1));
        System.out.println(matrix.subtractMatrix(matrix1));

        System.out.println("Умножение матриц");
        matrix = new Matrix(new double[][]{{1, 2}, {3, 4}});
        matrix1 = new Matrix(new double[][]{{5, 6}, {7, 8}});
        System.out.println(matrix);
        System.out.println(matrix1);
        System.out.println(Matrix.makeMatrixMultiplication(matrix, matrix1));
    }
}
