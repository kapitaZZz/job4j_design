package ru.job4j.collection.list;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
    }

    public int getSize() {
        return linked.getSize();
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}
