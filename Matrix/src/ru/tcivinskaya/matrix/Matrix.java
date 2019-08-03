package ru.tcivinskaya.matrix;

import ru.tcivinskaya.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int columnsCount, int rowsCount) {
        if (columnsCount == 0 || rowsCount == 0) {
            throw new IllegalArgumentException("вы пытаетесь создать пустую матрицу");
        }

        rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix otherMatrix) {
        int otherMatrixLength = otherMatrix.getRowsCount();
        this.rows = new Vector[otherMatrixLength];

        for (int i = 0; i < otherMatrixLength; ++i) {
            this.rows[i] = new Vector(otherMatrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("вы пытаетесь создать пустую матрицу");
        }

        int maxRowLength = 0;
        int arrayLength = array.length;
        for (double[] element : array) {
            maxRowLength = Math.max(maxRowLength, element.length);
        }

        if (maxRowLength == 0) {
            throw new IllegalArgumentException("вы пытаетесь создать пустую матрицу");
        }

        rows = new Vector[arrayLength];
        for (int i = 0; i < arrayLength; ++i) {
            this.rows[i] = new Vector(Arrays.copyOf(array[i], maxRowLength));
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("вы пытаетесь создать пустую матрицу");
        }
        int maxRowLength = 0;
        int vectorsArrayLength = vectors.length;
        for (Vector vector : vectors) {
            maxRowLength = Math.max(maxRowLength, vector.getSize());
        }

        this.rows = new Vector[vectorsArrayLength];
        for (int i = 0; i < vectorsArrayLength; ++i) {
            this.rows[i] = new Vector(maxRowLength).add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        if (rowIndex >= getRowsCount() || rowIndex < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс вектора-строки матрицы");
        }

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector setVector) {
        if (rowIndex >= getRowsCount() || rowIndex < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс вектора-строки матрицы");
        }

        if (setVector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("длина устанавливаемого вектора не соответствует размерам матрицы");
        }

        this.rows[rowIndex] = new Vector(setVector);
    }

    public Vector getColumn(int columnIndex) {
        int vectorsArrayLength = getRowsCount();
        int columnsNumber = getColumnsCount();

        if (vectorsArrayLength == 0 || columnIndex >= columnsNumber || columnIndex < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс вектора-столбца матрицы");
        }

        Vector columnVector = new Vector(vectorsArrayLength);
        for (int i = 0; i < vectorsArrayLength; ++i) {
            columnVector.setComponent(i, rows[i].getComponent(columnIndex));
        }

        return columnVector;
    }

    public Matrix transpose() {
        int columnNumber = getColumnsCount();

        Vector[] transposedMatrixRows = new Vector[columnNumber];

        for (int i = 0; i < columnNumber; ++i) {
            transposedMatrixRows[i] = getColumn(i);
        }

        rows = transposedMatrixRows;

        return this;
    }

    public Matrix multiplyByScalar(double scalar) {
        int vectorsArrayLength = getRowsCount();

        for (int i = 0; i < vectorsArrayLength; ++i) {
            rows[i].multiplyByScalar(scalar);
        }

        return this;
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("определитель считается только для квадратных матриц");
        }

        int matrixSize = getColumnsCount();

        double determinant = 1;
        double measurementError = 1.0e-10;

        Matrix triangularMatrix = new Matrix(this);

        for (int i = 0; i < matrixSize - 1; ++i) {
            double specialElement = 0;
            int specialRowNumber = 0;

            for (int j = i; j < matrixSize; ++j) {
                specialRowNumber = j;
                specialElement = triangularMatrix.rows[j].getComponent(i);

                if (Math.abs(specialElement) > measurementError) {
                    j = matrixSize;
                }
            }

            if (Math.abs(specialElement) > measurementError) {
                Vector newFirstRow = new Vector(triangularMatrix.rows[specialRowNumber]);
                triangularMatrix.rows[specialRowNumber] = new Vector(triangularMatrix.rows[i]);
                triangularMatrix.rows[i] = new Vector(newFirstRow);

                for (int j = i + 1; j < matrixSize; ++j) {
                    double thisRowMultiplier = -triangularMatrix.rows[j].getComponent(i) / specialElement;

                    for (int e = i; e < matrixSize; ++e) {
                        triangularMatrix.rows[j].setComponent(e, triangularMatrix.rows[j].getComponent(e) + triangularMatrix.rows[i].getComponent(e) * thisRowMultiplier);
                    }
                }
            }
        }

        for (int i = 0; i < matrixSize; ++i) {
            determinant *= triangularMatrix.rows[i].getComponent(i);
        }

        return determinant;
    }

    @Override
    public String toString() {
        String stringMatrix = Arrays.toString(rows);
        return "{ " + stringMatrix.substring(1, stringMatrix.length() - 1) + " }";
    }

    public Vector makeVectorColumnMultiplication(Vector vectorColumn) {
        int vectorsArrayLength = getRowsCount();
        int columnsNumber = getColumnsCount();

        if (columnsNumber != vectorColumn.getSize()) {
            throw new IllegalArgumentException("при умножении матрицы на вектор-столбец, кол-во столбцов матрицы должно совпадать с кол-вом строк вектора");
        }

        Vector vectorSum = new Vector(vectorsArrayLength);

        for (int i = 0; i < vectorsArrayLength; ++i) {
            double componentSum = 0;

            for (int j = 0; j < columnsNumber; ++j) {
                componentSum += rows[i].getComponent(j) * vectorColumn.getComponent(j);
            }

            vectorSum.setComponent(i, componentSum);
        }

        return vectorSum;
    }

    public Matrix sumMatrix(Matrix otherMatrix) {
        int rowsNumber = getRowsCount();
        int columnsNumber = getColumnsCount();

        if (rowsNumber != otherMatrix.getRowsCount() || columnsNumber != otherMatrix.getColumnsCount()) {
            throw new IllegalArgumentException("при сложении матрицы должны быть одного размера");
        }

        for (int i = 0; i < rowsNumber; ++i) {
            rows[i].add(otherMatrix.rows[i]);
        }

        return this;
    }

    public Matrix subtractMatrix(Matrix otherMatrix) {
        int rowsNumber = getRowsCount();
        int columnsNumber = getColumnsCount();

        if (rowsNumber != otherMatrix.getRowsCount() || columnsNumber != otherMatrix.getColumnsCount()) {
            throw new IllegalArgumentException("при вычитании матрицы должны быть одного размера");
        }

        for (int i = 0; i < rowsNumber; ++i) {
            rows[i].subtract(otherMatrix.rows[i]);
        }

        return this;
    }

    public static Matrix sumMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("при сложении матрицы должны быть одного размера");
        }

        Matrix sumMatrix = new Matrix(matrix1);

        return sumMatrix.sumMatrix(matrix2);
    }

    public static Matrix subtractMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("при вычитании матрицы должны быть одного размера");
        }

        Matrix subtractMatrix = new Matrix(matrix1);

        return subtractMatrix.subtractMatrix(matrix2);
    }

    public static Matrix makeMatrixMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("при умножении кол-во столбцов в первой матрице должно равняться кол-ву строк во второй");
        }

        int firstRowsCount = matrix1.getRowsCount();
        int secondColumnsCount = matrix2.getColumnsCount();
        int commonNumber = matrix1.getColumnsCount();

        Matrix multiplicationMatrix = new Matrix(secondColumnsCount, firstRowsCount);
        for (int i = 0; i < firstRowsCount; ++i) {
            for (int j = 0; j < secondColumnsCount; ++j) {
                double componentSum = 0;

                for (int k = 0; k < commonNumber; ++k) {
                    componentSum += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }

                multiplicationMatrix.rows[i].setComponent(j, componentSum);
            }
        }

        return multiplicationMatrix;
    }
}
