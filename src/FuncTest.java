/**
 * Created by anamika on 3/26/18.
 */

import com.example.associativecache.*;
import com.example.associativecache.algorithms.*;

public class FuncTest {
    public void TestPutGet() throws InterruptedException
    {
        int numberBlocks = 5;
        int numberEntriesPerBlock = 2;
        NWayCache nC = new NWayCache(numberBlocks,numberEntriesPerBlock);
        nC.put("key1","value1");
        Thread.sleep(1000);
        nC.put("key2","value2");
        Thread.sleep(1000);
        try {
            System.out.println(nC.get("key2"));
            System.out.println(nC.get("key3"));
        }
        catch (CacheLoadException ex)
        {
            System.out.println("Caught Exception "+ex);
            //Make call to memory and fetch the data and put it in cache
        }
    }
    public void TestEvictionLRU() throws InterruptedException
    {
        int numberBlocks = 2;
        int numberEntriesPerBlock = 2;
        NWayCache nC = new NWayCache(numberBlocks,numberEntriesPerBlock);
        nC.put(1,"value1");
        Thread.sleep(1000);
        nC.put(2,"value2");
        Thread.sleep(1000);
        nC.put(3,"value3");
        Thread.sleep(1000);
        nC.put(4,"value4");
        Thread.sleep(1000);
        try {
            System.out.println(nC.get(1));
        }
        catch (CacheLoadException ex)
        {
            System.out.println("Caught Exception "+ex);
            //Make call to memory and fetch the data and put it in cache
        }
        Thread.sleep(4000);
        nC.put(5,"value5"); //Should cause data entry 3 to be deleted from the cache
    }

    public void TestEvictionMRU() throws InterruptedException
    {
        int numberBlocks = 2;
        int numberEntriesPerBlock = 2;
        MRU repAlgo = new MRU();
        NWayCache nC = new NWayCache(numberBlocks,numberEntriesPerBlock,repAlgo);
        nC.put(1,"value1");
        Thread.sleep(1000);
        nC.put(2,"value2");
        Thread.sleep(1000);
        nC.put(3,"value3");
        Thread.sleep(1000);
        nC.put(4,"value4");
        Thread.sleep(1000);
        try {
            System.out.println(nC.get(1));
        }
        catch (CacheLoadException ex)
        {
            System.out.println("Caught Exception "+ex);
            //Make call to memory and fetch the data and put it in cache
        }
        Thread.sleep(4000);
        nC.put(5,"value5"); //Should cause data entry 1 to be deleted from the cache
    }

    public void TestDuplicateEntry() throws InterruptedException
    {

        int numberBlocks = 2;
        int numberEntriesPerBlock = 2;
        NWayCache nC = new NWayCache(numberBlocks,numberEntriesPerBlock);
        nC.put(1,"value1");
        Thread.sleep(1000);
        nC.put(2,"value2");
        Thread.sleep(1000);
        nC.put(3,"value3");
        Thread.sleep(1000);
        nC.put(4,"value4");
        Thread.sleep(1000);
        nC.put(1,"value111"); // Should replace outdated cache entry thereby modifying access time
        Thread.sleep(1000);
        nC.put(3,"value3"); // Should be ignored as the value remains unchanged
        Thread.sleep(1000);
        nC.put(5,"value5"); // LRU should cause data entry 3 to be deleted from the cache
    }

    public void TestLFU() throws InterruptedException
    {

        int numberBlocks = 2;
        int numberEntriesPerBlock = 2;
        LFU repAlgo = new LFU();
        NWayCache nC = new NWayCache(numberBlocks,numberEntriesPerBlock,repAlgo);
        nC.put(1,"value1");
        Thread.sleep(1000);
        nC.put(2,"value2");
        Thread.sleep(1000);
        nC.put(3,"value3");
        Thread.sleep(1000);
        nC.put(4,"value4");
        Thread.sleep(1000);
        nC.put(1,"value111"); // Should replace outdated cache entry thereby modifying count
        Thread.sleep(1000);
        nC.put(3,"value3"); // Should be ignored as the value remains unchanged - doesn't modify count
        Thread.sleep(1000);
        nC.put(5,"value5"); // LRU should cause data entry 3 to be deleted from the cache, as it's count value is lowest in the cache block
    }

    public void TestLFUDynamicAge() throws InterruptedException
    {

        int numberBlocks = 2;
        int numberEntriesPerBlock = 2;
        LFUDU repAlgo = new LFUDU();
        NWayCache nC = new NWayCache(numberBlocks,numberEntriesPerBlock,repAlgo);
        nC.put(1,"value1");
        Thread.sleep(1000);
        nC.put(2,"value2");
        Thread.sleep(1000);
        nC.put(3,"value3");
        Thread.sleep(1000);
        nC.put(4,"value4");
        Thread.sleep(1000);
        nC.put(5,"value5"); // should cause data entry 1 to be deleted from the cache, as both entry 1 and entry 3 have same count, but 1 is older
        Thread.sleep(1000);
        try {
            System.out.println(nC.get(2)); //count of 2 should increase
        }
        catch (CacheLoadException ex)
        {
            System.out.println("Caught Exception "+ex);
            //Make call to memory and fetch the data and put it in cache
        }
        Thread.sleep(1000);
        nC.put(6,"value6"); // Should cause value 4 to get evicted, since entry 2 is more popular and has higher count
    }

    public void TestDelKey() throws InterruptedException
    {
        int numberBlocks = 5;
        int numberEntriesPerBlock = 2;
        NWayCache nC = new NWayCache(numberBlocks,numberEntriesPerBlock);
        nC.put("key1","value1");
        Thread.sleep(1000);
        nC.put("key2","value2");
        Thread.sleep(1000);
        nC.delKey("key1");
        Thread.sleep(1000);
        try {
            System.out.println(nC.get("key2"));
            System.out.println(nC.get("key1"));
        }
        catch (CacheLoadException ex)
        {
            System.out.println("Caught Exception "+ex);
            //Make call to memory and fetch the data and put it in cache
        }
    }

    public static void main(String[] args)
    {
        FuncTest test = new FuncTest();
        try
        {
            /*
             *  Uncomment the functions that needs to be tested
             */

            //test.TestPutGet();

            //test.TestEvictionLRU();

            //test.TestEvictionMRU();

            //test.TestDuplicateEntry();

            //test.TestDelKey();

            //test.TestLFU();

            //test.TestLFUDynamicAge();

        }
        catch (Exception e) //to handle InterruptedException
        {
            System.out.println("Caught Exception "+e);
        }
    }
}
