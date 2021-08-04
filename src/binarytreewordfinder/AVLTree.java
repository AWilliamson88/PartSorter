//AVL Tree code is referenced from document provided on blackboard
package binarytreewordfinder;

public class AVLTree {

    private Node root;

    public void add(String value) {
        Node newItem = new Node(value);
        if (root == null) {
            root = newItem;
        } else {
            root = addRecursive(root, newItem);
        }
    }

    private Node addRecursive(Node current, Node n) {
        if (current == null) {
            current = n;
            return current;
        } else if (n.getValue().compareTo(current.getValue()) < 0) {
            current.setLeftNode(addRecursive(current.getLeftNode(), n));
            current = balanceTree(current);
        } else if (n.getValue().compareTo(current.getValue()) > 0) {
            current.setRightNode(addRecursive(current.getRightNode(), n));
            current = balanceTree(current);
        }
        return current;
    }

    public void delete(String target) {
        root = deleteNode(root, target);
    }

    private Node deleteNode(Node current, String target) {
        Node parent;
        if (current == null) {
            System.out.println(target + " can't be found");
            return null;
        } else {
            String lowTarget = target.toLowerCase();
            String lowCurrent = current.getValue().toLowerCase();
            if (lowTarget.compareTo(lowCurrent) < 0) {
                current.setLeftNode(deleteNode(current.getLeftNode(), target));
                if (balanceFactor(current) == - 2) {
                    if (balanceFactor(current.getRightNode()) <= 0) {
                        current = rotateRR(current);
                    } else {
                        current = rotateRL(current);
                    }
                }
            } else if (lowTarget.compareTo(lowCurrent) > 0) {
                current.setRightNode(deleteNode(current.getRightNode(), target));
                if (balanceFactor(current) == 2) {
                    if (balanceFactor(current.getLeftNode()) >= 0) {
                        current = rotateLL(current);
                    } else {
                        current = rotateLR(current);
                    }
                }
            } else {
                System.out.println(target + " removed from tree");
                if (current.getRightNode() != null) {
                    parent = current.getRightNode();
                    while (parent.getLeftNode() != null) {
                        parent = parent.getLeftNode();
                    }
                    current.setValue(parent.getValue());
                    current.setRightNode(deleteNode(current.getRightNode(), parent.getValue()));
                    if (balanceFactor(current) == 2) {
                        if (balanceFactor(current.getLeftNode()) >= 0) {
                            current = rotateLL(current);
                        } else {
                            current = rotateLR(current);
                        }
                    }
                } else {
                    return current.getLeftNode();
                }
                
            }
        }
        return current;
    }

    private Node balanceTree(Node current) {
        int bFactor = balanceFactor(current);
        if (bFactor > 1) {
            if (balanceFactor(current.getLeftNode()) > 0) {
                current = rotateLL(current);
            } else {
                current = rotateLR(current);
            }
        } else if (bFactor < -1) {
            if (balanceFactor(current.getRightNode()) > 0) {
                current = rotateRL(current);
            } else {
                current = rotateRR(current);
            }
        }
        return current;
    }

    private int balanceFactor(Node current) {
        int l = getHeight(current.getLeftNode());
        int r = getHeight(current.getRightNode());
        int bFactor = l - r;
        return bFactor;
    }

    private Node rotateRR(Node parent) {
        Node pivot = parent.getRightNode();
        parent.setRightNode(pivot.getLeftNode());
        pivot.setLeftNode(parent);
        return pivot;
    }

    private Node rotateLL(Node parent) {
        Node pivot = parent.getLeftNode();
        parent.setLeftNode(pivot.getRightNode());
        pivot.setRightNode(parent);
        return pivot;
    }

    private Node rotateLR(Node parent) {
        Node pivot = parent.getLeftNode();
        parent.setLeftNode(rotateRR(pivot));
        return rotateLL(parent);
    }

    private Node rotateRL(Node parent) {
        Node pivot = parent.getRightNode();
        parent.setRightNode(rotateLL(pivot));
        return rotateRR(parent);
    }

    private int max(int l, int r) {
        return l > r ? l : r;
    }

    private int getHeight(Node current) {
        int height = 0;
        if (current != null) {
            int l = getHeight(current.getLeftNode());
            int r = getHeight(current.getRightNode());
            int m = max(l, r);
            height = m + 1;
        }
        return height;
    }

    public void find(String key) {
        if (findRecursive(key, root) != null) {
            if (findRecursive(key, root).getValue().equalsIgnoreCase(key)) {
                System.out.println(key + " was found!");
            }
        } else {
            System.out.println(key + " was not found!");
        }
    }

    private Node findRecursive(String target, Node current) {
        if (current != null) {
            String lowTarget = target.toLowerCase();
            String lowCurrent = current.getValue().toLowerCase();
            if (lowTarget.compareTo(lowCurrent) < 0) {
                if (target.equalsIgnoreCase(current.getValue())) {
                    return current;
                } else {
                    return findRecursive(target, current.getLeftNode());
                }
            } else {
                if (target.equalsIgnoreCase(current.getValue())) {
                    return current;
                } else {
                    return findRecursive(target, current.getRightNode());
                }
            }
        }
        return null;
    }

    public void display() {
        if (root == null) {
            System.out.println("Tree is empty try adding some data!");
            return;
        }
        System.out.println("Root: " + this.root.getValue());
        System.out.println(displayInOrder(root));
    }

    private String displayInOrder(Node current) {
        String s = "";
        if (current != null) {
            s = displayInOrder(current.getLeftNode());
            System.out.println(current.getValue());
            s = displayInOrder(current.getRightNode());
            //Console.Write("({0}) ", current.data);
        }
        return s;
    }
    
    public String getRootValue() {
        return root.getValue();
    }
}
