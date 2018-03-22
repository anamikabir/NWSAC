package com.example.associativecache;

/**
 * Created by anamika on 3/22/18.
 */
public class IndividualEntry<Key, Value>{
    private final long accessTime;



    IndividualEntry()
    {
        this.accessTime=System.currentTimeMillis();
    }
}
