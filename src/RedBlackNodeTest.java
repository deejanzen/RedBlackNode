import org.testng.annotations.Test;

import static org.junit.Assert.*;

/**
 * Created by djanzen on 4/8/16.
 */
public class RedBlackNodeTest {
    @Test
    public void testbuildFrom234(){
        String [] onekeys = {"one" };
        String [] twokeys = {"one" , "Two"};
        String [] threekeys = {"one" , "Two", "Three"};
        Node234 [] children = {null, null};
        System.out.print(children.length);
        Node234 oneKey = new Node234(onekeys, children);

        RedBlackNode root = RedBlackNode.buildFrom234(oneKey);

        assertEquals( true, root.getBlack());
        assertEquals( RedBlackNode.FAKE_NODE, root.getRight().getKey());
        assertEquals( "one", root.getKey());
        assertEquals(null,root.getRight().getRight());

        Node234 [] twoKeychildren = {null, null, null};
        Node234 twoKey = new Node234(twokeys, twoKeychildren);


        RedBlackNode root0 = RedBlackNode.buildFrom234(twoKey);
        assertEquals("Two", root0.getKey());
        assertEquals(true, root0.getBlack());
        assertEquals("one", root0.getLeft().getKey());
        assertEquals(false, root0.getLeft().getBlack());

        Node234 [] threeKeyChildren = {null, null, null, null};
        Node234 threeKey = new Node234(threekeys, threeKeyChildren);

        RedBlackNode root1 = RedBlackNode.buildFrom234(threeKey);
        assertEquals("Two", root1.getKey());
        assertEquals(true, root1.getBlack());
        assertEquals("one", root1.getLeft().getKey());
        assertEquals(false, root1.getLeft().getBlack());
        assertEquals("Three", root1.getRight().getKey());
        assertEquals(false, root1.getRight().getBlack());

        System.out.print("\n" + root1.toDotFile(true));

        oneKey.child1 = threeKey;
        oneKey.child2 = twoKey;


        System.out.print("\n" + root1.toDotFile(true));
        String [] temp = root1.toArray();
        for (int i = 0; i < temp.length; i++){
            System.out.print(temp[i]);
        }


    }

    public void insertTDTests(){
        RedBlackNode root = new RedBlackNode("c", null, null, true, true);
    }
}