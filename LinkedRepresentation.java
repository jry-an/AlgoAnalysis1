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
    } // end of LinkedRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        rootNode = new Node(nodeLabel);
        System.err.println(rootNode.getVertLabel());
    } // end of setRootNode()

    @SuppressWarnings("unchecked")
    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        Node node = new Node(srcLabel);
        if (findNode(srcLabel)) {

            if (leftChild == null){
                System.err.println("Left child of " + srcLabel + " is null");
                leftChild = (T) EMPTY_NODE;
            }
            if (rightChild == null){
                System.err.println("Right child of " + srcLabel + " is null");
                rightChild = (T) EMPTY_NODE;
            }
                node.setLeftChild(new Node(leftChild));
            System.err.println(node.getLeftChild().getVertLabel());


            node.setRightChild(new Node(rightChild));
            System.err.println(node.getRightChild().getVertLabel());

        }
        else{
            System.err.println("Find node failed");
        }
    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        //TODO - test
        Node start = rootNode;
        Node goal = new Node(nodeLabel);
        if(nodeLabel == null){
            return false;
        }
        return findNodeRec(goal, start);
    } // end of findNode

    private boolean findNodeRec(Node goal, Node temp){
        //TODO - err node left and right are null


        System.err.println(temp.getVertLabel() + " , " + goal.getVertLabel());

        if (temp.getVertLabel().toString().equals(goal.getVertLabel().toString())){
            return true;
        } else{

          //  System.out.println(temp.getLeftChild().getVertLabel() + " , " + temp.getRightChild().getVertLabel());

            //recurs
            if (temp.getLeftChild() != null){
                if (temp.getLeftChild().getVertLabel() != EMPTY_NODE){
                    findNodeRec(goal,temp.getLeftChild());

                    System.out.println(temp.getLeftChild().getVertLabel() + " , " + goal.getVertLabel());

                }
            }
            else {
                System.err.println("temp left = null");
            }

            if (temp.getRightChild() != null) {
                if (temp.getRightChild().getVertLabel() != EMPTY_NODE) {
                    System.out.println("here");
                    findNodeRec(goal,temp.getRightChild());
                    System.out.println(temp.getVertLabel() + " , " + goal.getVertLabel());
                }
            }
            else {
                System.err.println("temp right = null");
            }
        }
        return false;
    }

    @Override
    public String findParent(T nodeLabel) {
        //TODO - test
        Node parent;
        if (nodeLabel == null){
            System.err.println("Find parent given NULL nodeLabel!!");
            return "nodeLabel given is NULL";
        }
        if (nodeLabel.toString().equals(rootNode.getVertLabel().toString())){
            return "nodeLabel is root node. Root node has no parent";
        }


        parent = findParentRec(nodeLabel, nodeLabel);
        if (parent == null){
            return nodeLabel.toString();
        } else{
            return nodeLabel.toString() + "" + parent.getVertLabel().toString();
        }
    } // end of findParent



    private Node findParentRec(T nodeLabel, T tempNode){
//        Node goal = new Node(nodeLabel);
//        Node tempNode =
//        if(nodeLabel == null){
//            System.err.println("nodeLabel == null");
//            return null;
//        }
//        if (goal.getLeftChild().getVertLabel().toString().equals()){
//            return true;
//        } else{
//            if (tempNode.getLeftChild() != null){
//                findNode(tempNode.getLeftChild().getVertLabel());
//            }
//            if (tempNode.getRightChild() != null) {
//                findNode(tempNode.getRightChild().getVertLabel());
//            }
//        }
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

    @SuppressWarnings("unchecked")
    @Override
    public void printInPreorder(PrintWriter writer) {
        // Implement me!
        Node start = rootNode;

        if (rootNode == null || rootNode == (T)EMPTY_NODE){
            return;
        }

        preOrder(start);


    } // end of printInPreorder

    public void preOrder(Node node){

    }

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

    }

} // end of class LinkedRepresentation
