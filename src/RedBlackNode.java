
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

    public RedBlackNode(String key, RedBlackNode left, RedBlackNode right, boolean black){
        this.key = key;
        this.left = left;
        this.right = right;
        this.black = black;
        internalName = "Vertex_" + hashCode();
    }

    public static RedBlackNode buildFrom234(Node234 root){
        //Your program must turn all 2-3-4 nodes with 2 key
        //in to a left-leaning widget (that is, with the red node on the left)
        //However, you will enforce this only in this method; do not enforce it on insertions!
        return new RedBlackNode(null, null, null, false);
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
        return new RedBlackNode(null, null, null, false);
    }

    public RedBlackNode insert_bu(String key){
        return new RedBlackNode(null, null, null, false);
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

}//end RedBlackNode
