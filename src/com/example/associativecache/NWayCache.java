package com.example.associativecache;

import com.example.associativecache.algorithms.*;
import java.util.*;
/**
 * Created by anamika on 3/25/18.
 *
 * Implementation of N-way set associative cache
 * Key value is hashed to retrieve a tag and an index
 * Overrides all the methods from Cache Interface -> CacheINTF
 * Implemented as array of cache blocks
 *
 *
 */
public class NWayCache<Key,Value> implements CacheINTF<Key,Value> {
    private final int numEntriesPerBlock;
    private final int numBlocks;
    private ReplacementAlgo evictionAlgo;
    private CacheBlock<Key,Value>[] cacheArr;            // Cache array to stores cache blocks


    /* Constructors --------------------------------------------------------> */

    // By default when an eviction policy isn't mentioned, LRU is used

    public NWayCache(int numB, int numEPB)
    {
        this.numBlocks = numB;
        this.numEntriesPerBlock = numEPB;
        this.evictionAlgo = new LRU();
        this.cacheArr = new CacheBlock[numB];
        this.clearCache();
    }

    public NWayCache(int numB, int numEPB, ReplacementAlgo policy)
    {
        this.numBlocks = numB;
        this.numEntriesPerBlock = numEPB;
        this.evictionAlgo = policy;
        this.cacheArr = new CacheBlock[numB];
        this.clearCache();
    }

     /* Methods --------------------------------------------------------> */



    /*
     * Calculates Tag based on Key object - uses object.hashCode ( Can be overridden if required )
     */
    protected int hashFunc(Key k)
    {
        return Math.abs(k.hashCode());
    }

    /*
     * Calculates the index of the block based on the calculated tag value
     */
    public int getBlockIndex(int tag)
    {
        int indx = tag%this.numBlocks;
        return indx;
    }

    // To check if two values are same
    public boolean isEqual(Value v1, Value v2)
    {
        return (v1==v2);
    }


    @Override
    public void put(Key k, Value v) {
        int tag = hashFunc(k);
        int i = getBlockIndex(tag);

        // NEWER VERSION with single scan //

        //returns index of entry if found, else returns the entry that can be evicted - SCAN 1
        int index = this.evictionAlgo.evictionCacheEntry(this.cacheArr[i], tag);

        if( index!=-1) {
            // returned entry
            IndividualEntry<Key, Value> temp = this.cacheArr[i].getEntries().get(index);


            if (temp.getTag() == tag) //if entry already exists
            {
                System.out.println("Duplicate Entry detected.");
                Value currVal = temp.getValue();

                if (!this.isEqual(currVal, v)) //If the existing value in cache and new value are not same
                {
                    //update the outdated entry
                    temp.setValue(v);
                    temp.setCount(temp.getCount()+1);
                    temp.setAccessTime(System.currentTimeMillis());
                    return;

                } else    //if an entry already exists and the value remains unchanged
                {
                    // If access time needs to be modified --> uncomment the following lines
                    //entry.setAccessTime(System.currentTimeMillis());
                    //temp.setCount(temp.getCount()+1);
                    return;
                }

            }
        }

        // If the entry doesn't already exist

        IndividualEntry<Key, Value> temp = new IndividualEntry<>(tag, v);

        if (this.cacheArr[i].getCurrSize() == this.cacheArr[i].getCapacity()) // if cache if full
        {
            //Carry out eviction
            System.out.println("Entry evicted from the cache:\t" + this.cacheArr[i].getEntries().get(index));
            this.cacheArr[i].addEntryAtIndex(index, temp); //replaces evicted entry with new one
        }
        else
            this.cacheArr[i].addEntry(temp); //adds the entry to cache


        /*

        // OLDER VERSION with additional scan during eviction //

        IndividualEntry<Key,Value> entry = this.cacheArr[i].findSpecific(tag);
        if (entry != null)
        {
            Value currVal = entry.getValue();

            if(!this.isEqual(currVal,v)) //If the existing value in cache and new value are not same
            {
                //update the outdated entry
                entry.setValue(v);
                entry.setAccessTime(System.currentTimeMillis());
                return;

            }

            else    //if an entry already exists and the value remains unchanged
            {
                // If access time needs to be modified --> uncomment the following lines
                //entry.setAccessTime(System.currentTimeMillis());
                return;
            }

        }
        IndividualEntry<Key,Value> temp = new IndividualEntry<>(tag,v);
        if (this.cacheArr[i].getCurrSize()==this.cacheArr[i].getCapacity())
        {
            //capacity is full --> invoke eviction policy
            //find index to place new entry
            int index = this.evictionAlgo.evictionCacheEntry(this.cacheArr[i]);
            this.cacheArr[i].addEntryAtIndex(index,temp);  //replaces older entry with the new one

        }
        else
        {
            this.cacheArr[i].addEntry(temp);
        }*/

    }


    @Override
    public Value get(Key k) throws CacheLoadException
    {
        int tag = hashFunc(k);
        int i = getBlockIndex(tag);
        IndividualEntry<Key,Value> entry = this.cacheArr[i].findSpecific(tag);
        if (entry == null)
        {
            throw new CacheLoadException("Cache miss");
            /*
             * Exception should be caught by the calling program , which should then make a function call
             * to fetch data from memory and also load it in the cache (Memory call-- Outside the scope)
             */
        }
        //System.out.println("After modifying access time");
        entry.setCount(entry.getCount()+1);
        entry.setAccessTime(System.currentTimeMillis());
        return entry.getValue();

    }

    @Override
    public void delKey(Key k)
    {
        int tag = hashFunc(k);
        int i = getBlockIndex(tag);
        this.cacheArr[i].delSpecific(tag);

    }

    @Override
    public void clearCache()
    {
        for (int i=0;i<this.numBlocks;i++)
            cacheArr[i]= new CacheBlock<>(this.numEntriesPerBlock);
    }

}
