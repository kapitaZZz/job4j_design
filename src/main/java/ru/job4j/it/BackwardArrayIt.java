package ru.job4j.it;

import java.util.NoSuchElementException;

public class BackwardArrayIt {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        this.point = data.length - 1;
    }

    public boolean hasNext() {
        return point >= 0;
    }

    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}