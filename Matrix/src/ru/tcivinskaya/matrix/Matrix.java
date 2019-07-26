package ru.tcivinskaya.matrix;

import ru.tcivinskaya.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int n, int m) {
        vectors = new Vector[m];
        for (int i = 0; i < m; ++i) {
            vectors[i] = new Vector(n);
        }
    }

    public Matrix(Matrix otherMatrix) {
        int otherMatrixLength = otherMatrix.getLineCount();
        this.vectors = new Vector[otherMatrixLength];

        for (int i = 0; i < otherMatrixLength; ++i) {
            this.vectors[i] = new Vector(otherMatrix.vectors[i]);
        }
    }

    public Matrix(double[][] array) {
        int maxLineLength = 0;
        int arrayLength = array.length;
        for (double[] element : array) {
            maxLineLength = Math.max(maxLineLength, element.length);
        }

        vectors = new Vector[arrayLength];
        for (int i = 0; i < arrayLength; ++i) {
            this.vectors[i] = new Vector(Arrays.copyOf(array[i], maxLineLength));
        }
    }

    public Matrix(Vector[] vectors) {
        int maxLineLength = 0;
        int vectorsArrayLength = vectors.length;
        for (Vector vector : vectors) {
            maxLineLength = Math.max(maxLineLength, vector.getSize());
        }

        this.vectors = new Vector[vectorsArrayLength];
        for (int i = 0; i < vectorsArrayLength; ++i) {
            this.vectors[i] = new Vector(maxLineLength).add(vectors[i]);
        }
    }

    public int getLineCount() {
        return vectors.length;
    }

    public int getColumnCount() {
        if (getLineCount() == 0) {
            return 0;
        }

        return vectors[0].getSize();
    }

    public Vector getVectorsLine(int lineIndex) {
        if (lineIndex >= getLineCount() || lineIndex < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс вектора-строки матрицы");
        }

        return new Vector(vectors[lineIndex]);
    }

    public void setVectorsLine(int lineIndex, Vector setVector) {
        if (lineIndex >= getLineCount() || lineIndex < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс вектора-строки матрицы");
        }

        if (setVector.getSize() != getColumnCount()) {
            throw new IllegalArgumentException("длина устанавливаемого вектора не соответствует размерам матрицы");
        }

        this.vectors[lineIndex] = new Vector(setVector);
    }

    public Vector getVectorsColumn(int columnIndex) {
        int vectorsArrayLength = getLineCount();
        int columnNumber = getColumnCount();

        if (vectorsArrayLength == 0 || columnIndex >= columnNumber || columnIndex < 0) {
            throw new IndexOutOfBoundsException("неверно введён индекс вектора-столбца матрицы");
        }

        Vector columnVector = new Vector(vectorsArrayLength);
        for (int i = 0; i < vectorsArrayLength; ++i) {
            columnVector.setComponent(i, vectors[i].getComponent(columnIndex));
        }

        return columnVector;
    }

    public Matrix transpose() {
        int columnNumber = getColumnCount();

        if (columnNumber == 0) {
            throw new IllegalArgumentException("вы пытаетесь транспонировать \"пустую\" матрицу");
        }

        Vector[] transposedMatrixVectors = new Vector[columnNumber];

        for (int i = 0; i < columnNumber; ++i) {
            transposedMatrixVectors[i] = getVectorsColumn(i);
        }

        vectors = Arrays.copyOf(transposedMatrixVectors, columnNumber);

        return this;
    }

    public Matrix makeScalarMultiplication(double scalar) {
        int vectorsArrayLength = getLineCount();

        for (int i = 0; i < vectorsArrayLength; ++i) {
            setVectorsLine(i, getVectorsLine(i).makeScalarMultiplication(scalar));
        }

        return this;
    }

    public double getDeterminant() {
        if (getColumnCount() == 0) {
            throw new IllegalArgumentException("вы ищете определитель пустой матрицы");
        }

        if (getLineCount() != getColumnCount()) {
            throw new IllegalArgumentException("определитель считается только для квадратных матриц");
        }

        int matrixSize = getColumnCount();

        double determinant = 1;
        Matrix triangularMatrix = new Matrix(this);

        for (int i = 0; i < matrixSize - 1; ++i) {
            double specialElement = 0;
            int specialLineNumber = 0;

            for (int j = i; j < matrixSize; ++j) {
                specialLineNumber = j;
                specialElement = triangularMatrix.vectors[j].getComponent(i);

                if (specialElement != 0) {
                    j = matrixSize;
                }
            }

            if (specialElement != 0) {
                Vector newFirstLine = new Vector(triangularMatrix.vectors[specialLineNumber]);
                triangularMatrix.vectors[specialLineNumber] = new Vector(triangularMatrix.vectors[i]);
                triangularMatrix.vectors[i] = new Vector(newFirstLine);

                for (int j = i + 1; j < matrixSize; ++j) {
                    double thisLineMultiplier = -triangularMatrix.vectors[j].getComponent(i) / specialElement;


                    for (int e = i; e < matrixSize; ++e) {
                        triangularMatrix.vectors[j].setComponent(e, triangularMatrix.vectors[j].getComponent(e) + triangularMatrix.vectors[i].getComponent(e) * thisLineMultiplier);
                    }
                }
            }
        }

        for (int i = 0; i < matrixSize; ++i) {
            determinant *= triangularMatrix.vectors[i].getComponent(i);
        }

        return determinant;
    }

    @Override
    public String toString() {
        String stringMatrix = Arrays.toString(vectors);
        return "{ " + stringMatrix.substring(1, stringMatrix.length() - 1) + " }";
    }

    public Matrix makeVectorColumnMultiplication(Vector vectorColumn) {
        int vectorsArrayLength = getLineCount();
        int columnNumber = getColumnCount();

        if (columnNumber != vectorColumn.getSize()) {
            throw new IllegalArgumentException("при умножении матрицы на вектор-столбец, кол-во столбцов матрицы должно совпадать с кол-вом строк вектора");
        }

        Vector[] vectorSum = new Vector[vectorsArrayLength];

        for (int i = 0; i < vectorsArrayLength; ++i) {
            double componentSum = 0;

            for (int j = 0; j < columnNumber; ++j) {
                componentSum += vectors[i].getComponent(j) * vectorColumn.getComponent(j);
            }

            vectorSum[i] = new Vector(new double[]{componentSum});
        }

        vectors = Arrays.copyOf(vectorSum, vectorsArrayLength);

        return this;
    }


    public Matrix makeVectorLineMultiplication(Vector vectorLine) {
        int vectorsArrayLength = getLineCount();

        if (getColumnCount() != 1) {
            throw new IllegalArgumentException("при умножении матрицы на вектор-строку, умножаемая матрица должна быть вектором-столбцом");
        }

        if (vectorsArrayLength != vectorLine.getSize()) {
            throw new IllegalArgumentException("при умножении матрицы на вектор-строку, кол-во строк матрицы должно совпадать с кол-вом столбцов вектора-строки");
        }

        Vector[] matrixLineVector = new Vector[vectorsArrayLength];

        for (int i = 0; i < vectorsArrayLength; ++i) {
            matrixLineVector[i] = new Vector(vectorsArrayLength);

            for (int j = 0; j < vectorsArrayLength; ++j) {
                matrixLineVector[i].setComponent(j, vectors[i].getComponent(0) * vectorLine.getComponent(j));
            }
        }

        vectors = Arrays.copyOf(matrixLineVector, vectorsArrayLength);
        return this;
    }

    public Matrix sumMatrix(Matrix otherMatrix) {
        int lineNumber = getLineCount();
        int columnNumber = getColumnCount();

        if (!(lineNumber == otherMatrix.getLineCount() && columnNumber == otherMatrix.getColumnCount())) {
            throw new IllegalArgumentException("при сложении матрицы должны быть одного размера");
        }

        for (int i = 0; i < lineNumber; ++i) {
            vectors[i].add(otherMatrix.getVectorsLine(i));
        }

        return this;
    }

    public Matrix subtractMatrix(Matrix otherMatrix) {
        int lineNumber = getLineCount();
        int columnNumber = getColumnCount();

        if (!(lineNumber == otherMatrix.getLineCount() && columnNumber == otherMatrix.getColumnCount())) {
            throw new IllegalArgumentException("при вычитании матрицы должны быть одного размера");
        }

        for (int i = 0; i < lineNumber; ++i) {
            vectors[i].subtract(otherMatrix.getVectorsLine(i));
        }

        return this;
    }

    public static Matrix sumMatrix(Matrix matrix1, Matrix matrix2) {
        Matrix sumMatrix = new Matrix(matrix1);

        return sumMatrix.sumMatrix(matrix2);
    }

    public static Matrix subtractMatrix(Matrix matrix1, Matrix matrix2) {
        Matrix subtractMatrix = new Matrix(matrix1);

        return subtractMatrix.subtractMatrix(matrix2);
    }

    public static Matrix makeMatrixMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getLineCount()) {
            throw new IllegalArgumentException("при умножении кол-во столбцов в первой матрице должно равняться кол-ву строк во второй");
        }

        int firstLineCount = matrix1.getLineCount();
        int secondColumnCount = matrix2.getColumnCount();
        int commonNumber = matrix1.getColumnCount();

        Matrix multiplicationMatrix = new Matrix(secondColumnCount, firstLineCount);
        double componentSum;

        for (int i = 0; i < firstLineCount; ++i) {
            for (int j = 0; j < secondColumnCount; ++j) {
                componentSum = 0;

                for (int e = 0; e < commonNumber; ++e) {
                    componentSum += matrix1.vectors[i].getComponent(e) * matrix2.vectors[e].getComponent(j);
                }
                multiplicationMatrix.vectors[i].setComponent(j, componentSum);
            }
        }

        return multiplicationMatrix;
    }
}
