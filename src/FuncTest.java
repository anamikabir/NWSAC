/**
 * Created by anamika on 3/26/18.
 */

import com.example.associativecache.*;
import com.example.associativecache.algorithms.*;

public class FuncTest {
    public void TestWrite()
    {
        int numberBlocks = 5;
        int numberEntriesPerBlock = 2;
        NWayCache nC = new NWayCache(numberBlocks,numberEntriesPerBlock);
        nC.put("key1","value1");
        nC.put("key2","value2");
        try {
            System.out.println(nC.get("key3"));
        }
        catch (CacheLoadException ex)
        {
            System.out.println("Caught Exception "+ex);
            //Make call to memory and fetch the data and put it in cache
        }
    }
    public void TestEvistion() throws InterruptedException
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
        nC.put(5,"value5");
    }


    public static void main(String[] args)
    {
        FuncTest test = new FuncTest();
        try {
            //test.TestWrite();

            test.TestEvistion();
        }
        catch (Exception e)
        {
            System.out.println("Caught Exception "+e);
        }
    }
}
