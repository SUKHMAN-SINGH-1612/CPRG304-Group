package implementations;


/***********************************************************************
 * Binary search tree implementation using BSTreeADT interface
 * 
 * @author Jaskaran Singh
 * @date 14 April 2025
 * @version Assignment 3
************************************************************************ */


import utilities.BSTreeADT;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> 
{
    private BSTreeNode<E> root;
    private int size;

    /********************************************************************
     * BSTree() - constructor
    ********************************************************************* */
    public BSTree(E data) 
    {
        this.root = new BSTreeNode<>(data);
        this.size = 1;
    }

    /********************************************************************
     * BSTree() - default constructor creates an empty BST
    ********************************************************************/
    public BSTree() 
    {
        this.root = null;
        this.size = 0;
    }

    

    /********************************************************************
     * getRoot() - returns the root of BST
     * 
     * @param none
     * @return BSTreeNode<E>
    ********************************************************************* */
    @Override
    public BSTreeNode<E> getRoot() throws NullPointerException 
    {
        if (root == null) 
        {
            throw new NullPointerException("Tree is empty, no root node.");
        }
        return root;
    }


    /********************************************************************
     * getHeight() - returns the height of the BST
     * 
     * @param none
     * @return int - height of the tree
     ********************************************************************/
    @Override
    public int getHeight() 
    {
        return getHeightRecursive(root);
    }

    /********************************************************************
     * getHeightRecursive() - helper method to calculate height recursively
     * 
     * @param node - current node in the tree
     * @return int - height of the subtree rooted at the given node
     ********************************************************************/
    private int getHeightRecursive(BSTreeNode<E> node) 
    {
        if (node == null) 
        {
            return 0;
        }
        
        int leftHeight = getHeightRecursive(node.getLeft());
        int rightHeight = getHeightRecursive(node.getRight());
        
        return Math.max(leftHeight, rightHeight) + 1;
    }


    /********************************************************************
     * size() - returns the number of elements in the BST
     * 
     * @param none
     * @return int - number of elements in the tree
     ********************************************************************/
    @Override
    public int size() 
    {
        return size;
    }


    /********************************************************************
     * isEmpty() - checks if the BST is empty
     * 
     * @param none
     * @return boolean - true if the tree is empty, false otherwise
     ********************************************************************/
    @Override
    public boolean isEmpty() 
    {
        return size == 0;
    }


    /********************************************************************
     * clear() - removes all elements from the BST and resets it
     * 
     * @param none
     * @return void
     ********************************************************************/
    @Override
    public void clear() 
    {
        root = null;
        size = 0;
    }



    /********************************************************************
     * contains() - checks if the tree contains a specific element
     * 
     * @param entry - the element to search for
     * @return boolean - true if element is found, false otherwise
     * @throws NullPointerException if the entry is null
     ********************************************************************/
    @Override
    public boolean contains(E entry) throws NullPointerException 
    {
        if (entry == null) 
        {
            throw new NullPointerException("Cannot search for null in BST.");
        }

        return containsRecursive(root, entry);
    }

    /********************************************************************
     * containsRecursive() - helper method to search for an element
     * 
     * @param current - current node in traversal
     * @param entry - element being searched for
     * @return boolean - true if found, false otherwise
     ********************************************************************/
    private boolean containsRecursive(BSTreeNode<E> current, E entry) 
    {
        if (current == null) 
        {
            return false;
        }

        int comparison = entry.compareTo(current.getElement());

        if (comparison == 0) 
        {
            return true;
        } 
        else if (comparison < 0) 
        {
            return containsRecursive(current.getLeft(), entry);
        } 
        else 
        {
            return containsRecursive(current.getRight(), entry);
        }
    }


    /********************************************************************
     * search() - searches for an element and returns the node if found
     * 
     * @param entry - element to search for
     * @return BSTreeNode<E> - node containing the element, or null if not found
     * @throws NullPointerException if the entry is null
     ********************************************************************/
    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException 
    {
        if (entry == null) 
        {
            throw new NullPointerException("Cannot search for null in BST.");
        }

        return searchRecursive(root, entry);
    }

    /********************************************************************
     * searchRecursive() - helper method to locate a node in BST
     * 
     * @param current - current node in the search
     * @param entry - value to look for
     * @return BSTreeNode<E> - matching node, or null if not found
     ********************************************************************/
    private BSTreeNode<E> searchRecursive(BSTreeNode<E> current, E entry) 
    {
        if (current == null) 
        {
            return null;
        }

        int comparison = entry.compareTo(current.getElement());

        if (comparison == 0) 
        {
            return current;
        } 
        else if (comparison < 0) 
        {
            return searchRecursive(current.getLeft(), entry);
        } 
        else 
        {
            return searchRecursive(current.getRight(), entry);
        }
    }





    /********************************************************************
     * add() - inserts a new element into the BST following BST rules
     * 
     * @param newEntry - element to be added
     * @return boolean - true if added successfully, false otherwise
     * @throws NullPointerException if newEntry is null
     ********************************************************************/
    @Override
    public boolean add(E newEntry) throws NullPointerException 
    {
        if (newEntry == null) 
        {
            throw new NullPointerException("Cannot insert null into BST.");
        }

        if (root == null) 
        {
            root = new BSTreeNode<>(newEntry);
            size++;
            return true;
        } 
        else 
        {
            return insertRecursive(root, newEntry);
        }
    }

    /********************************************************************
     * insertRecursive() - helper method to insert elements recursively
     * 
     * @param current - current node in traversal
     * @param newEntry - value to be inserted
     * @return boolean - true if added, false if duplicate
     ********************************************************************/
    private boolean insertRecursive(BSTreeNode<E> current, E newEntry) 
    {
        int comparison = newEntry.compareTo(current.getElement());

        if (comparison < 0) 
        {
            if (current.getLeft() == null) 
            {
                current.setLeft(new BSTreeNode<>(newEntry));
                size++;
                return true;
            } 
            else 
            {
                return insertRecursive(current.getLeft(), newEntry);
            }
        } 
        else if (comparison > 0) 
        {
            if (current.getRight() == null) 
            {
                current.setRight(new BSTreeNode<>(newEntry));
                size++;
                return true;
            } 
            else 
            {
                return insertRecursive(current.getRight(), newEntry);
            }
        } 
        else 
        {
            // Duplicate value, not added
            return false;
        }
    }


    /********************************************************************
     * removeMin() - removes the smallest element (leftmost node) from BST
     * 
     * @param none
     * @return BSTreeNode<E> - node that was removed, or null if tree is empty
     ********************************************************************/
    @Override
    public BSTreeNode<E> removeMin() 
    {
        if (root == null) 
        {
            return null;
        }

        if (root.getLeft() == null) 
        {
            BSTreeNode<E> removedNode = root;
            root = root.getRight();  // Promote right subtree
            size--;
            return removedNode;
        }

        return removeMinRecursive(root, root.getLeft());
    }

    /********************************************************************
     * removeMinRecursive() - helper method to remove leftmost node
     * 
     * @param parent - parent of the current node
     * @param current - current node being checked
     * @return BSTreeNode<E> - the node that was removed
     ********************************************************************/
    private BSTreeNode<E> removeMinRecursive(BSTreeNode<E> parent, BSTreeNode<E> current) 
    {
        if (current.getLeft() == null) 
        {
            parent.setLeft(current.getRight()); // Bypass the current node
            size--;
            return current;
        }

        return removeMinRecursive(current, current.getLeft());
    }




    /********************************************************************
     * removeMax() - removes the largest element (rightmost node) from BST
     * 
     * @param none
     * @return BSTreeNode<E> - node that was removed, or null if tree is empty
     ********************************************************************/
    @Override
    public BSTreeNode<E> removeMax() 
    {
        if (root == null) 
        {
            return null;
        }

        if (root.getRight() == null) 
        {
            BSTreeNode<E> removedNode = root;
            root = root.getLeft();  // Promote left subtree
            size--;
            return removedNode;
        }

        return removeMaxRecursive(root, root.getRight());
    }

    /********************************************************************
     * removeMaxRecursive() - helper method to remove rightmost node
     * 
     * @param parent - parent of the current node
     * @param current - current node being checked
     * @return BSTreeNode<E> - the node that was removed
     ********************************************************************/
    private BSTreeNode<E> removeMaxRecursive(BSTreeNode<E> parent, BSTreeNode<E> current) 
    {
        if (current.getRight() == null) 
        {
            parent.setRight(current.getLeft()); // Bypass the current node
            size--;
            return current;
        }

        return removeMaxRecursive(current, current.getRight());
    }




    /********************************************************************
     * InorderIterator - inner class to provide in-order traversal
     ********************************************************************/
    private class InorderIterator implements utilities.Iterator<E>
    {
        private java.util.List<E> elements;
        private int index;

        public InorderIterator() 
        {
            elements = new java.util.ArrayList<>();
            index = 0;
            inorderTraverse(root);
        }

        private void inorderTraverse(BSTreeNode<E> node) 
        {
            if (node != null) 
            {
                inorderTraverse(node.getLeft());
                elements.add(node.getElement());
                inorderTraverse(node.getRight());
            }
        }

        @Override
        public boolean hasNext() 
        {
            return index < elements.size();
        }

        @Override
        public E next() 
        {
            if (!hasNext()) 
            {
                throw new java.util.NoSuchElementException("No more elements.");
            }
            return elements.get(index++);
        }
    }



    /********************************************************************
     * PreorderIterator - inner class to provide pre-order traversal
     ********************************************************************/
    private class PreorderIterator implements utilities.Iterator<E>
    {
        private java.util.List<E> elements;
        private int index;

        public PreorderIterator() 
        {
            elements = new java.util.ArrayList<>();
            index = 0;
            preorderTraverse(root);
        }

        private void preorderTraverse(BSTreeNode<E> node) 
        {
            if (node != null) 
            {
                elements.add(node.getElement());
                preorderTraverse(node.getLeft());
                preorderTraverse(node.getRight());
            }
        }

        @Override
        public boolean hasNext() 
        {
            return index < elements.size();
        }

        @Override
        public E next() 
        {
            if (!hasNext()) 
            {
                throw new java.util.NoSuchElementException("No more elements.");
            }
            return elements.get(index++);
        }
    }



    /********************************************************************
     * PostorderIterator - inner class to provide post-order traversal
     ********************************************************************/
    private class PostorderIterator implements utilities.Iterator<E>
    {
        private java.util.List<E> elements;
        private int index;

        public PostorderIterator() 
        {
            elements = new java.util.ArrayList<>();
            index = 0;
            postorderTraverse(root);
        }

        private void postorderTraverse(BSTreeNode<E> node) 
        {
            if (node != null) 
            {
                postorderTraverse(node.getLeft());
                postorderTraverse(node.getRight());
                elements.add(node.getElement());
            }
        }

        @Override
        public boolean hasNext() 
        {
            return index < elements.size();
        }

        @Override
        public E next() 
        {
            if (!hasNext()) 
            {
                throw new java.util.NoSuchElementException("No more elements.");
            }
            return elements.get(index++);
        }
    }



    @Override
    public utilities.Iterator<E> inorderIterator() 
    {
        return new InorderIterator();
    }
    
    @Override
    public utilities.Iterator<E> preorderIterator() 
    {
        return new PreorderIterator();
    }
    
    @Override
    public utilities.Iterator<E> postorderIterator() 
    {
        return new PostorderIterator();
    }    

}