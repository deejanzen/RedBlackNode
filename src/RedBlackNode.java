
/**
 * RedBlackNode
 * @author Dustin Janzen
 */
public class RedBlackNode {
    private String key;
    private RedBlackNode left;
    private RedBlackNode right;
    private boolean color;

    public static RedBlackNode buildFrom234(Node234 root ){

    }

    public String[] toArray(){
        String [] result = {};
        return result;
    }

    public String[] toArray_PreOrder(){
        String [] result = {};
        return result;
    }

    public String[] insert_td(String key){

    }

    public String[] insert_bu(String key){

    }

    //toDotFile is generating a digraph. Boolean parameter per spec but unused. Using two helpers for vertices and edges
    public String toDotFile(Boolean b){
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

}
