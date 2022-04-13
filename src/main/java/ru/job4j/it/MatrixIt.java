package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
        this.row = 0;
        this.column = 0;
    }

    @Override
    public boolean hasNext() {
        if (column >= data[row].length) {
            column++;
            while (
                    row < data.length
                            && column >= data[row].length
            ) {
                column = 0;
                row++;
            }
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = data[row][column];
        column++;
        while (
                row < data.length
                        && column >= data[row].length
        ) {
            column = 0;
            row++;
        }
        return result;
    }
}
