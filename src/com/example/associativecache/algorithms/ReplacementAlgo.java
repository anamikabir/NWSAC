package com.example.associativecache.algorithms;


import com.example.associativecache.*;
import java.util.*;

/**
 * Created by anamika on 3/22/18.
 */
public interface ReplacementAlgo<Key,Value> {

    /*
     *  Method to implement cache eviction - deletes an existing entry from cache block to make space for the new entry
     *  @param cache block
     *  @return void
     */
    void evictionCacheEntry(List<IndividualEntry<Key, Value>> block);
}
