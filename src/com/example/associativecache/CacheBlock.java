package com.example.associativecache;

import java.util.*;
import java.lang.Long;
import com.example.associativecache.algorithms.*;

/**
 * Created by anamika on 3/24/18.
 */
public class CacheBlock<Key,Value> {

    private PriorityQueue<IndividualEntry> cacheEntries;
    private int capacity;

    /* Constructors --------------------------------------------------------> */

    CacheBlock()
    {
        Comparator<IndividualEntry> comparator = new MyComparator();
        this.cacheEntries = new PriorityQueue<IndividualEntry>(comparator);
        this.capacity = 0;
    }
    CacheBlock(int n)
    {
        Comparator<IndividualEntry> comparator = new MyComparator();
        this.cacheEntries = new PriorityQueue<IndividualEntry>(comparator);
        this.capacity = n;
    }

    /* Methods --------------------------------------------------------> */
     public int getCurrSize()
     {
         return cacheEntries.size();
     }


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

         PriorityQueue<IndividualEntry> pqnew = new PriorityQueue<IndividualEntry>();
         while(cacheEntries.size() > 1)
         {
             pqnew.add(cacheEntries.poll());
         }

         IndividualEntry temp = cacheEntries.poll();
         this.cacheEntries = pqnew;
         return temp;
     }

     public void delSpecific(Key k)
     {

     }

     public void addEntry(IndividualEntry entry)
     {
         this.cacheEntries.add(entry);
     }


}

class MyComparator implements Comparator<IndividualEntry> {

    @Override
    public int compare(IndividualEntry a, IndividualEntry b)
    {
        Long val1 = a.getAccessTime();
        Long val2 = b.getAccessTime();

        return val1.compareTo(val2);
    }
}