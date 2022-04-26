package ru.job4j.collection.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T temp;
        if (in.getSize() == 0 && out.getSize() == 0) {
            throw new NoSuchElementException();
        }
        if (out.getSize() <= 0) {
            while (in.getSize() != 0) {
                out.push(in.pop());
            }
        }
        temp = out.pop();
        return temp;
    }

    public void push(T value) {
        in.push(value);
    }
}