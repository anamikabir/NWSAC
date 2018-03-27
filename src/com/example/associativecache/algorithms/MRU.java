package com.example.associativecache.algorithms;

import com.example.associativecache.*;

/**
 * Created by anamika on 3/25/18.
 */
public class MRU<Key, Value> extends ReplacementAlgo<Key, Value> {

    /*
     * Implementing cache eviction method to remove the most recently used entry from the cache block
     */
    @Override
    public void evictionCacheEntry(CacheBlock<Key,Value> block) {
        if (block.getCurrSize() == 0)
            return;
        IndividualEntry deletedItem = block.delLast();

        System.out.println("MRU Deleted Entry: "+deletedItem);

    }


}

