package BST;


import Main.Program;

public class BST {
    private Node root;
    public int numOfSameValues = 0;


    public void insert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            this.root=insert(this.root, arr[i]);
        }
    }

    private Node insert(Node node, int value) {
        Program.numOfCompares++;
        if (node == null)
            return new Node(value);
        Program.numOfCompares++;
        if (value < node.value) {
            node.left = insert(node.left, value);
            Program.numOfAssigning++;
        } else if (value > node.value) {
            Program.numOfCompares++;
            Program.numOfAssigning++;
            node.right = insert(node.right, value);
        } else {
            Program.numOfCompares++;
            Program.numOfCompares++;
            Program.numOfAssigning++;
            numOfSameValues++;
        }
        return node;
    }
}