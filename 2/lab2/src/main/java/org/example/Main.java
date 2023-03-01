package org.example;

import org.example.fox_multiplication.FoxMultiplication;
import org.example.stripped_multiplication.StrippedMultiplication;
import org.example.utils.MatrixUtil;

public class Main {
    public static void main(String[] args) {
//        testOne();
//        testThreadsCount();
        testMatrixSize();
    }

    public static void testOne() {
        int[][] first = MatrixUtil.generateMatrix(500, 500, 10, 99);
        int[][] second = MatrixUtil.generateMatrix(500, 500, 10, 99);
        StrippedMultiplication sm = new StrippedMultiplication(first, second, first.length);

        long start = System.currentTimeMillis();
        sm.multiply();
        long finish = System.currentTimeMillis();

        System.out.println(finish - start);

        FoxMultiplication fm = new FoxMultiplication(first, second, 16);

        start = System.currentTimeMillis();
        fm.multiply();
        finish = System.currentTimeMillis();

        System.out.println(finish - start);
    }

    public static void testThreadsCount() {
        int size = 500;
        int min = 10;
        int max = 99;

        int[] strippedThreadsCounts = {(int) (size * 0.01), (int) (size * 0.05),
                (int) (size * 0.1), (int) (size * 0.2), (int) (size * 0.5), size};
        int[] foxThreadsCounts = {5, 10, 15, 25, 50, 100};
        int[][] first = MatrixUtil.generateMatrix(size, size, min, max);
        int[][] second = MatrixUtil.generateMatrix(size, size, min, max);
        int experimentsCount = 4;

        for (int threadsCount : strippedThreadsCounts) {
            StrippedMultiplication stripped = new StrippedMultiplication(first, second, threadsCount);

            long experimentsTime = 0;
            for (int i = 0; i < experimentsCount; i++) {
                long start = System.currentTimeMillis();
                stripped.multiply();
                long finish = System.currentTimeMillis();
                experimentsTime += finish - start;
            }
            long average = experimentsTime / experimentsCount;

            System.out.println("Stripped (500x500): threads count - " + threadsCount +
                    "; time - " + average + " ms;");
        }

        System.out.println();

        for (int threadsCount : foxThreadsCounts) {
            FoxMultiplication fox = new FoxMultiplication(first, second, threadsCount);

            long experimentsTime = 0;
            for (int i = 0; i < experimentsCount; i++) {
                long start = System.currentTimeMillis();
                fox.multiply();
                long finish = System.currentTimeMillis();

                fox.resetResult();
                experimentsTime += finish - start;
            }
            long average = experimentsTime / experimentsCount;

            System.out.println("Fox (500x500): threads count - " + threadsCount +
                    "; time - " + average + " ms;");
        }
    }

    public static void testMatrixSize() {
        int min = 10;
        int max = 99;

        int[] sizes = {500, 1000, 1500, 2000};
        int experimentsCount = 4;
        int threadsCount;

        for (int matrixSize : sizes) {
            int[][] first = MatrixUtil.generateMatrix(matrixSize, matrixSize, min, max);
            int[][] second = MatrixUtil.generateMatrix(matrixSize, matrixSize, min, max);
            threadsCount = first.length;

            StrippedMultiplication stripped = new StrippedMultiplication(first, second, threadsCount);

            long experimentsTime = 0;
            for (int i = 0; i < experimentsCount; i++) {
                long start = System.currentTimeMillis();
                stripped.multiply();
                long finish = System.currentTimeMillis();
                experimentsTime += finish - start;
            }
            long average = experimentsTime / experimentsCount;

            System.out.println("Stripped: matrix size - " + matrixSize +
                    "; time - " + average + " ms;");

            threadsCount = 16;
            FoxMultiplication fox = new FoxMultiplication(first, second, threadsCount);

            experimentsTime = 0;
            for (int i = 0; i < experimentsCount; i++) {
                long start = System.currentTimeMillis();
                fox.multiply();
                long finish = System.currentTimeMillis();

                fox.resetResult();
                experimentsTime += finish - start;
            }
            average = experimentsTime / experimentsCount;

            System.out.println("Fox: matrix size - " + matrixSize +
                    "; time - " + average + " ms;");
            System.out.println();
        }
    }
}