package implementations;

import java.io.Serializable;

/********************************************************************
 * BSTreeNode - Node class for Binary Search Tree
 * 
 * @author Jaskaran Singh
 * @date 14 April 2025
 * @version Assignment 3
 ********************************************************************/

public class BSTreeNode<E extends Comparable<? super E>> implements Serializable
{
    private static final long serialVersionUID = 1L;
    private E element;
    private BSTreeNode<E> left;
    private BSTreeNode<E> right;

    /********************************************************************
     * Constructor - creates a new BST node with given data
     * 
     * @param element - element to store in node
     ********************************************************************/
    public BSTreeNode(E element) 
    {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    /********************************************************************
     * getElement() - returns the data stored in this node
     * 
     * @param none
     * @return E - the data
     ********************************************************************/
    public E getElement() 
    {
        return element;
    }

    /********************************************************************
     * setElement() - updates the data stored in this node
     * 
     * @param element - new data to store
     * @return void
     ********************************************************************/
    public void setElement(E element) 
    {
        this.element = element;
    }

    /********************************************************************
     * getLeft() - returns the left child node
     * 
     * @param none
     * @return BSTreeNode<E> - left child
     ********************************************************************/
    public BSTreeNode<E> getLeft() 
    {
        return left;
    }

    /********************************************************************
     * setLeft() - sets the left child node
     * 
     * @param left - new left child node
     * @return void
     ********************************************************************/
    public void setLeft(BSTreeNode<E> left) 
    {
        this.left = left;
    }

    /********************************************************************
     * getRight() - returns the right child node
     * 
     * @param none
     * @return BSTreeNode<E> - right child
     ********************************************************************/
    public BSTreeNode<E> getRight() 
    {
        return right;
    }

    /********************************************************************
     * setRight() - sets the right child node
     * 
     * @param right - new right child node
     * @return void
     ********************************************************************/
    public void setRight(BSTreeNode<E> right) 
    {
        this.right = right;
    }
}
