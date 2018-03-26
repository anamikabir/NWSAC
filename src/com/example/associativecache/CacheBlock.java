package com.example.associativecache;

import java.util.*;
import java.lang.Long;

/**
 * Created by anamika on 3/24/18.
 *
 * This class signifies a cache block with at most N entries
 * Implemented using priority queue (based on access time)
 * Also implements methods to delete first or last elements, which can be used by cache replacement algo
 *
 */
public class CacheBlock<Key,Value> {

    private PriorityQueue<IndividualEntry<Key,Value>> cacheEntries;
    private int capacity;

    /* Constructors --------------------------------------------------------> */

    CacheBlock()
    {
        Comparator<IndividualEntry> comparator = new MyComparator();
        this.cacheEntries = new PriorityQueue<>(comparator);
        this.capacity = 0;
    }
    CacheBlock(int n)
    {
        Comparator<IndividualEntry> comparator = new MyComparator();
        this.cacheEntries = new PriorityQueue<>(comparator);
        this.capacity = n;
    }

    /* getters and setters --------------------------------------------------------> */

     public int getCurrSize()
     {
         return cacheEntries.size();
     }

    public int getCapacity()
    {
        return this.capacity;
    }


    public void setCapacity(int capacity)
    {
         this.capacity = capacity;
    }

    /* Methods --------------------------------------------------------> */

     // Method to delete first entry of the priority queue (with least value of access time)

     public IndividualEntry delFirst()
     {
         if (getCurrSize()==0)
            return null;
         return this.cacheEntries.poll();
     }

    //Method to delete last entry of the priority queue (with greatest value of access time)

     public IndividualEntry delLast()
     {
         if (getCurrSize()==0)
             return null;

         PriorityQueue<IndividualEntry<Key,Value>> pqnew = new PriorityQueue<>();
         while(cacheEntries.size() > 1)
         {
             pqnew.add(cacheEntries.poll());
         }

         IndividualEntry temp = cacheEntries.poll();
         this.cacheEntries = pqnew;
         return temp;
     }

    //Method to delete entry with a specific tag

    public void delSpecific(int tag)
     {
         for(IndividualEntry entry: cacheEntries)
             if (entry.getTag()==tag)
             {
                 cacheEntries.remove(entry);
             }

     }

    //Method to find a specific individual entry with a given tag

    public IndividualEntry findSpecific(int tag)
    {
        for(IndividualEntry<Key,Value> entry: cacheEntries)
            if (entry.getTag()==tag)
            {
                return entry;
            }
        return null;
    }


    //Method to add an entry to the cache block

     public void addEntry(IndividualEntry<Key,Value> entry)
     {
         this.cacheEntries.add(entry);
     }


}

/*
 *  Comparator class to compare two individual Entry based on their access time
 */

class MyComparator implements Comparator<IndividualEntry> {

    @Override
    public int compare(IndividualEntry a, IndividualEntry b)
    {
        Long val1 = a.getAccessTime();
        Long val2 = b.getAccessTime();

        return val1.compareTo(val2);
    }
}