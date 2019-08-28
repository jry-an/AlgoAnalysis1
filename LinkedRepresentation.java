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
public class LinkedRepresentation<T> implements BSPTree<T> {

    /**
     * Constructs empty tree.
     */

    protected Node rootNode;
    protected int size;

    public LinkedRepresentation() {
        rootNode = null;
        size = 0;

    } // end of LinkedRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        rootNode = new Node(nodeLabel);
        size++;
    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        Node node = new Node(srcLabel);
        if (findNode(srcLabel)) {
            if (leftChild != null){
                node.setLeftChild(new Node(leftChild));
                size++;
            }
            if (rightChild != null){
                node.setRightChild(new Node(rightChild));
                size++;
            }
        }
    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {

        //TODO - test
        Node temp = rootNode;
        Node goal = new Node(nodeLabel);
        if(nodeLabel == null){
            return false;
        }
        if (temp.getVertLabel() == nodeLabel){
            return true;
        } else{
            if(findNodeRec(temp, goal)){
                return true;
            }
        }
        return false;
    } // end of findNode

    private boolean findNodeRec(Node currNode, Node goal){
        //TODO - test
        if(currNode.getLeftChild() != null){
            if (currNode.getLeftChild().getVertLabel() == goal.getVertLabel()){
                return true;
            } else{
                findNodeRec(currNode.getLeftChild(),goal);
            }
        }
        if(currNode.getRightChild() != null){
            if (currNode.getRightChild().getVertLabel() == goal.getVertLabel()){
                return true;
            } else{
                findNodeRec(currNode.getRightChild(),goal);
            }
        }
        return false;
    }



    @Override
    public String findParent(T nodeLabel) {
        // Implement me!
        Node child = new Node(nodeLabel);
        Node temp = rootNode;
        Node parent;
        if (nodeLabel == null){
            System.err.println("Find parent given NULL nodeLabel!!");
            return "nodeLabel given is NULL";
        }
        parent = findParentRec(child,temp);
        if (parent.getVertLabel() == null){
            return nodeLabel.toString();
        }
        return nodeLabel.toString() + "" + parent.getVertLabel().toString();
    } // end of findParent



    private Node findParentRec(Node goal,Node temp){
    //TODO - test
        if(temp.getLeftChild() != null){
            if (temp.getLeftChild().getVertLabel() == goal.getVertLabel()){
                return temp;
            } else{
                findNodeRec(temp.getLeftChild(),goal);
            }
        }
        if(temp.getRightChild() != null){
            if (temp.getRightChild().getVertLabel() == goal.getVertLabel()){
                return temp;
            } else{
                findNodeRec(temp.getRightChild(),goal);
            }
        }
        return null;
    }

    @Override
    public String findChildren(T nodeLabel) {
        // Implement me!
        if(findNode(nodeLabel)){
            Node temp = new Node(nodeLabel);
            String leftChild = temp.getLeftChild().getVertLabel().toString();
            String rightChild = temp.getRightChild().getVertLabel().toString();
            return nodeLabel.toString() + "" + leftChild + "" + rightChild;
        }
        return nodeLabel.toString();
    } // end of findParent

    @Override
    public void printInPreorder(PrintWriter writer) {
        // Implement me!
    } // end of printInPreorder

    @Override
    public void printInInorder(PrintWriter writer) {
        // Implement me!
    } // end of printInInorder

    @Override
    public void printInPostorder(PrintWriter writer) {
        // Implement me!
    } // end of printInPostorder


    protected class Node{
        protected T vertLabel;
        protected Node rightChild, leftChild, parent;


        protected Node(T vertLabel) {
            this.vertLabel = vertLabel;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
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

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

    }

} // end of class LinkedRepresentation
