
/* Class containing left and right child of current 
   node and key value*/

import java.util.Scanner;
import java.util.Arrays;

class Node {
    int key, value;
    Node left, right;

    public Node(int item, int itemVal) {
        key = item;
        value = itemVal;
        left = right = null;
    }
}

class MinimizetheValue {
    Node root;

    MinimizetheValue() {
        root = null;
    }

    public int heightOfTree(Node root) {
        if (root == null)
            return 0;
        else {
            /* compute height of each subtree */
            int lheight = heightOfTree(root.left);
            int rheight = heightOfTree(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }

    public void levelOrderInsert(Node root, int key, int value) {
        int h = heightOfTree(root);
        int i;
        for (i = 1; i <= h; i++)
            if (addNewNode(root, i, key, value)) {
                break;
            }
    }

    /* add new nodes at a level */
    public boolean addNewNode(Node root, int level, int newKey, int newValue) {
        boolean isInserted = false;
        if (root == null)
            return isInserted;
        if (level == 1) {
            if (root.left == null) {
                root.left = new Node(newKey, newValue);
                isInserted = true;
            } else if (root.right == null) {
                root.right = new Node(newKey, newValue);
                isInserted = false;
            }
        } else if (level > 1) {
            isInserted = addNewNode(root.left, level - 1, newKey, newValue);
            if (!isInserted) {
                isInserted = addNewNode(root.right, level - 1, newKey, newValue);
            }
        }
        return isInserted;
    }

    public boolean searchInsert(Node node, int u, int v, int[] values) {
        boolean isInserted = false;
        if (node.key == u) {
            if (node.left == null) {
                node.left = new Node(v, values[v - 1]);
                isInserted = true;
                return isInserted;
            } else {
                node.right = new Node(v, values[v - 1]);
                isInserted = true;
                return isInserted;
            }
        } else {
            return searchInsert(node.left, u, v, values) || searchInsert(node.right, u, v, values);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] N_X = s.nextLine().split(" ");
        int N = Integer.parseInt(N_X[0]);
        int X = Integer.parseInt(N_X[1]);
        int[] values = Arrays.asList(s.nextLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
        MinimizetheValue tree = new MinimizetheValue();
        for (int i = 0; i < N - 1; i++) {
            String[] u_v = s.nextLine().split(" ");
            int u = Integer.parseInt(u_v[0]);
            int v = Integer.parseInt(u_v[1]);
            if (tree.root == null) {
                tree.root = new Node(u, values[u - 1]);
                tree.root.left = new Node(v, values[v - 1]);
            } else {
                tree.searchInsert(tree.root, u, v, values);
            }
        }
        tree.levelOrderInsert(tree.root, N + 1, X);
        s.close();
    }

}