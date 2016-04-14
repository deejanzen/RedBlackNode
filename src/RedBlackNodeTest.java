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
        String FAKE_NODE = RedBlackNode.FAKE_NODE;
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);
        RedBlackNode root = new RedBlackNode("c", fake, fake, true, true);

        root.insert_td("a");
        root.insert_td("b");
        root.insert_td("d");
        root.insert_td("B");
        root.insert_td("A");
        System.out.print("\n" + root.toDotFile(true));
    }
    @Test public void case4TEst(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);
        RedBlackNode root = new RedBlackNode("f", fake, fake, true, true);

        root.insert_td("g");

        root.insert_td("d");
        root.insert_td("a");
        root.insert_td("e");
        root.insert_td("B");
        root.insert_td("C");
        root.insert_td("A");
        root.insert_td("c");
        root.insert_td("b");
        root.insert_td("D");
        root.insert_td("E");
        root.insert_td("h");
        root.insert_td("i");
        root.insert_td("j");
        root.insert_td("F");

        System.out.print("\n" + root.toDotFile(true));

        String [] preOrder = root.toArray_preOrder();
        for (int i = 0; i< preOrder.length;i++){
            System.out.print(preOrder[i]);
        }

        System.out.println();
        String [] inOrder = root.toArray();
        for (int i = 0; i< inOrder.length;i++){
            System.out.print(inOrder[i]);
        }
    }


    @Test public void case5Test0(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("D", fake, fake, true, true);
        RedBlackNode uncle = new RedBlackNode("E", fake, fake, true, true);
        RedBlackNode parent = new RedBlackNode("C", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("B", fake, fake, false, true);

        root.setLeft(parent);
        root.setRight(uncle);
        parent.setLeft(newChile);

        RedBlackNode.fixupRotateToRight(root);



        System.out.print("\n" + root.toDotFile(true));
    }
    @Test
    public void TestfixupRotateToRight(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("C", fake, fake, true, true);
        RedBlackNode uncle = new RedBlackNode("D", fake, fake, true, true);
        RedBlackNode parent = new RedBlackNode("B", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("A", fake, fake, false, true);

        root.setRight(uncle);
        root.setLeft(parent);
        parent.setLeft(newChile);
        RedBlackNode.fixupRotateToRight(root);
        System.out.print("\n" + root.toDotFile(true));
    }

    @Test
    public void TestfixupRotateToLeftThenRight(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("C", fake, fake, true, true);
        RedBlackNode uncle = new RedBlackNode("D", fake, fake, true, true);
        RedBlackNode parent = new RedBlackNode("A", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("B", fake, fake, false, true);

        root.setRight(uncle);
        root.setLeft(parent);
        parent.setRight(newChile);
        RedBlackNode.fixupRotateToLeftThenRight(root);
        System.out.print("\n" + root.toDotFile(true));
    }
    @Test
    public void testfixupRotateToLeft(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("B", fake, fake, true, true);
        RedBlackNode uncle = new RedBlackNode("A", fake, fake, true, true);
        RedBlackNode parent = new RedBlackNode("C", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("D", fake, fake, false, true);

        root.setLeft(uncle);
        root.setRight(parent);
        parent.setRight(newChile);
        RedBlackNode.fixupRotateToLeft(root);
        System.out.print("\n" + root.toDotFile(true));
    }
    @Test
    public void TestfixupRotateToRightThenLeft(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("B", fake, fake, true, true);
        RedBlackNode uncle = new RedBlackNode("A", fake, fake, true, true);
        RedBlackNode parent = new RedBlackNode("D", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("C", fake, fake, false, true);

        root.setLeft(uncle);
        root.setRight(parent);
        parent.setLeft(newChile);
        RedBlackNode.fixupRotateToRightThenLeft(root);
        System.out.print("\n" + root.toDotFile(true));
    }
    @Test
    public void insert_buTests(){
        String FAKE_NODE = RedBlackNode.FAKE_NODE;
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);
        RedBlackNode root = new RedBlackNode("c", fake, fake, true, true);

        root.insert_bu("a");
        root.insert_bu("b");
        root.insert_bu("d");
        root.insert_bu("B");
        root.insert_bu("A");
        System.out.print("\n" + root.toDotFile(true));
    }

    @Test
    public void Moreinsert_buTests(){
        String FAKE_NODE = RedBlackNode.FAKE_NODE;
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);
        RedBlackNode root = new RedBlackNode("f", fake, fake, true, true);

        root.insert_bu("g");

        root.insert_bu("d");
        root.insert_bu("a");
        root.insert_bu("e");
        root.insert_bu("B");
        root.insert_bu("C");
        root.insert_bu("A");
        root.insert_bu("c");
        root.insert_bu("b");
        root.insert_bu("D");
        root.insert_bu("E");
        root.insert_bu("h");
        root.insert_bu("i");
        root.insert_bu("j");
        root.insert_bu("F");
        System.out.print("\n" + root.toDotFile(true));
    }
    @Test
    public void testfixupRedLLatRoot(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("B", fake, fake, true, true);
        RedBlackNode uncle = new RedBlackNode("A", fake, fake, false, true);
        RedBlackNode parent = new RedBlackNode("D", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("C", fake, fake, false, true);

        root.setLeft(parent);
        root.setRight(uncle);
        parent.setLeft(newChile);
        RedBlackNode.fixupRedLL(root);
        System.out.print("\n" + root.toDotFile(true));
    }

    @Test
    public void testfixupRedLL(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode internal = new RedBlackNode("B", fake, fake, true, false);
        RedBlackNode uncle = new RedBlackNode("A", fake, fake, false, true);
        RedBlackNode parent = new RedBlackNode("D", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("C", fake, fake, false, true);

        internal.setLeft(parent);
        internal.setRight(uncle);
        parent.setLeft(newChile);
        RedBlackNode.fixupRedLL(internal);
        System.out.print("\n" + internal.toDotFile(true));
    }
    @Test
    public void testfixupRedLRatRoot(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("C", fake, fake, true, true);
        RedBlackNode uncle = new RedBlackNode("D", fake, fake, false, true);
        RedBlackNode parent = new RedBlackNode("A", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("B", fake, fake, false, true);

        root.setLeft(parent);
        root.setRight(uncle);
        parent.setRight(newChile);
        RedBlackNode.fixupRedLR(root);
        System.out.print("\n" + root.toDotFile(true));
    }

    @Test
    public void testfixupRedLR(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode internal = new RedBlackNode("C", fake, fake, true, false);
        RedBlackNode uncle = new RedBlackNode("D", fake, fake, false, true);
        RedBlackNode parent = new RedBlackNode("A", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("B", fake, fake, false, true);

        internal.setLeft(parent);
        internal.setRight(uncle);
        parent.setRight(newChile);
        RedBlackNode.fixupRedLR(internal);
        System.out.print("\n" + internal.toDotFile(true));
    }

    @Test
    public void testfixupRedRRatRoot(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("B", fake, fake, true, true);
        RedBlackNode uncle = new RedBlackNode("A", fake, fake, false, true);
        RedBlackNode parent = new RedBlackNode("C", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("D", fake, fake, false, true);

        root.setLeft(uncle);
        root.setRight(parent);
        parent.setRight(newChile);
        RedBlackNode.fixupRedRR(root);
        System.out.print("\n" + root.toDotFile(true));
    }

    @Test
    public void testfixupRedRR(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode internal = new RedBlackNode("B", fake, fake, true, false);
        RedBlackNode uncle = new RedBlackNode("A", fake, fake, false, true);
        RedBlackNode parent = new RedBlackNode("C", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("D", fake, fake, false, true);

        internal.setLeft(uncle);
        internal.setRight(parent);
        parent.setRight(newChile);
        RedBlackNode.fixupRedRR(internal);
        System.out.print("\n" + internal.toDotFile(true));
    }

    @Test
    public void testfixupRedRLatRoot(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("B", fake, fake, true, true);
        RedBlackNode uncle = new RedBlackNode("A", fake, fake, false, true);
        RedBlackNode parent = new RedBlackNode("D", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("C", fake, fake, false, true);

        root.setLeft(uncle);
        root.setRight(parent);
        parent.setLeft(newChile);
        RedBlackNode.fixupRedRL(root);
        System.out.print("\n" + root.toDotFile(true));
    }

    @Test
    public void testfixupRedRL(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode internal = new RedBlackNode("B", fake, fake, true, false);
        RedBlackNode uncle = new RedBlackNode("A", fake, fake, false, true);
        RedBlackNode parent = new RedBlackNode("D", fake, fake, false, true);
        RedBlackNode newChile = new RedBlackNode("C", fake, fake, false, true);

        internal.setLeft(uncle);
        internal.setRight(parent);
        parent.setLeft(newChile);
        RedBlackNode.fixupRedRL(internal);
        System.out.print("\n" + internal.toDotFile(true));
    }

    @Test
    public void insertBUTestsMore(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("200", fake, fake, true, true);
        root.insert_bu("100");

        root.insert_bu("300");
        root.insert_bu("050");
        root.insert_bu("150");
        root.insert_bu("250");
        root.insert_bu("350");
//        root.insert_bu("025");
        //root.insert_bu("375");
        System.out.print("\n" + root.toDotFile(true));

        String [] inOrder = root.toArray();
        for (int i =0; i < inOrder.length;i++){
            System.out.print(inOrder[i] + " ");
        }


    }
    @Test
    public void insertBUTestsAnother(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("J", fake, fake, true, true);

        root.insert_bu("F");
        root.insert_bu("M");
        root.insert_bu("C");
        root.insert_bu("H");
        root.insert_bu("L");
        root.insert_bu("O");
        root.insert_bu("A");
        root.insert_bu("D");
        root.insert_bu("G");
        root.insert_bu("I");
        root.insert_bu("K");
        root.insert_bu("L2");
        root.insert_bu("N");
        root.insert_bu("P");

        String fromTest = "r:A b:C r:D r:F r:G b:H r:I b:J r:K b:L r:L2 r:M r:N b:O r:P";
        System.out.println("from test then mine");
        System.out.println(fromTest);
        String [] inOrder = root.toArray();
        for (int i =0; i < inOrder.length;i++){
            System.out.print(inOrder[i] );
        }

    }
    @Test
    public void insertBUTestsAnotherAnother(){
        String FAKE_NODE = "NULL";
        RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);

        RedBlackNode root = new RedBlackNode("A", fake, fake, true, true);
        printToArray(root);
        System.out.println("b:A");
        System.out.println();

        root.insert_bu("B");
        printToArray(root);
        System.out.println("b:A r:B");
        System.out.println();

        root.insert_bu("C");
        printToArray(root);
        System.out.println("r:A b:B r:C");
        System.out.println();

        root.insert_bu("D");
        printToArray(root);
        System.out.println("b:A b:B b:C r:D");
        System.out.println();

        root.insert_bu("E");
        printToArray(root);
        System.out.println("b:A b:B r:C b:D r:E");
        System.out.println();

        root.insert_bu("F");
        printToArray(root);
        System.out.println("b:A b:B b:C r:D b:E r:F");
        System.out.println();

        root.insert_bu("G");
        printToArray(root);
        System.out.println("b:A b:B b:C r:D r:E b:F r:G");
        System.out.println();

        root.insert_bu("H");
        printToArray(root);
        System.out.println("b:A r:B b:C b:D b:E r:F b:G r:H");
        System.out.println();
//        System.out.print("\n" + root.toDotFile(true));

        root.insert_bu("I");
        printToArray(root);
        System.out.println("b:A r:B b:C b:D b:E r:F r:G b:H r:I");
        System.out.println();
//        System.out.print("\n" + root.toDotFile(true));

        root.insert_bu("J");
        printToArray(root);
        System.out.println("b:A b:B b:C b:D b:E b:F b:G r:H b:I r:J");
        System.out.println();

        root.insert_bu("K");
        printToArray(root);
        System.out.println("b:A b:B b:C b:D b:E b:F b:G r:H r:I b:J r:K");
        System.out.println();

        root.insert_bu("L");
        printToArray(root);
        System.out.println("b:A b:B b:C b:D b:E r:F b:G b:H b:I r:J b:K r:L");
        System.out.println();

        root.insert_bu("M");
        printToArray(root);
        System.out.println("b:A b:B b:C b:D b:E r:F b:G b:H b:I r:J r:K b:L r:M");
        System.out.println();

        root.insert_bu("N");
        printToArray(root);
        System.out.println("b:A b:B b:C b:D b:E b:F b:G r:H b:I b:J b:K r:L b:M r:N");
        System.out.println();

        root.insert_bu("O");
        printToArray(root);
        System.out.println("b:A b:B b:C b:D b:E b:F b:G r:H b:I b:J b:K r:L r:M b:N r:O");
        System.out.println();

        root.insert_bu("P");
        printToArray(root);
        System.out.println("b:A b:B b:C b:D b:E b:F b:G r:H b:I r:J b:K b:L b:M r:N b:O r:P");
        System.out.println();
        System.out.print("\n" + root.toDotFile(true));

        root.insert_bu("Q");
        printToArray(root);
        System.out.println("b:A b:B b:C b:D b:E b:F b:G r:H b:I r:J b:K b:L b:M r:N r:O b:P r:Q");
        System.out.println();
        System.out.print("\n" + root.toDotFile(true));

        root.insert_bu("R");
        printToArray(root);
        System.out.println("b:A");
        System.out.println();

        root.insert_bu("S");
        printToArray(root);
        System.out.println("b:A");
        System.out.println();

        root.insert_bu("T");
        printToArray(root);
        System.out.println("b:A");
        System.out.println();

        root.insert_bu("U");
        printToArray(root);
        System.out.println("b:A");
        System.out.println();

        root.insert_bu("V");
        printToArray(root);
        System.out.println("b:A");
        System.out.println();

        root.insert_bu("W");
        printToArray(root);
        System.out.println("b:A");
        System.out.println();

        root.insert_bu("X");
        printToArray(root);
        System.out.println("b:A");
        System.out.println();

        root.insert_bu("Y");
        printToArray(root);
        System.out.println("b:A");
        System.out.println();

        root.insert_bu("Z");
        printToArray(root);
        System.out.println("b:A");
        System.out.println();

//        String fromTest = "b:A b:B b:C b:D b:E b:F b:G b:H b:I b:J b:K r:L b:M b:N b:O b:P b:Q b:R b:S r:T b:U b:V b:W r:X b:Y r:Z";
//        System.out.println("from test then mine");
//        System.out.println(fromTest);
//        String [] inOrder = root.toArray();
//        for (int i =0; i < inOrder.length;i++){
//            System.out.print(inOrder[i] );
//        }
//        System.out.println();
//        System.out.print("\n" + root.toDotFile(true));

    }
    public static void printToArray(RedBlackNode root){
        String [] inOrder = root.toArray();
        for (int i =0; i < inOrder.length;i++){
            System.out.print(inOrder[i] );
        }
        System.out.println();
//        String [] preOrder = root.toArray();
//        for (int i =0; i < preOrder.length;i++){
//            System.out.print(inOrder[i] );
//        }

    }

}