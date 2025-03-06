package utilities;

/**
* DictionaryADT.java
*
* @author Jaskaran Singh, Santiago Cardenas Delgadillo, Sukhman Singh
* @version major.minor revision number starting at 1.0
* 
* Class Definition: This interface represents the public contract for the
* implementation of Dictionary for the DictionaryADT Lab. 
*/

public interface DictionaryADT<K,V>
{
    /**
     * insert() - Add a key-value pair to the dictionary
     * 
     * @param key - the key to be added
     * @param value - value associated with the key
     * @throws IllegalArgumentException if the key already exists in the dictionary
     * @pre key is not null, value is not null
     * @post The dictionary contains the new (key, value) pair
     */
    void insert(K key, V value);

    /**
     * remove() - Removes a key-value pair from the dictionary.
     *
     * @param key the key to be removed
     * @return the value associated with the removed key, or null if the key was not found
     * @pre key is not null
     * @post If the key exists, it is removed from the dictionary
     */
    V remove(K key);

    /**
     *update() - Updates the value of an existing key.
     *
     * @param key   the key whose value needs to be updated
     * @param value the new value to be assigned
     * @throws IllegalArgumentException if the key does not exist
     * @pre key is not null, value is not null, key exists in dictionary
     * @post The value of the key is updated
     */
    void update(K key, V value);

    /**
     * lookup() - Retrieves the value associated with a given key.
     *
     * @param key the key whose value is to be retrieved
     * @return the value associated with the key, or null if the key is not found
     * @pre key is not null
     * @post No changes to the dictionary
     */
    V lookup(K key);
}
