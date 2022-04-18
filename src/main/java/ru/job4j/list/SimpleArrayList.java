package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private Object[] container;

    private int size;

    private int modCount;

    private int pointer;

    public SimpleArrayList() {
        this.container = new Object[10];
    }

    public SimpleArrayList(int capacity) {
        this.container = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (pointer == container.length - 1) {
            resize(container.length * 2);
            container[pointer++] = value;
        }
    }

    private void resize(int newLength) {
        Object[] newArr = new Object[newLength];
        System.arraycopy(container, 0, newArr, 0, pointer);
        container = newArr;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = (T) container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T curr = (T) container[index];
        for (int i = index; i < pointer; i++) {
            container[i] = container[i + 1];
        }
        container[pointer] = null;
        pointer--;
        return curr;
    }

    @Override
    public T get(int index) {
//        Objects.checkIndex(index, size);
        if (index <= 0 || index > size()) {
            throw new IllegalArgumentException();
        }
        return (T) container[index];
    }

    @Override
    public int size() {
        return container.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final Object[] data = container;
            private final int tempModificateCount = modCount;
            private final int tempSize = size;
            private int tempPoint;

            @Override
            public boolean hasNext() {
                if (tempModificateCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < tempSize;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new ConcurrentModificationException();
                }
                return (T) data[tempPoint++];
            }
        };
    }
}

