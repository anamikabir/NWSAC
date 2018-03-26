package com.example.associativecache;

import com.example.associativecache.algorithms.*;
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

    NWayCache(int numB, int numEPB)
    {
        this.numBlocks = numB;
        this.numEntriesPerBlock = numEPB;
        this.evictionAlgo = new LRU();
        this.cacheArr = new CacheBlock[numB];
        this.clearCache();
    }

    NWayCache(int numB, int numEPB, ReplacementAlgo policy)
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



    @Override
    public void put(Key k, Value v)
    {
        int tag = hashFunc(k);
        int i = getBlockIndex(tag);
        IndividualEntry<Key,Value> entry = this.cacheArr[i].findSpecific(tag);
        if (entry != null)
        {
            // entry.setAccessTime(); // If access time needs to be modified
            return;  //if an entry already exists

        }
        IndividualEntry<Key,Value> temp = new IndividualEntry<>(tag,v);
        if (this.cacheArr[i].getCurrSize()==this.cacheArr[i].getCapacity())
        {
            //capacity is full --> invoke eviction policy
            this.evictionAlgo.evictionCacheEntry(this.cacheArr[i]);
        }
        this.cacheArr[i].addEntry(temp);
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
        entry.setAccessTime();
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
            cacheArr[i]= new CacheBlock<Key,Value>(this.numEntriesPerBlock);
    }

}
