package com.example.associativecache;

import com.example.associativecache.algorithms.*;

/**
 * Created by anamika on 4/2/18.
 * Created to abstract the implementation of N-way cache and to support client to override some of the functionality
 */
public class ClientInterface<Key,Value> extends NWayCache<Key,Value>{

        //When no replacement strategies are mentioned, default LRU
        public ClientInterface(int numN, int numSets)
        {
            super(numN,numSets);
        }

        public ClientInterface(int numN, int numSets, ReplacementAlgo algo)
        {
            super(numN,numSets,algo);
        }

        /* To override replacement algorithm,
         * Extend the ReplacementAlgo class and provide custom implementation ( Say CustomAlgo )
         * Use the CustomAlgo class to create an object and pass it to the ClientInterface
         *
         * It's as simple as:
         *
         * CustomAlgo  repAlgo = new CustomAlgo();
         * ClientInterface myCache = new ClientInterface(N,numS,repAlgo);
         *
         */

        @Override protected int hashFunc(Key k)
        {
            /*
             *  Can be overriden to provide a custom hashing algorithm to calculate tag from Key object
             */
            return Math.abs(k.hashCode());
        }

}
