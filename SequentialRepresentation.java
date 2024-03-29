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

    private T[] binaryTreeArray;
    private T[] tempArray;
    private int counter;

    @SuppressWarnings("unchecked")
    SequentialRepresentation() {
        binaryTreeArray = (T[]) new Object[3];
    } // end of SequentialRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        if (binaryTreeArray[0] == null){
            binaryTreeArray [0] = nodeLabel;
        }
        if(nodeLabel == null){
            System.err.println("Root can't be null! Please set an actual node as root");
        }

    } // end of setRootNode()

    @SuppressWarnings("unchecked")
    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        //if node exists, then split left and right child - following algorithm locations
        if(findNode(srcLabel)){
            //get position of srcLabel
            int nodePos =  getNodePosition(srcLabel);
            if (nodePos >= (binaryTreeArray.length/2)){
                //copies old array into new array with updated size
                T[] newArray = dynamicArray(binaryTreeArray, (binaryTreeArray.length*2+1)*2+1);
                binaryTreeArray = newArray;
            }
            //if nodeLabel null, nodeLabel is set to empty node
            if (leftChild == null){
                leftChild = (T) EMPTY_NODE;
                System.err.println("Left child was null, left child of " + srcLabel.toString() + " set as an empty node");
            }
            if (rightChild == null){
                rightChild = (T) EMPTY_NODE;
                System.err.println("Left child was null, right child of " + srcLabel.toString() + " set as an empty node");
            }
            binaryTreeArray[2 * nodePos + 1] = leftChild;
            binaryTreeArray[2 * nodePos + 2] = rightChild;
        }
    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        //loops through array, if nodeLabel found returns true
        for (int i = 0; i < binaryTreeArray.length; i++) {
            if (binaryTreeArray[i] != null) {
                if (binaryTreeArray[i].toString().equals(nodeLabel.toString())) {
                    return true;
                }
            }
        }
        return false;
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {
        //finds node, then gets the parent of that node by array position
        String parent;
        for (int i = 0; i < binaryTreeArray.length-1; i++){
            if (binaryTreeArray[i] != null) {
                //if node is found get parent location
                if (binaryTreeArray[i].toString().equals(nodeLabel)) {
                    parent = binaryTreeArray[(i-1)/2].toString();
                    //check if root node
                    if (!parent.equals(nodeLabel.toString())) {
                        return nodeLabel.toString() + " " + parent;
                    }
                }
            }
        }
        return nodeLabel.toString();
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
        //finds node then gets the children of that node, by array position
        int nodePos =  getNodePosition(nodeLabel);
        int left = 2 * nodePos + 1;
        int right = 2 * nodePos + 2;

        //check if in bounds of array
        if (nodePos < 0 || nodePos>=binaryTreeArray.length) {
            return null;

            //return children
        } else if (nodePos<binaryTreeArray.length/2){
            if (binaryTreeArray[left] != null && binaryTreeArray[right] != null) {
                return (nodeLabel + " " + binaryTreeArray[left] + " " + binaryTreeArray[right]);
            }
        }
        return (nodeLabel + " ");
    } // end of findParent

    @SuppressWarnings("unchecked")
    @Override
    public void printInPreorder(PrintWriter writer) {
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
        counter = 0;
        preOrder(index);

        // iterate through tempArray and print nodes in Preorder
        for (int i=0; i < tempArray.length; i++) {
            if (tempArray[i] == null){
                break;
            } else{
                writer.print(tempArray[i].toString() + " ");
            }

        }
        writer.println();


    } // end of printInPreorder

    private void preOrder(int index) {
        if (binaryTreeArray[index] != null) {
            tempArray[counter] = binaryTreeArray[index];
            counter ++;

            int leftNodeIndex = 2 * index + 1;

            //check left subtree
            if (leftNodeIndex<binaryTreeArray.length) {
                // check left sub tree
                if (binaryTreeArray[leftNodeIndex] != null) {
                    if (findNode(binaryTreeArray[leftNodeIndex])) {
                        if (leftNodeIndex < tempArray.length) {
                            preOrder(leftNodeIndex);
                        }
                    }
                }
            }

            int rightNodeIndex = 2 * index + 2;
            //check right subtree
            if (rightNodeIndex<binaryTreeArray.length) {
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
    } // end of preOrder

    @SuppressWarnings("unchecked")
    @Override
    public void printInInorder(PrintWriter writer) {
        /*
        1. Traverse the left subtree
        2. Visit the root.
        3. Traverse the right subtree
        */
        int index = 0;
        tempArray = (T[]) new Object[binaryTreeArray.length];

        // check if Tree is empty (no root node)
        if(binaryTreeArray[0] == null || binaryTreeArray[0] == EMPTY_NODE) {
            return;
        }
        // run inOrder recursive algorithm starting from index 0 (root)
        counter = 0;
        inOrder(index);

        // iterate through tempArray and print nodes in inOrder
        for (int i=0; i < tempArray.length; i++) {
            if (tempArray[i] == null){
                break;
            } else{
                writer.print(tempArray[i].toString() + " ");
            }

        }
        writer.println();


    } // end of printInPreorder

    private void inOrder(int index) {
        if (binaryTreeArray[index] != null) {
            int leftNodeIndex = 2 * index + 1;

            //get left child
            if (leftNodeIndex<binaryTreeArray.length) {
                // check left sub tree
                if (binaryTreeArray[leftNodeIndex] != null) {
                    if (findNode(binaryTreeArray[leftNodeIndex])) {
                        if (leftNodeIndex < tempArray.length) {
                            inOrder(leftNodeIndex);
                        }
                    }
                }
            }

            // visit root
            tempArray[counter] = binaryTreeArray[index];
            counter ++;

            int rightNodeIndex = 2 * index + 2;
            //check right subtree
            if (rightNodeIndex<binaryTreeArray.length) {
                // check right sub tree
                if (binaryTreeArray[rightNodeIndex] != null) {
                    if (findNode(binaryTreeArray[rightNodeIndex])) {
                        if (rightNodeIndex < tempArray.length) {
                            inOrder(rightNodeIndex);
                        }
                    }
                }
            }
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
        int index = 0;
        tempArray = (T[]) new Object[binaryTreeArray.length];

        // check if Tree is empty (no root node)
        if(binaryTreeArray[0] == null || binaryTreeArray[0] == EMPTY_NODE) {
            return;
        }
        // run preOrder recursive algorithm starting from index
        counter = 0;
        postOrder(index);

        // iterate through tempArray and print nodes in Postorder
        for (int i=0; i < tempArray.length; i++) {
            if (tempArray[i] == null){
                break;
            } else{
                writer.print(tempArray[i].toString() + " ");
            }

        }
        writer.println();


    } // end of printInPreorder

    private void postOrder(int index) {
        if (binaryTreeArray[index] != null) {


            // check left sub tree
            int leftNodeIndex = 2 * index + 1;
            if (leftNodeIndex<binaryTreeArray.length) {
                if (binaryTreeArray[leftNodeIndex] != null) {
                    if (findNode(binaryTreeArray[leftNodeIndex])) {
                        if (leftNodeIndex < tempArray.length) {
                            postOrder(leftNodeIndex);
                        }
                    }
                }
            }

            // check right sub tree
            int rightNodeIndex = 2 * index + 2;
            if (rightNodeIndex<binaryTreeArray.length) {
                if (binaryTreeArray[rightNodeIndex] != null) {
                    if (findNode(binaryTreeArray[rightNodeIndex])) {
                        if (rightNodeIndex < tempArray.length) {
                            postOrder(rightNodeIndex);
                        }
                    }
                }
            }

            // visit root
            tempArray[counter] = binaryTreeArray[index];
            counter ++;
        }
    } // end of postOrder

    private int getNodePosition(T nodeLabel){
        for (int i = 0; i < binaryTreeArray.length; i++ ){
            if (binaryTreeArray[i] != null) {
                if (binaryTreeArray[i].toString().equals(nodeLabel.toString())) {
                    return i;
                }
            }
        }
        return -1;
    }

    //updates the array size/dynamic array implementation
    @SuppressWarnings("unchecked")
    private T[] dynamicArray(T[] array, int newSize){
        T[] newArray = (T[]) new Object[newSize];

        //copy oldArray to newArray
        for (int i=0; i<array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

} // end of class SequentialRepresentation