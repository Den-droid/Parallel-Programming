package org.example.sequential_multiplication;

import org.example.common.Result;

public class SequentialMultiplication {
    private int[][] first;
    private int[][] second;
    private Result result;

    public SequentialMultiplication(int[][] first, int[][] second) {
        this.first = first;
        this.second = second;
        this.result = new Result(first.length, second[0].length);
    }

    public void multiply() {
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < second[i].length; j++) {
                int sum = 0;
                int columnFirst = 0;
                int rowSecond = 0;
                while (columnFirst != first[i].length &&
                        rowSecond != second.length) {
                    sum += first[i][columnFirst] * second[rowSecond][j];
                    columnFirst++;
                    rowSecond++;
                }
                this.result.setElement(i, j, sum);
            }
        }
    }
}
