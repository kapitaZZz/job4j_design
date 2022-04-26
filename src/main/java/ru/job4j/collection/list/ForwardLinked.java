package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int length;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            length++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        length++;
        tail.next = node;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        length++;
    }

    public int getSize() {
        return length;
    }

    public boolean revert() {
        boolean flag = true;
        if (length == 0 || length == 1) {
            flag = false;
        }
        Node<T> current = head;
        Node<T> goal = null;

        while (current != null) {
            Node<T> next = current.next;
            current.next = goal;
            goal = current;
            current = next;
        }
        head = goal;
        return flag;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T first = head.value;
        Node<T> node = head;
        head = head.next;
        node.next = null;
        node.value = null;
        length--;
        return first;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}

