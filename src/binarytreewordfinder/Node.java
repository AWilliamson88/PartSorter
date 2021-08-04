package binarytreewordfinder;

public class Node {
    private String value;
    private Node left;
    private Node right;
    
    public Node (String value) {
        this.value = value;
    }
    
    public String getValue () {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public Node getLeftNode () {
        return this.left;
    }
    
    public void setLeftNode (Node left) {
        this.left = left;
    }
    
    public Node getRightNode () {
        return this.right;
    }
    
    public void setRightNode (Node right) {
        this.right = right;
    }
}
