
/* Node234
 *
 * CSc 345 Spring 16 - Project 5
 *
 * Author: Russ Lewis
 * TA:     n/a
 *
 * Implements one node of a 2-3-4 tree.  Has code to build it from a
 * String specification.  This mostly exists as a transient state which
 * we convert to a Red-Black tree.
 */


import java.util.Arrays;


public class Node234
{
    public int    keyCount;
    public String key1,key2,key3;

    public Node234 parent;
    public Node234 child1,child2,child3,child4;

    private Node234(String firstKey)
    {
        this.keyCount = 1;
        this.key1     = firstKey;
    }

    private Node234(String split, Node234 leftChild, Node234 rightChild)
    {
        this.keyCount = 1;
        this.key1     = split;

        this.child1 =  leftChild;
        this.child2 = rightChild;

        leftChild.parent = this;
        rightChild.parent = this;
    }

    private Node234(String[] keys)
    {
        if (keys == null || keys.length < 1 || keys.length > 3)
            throw new IllegalArgumentException();

        this.keyCount = keys.length;
        this.key1     = keys[0];

        if (keys.length > 1)
            this.key2 = keys[1];
        if (keys.length > 2)
            this.key3 = keys[2];
    }

    public Node234(String[] keys, Node234[] children)
    {
        if (keys == null || keys.length < 1 || keys.length > 3 ||
        children == null || children.length != keys.length+1)
        {
            throw new IllegalArgumentException();
        }

        this.keyCount = keys.length;

        this.key1   = keys[0];
        this.child1 = children[0];
        this.child2 = children[1];

        if (keyCount > 1)
        {
            this.key2   = keys[1];
            this.child3 = children[2];
        }

        if (keyCount > 2)
        {
            this.key3   = keys[2];
            this.child4 = children[3];
        }
    }



    public static Node234 build234Tree(String[][] sets)
    {
        // The input to this method is a list of key sets.  Each set
        // is 1 to 3 keys, and the sets are listed in a pre-order
        // traversal of the tree.
        //
        // Getting the root node is easy; we simply take the first
        // set, and build a node from that.  The trick, though, is
        // splitting the rest of the array into subtrees: to do that,
        // we use the keys in the root node as partition markers to
        // split the rest of the array.

        if (sets == null || sets.length == 0)
            throw new IllegalArgumentException();

        for (String[] set: sets)
            if (set == null || set.length < 1 || set.length > 3)
                throw new IllegalArgumentException();


        // build the root node, currently without any children.
        Node234 root = new Node234(sets[0]);

        // if we only had a single set, then we're building a leaf.
        if (sets.length == 1)
            return root;



        // find the indices for the beginning of each range, as
        // defined by our root's keys.  This code isn't perfect;
        // it will run over the end of arrays sometimes.  But
        // it's good enough for this project.

        int split1 = 1;
        while (sets[split1][0].compareTo(root.key1) < 0)
            split1++;

        int split2;
        if (root.keyCount < 2)
            split2 = sets.length;
        else
        {
            split2 = split1;
            while (sets[split2][0].compareTo(root.key2) < 0)
                split2++;
        }

        int split3;
        if (root.keyCount < 3)
            split3 = sets.length;
        else
        {
            split3 = split2;
            while (sets[split3][0].compareTo(root.key3) < 0)
                split3++;
        }

// System.out.println(sets.length);
// System.out.println("  "+split1);
// System.out.println("  "+split2);
// System.out.println("  "+split3);


        // now that we've found *where* the array splits, actually
        // divide it up and recurse.
        root.child1 = build234Tree(Arrays.copyOfRange(sets, 1,     split1));
        root.child2 = build234Tree(Arrays.copyOfRange(sets, split1,split2));

        if (root.keyCount > 1)
            root.child3 = build234Tree(Arrays.copyOfRange(sets, split2,split3));
        if (root.keyCount > 2)
            root.child4 = build234Tree(Arrays.copyOfRange(sets, split3,sets.length));

        return root;
    }



    public String encode()
    {
        String retval = "";

        retval += "{"+this.key1;

        if (this.keyCount > 1)
            retval += ","+this.key2;

        if (this.keyCount > 2)
            retval += ","+this.key3;

        retval += "}";

        if (this.child1 != null)
            retval += this.child1.encode();
        if (this.child2 != null)
            retval += this.child2.encode();
        if (this.child3 != null)
            retval += this.child3.encode();
        if (this.child4 != null)
            retval += this.child4.encode();

        return retval;
    }



    public String dotFileName()
    {
        return "vertex_"+(0x7fffffff & this.hashCode());
    }
    public String toDotFile(boolean isRoot)
    {
        String retval = "";
        if (isRoot)
            retval += "digraph {\n";


        // the label is a little more complex to build than
        // in a binary tree.
        String label = key1;
        if (keyCount > 1)
            label += " "+key2;
        if (keyCount > 2)
            label += " "+key3;

        retval += String.format("  %s [label=\"%s\"];\n",
        this.dotFileName(), label);


        if (child1 != null)
        {
            retval += String.format("    %s -> %s;\n",
            this.       dotFileName(),
            this.child1.dotFileName());
        }
        if (child2 != null)
        {
            retval += String.format("    %s -> %s;\n",
            this.       dotFileName(),
            this.child2.dotFileName());
        }
        if (child3 != null)
        {
            retval += String.format("    %s -> %s;\n",
            this.       dotFileName(),
            this.child3.dotFileName());
        }
        if (child4 != null)
        {
            retval += String.format("    %s -> %s;\n",
            this.       dotFileName(),
            this.child4.dotFileName());
        }

        if (child1 != null)
            retval += this.child1.toDotFile(false);
        if (child2 != null)
            retval += this.child2.toDotFile(false);
        if (child3 != null)
            retval += this.child3.toDotFile(false);
        if (child4 != null)
            retval += this.child4.toDotFile(false);


        if (isRoot)
            retval += "}\n";
        return retval;
    }
}

