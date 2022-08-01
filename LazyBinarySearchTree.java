/* Mansi Patel
 * CS 3345.004 -- Data Structures and Introduction to Algorithmic Analysis
 * April 4 2022
 * 
 */

public class LazyBinarySearchTree {
	// Create a data field to hold the root node
	// the binary search tree. 
	private TreeNode root = null;
	// Create a data field to hold the size of the binary
	// search tree.
	private int size = 0;
	
	// TreeNode class nested inside LazyBinarySearchTree class
	private class TreeNode {
		// Field to hold key of the node
		int key = 0;
		// Field to hold the left child of the node
		TreeNode leftChild = null;
		// Field to hold the left child of the node
		TreeNode rightChild = null;
		// Field to hold delete status of node
		boolean deleted = false;
		
		// Custom constructor of node in tree
		TreeNode(int key) {
			this.key = key;
		}
	} // end TreeNode class
	
	// Class Constructor
	LazyBinarySearchTree() {
		this.root = null;
		this.size = 0;
	} 
	
	
	/* The insert method will logically insert a node
	 * with a given key into the binary search tree. 
	 * The method takes an integer as an input parameter and
	 * it returns a boolean.
	 */
	public boolean insert(int key) {
		// Create a new node with the input value as its key.
		TreeNode new_node = new TreeNode(key);
		
		// If the key is not between 1 and 99 inclusive, throw an
		// IllegalArgumentException.
		if (key < 1 || key > 99) {
			throw new IllegalArgumentException();
		} else {
			
			// Call the search method to search for the key in the tree.
			if (search(root, key) == null) {
				// If the tree does not have a node with the key already, 
				// insert the new element to a leaf node.
		
				// Store the root in two nodes: 
				// one acts as a traversal node for the tree (current) and one
				// to hold the parent of the new node (parent). 
				TreeNode current = root;
				TreeNode parent = root;
				
				// If the tree is not empty, find a place for the key in the tree.
				if (root != null) {
					
					// Traverse the tree to find the appropriate place for the node in the tree.
					while(current != null) {
						
						// If the current node's key is greater
						// than the key we want to insert, current is stored 
						// into parent and then current is updated to be current's
						// left child (traversing to the left).
						if (key < current.key) {
							parent = current;
							current = current.leftChild;
						} else {
							// If the current node's key is less than
							// the key we want to insert, current is stored 
							// into parent and then current is updated to be current's
							// right child (traversing to the right).
							parent = current;
							current = current.rightChild;
						}
						
					} // end while
					
					// After exiting the while loop, parent will hold the parent node
					// or root of the subtree where its direct child will become the new leaf node.
					// If the key is less than the parent node's key, it becomes the left child
					// of the parent.
					if (key < parent.key) {
						parent.leftChild = new_node;
					// Otherwise, it becomes the right child.
					} else {
						parent.rightChild = new_node;
					}
					
					// Increment the size field as we inserted a new node into the tree.
					size++;
					// Return true as we were able to logically insert a new node into the tree. 
					return true;
					
				} else {
					// If the tree is initially empty, set the root node equal 
					// to the new node.
					root = new_node;
					// Increment the size as we have created our first node.
					size++;
					// Return true as we were able to logically insert a new node into the tree. 
					return true;
				} // end inner else
				
			// If the key is inside the tree, but the node holding it was deleted, switch
			// the deleted field of the node to false.
			} else if (search(root, key) != null && (search(root, key)).deleted == true) {
				
				// Store the existing node in the tree that is holding the key. 
				TreeNode existing_node = search(root, key);
				// Switch the deleted field of the existing node to false as we are
				// logically reinserting the key into the tree again.
				existing_node.deleted = false;
				// Return true as we were able to logically insert a new node into the tree. 
				return true;
				
			} else {
				
				// If the key is found in the tree and it is non-deleted, do nothing.
				// Since we do nothing in this case, return false.
				return false;
				
			} // end outer else
			
		} // end outermost else
	} // end insert method
	

	/* The delete method will logically delete a node
	 * with a given key into the binary search tree. 
	 * The method takes an integer as an input parameter and
	 * it returns a boolean.
	 */
	public boolean delete(int key) {
		// If the key is not between 1 and 99 inclusive, throw an
		// IllegalArgumentException.
		if (key < 1 || key > 99) {
			throw new IllegalArgumentException();
		// If the tree is empty, return false as there is nothing to delete.
		} else if (root == null) {
			return false;
		} else {
			
			// If the tree is nonempty and the key is valid, call the search method to
			// search for the node to delete.
			if (search(root, key) == null) {
				// If the specified element is not in the 
				// tree, do nothing and return false.
				return false;
			} else {
				
				// If the node with the given key was found, check for its delete status.
				if ((search(root, key)).deleted == false) {
					// If the node to be deleted has not yet been deleted, 
					// switch the node's deleted field to true to mark
					// the node as logically deleted.
					// Store the existing node in the tree that is holding the key. 
					TreeNode existing_node = search(root, key);
					// Switch the deleted field of the existing node to true as we are
					// logically deleting the node holding the key.
					existing_node.deleted = true;
					// Return true as we logically deleted a node in the tree.
					return true;
				} else {
					// If the node to be deleted is already deleted,
					// do nothing and return false.
					return false;
				} // end inner else
				
			} // end outer else
			
		} // end outermost else
	} // end delete method
	

	/* The findMin method searches for the minimum
	 * non-deleted element in the tree. The method
	 * takes in no input parameters and returns an integer.
	 */
	public int findMin() {
		// Create a traversal node and a node
		// to hold the minimum non-deleted element.
		TreeNode traversal = root;
		TreeNode mostRecentMin = null;
		
		// If the tree is empty, return -1 as there is no minimum element.
		if (root == null) {
			return -1;
		} else {
			
			// If the tree is non-empty, traverse through 
			// the tree using traversal node.
			while (traversal != null) {
				// If the traversal node is not deleted, then
				// update mostRecentMin to hold the traversal node.
				if (traversal.deleted == false) {
					mostRecentMin = traversal;
				}
				// Update the traversal node to go left.
				traversal = traversal.leftChild;
			}
			// Return the key field of the mostRecentMin node.
			return mostRecentMin.key;
			
		} // end else
	} // end findMin method

	
	/* The findMax method searches for the maximum
	 * non-deleted element in the tree. The method
	 * takes in no input parameters and returns an integer.
	 */
	public int findMax() {
		// Create a traversal node and a node
		// to hold the maximum non-deleted element.
		TreeNode traversal = root;
		TreeNode mostRecentMax = null;
		
		// If the tree is empty, return -1 as there is no minimum element.
		if (root == null) {
			return -1;
		} else {
			
			//	If the tree is non-empty, traverse through the tree 
			// using traversal node.
			while (traversal != null) {
				// If the traversal node is not deleted, then
				// update mostRecentMax to hold the traversal node.
				if (traversal.deleted == false) {
					mostRecentMax = traversal;
				}
				// Update the traversal node to go left.
				traversal = traversal.rightChild;
			}
			// Return the key field of the mostRecentMax node.
			return mostRecentMax.key;
			
		} // end else
	} // end findMax method
	
	
	/* The contains method checks whether the given element 
	 * both exists in the tree and is non-deleted. The method 
	 * takes an integer as an input parameter and it returns a boolean.
	 */
	public boolean contains(int key) {
		// If the key is not between 1 and 99 inclusive, throw an
		// IllegalArgumentException.
		if (key < 1 || key > 99) {
			throw new IllegalArgumentException();
		// If the tree is empty, return false as the key is not in the tree.
		} else if (root == null) {
			return false;
		} else {
			
			// Call the search method to find the node holding the key in the tree.
			// If the search method found the key, check the delete status of the node.
			if (search(root, key) != null) {
				
				// If the node holding the key was deleted, return false.
				if ((search(root, key)).deleted == true) {
					return false;
				} else {
					// Otherwise, return true as the element is in the tree and its node
					// is non-deleted. 
					return true;
				} // end inner else
				
			} // end outer if
			
			// If the key was not found in the tree at all, return false. 
			return false;
		
		} // end outermost else
	} // end contains method
	
	
	/* The toString method performs a pre-order traversal of
	 * the tree and stores each node into a String. 
	 * The method takes in no input parameters and is 
	 * expected to return a String.
	 */
	public String toString() {
		// Create a StringBuilder object to add to and manipulate as we traverse
		// the tree in a recursive helper method.
		StringBuilder preorderOutput = new StringBuilder("");
		// If the tree is empty, return null.
		if (root == null) {
			return null;
		}
		// Otherwise, call the recursive helper method printTree
		// and return the final string (method returns StringBuilder object,
		// which is converted to a String and then returned for toString method).
		return printTree(preorderOutput, root).toString();
	} // end toString method
	
	
	/* The printTree method is a recursive helper method for 
	 * the toString method. The method takes in a StringBuilder object 
	 * and the root node of the tree. The method returns a StringBuilder object.
	 */
	public StringBuilder printTree(StringBuilder output, TreeNode root) {
		// Preorder Traversal: Middle - left - right
		// Create a traversal node and store the root in it.
		TreeNode traversal = root;
		
		// If the root is null, return the current StringBuilder output.
		if (root == null) {
			return output;
		}
		
		// If the traversal node is deleted, append an asterisk and its key
		// to the StringBuilder output (asterisk indicates the node has 
		// been deleted).
		if (traversal.deleted == true) {
			output = output.append("*" + traversal.key + " ");
		// If the traversal node has not been deleted, append the traversal key
		// to the StringBuilder output.
		} else {
			output = output.append(traversal.key + " ");
		}
		
		// Recursively call the method for the left and right subtrees.
		printTree(output, traversal.leftChild);
		printTree(output, traversal.rightChild);
		
		// Return the final StringBuilder output.
		return output;
	} // end printTree method
	
	
	/* The height method returns the height of the tree,
	 * including the deleted elements. The method takes in no
	 * input parameters and returns an int.
	 */
	public int height() {
		// Call the recursive helper method calculateHeight
		// and return its value.
		return calculateHeight(root);
	} // end height method
	
	
	/* The calculateHeight method is a recursive helper method to
	 * calculate the height of the tree. The method takes in the root
	 * node of the tree as an input parameter and it returns an int.
	 */
	int calculateHeight(TreeNode root) {
		// Create a traversal node to calculate height of the tree
		// as we traverse the tree. Store the root in it.
		TreeNode traversal = root;
		
        // if the tree is empty, return -1.
        if (root == null) {
            return -1;
        } else {
        	
            // Calculate the leftHeight by recursively traversing the left subtree
            int leftHeight = calculateHeight(traversal.leftChild);
            // Calculate the rightDepth by recursively traversing the right subtree
            int rightHeight = calculateHeight(traversal.rightChild);
         
            // If the leftHeight is greater than the rightDepth, add 1
            // to the leftHeight to account for the root and return the value.
            if (leftHeight > rightHeight) {
            	return (leftHeight + 1);
            } else {
            // Otherwise, add 1 to the right height and return the value.
            	return (rightHeight + 1);
            } // end inner else
            
        } // end outermost else
	} // end calculateHeight method
	
	
	/* The size method returns the count of nodes 
	 * in the tree, including the deleted nodes. The method does not take
	 * in any input parameters and is expected to return an int.
	 */
	public int size() {
		// Return the size data field.
		return size;
	} // end size method
	
	
	/* The search method is a recursive helper method used by delete, insert,
	 * and contains to find a node in the tree regardless of its delete status.
	 * The method takes in the root node of the tree and an integer key as input
	 * parameters. The method is expected to return a tree node.
	 */
	TreeNode search(TreeNode root, int key) {
		// Create a traversal node to traverse the tree with.
		TreeNode traversal = root;
		
		// If the key is not found (the traversal node has become
		// null before node with value was found),
		// return null.
		if (traversal == null) {
			return traversal;
		// If the traversal node's key matches
		// our search key, return the traversal node.
		} else if (traversal.key == key) {
			return traversal;
		} else {
			
			// If the search key is less than
			// the traversal node's key, do a recursive call
			// and go to the next left child.
			if (key < traversal.key) {
				return search(traversal.leftChild, key);
				// If the input key is greater than 
				// the traversal node's key, do a recursive call 
				// and go to the next right child.
			} else {
				return search(traversal.rightChild, key);
			} // end inner else
			
		} // end outermost else
	} // end search method
	
	
	/* The getRoot method is a getter method for the root node.
	* The method does not take in any input parameters 
	* and it returns a tree node.
	*/
	public TreeNode getRoot() {
		return root;
	} // end getRoot method
	

}
