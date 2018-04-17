package com.example.associativecache.algorithms;

import com.example.associativecache.*;

import java.util.ArrayList;

/**
 * Created by anamika on 3/25/18.
 */
public class MRU<Key, Value> extends ReplacementAlgo<Key, Value> {

    /*
     * Implementing cache eviction method to remove the most recently used entry from the cache block
     */
    @Override
    public int evictionCacheEntry(CacheBlock<Key,Value> block) {
        int res = 0;

        if (block.getCurrSize() == 0)
            return res;

        Long maxTime = Long.MIN_VALUE;
        IndividualEntry<Key,Value> markedForDeletion = null;
        ArrayList<IndividualEntry<Key,Value>> arr = block.getEntries();

        for(int i=0;i<arr.size();i++)
        {
            IndividualEntry<Key,Value> temp = arr.get(i);
            if (temp.getAccessTime()>maxTime)
            {
                maxTime = temp.getAccessTime();
                markedForDeletion = temp;
                res = i;
            }
        }

        System.out.println("MRU Deleted Entry: "+markedForDeletion);
        return res;

    }


}

