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
    A B C D E F G
    left	2 * i + 1
    right	2 * i + 2
    javac -cp .:jopt-simple-5.0.2.jar:sample.jar *.java
    python assign1TestScript.py -v ../AlgoAnal1  seqtree tests/test1.in

    */

   protected T[] binaryTreeArray;
   protected T[] tempArray;
   protected int counter = 0;

    private final int TEST_SIZE = 100;

    public SequentialRepresentation() {
        binaryTreeArray = (T[]) new Object[TEST_SIZE];
    } // end of SequentialRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        if (binaryTreeArray[0] == null){
            binaryTreeArray [0] = nodeLabel;
            System.err.println("Root = " + binaryTreeArray[0].toString());
            if (binaryTreeArray[0] == null){
                System.err.println("Root node null!");
            }
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
           if (nodePos >= (binaryTreeArray.length/2)){
              binaryTreeArray = dynamicArray(binaryTreeArray);
           }
           System.err.println(nodePos);
           binaryTreeArray[2 * nodePos + 1] = leftChild;
           System.err.println(2 * nodePos + 1);

           binaryTreeArray[2 * nodePos + 2] = rightChild;
            System.err.println(2 * nodePos + 2);


            System.err.println("Left: " +binaryTreeArray[2 * nodePos + 1].toString() + " Right = " + binaryTreeArray[2 * nodePos + 2].toString() );
        }
        else {
            System.err.println("Node to split not found ");
        }

    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        // Implement me!
        System.err.println("find node: " + nodeLabel );
        for (int i = 0; i < binaryTreeArray.length-1; i++) {
            if (binaryTreeArray[i].toString().equals(nodeLabel.toString())) {
                return true;
            }
        }
       // System.err.println("Couldn't find node @ findNode()");
        return false;
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {
        // Implement me!
        //not sure if right, need to figure out what to return
        String parent;
        for (int i = 0; i < binaryTreeArray.length-1; i++){
            if (binaryTreeArray[i].toString().equals(nodeLabel)) {
//                if (i%2 ==0) {  //if i%2 then i is left child
//                    parent = binaryTreeArray[(i/2)-1].toString();
//                }
//                else { //else it's right child
//                    parent = binaryTreeArray[(i/2)-2].toString();
//                }
                parent = binaryTreeArray[(i-1)/2].toString();
                return nodeLabel + " " + parent;
            }
        }
        return nodeLabel.toString();
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
        // Implement me!
       int nodePos =  getNodePosition(nodeLabel);
        int left = 2 * nodePos + 1;
        int right = 2 * nodePos + 2;
        if (nodePos == 0) {
           System.err.println("No node found");
           return null;
       } else {
           if (binaryTreeArray[left] != null && binaryTreeArray[right] != null) {
               return (nodeLabel + " " + binaryTreeArray[left] + " " + binaryTreeArray[right]);
           } else if(binaryTreeArray[left] != null && binaryTreeArray[right] == null){
               return (nodeLabel + " " + binaryTreeArray[left]);
           }
           else if(binaryTreeArray[left] == null && binaryTreeArray[right] != null){
               return (nodeLabel + " " + " " + binaryTreeArray[right]);
           } else if (binaryTreeArray[left] == null && binaryTreeArray[right] == null){
               return (nodeLabel + " ");
           }
       }
       return null;
    } // end of findParent

    @Override
    public void printInPreorder(PrintWriter writer) {
        // Implement me!
    	// Root, Left, Right
        /*
        1. Visit the root.
        2. Traverse the left subtree
        3. Traverse the right subtree
        */
        int index = 0;
        tempArray = (T[]) new Object[binaryTreeArray.length];

        // check if Tree is empty (no root node)
        if(binaryTreeArray[0] == null || binaryTreeArray[0] == EMPTY_NODE) {
            return;
        }
        // run preOrder recursive algorithm starting from index
        preOrder(index);

        // iterate through tempArray and print nodes in Preorder
        for (int i=0; i < tempArray.length-1; i++) {
            if (tempArray[i] == null){
                break;
            } else{
                System.err.print(tempArray[i] + " ");
                writer.print(tempArray[i].toString() + " ");
            }

        }
        writer.println();
        System.out.println();


    } // end of printInPreorder

    public void preOrder(int index) {
        if (binaryTreeArray[index] != null) {
            tempArray[counter] = binaryTreeArray[index];
            System.err.println("index = " + tempArray[counter].toString());
            System.out.println("ADDED: " + tempArray[counter].toString());
            counter ++;
            //print list
//        System.err.println("Tree: ");
//        for (int i = 0; i <tempArray.length-1; i++) {
//            System.err.println(tempArray[i]);
//        }

            int leftNodeIndex = 2 * index + 1;


            // check left sub tree
            if (binaryTreeArray[leftNodeIndex] != null) {
                if (findNode(binaryTreeArray[leftNodeIndex])) {
                    if (leftNodeIndex < tempArray.length) {
                        preOrder(leftNodeIndex);
                    }
                }
            }

            int rightNodeIndex = 2 * index + 2;
            // check right sub tree
            if (binaryTreeArray[rightNodeIndex] != null) {
                if (findNode(binaryTreeArray[rightNodeIndex])) {
                    if (rightNodeIndex < tempArray.length) {
                        preOrder(rightNodeIndex);
                    }
                }
            }
        }
    }

    @Override
    public void printInInorder(PrintWriter writer) {
        // Implement me!
    } // end of printInInorder

    @Override
    public void printInPostorder(PrintWriter writer) {
        // Implement me!
    } // end of printInPostorder


    private int getNodePosition(T nodeLabel){
        for (int i = 0; i < binaryTreeArray.length-1; i++ ){
            if (binaryTreeArray[i].toString().equals(nodeLabel.toString())) {
                return i;
            }
        }
        return 0;
    }

    private T[] dynamicArray(T[] array){
        return (T[]) new Object[array.length*3];
    }

} // end of class SequentialRepresentation



