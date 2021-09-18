package mx.chux.cs.ds.tree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class AVLTreeTest {

    private static BalancedBinaryTree<Integer> tree;
    
    @BeforeClass
    public static void beforeClass() {
        
        final List<Integer> values = Arrays
                .stream(new int[] { 1,2,3,4,5,6,7,8,9,10 })
                .boxed().collect(Collectors.toList());
        
        tree = BinaryTree.newBalancedInstance();

        for( final Integer value : values ) {
            tree.insert(value);
        }
        
    }
    
    @Test
    public void rootTest() {
        assertThat(tree.getRoot()).isEqualTo(Integer.valueOf(5));
    }
    
    @Test
    public void sizeTest() {
        assertThat(tree.size()).isEqualTo(10);
    }
    
    @Test
    public void heightTest() {
        assertThat(tree.getHeight()).isEqualTo(5);
    }
    @Test
    public void balanceTest() {
        assertThat(tree.getBalance()).isEqualTo(-1);
    }
    
    @Test
    public void inOrderTest() {
        assertThat(tree.toList()).containsExactly(1,2,3,4,5,6,7,8,9,10);
    }
    
}
