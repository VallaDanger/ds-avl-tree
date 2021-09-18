package mx.chux.cs.ds.tree;

final class AVLTree<E extends Comparable<E>> extends BalancedBinaryTree<E> {

    AVLTree() {}
    
    @Override
    protected Node<E> insert(final Node<E> node, final E value) {

        if (node == null) {
            return Node.from(value);
        }

        if (value.compareTo(node.get()) > 0) {
            node.setRight(insert(node.getRight(), value));
            setParent(node, node.getRight());
        } else {
            node.setLeft(insert(node.getLeft(), value));
            setParent(node, node.getLeft());
        }

        int balance = getBalance(this.root);

        if (Math.abs(balance) > 1) {
            return balance(node, balance, value);
        }

        return node;
    }

    @Override
    protected Node<E> balance(final Node<E> node, final int balance, final E value) {

        final int left = node.hasLeft() ? value.compareTo(node.getLeft().get()) : 0;
        final int right = node.hasRight() ? value.compareTo(node.getRight().get()) : 0;

        // if tree is left-heavy and
        // the value is smaller than left child
        if ((balance > 1) && (left < 0)) {
            // should rotate right
            return rotateRight(node);
        }

        // if the tree is right-heavy and
        // the value is greater than right child
        if ((balance < -1) && (right > 0)) {
            // should rotate left
            return rotateLeft(node);
        }

        if ((balance > 1) && (left > 0)) {
            // rotate right after rotating left
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        if ((balance < -1) && (right < 0)) {
            // rotate left after rotating right
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    private Node<E> rotateRight(final Node<E> node) {
        final Node<E> left = node.getLeft();
        final Node<E> leftRight = left.getRight();

        // node will be son of its left child
        setParent(node.getParent(), left);
        left.setRight(node);
        setParent(left, node);

        // right subtree of node's left child
        // will hang below node's left branch
        node.setLeft(leftRight);
        setParent(node, leftRight);

        return left;
    }

    private Node<E> rotateLeft(final Node<E> node) {
        final Node<E> right = node.getRight();
        final Node<E> rightLeft = right.getLeft();

        setParent(node.getParent(), right);
        right.setLeft(node);
        setParent(right, node);

        node.setRight(rightLeft);
        setParent(node, rightLeft);

        return right;
    }

}
