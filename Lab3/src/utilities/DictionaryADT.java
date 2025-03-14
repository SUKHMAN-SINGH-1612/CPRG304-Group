package utilities;

import exceptions.DuplicateKeyException;

/**
* DictionaryADT.java
*
* @author kitty
* @version 1.1
* 
* Class Definition: This interface represents the public contract for the
* implementation of Dictionary for the DictionaryADT Lab. This data type 
* will store data in (key, value) pairs and keys must be unique. 
*/

public interface DictionaryADT<K,V>
{
	/**
	 * Constructor method to create a new Dictionary object.
	 * 
	 * Precondition: None.
	 * 
	 * Postcondition: A dictionary object is created and its size is 
	 * initialized to the value in the argument, or a default of 10.
	 * 
	 * @param size Optional: the size of the new dictionary, default is 10.
	 */
	public void create( int size );

	/**
	 * Mutator method to insert a new key-value pair into the Dictionary.
	 * 
	 * Precondition: A valid dictionary object exists and non-null values
	 * are passed as arguments.
	 * 
	 * Postcondition: The new key-value pair is added to the Dictionary.
	 * 
	 * @param K key
	 * @param V value
	 * @return true if key-value pair has been added successfully.
	 * 
	 * @throws DuplicateKeyException is thrown if key already exist.
	 */
	public boolean insert( K key, V value ) throws DuplicateKeyException;

	/**
	 * Mutator method to remove a key-value pair from the Dictionary.
	 * 
	 * Precondition: A valid dictionary object exists and a non-null value
	 * is passed as argument.
	 * 
	 * Postcondition: The key-value pair is deleted from the Dictionary.
	 * 
	 * @param K key
	 * @return the value of the key removed, null if key does not exist.
	 */
	public V remove( K key );

	/**
	 * Mutator method to update a key-value pair from the Dictionary.
	 * 
	 * Precondition: A valid dictionary object exists and a non-null value
	 * is passed as argument.
	 * 
	 * Postcondition: The value of the key is changed in the Dictionary.
	 * 
	 * @param K key
	 * @param V the new value
	 * @return true if key-value pair has been changed successfully, or 
	 * false if key does not exist.
	 */
	public boolean update( K key, V value );

	/**
	 * Accessor method to retrieve the value of a key from the Dictionary.
	 * 
	 * Precondition: A valid dictionary object exists and a non-null value
	 * is passed as argument.
	 * 
	 * Postcondition: The value of the key is returned from the Dictionary.
	 * 
	 * @param K key
	 * @return the value of the key, null if key does not exist.
	 */
	public V lookup( K key );

}
