import java.util.ArrayList;
import java.util.List;

/**
 * RedBlackNode
 * @author Dustin Janzen
 */


public class RedBlackNode {
    final static String FAKE_NODE = "CDs Fake Nodes";

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
        if (current.getBlack())  result.add("b: " + current.getKey() + " ");
        else                     result.add("r: " + current.getKey() + " ");
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
            result = toArrayHelper(current.getLeft(), result);

        if (!current.getRight().getKey().equals(FAKE_NODE))
            result = toArrayHelper(current.getRight(), result);

        return result;
    }

    public RedBlackNode insert_td(String key){
        return new RedBlackNode(null, null, null, false, false);
    }

    public RedBlackNode insert_bu(String key){
        return new RedBlackNode(null, null, null, false, false);
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
        if (this.getBlack() == true) return "black";
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

    public boolean getBlack(){
        return black;
    }

    public void setBlack(boolean black){
        this.black = black;
    }

    private boolean isRoot(){
        return isRoot;
    }

}//end RedBlackNode
