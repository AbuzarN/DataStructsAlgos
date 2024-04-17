import java.io.BufferedReader;
import java.io.InputStreamReader;
class MySearchTree <T extends Comparable<T>> {
    public static void main(String[] args) throws Exception {
        MySearchTree<String> tree = new MySearchTree<>();
        String input;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       while ( (input =in.readLine()) != null)
       {
            switch ( input.charAt(0) )
            {
                case 'A':
                    tree.add(input.substring(2));
                    break; 
                case 'C':
                    tree.find(input.substring(2));
                    break;
                case 'L':
                    tree.countLeaf();
                    break;
                case 'P':
                    tree.countParent();
                    break;
                case 'T':
                    tree.countTwoChildren();
                    break;
                case 'D':
                    tree.preOrderPrint();
                    break;

            }
       }
    }

    private Node<T> root;

    MySearchTree() {
        root = null;
    }

    public void add(T payload) {
        root = addRecursive(root, payload);
    }

    private Node<T> addRecursive(Node<T> node, T payload){
        if (node == null){
            return new Node<>(payload);
        }else if (payload.compareTo(node.payload) < 0){
            node.left = addRecursive(node.left, payload);
        }else{
            node.right = addRecursive(node.right, payload);
        }
        return node;
    }

    public void countLeaf(){
        System.out.println(countLeafRecursive(root));
    }
    
    public int countLeafRecursive (Node<T> node){
        if (node == null){
            return 0;
        }
        else if (node.left == null && node.right == null){
            return 1;
        }
        return countLeafRecursive(node.left) + countLeafRecursive(node.right);
    }

    public void countParent() {
        System.out.println( countParentRecursive(root) );
    }

    private int countParentRecursive(Node<T> node) {
        if (node == null) {
            return 0;
        }else if (node.left != null || node.right != null) {
            return 1 + countParentRecursive(node.left) + countParentRecursive(node.right);
        }else{
        return 0;
        }
    }

    public void countTwoChildren(){
        System.out.println(countTwoChildrenRecursive(root));
    }

    private int countTwoChildrenRecursive(Node<T> node){
        if (node == null) {
            return 0;
        }

        if (node.left != null && node.right != null) {
            return 1 + countTwoChildrenRecursive(node.left) + countTwoChildrenRecursive(node.right);
        }

        return countTwoChildrenRecursive(node.left) + countTwoChildrenRecursive(node.right);
    }

    public void find(T payload){
        if (findRecursive(root, payload)){
            System.out.println("Found");
        }else{
            System.out.println("Not Found");
        }
    }

    private boolean findRecursive(Node<T> node, T payload) {
        if (node == null) {
            return false;
        }else if (payload.compareTo(node.payload) == 0) {
            return true;
        }else if (payload.compareTo(node.payload) < 0 ){
            return findRecursive(node.left, payload);
        }else{
            return findRecursive(node.right, payload);
         }
    }

    public void preOrderPrint() {
        preOrderPrintRecursive(root);
        System.out.println();
    }

    private void preOrderPrintRecursive(Node<T> node) {
        if (node != null) {
            System.out.print(node.payload + " ");
            preOrderPrintRecursive(node.left);
            preOrderPrintRecursive(node.right);
        }
    }
    
    private static class Node<T> {
        T payload;
        Node<T> left;
        Node<T> right;
        Node(T payload) {
            this.payload = payload;
            this.left = null;
            this.right = null;
        }
    }
}