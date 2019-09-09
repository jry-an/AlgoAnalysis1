import java.io.PrintWriter;

/**
 * Linked Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016. 
 * @author Yongli Ren, 2019.
 */
public class LinkedRepresentation <T> implements BSPTree <T> {

    private Node foundNode;
    private String nodeParent;
    private boolean isFound;
    protected Node rootNode;
    protected int size;
    private int counter;
    private T[] tempArray;

    public LinkedRepresentation() {
        rootNode = null;
        size = 0;
    } // end of LinkedRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        if (rootNode == null) {
            rootNode = new Node(nodeLabel);
            size += 1;
        }
    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        //check if srcLabel exists in tree
        if (findNode(srcLabel)) {
            //check if left and right child nodes are not already set
            if (foundNode.getLeftChild() == null && foundNode.getRightChild() == null) {
                //check if left and right child's passed through function are not null
                if (leftChild != null && rightChild != null) {
                    //if all true, set the foundNode's children to new Nodes
                    foundNode.setLeftChild(new Node(leftChild));
                    foundNode.setRightChild(new Node(rightChild));
                    size += 2;
                }
            }
        }
    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        //traverses the tree then sets foundNode equal to the Node found.
        Node start = rootNode;
        if (nodeLabel != null) {
            String goal = nodeLabel.toString();
            foundNode = null;
            isFound = false;
            recFindNode(start, goal);
        }
        return isFound;
    } // end of findNode

    private void recFindNode(Node temp, String goal) {
        String tempLabel;
        //check base case (rootNode = goal)
        tempLabel = temp.getVertLabel().toString();
        if (tempLabel.equals(goal)) {
            isFound = true;
            foundNode = temp;
            return;
        }
        // recurring call
        else {
            Node left = temp.getLeftChild();
            Node right = temp.getRightChild();

            //check if left/right are null
            if (right == null && left == null) {
                return;
            }
            //check left tree
            if (left != null) {
                recFindNode(left, goal);
            }

            //check right tree
            if (right != null) {
                recFindNode(right, goal);
            }
        }

    }

    @Override
    public String findParent(T nodeLabel) {
        String goalChild = nodeLabel.toString();
        Node start = rootNode;
        nodeParent = null;

        //if node is in tree
        if (findNode(nodeLabel)) {
            //starting from the root node,
            //return parent when child node = nodeLabel
            recFindParent(start, goalChild);

        }
        if (nodeParent == null) {
            return nodeLabel.toString();
        }
        return nodeLabel.toString() + " " + nodeParent;
    } // end of findParent


    private void recFindParent(Node temp, String nodeLabel) {
        if (temp.getLeftChild() != null && temp.getRightChild() != null) {
            //check if left child matches nodeLabel
            String left = temp.getLeftChild().getVertLabel().toString();
            if (left.equals(nodeLabel)) {
                nodeParent = temp.getVertLabel().toString();
            }
            //recur until child found
            recFindParent(temp.getLeftChild(), nodeLabel);

            //check if right child matches nodeLabel
            String right = temp.getRightChild().getVertLabel().toString();
            if (right.equals(nodeLabel)) {
                nodeParent = temp.getVertLabel().toString();
            }
            //recur until child found
            recFindParent(temp.getRightChild(), nodeLabel);
        }
    }

    @Override
    public String findChildren(T nodeLabel) {
        //check if nodeLabel exists in Tree
        if (findNode(nodeLabel)) {
            //check if node has no children
            if (foundNode.getLeftChild() != null && foundNode.getRightChild() != null) {
                String left = foundNode.getLeftChild().getVertLabel().toString();
                String right = foundNode.getRightChild().getVertLabel().toString();
                return nodeLabel.toString() + " " + left + " " + right;
            }
        }
        return nodeLabel.toString();
    } // end of findParent


    @SuppressWarnings("unchecked")
    @Override
    public void printInPreorder(PrintWriter writer) {
  /*
        1. Visit the root.
        2. Traverse the left subtree
        3. Traverse the right subtree
        */
        Node start = rootNode;
        counter = 0;
        tempArray = (T[]) new Object[size];

        // check if Tree is empty (no root node)
        if (rootNode != null) {
            // run preOrder recursive algorithm starting from rootNode (start)
            preOrder(start);
        }

        // iterate through tempArray and print nodes in Preorder
        for (int i = 0; i < size; i++) {
            if (tempArray[i] == null) {
                break;
            } else {
                writer.print(tempArray[i].toString() + " ");
            }
        }
        writer.println();



    } // end of printInPreorder
    public void preOrder(Node node) {
        Node left = node.getLeftChild();
        Node right = node.getRightChild();

        tempArray[counter] = node.getVertLabel();
        counter++;

        //check if children exist
        if (right == null && left == null) {
            return;
        }

        //check left tree
        if (left != null) {
            preOrder(left);
        }

        //check right tree
        if (right != null) {
            preOrder(right);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void printInInorder(PrintWriter writer) {
  /*
        1. Traverse the left subtree
        2. Visit the root.
        3. Traverse the right subtree
        */
        Node start = rootNode;
        counter = 0;
        tempArray = (T[]) new Object[size];

        // check if Tree is empty (no root node)
        if (rootNode != null) {
            // run inOrder recursive algorithm starting from rootNode (start)
            inOrder(start);
        }
        // iterate through tempArray and print nodes in Inorder
        for (int i = 0; i < size; i++) {
            if (tempArray[i] == null) {
                break;
            } else {
                writer.print(tempArray[i].toString() + " ");
            }
        }
        writer.println();
    } // end of printInInorder

    public void inOrder(Node node) {
        Node left = node.getLeftChild();
        Node right = node.getRightChild();
        //check left
        if (left != null) {
            inOrder(left);
        }
        // visit root
        tempArray[counter] = node.getVertLabel();
        counter++;

        // check right
        if (right != null) {
            inOrder(right);
        }
    } // end of inOrder

    @SuppressWarnings("unchecked")
    @Override
    public void printInPostorder(PrintWriter writer) {
  /*
        1. Traverse the left subtree
        2. Traverse the right subtree
        3. Visit the root.
        */
        Node start = rootNode;
        counter = 0;
        tempArray = (T[]) new Object[size];

        // check if Tree is empty (no root node)
        if (rootNode != null) {
            // run postOrder recursive algorithm starting from rootNode (start)
            postOrder(start);
        }
        // iterate through tempArray and print nodes in Postorder
        for (int i = 0; i < size; i++) {
            if (tempArray[i] == null) {
                break;
            } else {
                writer.print(tempArray[i].toString() + " ");
            }
        }
        writer.println();
    } // end of printInPostorder

    public void postOrder(Node node) {
        Node left = node.getLeftChild();
        Node right = node.getRightChild();

        // check left
        if (left != null) {
            postOrder(left);
        }

        // check right
        if (right != null) {
            postOrder(right);
        }

        // visit root
        tempArray[counter] = node.getVertLabel();
        counter++;
    } // end of postOrder


    protected class Node {
        protected T vertLabel;
        protected Node rightChild, leftChild;

        protected Node(T vertLabel) {
            this.vertLabel = vertLabel;
            this.leftChild = null;
            this.rightChild = null;
        }

        public T getVertLabel() {
            return vertLabel;
        }

        public void setVertLabel(T vertLabel) {
            this.vertLabel = vertLabel;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

    } // end of class Node

} // end of class LinkedRepresentation