package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private Object[] container;
    private Object[] testContainer;
    private int size;

    private int modCount;

    public SimpleArrayList() {
        this.container = new Object[10];
    }

    public SimpleArrayList(int capacity) {
        this.container = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length <= size) {
            testContainer = Arrays.copyOf(container, container.length * 2);
            container = Arrays.copyOf(testContainer, testContainer.length);
            container[size++] = value;
        } else {
            container[size++] = value;
        }
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = (T) container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T oldValue = (T) container[index];
        System.arraycopy(container, index + 1, container,
                index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return oldValue;
    }

    @Override
    public Iterator<T> iterator() {
        int temModCount = modCount;
        return new Iterator<T>() {

            int valueIter;

            @Override
            public boolean hasNext() {
                return valueIter < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (temModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[valueIter++];
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArrayList<?> that = (SimpleArrayList<?>) o;
        return size == that.size && modCount == that.modCount && Arrays.equals(container, that.container);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, modCount);
        result = 31 * result + Arrays.hashCode(container);
        return result;
    }
}

