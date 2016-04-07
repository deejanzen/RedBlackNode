
/* Proj05Main
 *
 * CSc 345 Spring 16 - Project 5
 *
 * Author: Russ Lewis
 * TA:     n/a
 *
 * The main method for Project 5.  Takes a set of testcase files (given as
 * command line arguments; reads each one, and executes the testcase
 * specified.  Reports the number of passed & failed testcases at the end.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.PrintWriter;


public class Proj05Main
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Give a list of testcases to test.");
            System.exit(1);
        }


        int count = 0;
        int pass  = 0;

        for (int i=0; i<args.length; i++)
        {
            count++;
            if (doTestcase(args[i]))
                pass++;
        }

        System.out.printf("FINAL REPORT: %d of %d testcases passed.\n",
        pass, count);

        if (pass != count)
            System.exit(1);
        // else, return normally
    }


    public static boolean doTestcase(String filename)
    {
        System.out.printf("---- TESTCASE '%s' BEGIN ----\n", filename);

        Scanner in = null;
        try {
            in = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e)
        {
            System.out.printf("%s: ERROR: cannot open file\n", filename);
            return false;
        }


        // our initial tree is always given by the first word in the
        // file.  It can stretch over multiple lines, and we ignore
        // whitespace in the middle; curly braces are used to mark off
        // the bounds of the arrays.
        //
        // Once we build the 234 tree, we immediately convert it to
        // a red-black tree, and save that.

        Node234 init234 = read234(in);
        if (init234 == null)
        {
            System.out.printf("%s: ERROR: Cannot read the initial 234 node.\n", filename);
            in.close();
            return false;
        }
        writeDotFile(filename,0, init234.toDotFile(true));

        RedBlackNode root = RedBlackNode.buildFrom234(init234);
        writeDotFile(filename,1, root.toDotFile(true));

        int dotCount = 2;


        // read all of the commands from the input file.
        while (in.hasNext())
        {
            String cmd = in.next();

            if (cmd.equals("insert_td"))
            {
                String word = in.next();

                try
                {
                    root = root.insert_td(word);
                    System.out.printf("inserted '%s' OK\n", word);
                }
                catch (Exception e)
                {
                    System.out.printf("%s: ERROR: Could not insert '%s'\n", filename,word);
                    in.close();
                    return false;
                }

                writeDotFile(filename,dotCount, root.toDotFile(true));
                dotCount++;
            }
            else if (cmd.equals("insert_bu"))
            {
                String word = in.next();

                try
                {
                    root = root.insert_bu(word);
                    System.out.printf("inserted '%s' OK\n", word);
                }
                catch (Exception e)
                {
                    System.out.printf("%s: ERROR: Could not insert '%s'\n", filename,word);
                    in.close();
                    return false;
                }

                writeDotFile(filename,dotCount, root.toDotFile(true));
                dotCount++;
            }
            else if (cmd.equals("insert_dup"))
            {
                String word = in.next();

                try
                {
                    root.insert_bu(word);
                    System.out.printf("%s: ERROR: Insertion of the duplicate key '%s' was allowed!\n", filename,word);
                    in.close();
                    return false;
                }
                catch (Exception e)
                {
                    System.out.printf("GOOD: rejected the insertion of duplicate value '%s'\n", word);
                }
            }
            else if (cmd.equals("toArray"))
            {
                String[] expect = readStringArray(in);
                String[] actual = root.toArray();

                if (arrEq(expect,actual) == false)
                {
                    System.out.printf("%s: ERROR: toArray miscompare\n", filename);

                    System.out.print("  expected: ");
                    printArray(expect);

                    System.out.print("  actual  : ");
                    printArray(actual);

                    in.close();
                    return false;
                }
            }
            else if (cmd.equals("toArray_preOrder"))
            {
                String[] expect = readStringArray(in);
                String[] actual = root.toArray_preOrder();

                if (arrEq(expect,actual) == false)
                {
                    System.out.printf("%s: ERROR: toArray_preOrder miscompare\n", filename);

                    System.out.print("  expected: ");
                    printArray(expect);

                    System.out.print("  actual  : ");
                    printArray(actual);

                    in.close();
                    return false;
                }
            }
/*
			else if (cmd.equals("compare234"))
			{
				String expect =  read234(in).encode();
				String actual = root.to234().encode();

				if (expect.equals(actual) == false)
				{
					System.out.printf("%s: ERROR: compare234 mismatch.\n", filename);
					System.out.printf("  expected: %s\n", expect);
					System.out.printf("  actual  : %s\n", actual);

					in.close();
					return false;
				}
			}
*/
            else
            {
                System.out.println("ERROR: Unrecognized command in the file!  cmd="+cmd);
            }
        }
        in.close();


        System.out.printf("---- TESTCASE '%s' PASSED ----\n", filename);
        return true;
    }

    public static Node234 read234(Scanner in)
    {
        // the 234 tree is encoded as a set of comma-delimited
        // Strings.  Originally, I wanted a very flexible parser, but
        // it's not really worth the time.
        String buf = in.nextLine().trim();

// System.out.println(buf);

        if (buf.indexOf(' ') != -1)
            return null;
        if (buf.charAt(0) != '{' || buf.charAt(buf.length()-1) != '}')
            return null;
        buf = buf.substring(1, buf.length()-1);

        String[]   pieces = buf.split("[{]");
        String[][] input  = new String[pieces.length-1][];

// System.out.println(pieces.length);

        for (int i=1; i<pieces.length; i++)
        {
            String piece = pieces[i];
            int    len   = piece.length();
// System.out.println(piece);
// System.out.println(len);

            if (piece.length() < 1 || piece.charAt(len-1) != '}')
            {
                return null;
            }

            piece = piece.substring(0,len-1);
            input[i-1] = piece.split(",");
        }

/*
for (int i=0; i<input   .length; i++)
for (int j=0; j<input[0].length; j++)
	System.out.printf("%d,%d: '%s'\n", i,j, input[i][j]);
*/

        return Node234.build234Tree(input);
    }

    public static void writeDotFile(String basename, int dotCount, String data)
    {
        String name = String.format("%s-%02d.dot", basename, dotCount);

        try
        {
            PrintWriter out = new PrintWriter(new File(name));
            out.print(data);
            out.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.printf("ERROR: Cannot open the output file '%s'.\n", name);
        }
    }

    public static String[] readStringArray(Scanner in)
    {
        return in.nextLine().trim().split(" ");
    }

    public static boolean arrEq(String[] arr1, String[] arr2)
    {
        if (arr1.length != arr2.length)
            return false;

        for (int i=0; i<arr1.length; i++)
            if (arr1[i].equals(arr2[i]) == false)
                return false;

        return true;
    }

    public static void printArray(String[] arr)
    {
        System.out.print("{");

        for (int i=0; i<arr.length; i++)
        {
            if (i != 0)
                System.out.print(",");
            System.out.print(arr[i]);
        }

        System.out.println("}");
    }
}

