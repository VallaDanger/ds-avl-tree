package mx.chux.cs.ds;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import mx.chux.cs.ds.tree.BalancedBinaryTree;
import mx.chux.cs.ds.tree.BinaryTree;
import mx.chux.cs.ds.tree.Node;

public class App {

    public static void main(String[] args) {

        final List<Integer> values = Arrays.stream(new int[] { 1,2,3,4,5,6,7,8,9,10 })
                .boxed().collect(Collectors.toList());

        final BalancedBinaryTree<Integer> tree = BinaryTree.newBalancedInstance();

        for( final Integer value : values ) {
            tree.insert(value);
        }

        System.out.println("- root is: " + tree.getRoot());
        System.out.println("- height is: " + tree.getHeight());
        System.out.println("- balance is: " + tree.getBalance());
        
        final Consumer<Node<Integer>> visitor = (node) -> {
            System.out.println(node.get());
        };
        
        System.out.println("----------------------------------");
        System.out.println("in-order:");
        tree.inOrder(visitor);
        
        System.out.println("----------------------------------");
        System.out.println("pre-order:");
        tree.preOrder(visitor);
        
        System.out.println("----------------------------------");
        System.out.println("post-order:");
        tree.postOrder(visitor);

    }

}
