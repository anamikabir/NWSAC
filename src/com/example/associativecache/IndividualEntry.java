package com.example.associativecache;

/**
 * Created by anamika on 3/22/18.
 *
 * This class represents a single entry in the cache block
 * Unique Tags(keys) are calculated using Object.hashCode() [custom hashing method can be written]
 * Value is the Data at the memory location
 * Access time is initialized with creation time and it gets updated when the entry is accessed
 * If value is a class object, override toString() method to view contents
 */
public class IndividualEntry<Key,Value>{
    private long accessTime;
    private long creationTime;
    private int tag;
    private Value myVal;
    private boolean isValid;


    /*
     *  Calculates the access time based on current system time
     */
    private long calcAccessTime()
    {
        return System.currentTimeMillis();
    }


    /* Constructors --------------------------------------------------------> */

    public IndividualEntry()
    {
        this.creationTime = calcAccessTime();
        this.accessTime=this.creationTime;
        this.tag = -1;
        this.myVal = null;
        this.isValid = false;
    }
    public IndividualEntry(int tag, Value v)
    {
        this.creationTime = calcAccessTime();
        this.accessTime=this.creationTime;
        this.tag = tag;
        this.myVal = v;
        this.isValid = true;
    }


    /* Getters and Setters --------------------------------------------------> */

    /*
     * Get integer tag (Hash value of Key)
     */
    public int getTag()
    {
        return this.tag;
    }


    /*
     * Get stored value
     */

    public Value getValue()
    {
        return this.myVal;
    }

    /*
     * Get validity of cache block
     */
    public boolean isValid()
    {
        return this.isValid;
    }

    /*
     * Get current access time of the entry (in milliseconds)
     */
    public long getAccessTime()
    {
        return this.accessTime;
    }

    /*
     * Get creation time of the entry (in milliseconds)
     */
    public long getCreationTime()
    {
        return this.creationTime;
    }

    /*
     * Set integer tag (Hash value of Key)
     */
    public void setTag(int tag)
    {
        this.tag=tag;
    }


    /*
     * Set stored value
     */
    public void setValue(Value val)
    {
        this.myVal = val;
    }

    /*
     * Set validity of cache block
     */
    public void setValidity(boolean isValid)
    {
        this.isValid = isValid;
    }

    /*
     * Set current access time of the entry (in milliseconds)
     */
    public void setAccessTime(long timestamp)
    {
        this.accessTime = timestamp;
    }


    // Need to override toString method of Value if it's not a primitive data type

    public String toString()
    {
        String msg = "Tag: "+this.tag+" , Value: "+this.myVal+" , Access time(in millisec): "+this.accessTime;
        return msg;
    }
}
