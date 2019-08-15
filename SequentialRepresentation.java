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

    /*
    parent	(i - 1) / 2
    left	2 * i + 1
    right	2 * i + 2
    */

   protected T[] binaryTreeArray;

    public SequentialRepresentation() {
        binaryTreeArray = (T[]) new Object[100];
    } // end of SequentialRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        if (binaryTreeArray[0] != EMPTY_NODE){
            binaryTreeArray [0] = nodeLabel;
        } else {
            System.out.println("Root already set");
        }

    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        // Implement me!
        //if node exists, then check if left and right children aren't already set
        if(findNode(srcLabel)){
            //get position of srcLabel
           int nodePos =  getNodePosition(srcLabel);
           binaryTreeArray[2 * nodePos + 1] = leftChild;
           binaryTreeArray[2 * nodePos + 2] = rightChild;
        }

    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        // Implement me!
        for (int i = 0; i < binaryTreeArray.length; i++ ){
            if (binaryTreeArray[i] == nodeLabel){
                return true;
            }
        }
        return false;
    } // end of findNode



    @Override
    public String findParent(T nodeLabel) {
        // Implement me!
        //not sure if right, need to figure out what to return
        String parent;
        for (int i = 0; i < binaryTreeArray.length; i++){
            if (binaryTreeArray[i] == nodeLabel){
                parent = binaryTreeArray[i-1/2].toString();
                return parent;
            }
        }
        return null;
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
        // Implement me!
        String leftChild,rightChild;
       int nodePos =  getNodePosition(nodeLabel);
       leftChild =  binaryTreeArray[2 * nodePos + 1].toString();
       rightChild = binaryTreeArray[2 * nodePos + 2].toString();
        return (leftChild + " " + rightChild);
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


    private int getNodePosition(T nodeLabel){
        for (int i = 0; i < binaryTreeArray.length; i++ ){
            if (binaryTreeArray[i] == nodeLabel){
                return i;
            }
        }
        return 0;
    }

} // end of class SequentialRepresentation



