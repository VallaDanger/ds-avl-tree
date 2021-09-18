package mx.chux.cs.ds.tree;

import java.util.function.Supplier;

public class Node<E extends Comparable<E>> implements Supplier<E> {

    private final E value;

    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;

    static <E extends Comparable<E>> Node<E> from(E value) {
        return new Node<>(value);
    }
    
    private Node(E value) {
        this.value = value;
    }

    E getValue() {
        return this.value;
    }
    
    @Override
    public E get() {
        return getValue();
    }

    @Override
    public String toString() {
        return "Node(" + this.value + ")";
    }

    Node<E> setParent(final Node<E> node) {
        this.parent = node;
        return this;
    }

    Node<E> getParent() {
        return this.parent;
    }

    Node<E> setLeft(final Node<E> node) {
        this.left = node;
        return this;
    }

    boolean hasLeft() {
        return this.left != null;
    }

    Node<E> getLeft() {
        return this.left;
    }

    Node<E> setRight(final Node<E> node) {
        this.right = node;
        return this;
    }

    Node<E> getRight() {
        return this.right;
    }

    boolean hasRight() {
        return this.right != null;
    }
    
    boolean isParent() {
        return hasLeft() || hasRight();
    }

    boolean isComplete() {
        return hasLeft() && hasRight();
    }
    
}
