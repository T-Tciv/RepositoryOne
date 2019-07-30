package ru.tcivinskaya.matrix;

import ru.tcivinskaya.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixVectors;

    public Matrix(int n, int m) {
        if (n == 0 || m == 0) {
            throw new IllegalArgumentException("вы пытаетесь создать пустую матрицу");
        }

        matrixVectors = new Vector[m];
        for (int i = 0; i < m; ++i) {
            matrixVectors[i] = new Vector(n);
        }
    }

    public Matrix(Matrix otherMatrix) {
        int otherMatrixLength = otherMatrix.getRowsCount();
        this.matrixVectors = new Vector[otherMatrixLength];

        for (int i = 0; i < otherMatrixLength; ++i) {
            this.matrixVectors[i] = new Vector(otherMatrix.matrixVectors[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("вы пытаетесь создать пустую матрицу");
        }

        int maxRowLength = 0;
        int arrayLength = array.length;
        for (double[] element : array) {
            maxRowLength = Math.max(maxRowLength, element.length);
        }

        matrixVectors = new Vector[arrayLength];
        for (int i = 0; i < arrayLength; ++i) {
            this.matrixVectors[i] = new Vector(Arrays.copyOf(array[i], maxRowLength));
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

        this.matrixVectors = new Vector[vectorsArrayLength];
        for (int i = 0; i < vectorsArrayLength; ++i) {
            this.matrixVectors[i] = new Vector(maxRowLength).add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return matrixVectors.length;
    }

    public int getColumnsCount() {
        return matrixVectors[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        if (rowIndex >= getRowsCount() || rowIndex < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс вектора-строки матрицы");
        }

        return new Vector(matrixVectors[rowIndex]);
    }

    public void setRow(int rowIndex, Vector setVector) {
        if (rowIndex >= getRowsCount() || rowIndex < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс вектора-строки матрицы");
        }

        if (setVector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("длина устанавливаемого вектора не соответствует размерам матрицы");
        }

        this.matrixVectors[rowIndex] = new Vector(setVector);
    }

    public Vector getColumn(int columnIndex) {
        int vectorsArrayLength = getRowsCount();
        int columnsNumber = getColumnsCount();

        if (vectorsArrayLength == 0 || columnIndex >= columnsNumber || columnIndex < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс вектора-столбца матрицы");
        }

        Vector columnVector = new Vector(vectorsArrayLength);
        for (int i = 0; i < vectorsArrayLength; ++i) {
            columnVector.setComponent(i, matrixVectors[i].getComponent(columnIndex));
        }

        return columnVector;
    }

    public Matrix transpose() {
        int columnNumber = getColumnsCount();

        Vector[] transposedMatrixVectors = new Vector[columnNumber];

        for (int i = 0; i < columnNumber; ++i) {
            transposedMatrixVectors[i] = getColumn(i);
        }

        matrixVectors = transposedMatrixVectors;

        return this;
    }

    public Matrix multiplyByScalar(double scalar) {
        int vectorsArrayLength = getRowsCount();

        for (int i = 0; i < vectorsArrayLength; ++i) {
            setRow(i, getRow(i).makeScalarMultiplication(scalar));
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
                specialElement = triangularMatrix.matrixVectors[j].getComponent(i);

                if (Math.abs(specialElement) > measurementError) {
                    j = matrixSize;
                }
            }

            if (Math.abs(specialElement) > measurementError) {
                Vector newFirstRow = new Vector(triangularMatrix.matrixVectors[specialRowNumber]);
                triangularMatrix.matrixVectors[specialRowNumber] = new Vector(triangularMatrix.matrixVectors[i]);
                triangularMatrix.matrixVectors[i] = new Vector(newFirstRow);

                for (int j = i + 1; j < matrixSize; ++j) {
                    double thisRowMultiplier = -triangularMatrix.matrixVectors[j].getComponent(i) / specialElement;

                    for (int e = i; e < matrixSize; ++e) {
                        triangularMatrix.matrixVectors[j].setComponent(e, triangularMatrix.matrixVectors[j].getComponent(e) + triangularMatrix.matrixVectors[i].getComponent(e) * thisRowMultiplier);
                    }
                }
            }
        }

        for (int i = 0; i < matrixSize; ++i) {
            determinant *= triangularMatrix.matrixVectors[i].getComponent(i);
        }

        return determinant;
    }

    @Override
    public String toString() {
        String stringMatrix = Arrays.toString(matrixVectors);
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
                componentSum += matrixVectors[i].getComponent(j) * vectorColumn.getComponent(j);
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
            matrixVectors[i].add(otherMatrix.getRow(i));
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
            matrixVectors[i].subtract(otherMatrix.getRow(i));
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
        double componentSum;

        for (int i = 0; i < firstRowsCount; ++i) {
            for (int j = 0; j < secondColumnsCount; ++j) {
                componentSum = 0;

                for (int e = 0; e < commonNumber; ++e) {
                    componentSum += matrix1.matrixVectors[i].getComponent(e) * matrix2.matrixVectors[e].getComponent(j);
                }

                multiplicationMatrix.matrixVectors[i].setComponent(j, componentSum);
            }
        }

        return multiplicationMatrix;
    }
}
