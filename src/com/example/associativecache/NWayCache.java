package com.example.associativecache;

import com.example.associativecache.algorithms.*;
/**
 * Created by anamika on 3/25/18.
 */
public class NWayCache {
    private final int numEnteriesPerBlock;
    private final int numBlocks;
    private ReplacementAlgo evictionAlgo;


    /* Constructors --------------------------------------------------------> */

    // By default when an eviction policy isn't mentioned, LRU is used

    NWayCache(int numB, int numEPB)
    {
        this.numBlocks = numB;
        this.numEnteriesPerBlock = numEPB;
        this.evictionAlgo = new LRU();
    }

    NWayCache(int numB, int numEPB, ReplacementAlgo policy)
    {
        this.numBlocks = numB;
        this.numEnteriesPerBlock = numEPB;
        this.evictionAlgo = policy;
    }

     /* Methods --------------------------------------------------------> */


}
