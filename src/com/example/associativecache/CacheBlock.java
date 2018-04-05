package com.example.associativecache;

import java.util.*;
import java.lang.Long;

/**
 * Created by anamika on 3/24/18.
 *
 * This class represents a cache block with at most N entries
 * Implemented using priority queue (based on access time)
 * Also implements methods to delete first or last elements of priority queue,
 * which can be utilized by cache replacement algorithms
 *
 */
public class CacheBlock<Key,Value> {

    private PriorityQueue<IndividualEntry<Key,Value>> cacheEntries;
    private int capacity;

    /* Constructors --------------------------------------------------------> */

    public CacheBlock()
    {
        Comparator<IndividualEntry> comparator = new MyComparator();
        this.cacheEntries = new PriorityQueue<>(comparator);
        this.capacity = 0;
    }
    public CacheBlock(int n)
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

    // Method to delete last entry of the priority queue (with greatest value of access time)

     public IndividualEntry delLast()
     {
         if (getCurrSize()==0)
             return null;
         Comparator<IndividualEntry> comparator = new MyComparator();
         PriorityQueue<IndividualEntry<Key,Value>> pqnew = new PriorityQueue<>(comparator);
         while(cacheEntries.size() > 1)
         {
             pqnew.add(cacheEntries.poll());
         }

         IndividualEntry temp = cacheEntries.poll();
         this.cacheEntries = pqnew;
         return temp;
     }

     public void reorderCache()
     {
         if (getCurrSize()==0)
             return;

         try {
             Comparator<IndividualEntry> comparator = new MyComparator();
             PriorityQueue<IndividualEntry<Key, Value>> pqnew = new PriorityQueue<>(comparator);
             while (cacheEntries.size() > 0) {
                 IndividualEntry<Key, Value> temp = cacheEntries.poll();
                 //System.out.println(temp);
                 pqnew.add(temp);
             }
             this.cacheEntries = pqnew;
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }

     }

    // Method to delete entry with a specific tag

    public void delSpecific(int tag)
     {
         for(IndividualEntry entry: cacheEntries)
             if (entry.getTag()==tag)
             {
                 cacheEntries.remove(entry);
                 System.out.println("Entry removed from cache:\n"+entry);
             }

     }

     public void delEntry(IndividualEntry entry){
         //if (cacheEntries.contains(entry))
         cacheEntries.remove(entry);
     }

    // Method to find a specific individual entry with a given tag

    public IndividualEntry findSpecific(int tag)
    {
        for(IndividualEntry<Key,Value> entry: cacheEntries)
            if (entry.getTag()==tag)
            {
                return entry;
            }
        return null;
    }

    // Method to view all the entries in cache block

    public void viewEntries()
    {
        for(IndividualEntry<Key,Value> entry: cacheEntries)
            System.out.println(entry);
    }


    // Method to add an entry to the cache block

     public void addEntry(IndividualEntry<Key,Value> entry)
     {

         this.cacheEntries.add(entry);
         System.out.println("Entry added to cache:\n"+entry);
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