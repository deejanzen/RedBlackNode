import java.util.ArrayList;
import java.util.List;

/**
 * RedBlackNode
 * @author Dustin Janzen
 */


public class RedBlackNode {
    final static String FAKE_NODE = "NULL";

    private String key;
    private RedBlackNode left;
    private RedBlackNode right;
    private boolean black;
    private boolean isRoot;
    private String internalName;
    private static RedBlackNode fake = new RedBlackNode(FAKE_NODE, null, null, true, false);



    public RedBlackNode(String key, RedBlackNode left, RedBlackNode right, boolean black, boolean isRoot){
        this.key = key;
        this.left = left;
        this.right = right;
        this.black = black;
        this.isRoot = isRoot;
        internalName = "Vertex_" + hashCode();
    }

    public static RedBlackNode buildFrom234(Node234 root){
        //Your program must turn all 2-3-4 nodes with 2 key
        //in to a left-leaning widget (that is, with the red node on the left)
        //However, you will enforce this only in this method; do not enforce it on insertions!

        RedBlackNode redBlackNodeRoot = new RedBlackNode(null, null, null, true, true);
        return buildFrom234Helper(root, redBlackNodeRoot );

    }

    private static RedBlackNode buildFrom234Helper(Node234 current, RedBlackNode build ){
        if (current.keyCount == 1){
            //build widget
            build.setKey(current.key1);
            build.setBlack(true);

            //leaf?
            if (current.child1 == null && current.child2 == null ){
                //RedBlackNode fake = new RedBlackNode("fake", null, null, true, null);
                build.setLeft(fake);
                build.setRight(fake);
                return build;
            }

            //create new RB nodes to pass with recursive call
            RedBlackNode blackNodeLeft = new RedBlackNode(null, null, null, true, false);
            RedBlackNode blackNodeRight = new RedBlackNode(null, null, null, true, false);

            //set widgets nodes
            build.setLeft(blackNodeLeft);
            build.setRight(blackNodeRight);

            buildFrom234Helper(current.child1, blackNodeLeft );
            buildFrom234Helper(current.child2, blackNodeRight);
            return build;
        }
        else if (current.keyCount == 2){
            //Build widget
            //left leaning so key2 is root
            build.setKey(current.key2);
            build.setBlack(true);

            //blackNodeLeft = red per false in constructor
            RedBlackNode blackNodeLeft = new RedBlackNode(current.key1, null, null, false, false);
            build.setLeft(blackNodeLeft);

            //leaf
            if (current.child1 == null && current.child2 == null && current.child3 == null){
                build.setRight(fake);
                blackNodeLeft.setLeft(fake);
                blackNodeLeft.setRight(fake);
                return build;
            }

            //create new RB nodes to pass with recursive call
            RedBlackNode redNodeLeft = new RedBlackNode(null, null, null, true, false);
            RedBlackNode redNodeRight = new RedBlackNode(null, null, null, true, false);
            RedBlackNode blackNodeRight = new RedBlackNode(null, null, null, true, false);

            //set widgets nodes
            build.setRight(blackNodeRight);
            build.getLeft().setLeft(redNodeLeft);
            build.getLeft().setRight(redNodeRight);
            //current.child1, toSetLeft.left : child2, toSetLeft.right : child3, build.right

            buildFrom234Helper(current.child1, redNodeLeft);
            buildFrom234Helper(current.child2, redNodeRight);
            buildFrom234Helper(current.child3, blackNodeRight);
            return build;
        }
        else { //has three keys
            //build widget
            build.setKey(current.key2);
            build.setBlack(true);

            //both red per false
            RedBlackNode blackNodeLeft = new RedBlackNode(current.key1, null, null, false, false);
            RedBlackNode blackNodeRight = new RedBlackNode(current.key3, null, null, false, false);

            build.setLeft(blackNodeLeft);
            build.setRight(blackNodeRight);

            //leaf
            if (current.child1 == null && current.child2 == null &&
                current.child3 == null && current.child4 == null    ){
                blackNodeLeft.setLeft(fake);
                blackNodeLeft.setRight(fake);
                blackNodeRight.setLeft(fake);
                blackNodeRight.setRight(fake);
                return build;
            }

            //create new RB nodes to pass with recursive call
            RedBlackNode redNodeLeftLeft = new RedBlackNode(null, null, null, true, false);
            RedBlackNode redNodeLeftRight = new RedBlackNode(null, null, null, true, false);
            RedBlackNode redNodeRightLeft = new RedBlackNode(null, null, null, true, false);
            RedBlackNode redNodeRightRight = new RedBlackNode(null, null, null, true, false);

            //set widgets nodes
            build.getLeft().setLeft(redNodeLeftLeft);
            build.getLeft().setRight(redNodeLeftRight);
            build.getRight().setLeft(redNodeRightLeft);
            build.getRight().setRight(redNodeRightRight);

            buildFrom234Helper(current.child1, redNodeLeftLeft);
            buildFrom234Helper(current.child2, redNodeLeftRight);
            buildFrom234Helper(current.child3, redNodeRightLeft);
            buildFrom234Helper(current.child4, redNodeRightRight);

            return build;
        }
    }



    public String[] toArray(){
        List<String> temp = toArrayHelper(this, new ArrayList<>());
        String [] result = new String [temp.size()];
        return temp.toArray(result);
    }

    private List<String> toArrayHelper(RedBlackNode current, List<String> result){
        if (current.getLeft().getKey().equals(FAKE_NODE) &&
           current.getRight().getKey().equals(FAKE_NODE)    ){
            return writeToArray(current, result);
        }

        if (!current.getLeft().getKey().equals(FAKE_NODE))
            result = toArrayHelper(current.getLeft(), result);

        result = writeToArray(current, result);

        if (!current.getRight().getKey().equals(FAKE_NODE))
            result = toArrayHelper(current.getRight(), result);

        return result;

    }

    private List<String> writeToArray(RedBlackNode current, List<String> result){
        if (current.isBlack())  result.add("b:" + current.getKey() + " ");
        else                     result.add("r:" + current.getKey() + " ");
        return result;
    }

    public String[] toArray_preOrder(){
        List<String> temp = toArray_preOrderHelper(this, new ArrayList<>());
        String [] result = new String [temp.size()];
        return temp.toArray(result);
    }

    private List<String> toArray_preOrderHelper(RedBlackNode current, List<String> result){
        if (current.getLeft().getKey().equals(FAKE_NODE) &&
           current.getRight().getKey().equals(FAKE_NODE)    ){
            return writeToArray(current, result);
        }

        result = writeToArray(current, result);

        if (!current.getLeft().getKey().equals(FAKE_NODE))
            result = toArray_preOrderHelper(current.getLeft(), result);

        if (!current.getRight().getKey().equals(FAKE_NODE))
            result = toArray_preOrderHelper(current.getRight(), result);

        return result;
    }

    public RedBlackNode insert_td(String key){
        this.setIsRoot(true);
        return cleanup(insert_tdHelper(this,key));
    }
    private RedBlackNode insert_tdHelper(RedBlackNode current, String key){
        pushBlackDown(current);
        //base
        if (key.compareTo(current.getKey()) < 0          &&
            current.getLeft().getKey().equals(FAKE_NODE)    ){
                current.setLeft(new RedBlackNode(key, fake, fake, false, false));
                return current;
        }
        //base

        if (key.compareTo(current.getKey()) > 0           &&
            current.getRight().getKey().equals(FAKE_NODE)    ){
                current.setRight(new RedBlackNode(key, fake, fake, false, false));
                return current;
        }

        //duplicate checking
        if (key.compareTo(current.getKey()) == 0) throw new IllegalArgumentException();

        //recurse
        if (key.compareTo(current.getKey()) < 0){
            cleanup(insert_tdHelper(current.getLeft(), key));
            return current;
        }
        else{
            cleanup(insert_tdHelper(current.getRight(), key));
            return current;
        }
    }//end insert_tdHelper

    public RedBlackNode insert_bu(String key){
        this.setIsRoot(true);
        return cleanup(pushBlackDownBU(insert_buHelper(this,key)));
    }

    private RedBlackNode insert_buHelper(RedBlackNode current, String key){

        //base
        if (key.compareTo(current.getKey()) < 0          &&
        current.getLeft().getKey().equals(FAKE_NODE)    ){
            current.setLeft(new RedBlackNode(key, fake, fake, false, false));
            return current;
        }
        //base

        if (key.compareTo(current.getKey()) > 0           &&
        current.getRight().getKey().equals(FAKE_NODE)    ){
            current.setRight(new RedBlackNode(key, fake, fake, false, false));
            return current;
        }

        //duplicate checking
        if (key.compareTo(current.getKey()) == 0) throw new IllegalArgumentException();

        //recurse
        if (key.compareTo(current.getKey()) < 0){
            cleanup(pushBlackDownBU(insert_tdHelper(current.getLeft(), key)));
            return current;
        }
        else{
            cleanup(pushBlackDownBU(insert_tdHelper(current.getRight(), key)));
            return current;
        }
    }//end insert_buHelper

    public static RedBlackNode cleanup(RedBlackNode current){
        fixupRotateToRight(current);
        fixupRotateToLeftThenRight(current);
        fixupRotateToLeft(current);
        fixupRotateToRightThenLeft(current);
        return current;
    }

    public static RedBlackNode pushBlackDownBU(RedBlackNode current){
        fixupRedLL(current);
        fixupRedLR(current);
        fixupRedRR(current);
        fixupRedRL(current);
        return current;
    }
    public static void fixupRedLL(RedBlackNode current){
        if ( current.isBlack() == true                      &&
        current.getRight().isBlack() == false           &&
        current.getLeft().isBlack() == false           &&
        current.getLeft().getLeft().isBlack() == false    ){
            if (current.isRoot() == false) current.setBlack(false);
            current.getLeft().setBlack(true);
            current.getRight().setBlack(true);
        }
    }

    public static void fixupRedLR(RedBlackNode current){
        if ( current.isBlack() == true                      &&
        current.getRight().isBlack() == false           &&
        current.getLeft().isBlack() == false           &&
        current.getLeft().getRight().isBlack() == false    ){
            if (current.isRoot() == false) current.setBlack(false);
            current.getLeft().setBlack(true);
            current.getRight().setBlack(true);
        }
    }

    public static void fixupRedRR(RedBlackNode current){
        if ( current.isBlack() == true                      &&
        current.getRight().isBlack() == false           &&
        current.getLeft().isBlack() == false           &&
        current.getRight().getRight().isBlack() == false    ){
            if (current.isRoot() == false) current.setBlack(false);
            current.getLeft().setBlack(true);
            current.getRight().setBlack(true);
        }
    }

    public static void fixupRedRL(RedBlackNode current){
        if ( current.isBlack() == true                      &&
        current.getRight().isBlack() == false           &&
        current.getLeft().isBlack() == false           &&
        current.getRight().getLeft().isBlack() == false    ){
            if (current.isRoot() == false) current.setBlack(false);
            current.getLeft().setBlack(true);
            current.getRight().setBlack(true);
        }
    }

    public static void fixupRotateToRight(RedBlackNode current){
        if ( current.isBlack() == true                      &&
             current.getRight().isBlack() == true           &&
             current.getLeft().isBlack() == false           &&
             current.getLeft().getLeft().isBlack() == false    ){
                current.setRight(new RedBlackNode(current.getKey(),
                                                  current.getLeft().getRight(),
                                                  current.getRight(),
                                                  false,
                                                  false));
                current.setKey(current.getLeft().getKey());
                current.setLeft(current.getLeft().getLeft());
//                current.getLeft().setKey(current.getLeft().getLeft().getKey());
//                current.getLeft().setLeft(fake);
        }
    }

    public static void fixupRotateToLeftThenRight(RedBlackNode current){
        if (current.isBlack() == true                      &&
            current.getRight().isBlack() == true           &&
            current.getLeft().isBlack() == false           &&
            current.getLeft().getRight().isBlack() == false    ){
                current.setRight(new RedBlackNode(current.getKey(),
                                                  current.getLeft().getRight().getRight(),
                                                  current.getRight(),
                                                  false,
                                                  false));
                current.setKey(current.getLeft().getRight().getKey());
                current.getLeft().setRight(current.getLeft().getRight().getLeft());


        }
    }
    //TODO fix this
    public static void fixupRotateToLeft(RedBlackNode current){
        if ( current.isBlack() == true                        &&
             current.getLeft().isBlack() == true              &&
             current.getRight().isBlack() == false            &&
             current.getRight().getRight().isBlack() == false    ){
                current.setLeft(new RedBlackNode(current.getKey(),
                                                 current.getLeft(),
                                                 current.getRight().getLeft() ,
                                                 false,
                                                 false));
                current.setKey(current.getRight().getKey());
                current.getLeft().setRight(current.getRight().getLeft());
                current.setRight(current.getRight().getRight());


        }
    }

    public static void fixupRotateToRightThenLeft(RedBlackNode current){
        if ( current.isBlack() == true                        &&
             current.getLeft().isBlack() == true              &&
             current.getRight().isBlack() == false            &&
             current.getRight().getLeft().isBlack() == false    ){
                current.setLeft(new RedBlackNode(current.getKey(),
                                                 current.getLeft(),
                                                 current.getRight().getLeft().getLeft(),
                                                 false,
                                                 false
                                                                                        ));
                current.setKey(current.getRight().getLeft().getKey());
                current.getRight().setLeft(current.getRight().getLeft().getRight());
        }
    }
    private static RedBlackNode pushBlackDown(RedBlackNode current){
        if (current.getLeft() != null && current.getLeft().isBlack()  == false &&
           current.getRight() != null && current.getRight().isBlack() == false    ){
            //TODO check current is black?
            if (current.isRoot == false)
                current.setBlack(false);
            current.getLeft().setBlack(true);
            current.getRight().setBlack(true);
        }
        return current;
    }



    //toDotFile is generating a digraph. Boolean parameter per spec but unused. Using two helpers for vertices and edges
    public String toDotFile(Boolean isRoot){
        String result = "digraph {\n" + "\t" + getInternalName() + "[penwidth=3 color=orange];\n" + toDotFileHelperLabel(new String(""));
        result += toDotFileHelperEdge(new String(""));
        return result + "}\n";
    }

    //style=filled fillcolor=black fontcolor=white
    public String toDotFileHelperLabel(String s){
        if (left == null && right == null)
            return s + "\t" + getInternalName() + "[label=\"" + getKey() + "\" style=filled fillcolor=" + getColorAsString() +" fontcolor=white];\n";

        s+= "\t" + getInternalName() + "[label=\"" + getKey() +"\" style=filled fillcolor=" + getColorAsString() +" fontcolor=white];\n";
        if (left != null) {
            s = getLeft().toDotFileHelperLabel(s);
        }
        if (right != null) {
            s = getRight().toDotFileHelperLabel(s);
        }

        return s;

    }
    public String toDotFileHelperEdge(String s){
        if (left == null && right == null) return s;

        if (left != null) {
            s += "\t" + getInternalName() + " -> " + getLeft().getInternalName()+ "[label=left]" + "\n";
            s = getLeft().toDotFileHelperEdge(s);
        }
        if (right != null) {
            s += "\t" +getInternalName() + " -> " + getRight().getInternalName()+ "[label=right]" + "\n";
            s = getRight().toDotFileHelperEdge(s);
        }

        return s;

    }

    private String getColorAsString(){
        if (this.isBlack() == true) return "black";
        return "red";
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public RedBlackNode getLeft(){
        return left;
    }

    public void setLeft(RedBlackNode left){
        this.left = left;
    }

    public RedBlackNode getRight(){
        return right;
    }

    public void setRight(RedBlackNode right){
        this.right = right;
    }

    //using hashCode per spec suggestion
    private String getInternalName(){
        return internalName;
    }

    public boolean isBlack(){
        return black;
    }

    public void setBlack(boolean black){
        this.black = black;
    }

    private boolean isRoot(){
        return isRoot;
    }
    private void setIsRoot(boolean isRoot){
        this.isRoot = isRoot;
    }

}//end RedBlackNode



//    private RedBlackNode insert_tdHelper(RedBlackNode current, String key){
//        //push black down
//        if (current.isRoot() == true)
//            pushBlackDown(current);
//        if (!current.getKey().equals(FAKE_NODE)  &&
//        current.isBlack() == true                      &&
//        current.getLeft().isBlack() == true               )
//            pushBlackDown(current.getLeft());
//        if ( !current.getKey().equals(FAKE_NODE)  &&current.isBlack() == true && current.getRight().isBlack() == true)
//            pushBlackDown(current.getRight());
//
//        //base
//        //case 2
//        if (key.compareTo(current.getKey()) < 0          &&
//        current.getLeft().getKey().equals(FAKE_NODE) &&
//        current.isBlack() == true                       ){
//            current.setLeft(new RedBlackNode(key, fake, fake, false, false));
//            return current;
//        }
//        //base
//        //case 2
//        if (key.compareTo(current.getKey()) > 0           &&
//        current.getRight().getKey().equals(FAKE_NODE) &&
//        current.isBlack() == true                        ){
//            current.setRight(new RedBlackNode(key, fake, fake, false, false));
//            return current;
//        }
//
//        //base
//        //case 4 inner child black uncle
//        //left-right rotate
//        //TODO reflection of this
//        if ( key.compareTo(current.getKey()) < 0            &&
//        key.compareTo(current.getLeft().getKey()) > 0  &&
//        current.getLeft().isBlack() == false           &&
//        current.getRight().getKey().equals(FAKE_NODE)  &&
//        current.getRight().getKey().equals(FAKE_NODE)      ){
//
//            RedBlackNode newRightNode = new RedBlackNode(current.getKey(),
//            fake,
//            current.getRight(),
//            false,
//            false);
//            current.setKey(key);
//            current.setRight(newRightNode);
//            return current;
//        }
//
//
//        //base
//        //case 5 outer child black uncle
//        //right rotate
//        if ( key.compareTo(current.getKey()) < 0            &&
//        key.compareTo(current.getLeft().getKey()) < 0  &&
//        current.getLeft().isBlack() == false           &&
//        current.getRight().getKey().equals(FAKE_NODE)  &&
//        current.getRight().getKey().equals(FAKE_NODE)      ){
//
//            RedBlackNode newRightNode = new RedBlackNode(current.getKey(),
//            fake,
//            current.getRight(),
//            false,
//            false                );
//            current.setKey(current.getLeft().getKey());
//            current.setRight(newRightNode);
//            current.getLeft().setKey(key);
//            return current;
//        }
//
//        //duplicate checking
//        if (key.compareTo(current.getKey()) == 0) throw new IllegalArgumentException();
//
//        //recurse
//        if (key.compareTo(current.getKey()) < 0)
//            return insert_tdHelper(current.getLeft(), key);
//        else
//            return insert_tdHelper(current.getRight(), key);
//
//    }//end insert_tdHelper
