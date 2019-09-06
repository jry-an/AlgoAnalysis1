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
   protected int counter;

    @SuppressWarnings("unchecked")
	public SequentialRepresentation() {
        binaryTreeArray = (T[]) new Object[3];
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
    @SuppressWarnings("unchecked")
    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        //if node exists, then check if left and right children aren't already set
        if(findNode(srcLabel)){
            //get position of srcLabel
           int nodePos =  getNodePosition(srcLabel);
           if (nodePos >= (binaryTreeArray.length/2)){
        	   //TODO Devise an appropriate way of setting new length
        	   //Test in interactive mode and see what happens after
        	   //RN A
        	   //SP A B C
        	   //TI
        	   //Last node is missing because that nodes 
        	   //child doesn't exist so the node is never visited.
        	   T[] newArray = dynamicArray(binaryTreeArray, (binaryTreeArray.length*2+1)*2+1);
        	   binaryTreeArray = newArray;
           }
           System.err.println(nodePos);
           if (leftChild == null){
               System.err.println("Left child of " + srcLabel + " is null");
               leftChild = (T) EMPTY_NODE;
           }
           if (rightChild == null){
               System.err.println("Right child of " + srcLabel + " is null");
               rightChild = (T) EMPTY_NODE;
           }
           binaryTreeArray[2 * nodePos + 1] = leftChild;
           System.err.println(2 * nodePos + 1);

           binaryTreeArray[2 * nodePos + 2] = rightChild;
            System.err.println(2 * nodePos + 2);


            System.err.println("Left: " +binaryTreeArray[2 * nodePos + 1].toString() + " Right = " + binaryTreeArray[2 * nodePos + 2].toString());
        }
        else {
            System.err.println("Node to split not found ");
        }
        

    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        System.err.println("find node: " + nodeLabel );
        for (int i = 0; i < binaryTreeArray.length; i++) {
            //System.err.println(binaryTreeArray[i]);
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
        //not sure if right, need to figure out what to return
        String parent;
        for (int i = 0; i < binaryTreeArray.length-1; i++){
        	if (binaryTreeArray[i] != null) {
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

       int nodePos =  getNodePosition(nodeLabel);
       int left = 2 * nodePos + 1;
       int right = 2 * nodePos + 2;
       
       if (nodePos < 0 || nodePos>=binaryTreeArray.length) {
           System.err.println("No node found");
           return null;
       
       } else if (nodePos<binaryTreeArray.length/2){
           if (binaryTreeArray[left] != null && binaryTreeArray[right] != null) {
               return (nodeLabel + " " + binaryTreeArray[left] + " " + binaryTreeArray[right]);
           }// else if(binaryTreeArray[left] != null && binaryTreeArray[right] == null){
//               return (nodeLabel + " " + binaryTreeArray[left]);
//           }
//           else if(binaryTreeArray[left] == null && binaryTreeArray[right] != null){
//               return (nodeLabel + " " + " " + binaryTreeArray[right]);
//           } else if (binaryTreeArray[left] == null && binaryTreeArray[right] == null){
//               return (nodeLabel + " ");
//           }
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
            counter ++;

            int leftNodeIndex = 2 * index + 1;

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
                System.err.print(tempArray[i] + " ");
                writer.print(tempArray[i].toString() + " ");
            }

        }
        writer.println();
        System.out.println();


    } // end of printInPreorder

    public void inOrder(int index) {
        if (binaryTreeArray[index] != null) {
            int leftNodeIndex = 2 * index + 1;
            
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
                System.err.print(tempArray[i] + " ");
                writer.print(tempArray[i].toString() + " ");
            }

        }
        writer.println();
        System.out.println();


    } // end of printInPreorder

    public void postOrder(int index) {
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



