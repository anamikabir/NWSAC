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
public class IndividualEntry<Key, Value>{
    private long accessTime;
    private int tag;
    private Value myVal;
    private boolean isValid;

    /*
     * Calculates Tag based on Key object - uses object.hashCode
     */
    private int calcTag(Key k)
    {
        return Math.abs(k.hashCode());
    }

    /*
     *  Calculates the access time based on current system time
     */
    private long calcAccessTime()
    {
        return System.currentTimeMillis();
    }


    /* Constructors --------------------------------------------------------> */

    IndividualEntry()
    {
        this.accessTime=calcAccessTime();
        this.tag = -1;
        this.myVal = null;
        this.isValid = false;
    }
    IndividualEntry(Key k, Value v)
    {
        this.accessTime=calcAccessTime();
        this.tag = calcTag(k);
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
    public void getAccessTime(long timestamp)
    {
        this.accessTime = timestamp;
    }

    public Value getMyVal() {
        return myVal;
    }

    // Need to override toString method of Value if it's not a primitive data type

    public String toString()
    {
        String msg = "Tag: "+this.tag+" , Value: "+this.myVal;
        return msg;
    }
}
