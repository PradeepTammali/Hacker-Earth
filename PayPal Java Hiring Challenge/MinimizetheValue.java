
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

    public int levelOrderWeight(int h, Node root, int key) {
        int level = 0;
        int levelWeight = 0;
        for (int i = 1; i <= h; i++) {
            if (findLevel(h, root, key, i)) {
                level = i;
                break;
            }
        }
        if (level > 0) {
            levelWeight = findlevelWeight(root, level, key);
        }
        return levelWeight;
    }

    public boolean findLevel(int height, Node root, int key, int level) {
        boolean isFound = false;
        if (root == null)
            return false;
        if (level == 1) {
            if (root.key == key) {
                isFound = true;
            }
        } else if (level > 1) {
            isFound = findLevel(height, root.left, key, level - 1);
            if (!isFound) {
                isFound = findLevel(height, root.right, key, level - 1);
            }
        }
        return isFound;
    }

    public int findlevelWeight(Node root, int level, int key) {
        int weightOfLevel = 0;
        if (root == null)
            return 0;
        if (level == 1) {
            return root.value;
        } else if (level > 1) {
            weightOfLevel += findlevelWeight(root.left, level - 1, key);
            weightOfLevel += findlevelWeight(root.right, level - 1, key);
        }
        return weightOfLevel;
    }

    public void levelOrderInsert(int h, Node root, int key, int value) {
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
            return false;
        if (level == 1) {
            if (root.left == null) {
                root.left = new Node(newKey, newValue);
                isInserted = true;
            } else if (root.right == null) {
                root.right = new Node(newKey, newValue);
                isInserted = true;
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
        if (node == null)
            return false;
        if (node.key == u) {
            if (node.left == null) {
                node.left = new Node(v, values[v - 1]);
                isInserted = true;
            } else {
                node.right = new Node(v, values[v - 1]);
                isInserted = true;
            }
        } else if (node.key == v) {
            if (node.left == null) {
                node.left = new Node(u, values[u - 1]);
                isInserted = true;
            } else {
                node.right = new Node(u, values[u - 1]);
                isInserted = true;
            }
        } else {
            isInserted = searchInsert(node.left, u, v, values);
            if (!isInserted) {
                isInserted = searchInsert(node.right, u, v, values);
            }
        }
        return isInserted;
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
                if (u < v) {
                    tree.root = new Node(u, values[u - 1]);
                    tree.root.left = new Node(v, values[v - 1]);
                } else {
                    tree.root = new Node(v, values[v - 1]);
                    tree.root.left = new Node(u, values[u - 1]);
                }
            } else {
                tree.searchInsert(tree.root, u, v, values);
            }
        }
        int intialWeight = X;
        for (int i : values) {
            intialWeight += i;
        }
        int h = tree.heightOfTree(tree.root);
        tree.levelOrderInsert(h, tree.root, N + 1, X);
        int branchWeight = tree.levelOrderWeight(h, tree.root, N + 1);
        int finalWeight = branchWeight + intialWeight;
        System.out.println(finalWeight);
        s.close();
    }

}