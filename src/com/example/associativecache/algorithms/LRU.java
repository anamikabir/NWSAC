package com.example.associativecache.algorithms;

import com.example.associativecache.*;

import java.util.ArrayList;


/**
 * Created by anamika on 3/22/18.
 */
public class LRU<Key, Value> extends ReplacementAlgo<Key, Value> {

    /*
     * Implementing cache eviction method to remove the oldest entry from the cache block
     * Most recent entry is at the end
     */
    @Override
    public int evictionCacheEntry(CacheBlock<Key,Value> block)
    {
        int res = 0;

        if (block.getCurrSize() == 0)
            return res;

        Long minTime = Long.MAX_VALUE;
        IndividualEntry<Key,Value> markedForDeletion = null;
        ArrayList<IndividualEntry<Key,Value>> arr = block.getEntries();

        for(int i=0;i<arr.size();i++)
        {
            IndividualEntry<Key,Value> temp = arr.get(i);
            if (temp.getAccessTime()<minTime)
            {
                minTime = temp.getAccessTime();
                markedForDeletion = temp;
                res = i;
            }
        }

        System.out.println("LRU Deleted Entry: "+markedForDeletion);
        return res;
    }

}
