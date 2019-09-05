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
	private boolean isFound;
	private Node desiredNode;
    protected Node rootNode;
    protected int size;

    public LinkedRepresentation() {
        rootNode = null;
    } // end of LinkedRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        rootNode = new Node(nodeLabel, null);
        System.err.println(rootNode.getVertLabel());
    } // end of setRootNode()

    @SuppressWarnings("unchecked")
    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        Node node = rootNode;
        if (findNode(srcLabel)) {

            if (leftChild == null){
                System.err.println("Left child of " + srcLabel + " is null");
                leftChild = (T) EMPTY_NODE;
            }
            if (rightChild == null){
                System.err.println("Right child of " + srcLabel + " is null");
                rightChild = (T) EMPTY_NODE;
            }
            node.setLeftChild(new Node(leftChild, node));
            node.setRightChild(new Node(rightChild, node));

        }
        else{
            System.err.println("Find node failed");
        }
    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        //TODO - test
        Node start = rootNode;
        Node goal = new Node(nodeLabel, null);
        if(nodeLabel == null){
            return false;
        }
        isFound = false;
        findNodeRec(goal, start);
        
        return isFound;
    } // end of findNode

    private void findNodeRec(Node goal, Node temp){
        System.err.println("root = " + temp.getVertLabel().toString());

        if (temp.getVertLabel().toString().equals(goal.getVertLabel().toString())){
        	desiredNode = temp;
        	isFound = true;
        } 
        else {
        	Node left = temp.getLeftChild();
        	if (left != null) {
        		System.out.println("left = " + temp.getLeftChild().getVertLabel().toString());
        		findNodeRec(goal, left);
        	}

        	Node right = temp.getRightChild();
        	if (right != null) {
        		System.out.println("right = " + temp.getRightChild().getVertLabel().toString());
        		findNodeRec(goal, right);
        	}
        	
        	if (right == null && left == null) {
        		return;
        	}
        }
    }

    @Override
    public String findParent(T nodeLabel) {
        
        if (nodeLabel == null){
            System.err.println("Find parent given NULL nodeLabel!!");
            return "nodeLabel given is NULL";
        }

        if (findNode(nodeLabel)) {
        	Node parent = desiredNode.getParent();
            
            if (parent != null){
                return (nodeLabel.toString() + " " + parent.getVertLabel().toString());
            }
        }
        return nodeLabel.toString();
        
    } // end of findParent


    @Override
    public String findChildren(T nodeLabel) {
        // Implement me!
        if(findNode(nodeLabel)){
            if (desiredNode.getLeftChild() != null && desiredNode.getRightChild() != null) {
                String leftChild = desiredNode.getLeftChild().getVertLabel().toString();
                String rightChild = desiredNode.getRightChild().getVertLabel().toString();
                
                return nodeLabel.toString() + " " + leftChild + " " + rightChild;
            }
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
        protected Node parent;


        protected Node(T vertLabel, Node parent) {
            this.vertLabel = vertLabel;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = parent;
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

    }

} // end of class LinkedRepresentation
