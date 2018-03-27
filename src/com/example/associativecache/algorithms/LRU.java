package com.example.associativecache.algorithms;

import com.example.associativecache.*;



/**
 * Created by anamika on 3/22/18.
 */
public class LRU<Key, Value> extends ReplacementAlgo<Key, Value> {

    /*
     * Implementing cache eviction method to remove the oldest entry from the cache block
     * Most recent entry is at the end
     */
    @Override
    public void evictionCacheEntry(CacheBlock<Key,Value> block)
    {
        if(block.getCurrSize()==0)
            return;
        IndividualEntry deletedItem = block.delFirst();

        System.out.println("LRU Deleted Entry: "+deletedItem);

    }

}
