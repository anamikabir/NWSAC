package com.example.associativecache.algorithms;

import com.example.associativecache.*;

import java.util.ArrayList;

/**
 * Created by anamika on 3/25/18.
 */
public class MRU<Key, Value> extends ReplacementAlgo<Key, Value> {

    /*
     * Most Recently Used
     * Implementing cache eviction method to remove the most recently used entry from the cache block
     */
    @Override
    public int evictionCacheEntry(CacheBlock<Key,Value> block, int tag) {
        int res = -1;

        if (block.getCurrSize() == 0)
            return res;

        Long maxTime = Long.MIN_VALUE;
        IndividualEntry<Key,Value> markedForDeletion = null;
        ArrayList<IndividualEntry<Key,Value>> arr = block.getEntries();

        for(int i=0;i<arr.size();i++)
        {
            IndividualEntry<Key,Value> temp = arr.get(i);
            if (temp.getTag()== tag)
            {
                System.out.println("Duplicate Key detected: "+markedForDeletion +"\nOverwriting older value");
                return i;
            }
            if (temp.getAccessTime()>maxTime)
            {
                maxTime = temp.getAccessTime();
                markedForDeletion = temp;
                res = i;
            }
        }

        //System.out.println("MRU Entry: "+markedForDeletion);
        return res;

    }


}

