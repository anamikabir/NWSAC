package com.example.associativecache.algorithms;

import com.example.associativecache.*;

import java.util.ArrayList;

/**
 * Created by anamika on 4/29/18.
 */
public class LFUDU<Key, Value> extends ReplacementAlgo<Key, Value> {

    /*
     * Least Frequently Used with Dynamic Aging
     * Implementing cache eviction method to remove the least frequently entry from the cache block, and resolving ties based on the age of least popular entries
     */
    @Override
    public int evictionCacheEntry(CacheBlock<Key,Value> block, int tag)
    {
        int res = -1;

        if (block.getCurrSize() == 0)
            return res;

        Integer minCount = Integer.MAX_VALUE;
        Long ageofEntry=Long.MAX_VALUE;
        ArrayList<IndividualEntry<Key,Value>> arr = block.getEntries();

        for(int i=0;i<arr.size();i++)
        {
            IndividualEntry<Key,Value> temp = arr.get(i);
            if (temp.getTag() == tag)
            {
                return i;
            }
            if (temp.getCount()==minCount)
            {
                if(temp.getAccessTime()<ageofEntry)
                {
                    ageofEntry = temp.getAccessTime();
                    res = i;
                }
            }
            if (temp.getCount() <minCount)
            {
                minCount = temp.getCount();
                ageofEntry = temp.getAccessTime();
                res = i;
            }

        }

        //System.out.println("LRU Entry: "+markedForDeletion);
        return res;
    }
}
