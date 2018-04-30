package com.example.associativecache.algorithms;

import com.example.associativecache.*;

import java.util.ArrayList;

/**
 * Created by anamika on 4/29/18.
 */
public class LFU <Key, Value> extends ReplacementAlgo<Key, Value> {

    /*
     * Least Frequently Used
     * Implementing cache eviction method to remove the least frequently entry from the cache block
     */
    @Override
    public int evictionCacheEntry(CacheBlock<Key,Value> block, int tag)
    {
        int res = -1;

        if (block.getCurrSize() == 0)
            return res;

        Integer minCount = Integer.MAX_VALUE;
        //IndividualEntry<Key,Value> markedForDeletion = null;
        ArrayList<IndividualEntry<Key,Value>> arr = block.getEntries();

        for(int i=0;i<arr.size();i++)
        {
            IndividualEntry<Key,Value> temp = arr.get(i);
            if (temp.getTag()== tag)
            {
                return i;
            }
            if (temp.getCount() <minCount)
            {
                minCount = temp.getCount();
                //markedForDeletion = temp;
                res = i;
            }
        }

        //System.out.println("LRU Entry: "+markedForDeletion);
        return res;
    }
}
