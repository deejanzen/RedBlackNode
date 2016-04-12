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

        assertEquals( true, root.isBlack());
        assertEquals( RedBlackNode.FAKE_NODE, root.getRight().getKey());
        assertEquals( "one", root.getKey());
        assertEquals(null,root.getRight().getRight());

        Node234 [] twoKeychildren = {null, null, null};
        Node234 twoKey = new Node234(twokeys, twoKeychildren);


        RedBlackNode root0 = RedBlackNode.buildFrom234(twoKey);
        assertEquals("Two", root0.getKey());
        assertEquals(true, root0.isBlack());
        assertEquals("one", root0.getLeft().getKey());
        assertEquals(false, root0.getLeft().isBlack());

        Node234 [] threeKeyChildren = {null, null, null, null};
        Node234 threeKey = new Node234(threekeys, threeKeyChildren);

        RedBlackNode root1 = RedBlackNode.buildFrom234(threeKey);
        assertEquals("Two", root1.getKey());
        assertEquals(true, root1.isBlack());
        assertEquals("one", root1.getLeft().getKey());
        assertEquals(false, root1.getLeft().isBlack());
        assertEquals("Three", root1.getRight().getKey());
        assertEquals(false, root1.getRight().isBlack());

        System.out.print("\n" + root1.toDotFile(true));

        oneKey.child1 = threeKey;
        oneKey.child2 = twoKey;


        System.out.print("\n" + root1.toDotFile(true));
        String [] temp = root1.toArray();
        String [] temp0 = root1.toArray_preOrder();
        for (int i = 0; i < temp.length; i++){
            System.out.print(temp[i]);
        }
        System.out.println();
        for (int i = 0; i < temp0.length; i++){
            System.out.print(temp0[i]);
        }


    }
    @Test
    public void insertTDTests(){
        String FAKE_NODE = "CDs Fake Nodes";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);
        RedBlackNode root = new RedBlackNode("c", fake, fake, true, true);

        root.insert_td("a");
        root.insert_td("b");
//        root.insert_td("d");
//        root.insert_td("B");
//        root.insert_td("A");
        System.out.print("\n" + root.toDotFile(true));
    }
}