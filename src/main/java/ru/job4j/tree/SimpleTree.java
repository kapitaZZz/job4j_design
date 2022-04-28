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
        Optional<Node<E>> rsl;
        boolean flag = false;
        Predicate<Node<E>> tempChild = ch -> ch.children.contains(new Node<>(child));
        Predicate<Node<E>> predicateChild = pr -> pr.value.equals(parent);
        if (findByPredicate(tempChild).isEmpty()) {
            rsl = findByPredicate(predicateChild);
            if (rsl.isPresent()) {
                rsl.get().children.add(new Node<>(child));
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> predicate = ch -> !ch.children.isEmpty() && ch.children.size() > 2;
        Optional<Node<E>> rsl = findByPredicate(predicate);
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
        List<Node<E>> list = new LinkedList<>();
        list.add(root);
        Node<E> node;
        while (!list.isEmpty()) {
            node = list.remove(0);
            if (condition.test(node)) {
                rsl = Optional.of(node);
                break;
            }
            list.addAll(node.children);
        }
        return rsl;
    }
}
