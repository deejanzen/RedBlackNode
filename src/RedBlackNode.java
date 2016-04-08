
/**
 * RedBlackNode
 * @author Dustin Janzen
 */
public class RedBlackNode {
    private String key;
    private RedBlackNode left;
    private RedBlackNode right;
    private boolean black;
    private String internalName;
    private static RedBlackNode fake = new RedBlackNode(null, null, null, true, null);

    public RedBlackNode(String key, RedBlackNode left, RedBlackNode right, boolean black, RedBlackNode fake){
        this.key = key;
        this.left = left;
        this.right = right;
        this.black = black;
        this.fake = fake;
        internalName = "Vertex_" + hashCode();
    }

    public static RedBlackNode RedBlackNode(Node234 root){
        //Your program must turn all 2-3-4 nodes with 2 key
        //in to a left-leaning widget (that is, with the red node on the left)
        //However, you will enforce this only in this method; do not enforce it on insertions!

        RedBlackNode redBlackNodeRoot = new RedBlackNode(null, null, null, true, fake);
        return buildFrom234Helper(root, redBlackNodeRoot );

    }

    private static RedBlackNode buildFrom234Helper(Node234 current, RedBlackNode build ){
        if (current.keyCount == 1){
            //build widget
            build.setKey(current.key1);
            build.setBlack(true);

            //leaf?
            if (current.child1 == null && current.child2 == null ){
                build.setLeft(fake);
                build.setRight(fake);
                return build;
            }

            //create new RB nodes to pass with recursive call
            RedBlackNode blackNodeLeft = new RedBlackNode(null, null, null, true, null);
            RedBlackNode blackNodeRight = new RedBlackNode(null, null, null, true, null);

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

            //set black to false or not black thus red
            RedBlackNode blackNodeLeft = new RedBlackNode(current.key1, null, null, false, null);
            build.setLeft(blackNodeLeft);

            //leaf
            if (current.child1 == null && current.child2 == null && current.child3 == null){
                build.setRight(fake);
                blackNodeLeft.setLeft(fake);
                blackNodeLeft.setRight(fake);
                return build;
            }

            //create new RB nodes to pass with recursive call
            RedBlackNode redNodeLeft = new RedBlackNode(null, null, null, true, null);
            RedBlackNode redNodeRight = new RedBlackNode(null, null, null, true, null);
            RedBlackNode blackNodeRight = new RedBlackNode(null, null, null, true, null);

            //set widgets nodes
            build.setRight(blackNodeRight);
            build.getLeft().setLeft(redNodeLeft);
            build.getLeft().setRight(redNodeRight);
            //current.child1, toSetLeft.left : child2, toSetLeft.right : child3, build.right

            buildFrom234Helper(current.child1, redNodeLeft);
            buildFrom234Helper(current.child2, redNodeRight);
            buildFrom234Helper(current.child2, blackNodeRight);
            return build;
        }
        else { //has three keys
            //build widget
            build.setKey(current.key2);

            return build;
        }
    }



    public String[] toArray(){
        String [] result = {};
        return result;
    }

    public String[] toArray_preOrder(){
        String [] result = {};
        return result;
    }

    public RedBlackNode insert_td(String key){
        return new RedBlackNode(null, null, null, false, fake);
    }

    public RedBlackNode insert_bu(String key){
        return new RedBlackNode(null, null, null, false, fake);
    }

    //toDotFile is generating a digraph. Boolean parameter per spec but unused. Using two helpers for vertices and edges
    public String toDotFile(Boolean isRoot){
        String result = "digraph {\n" + "\t" + getInternalName() + "[penwidth=3];\n" + toDotFileHelperLabel(new String(""));
        result += toDotFileHelperEdge(new String(""));
        return result + "}\n";
    }

    public String toDotFileHelperLabel(String s){
        if (left == null && right == null) return s + "\t" + getInternalName() + "[label=" + getKey() +"];\n";

        s+= "\t" + getInternalName() + "[label=" + getKey() +"];\n";
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
    public String getInternalName(){
        return internalName;
    }

    public boolean getBlack(){
        return black;
    }

    public void setBlack(boolean black){
        this.black = black;
    }

    private RedBlackNode getFake(){
        return fake;
    }

}//end RedBlackNode
