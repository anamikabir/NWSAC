package com.example.associativecache.algorithms;

import com.example.associativecache.*;
import java.util.List;

/**
 * Created by anamika on 3/22/18.
 */
public class LRU<Key, Value> implements ReplacementAlgo<Key, Value> {

    /*
     * Implementing cache eviction method to remove the oldest entry from the cache block
     */
    @Override
    public void evictionCacheEntry(List<IndividualEntry<Key, Value>> block)
    {
        if(block.isEmpty())
            return;
        for(IndividualEntry<Key, Value> entry:block)
        {
            ;
        }
    }

}
