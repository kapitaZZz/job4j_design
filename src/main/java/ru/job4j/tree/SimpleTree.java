package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean flag = false;
        if (findBy(child).isEmpty()) {
            Node<E> rsl = findBy(parent).orElse(null);
            flag = rsl.children.add(new Node<>(child));
        }
        return flag;
    }

    @Override
    public boolean isBinary() {
        Optional<Node<E>> rsl = findByPredicate(ch -> ch.children.size() > 2);
        return rsl.isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl;
        Predicate<Node<E>> pred = node -> node.value.equals(value);
        rsl = findByPredicate(pred);
        return rsl;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (condition.test(node)) {
                rsl = Optional.of(node);
                break;
            }
            queue.addAll(node.children);
        }
        return rsl;
    }
}

