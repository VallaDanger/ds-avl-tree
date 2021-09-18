package mx.chux.cs.ds.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public abstract class BinaryTree<E extends Comparable<E>> {

    protected int size;
    
    protected BinaryTree() {
        this.size = 0;
    }
    
    protected Node<E> root = null;

    public static final <E extends Comparable<E>> BalancedBinaryTree<E> newBalancedInstance() {
        return new AVLTree<>();
    }
    
    public final E getRoot() {
        return (this.root == null)? null : this.root.getValue();
    }
    
    public final BinaryTree<E> insert(E value) {

        this.root = insert(this.root, value);

        setParent(null, this.root);
        
        this.size += 1;

        return this;
    }
    
    public final int size() {
        return this.size;
    }
    
    public final int height() {
        return this.getHeight();
    }
    
    public final int getHeight() {
        return this.getHeight(this.root);
    }

    protected final int getHeight(final Node<?> node) {
        return (node == null) ? 0 : Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
    }

    protected final Node<E> setParent(final Node<E> parent, final Node<E> child) {
        if (child != null) {
            child.setParent(parent);
        }
        return parent;
    }

    public final void inOrder(final Consumer<Node<E>> visitor) {
        inOrder(this.root, visitor);
    }

    public final void preOrder(final Consumer<Node<E>> visitor) {
        preOrder(this.root, visitor);
    }

    public final void postOrder(final Consumer<Node<E>> visitor) {
        postOrder(this.root, visitor);
    }

    private void visit(final Consumer<Node<E>> visitor, final Node<E> node) {
        visitor.accept(node);
    }
    
    private void inOrder(final Node<E> node, final Consumer<Node<E>> visitor) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), visitor);
        visit(visitor, node);
        inOrder(node.getRight(), visitor);
    }

    private void preOrder(final Node<E> node, final Consumer<Node<E>> visitor) {
        if (node == null) {
            return;
        }
        visit(visitor, node);
        preOrder(node.getLeft(), visitor);
        preOrder(node.getRight(), visitor);
    }

    private void postOrder(final Node<E> node, final Consumer<Node<E>> visitor) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft(), visitor);
        postOrder(node.getRight(), visitor);
        visit(visitor, node);
    }
    
    public List<E> toList() {
        final List<E> list = new ArrayList<>(this.size);
        this.inOrder(node -> list.add(node.get()));
        return Collections.unmodifiableList(list);
    }

    protected abstract Node<E> insert(final Node<E> node, final E value);

}
