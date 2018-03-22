package com.example.associativecache;

/**
 * @author anamika
 * @version 1.0
 *
 *
 * Created by anamika on 3/20/18.
 * Package accessible interface for supporting creation of a generic implementation of cache
 * Using generic classes for key,value
 *
 */

public interface CacheINTF<Key,Value>
{
    /*
     * Method for storing associated key and value pairs
     * @param Key , Value
     * @return void
     */
     void put(Key k, Value v);

    /*
     * Method for retrieving value associated with the key
     * Can raise an Exception if entry is not found
     * @param Key
     * @return Value
     */

     Value get(Key k) throws CacheLoadException;

    /*
     * Method for deleting key,value from cache, returns value for the deleted entry
     * @param Key
     * @return Value
     */

     Value delKey(Key k);

     /*
      * Method for deleting the entire cache(along with stored entries)
      * @param No parameters passed
      * @return void
      */

     void clearCache();

}
