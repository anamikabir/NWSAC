package com.example.associativecache.algorithms;

import com.example.associativecache.*;


/**
 * Created by anamika on 3/22/18.
 * Abstract class for replacement strategy
 */
public abstract class ReplacementAlgo<Key,Value> {

    /*
     *  Method to implement cache eviction - deletes an existing entry from cache block to make space for the new entry
     *  @param cache block
     *  @return void
     */
    public abstract int evictionCacheEntry(CacheBlock<Key,Value> block);
}
