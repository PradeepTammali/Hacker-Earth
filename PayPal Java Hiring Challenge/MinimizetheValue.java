/* Class containing left and right child of current 
   node and key value*/
class Node 
{ 
    int key, value; 
    Node left, right; 
  
    public Node(int item, int itemVal) 
    { 
        key = item; 
        value = itemVal;
        left = right = null; 
    } 
}
class BinaryTree
{
    Node root;
    BinaryTree()
    {
        root = null;   
    }

    //Scanner
    Scanner s = new Scanner(System.in);
    int N, X = scanner.nextLine().split(" "); // Reading input from STDIN
    System.out.println("Nodes:"+N+",New node:"+X);
} 