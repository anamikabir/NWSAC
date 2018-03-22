package com.example.associativecache;

/**
 * Created by anamika on 3/22/18.
 */
public class CacheLoadException extends Exception {

    public CacheLoadException() {}

    // Constructor that accepts a message

    public CacheLoadException(String message)
    {
        super(message);
    }
}
