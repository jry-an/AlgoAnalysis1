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
    left	2 * i
    right	2 * i + 1
    */

   protected T[] binaryTreeArray;

    public SequentialRepresentation() {
        binaryTreeArray = (T[]) new Object[100];
    } // end of SequentialRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        if (binaryTreeArray[1] != EMPTY_NODE){
            binaryTreeArray [1] = nodeLabel;
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
           binaryTreeArray[2 * nodePos] = leftChild;
           binaryTreeArray[2 * nodePos + 1] = rightChild;
        }

    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        // Implement me!
        for (int i = 1; i < binaryTreeArray.length-1; i++ ){
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
        for (int i = 1; i < binaryTreeArray.length-1; i++){
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
       int nodePos =  getNodePosition(nodeLabel);
       if (nodePos == 0) {
           System.err.println("No node found");
           return null;
       } else {
           //leftChild =  binaryTreeArray[2 * nodePos];
         //  rightChild = binaryTreeArray[2 * nodePos + 1];

           if (binaryTreeArray[2 * nodePos] != EMPTY_NODE && binaryTreeArray[2 * nodePos + 1] != EMPTY_NODE) {
               return (nodeLabel + " " + binaryTreeArray[2 * nodePos] + " " + binaryTreeArray[2 * nodePos + 1]);
           } else if(binaryTreeArray[2 * nodePos] != EMPTY_NODE && binaryTreeArray[2 * nodePos + 1] == EMPTY_NODE){
               return (nodeLabel + " " + binaryTreeArray[2 * nodePos]);
           }
           else if(binaryTreeArray[2 * nodePos] == EMPTY_NODE && binaryTreeArray[2 * nodePos + 1] != EMPTY_NODE){
               return (nodeLabel + " " + " " + binaryTreeArray[2 * nodePos + 1]);
           } else if (binaryTreeArray[2 * nodePos] == EMPTY_NODE && binaryTreeArray[2 * nodePos + 1] == EMPTY_NODE){
               return (nodeLabel + " ");
           }
       }
       return null;
    } // end of findParent

    @Override
    public void printInPreorder(PrintWriter writer) {
        // Implement me!
        /*
        1. Visit the root.
        2. Traverse the left subtree
        3. Traverse the right subtree
        */
//        T left,right;
//            int index = 0;
//            if(binaryTreeArray[0] == null) {
//                return;
//            }
//                while (index <= binaryTreeArray.length) {
//                    T node = binaryTreeArray[index];
//                    left = binaryTreeArray[2 * index];
//                    right = binaryTreeArray[2 * index + 1];
//
//
//                    printInPreorder(writer);
//
//                    index++;
//                }


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
        for (int i = 1; i < binaryTreeArray.length-1; i++ ){
            if (binaryTreeArray[i] == nodeLabel){
                return i;
            }
        }
        return 0;
    }

} // end of class SequentialRepresentation



