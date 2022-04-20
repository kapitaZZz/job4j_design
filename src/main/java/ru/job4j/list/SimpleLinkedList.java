package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            return first.item;
        } else if (index + 1 == size) {
            return last.item;
        }
        Node<E> rsl;
        if (index < size / 2) {
            rsl = last;
            for (int i = size - 1; i > index; i--) {
                rsl = rsl.prev;
            }
        } else {
            rsl = first;
            for (int i = 0; i < index; i++) {
                rsl = rsl.next;
            }
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private Node<E> cursor = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = cursor.item;
                cursor = cursor.next;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
