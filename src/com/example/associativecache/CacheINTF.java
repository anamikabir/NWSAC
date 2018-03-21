package com.example.associativecache;

/**
 * @author anamika
 * @version 1.0
 *
 *
 * Created by anamika on 3/20/18.
 * Package accessible interface for supporting creation of a generic implementation of cache
 *
 */
public interface CacheINTF<Key,Value>
{
    /*
    * This method associates key and value pairs 
    */
    void put(Key k, Value v);


}
