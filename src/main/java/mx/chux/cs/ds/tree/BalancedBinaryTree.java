package mx.chux.cs.ds.tree;

public abstract class BalancedBinaryTree<E extends Comparable<E>> extends BinaryTree<E> {
    
    protected BalancedBinaryTree() {}
    
    public static final <E extends Comparable<E>> BalancedBinaryTree<E> newInstance() {
        return new AVLTree<E>();
    }
    
    public final int getBalance() {
        return this.getBalance(this.root);
    }

    protected final int getBalance(final Node<E> node) {
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }
    
    protected abstract Node<E> balance(final Node<E> node, final int balance, final E value);

}
