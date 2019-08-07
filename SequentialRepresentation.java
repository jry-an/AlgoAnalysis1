import java.io.PrintWriter;
/**
 * Sequential Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Yongli Ren, 2019.
 */
public class SequentialRepresentation<T> implements BSPTree<T> {
    /**
     * Constructs empty graph.
     */
    protected Node rootNode;
    protected int size;

    public SequentialRepresentation() {
        // Implement me!
        rootNode = null;
        size = 0;
    } // end of SequentialRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        rootNode = new Node(nodeLabel);
        size++;

    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        // Implement me!
    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        // Implement me!
        return false;
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {
        // Implement me!
        return null;
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
        // Implement me!
        return null;
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


        public Node(T vertLabel) {
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

} // end of class SequentialRepresentation



