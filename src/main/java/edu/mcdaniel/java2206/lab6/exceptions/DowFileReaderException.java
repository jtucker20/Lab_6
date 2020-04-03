package edu.mcdaniel.java2206.lab6.exceptions;

import edu.mcdaniel.java2206.lab6.components.DowFileReader;

public class DowFileReaderException extends Exception {

    // Following the same format as the InflationRateException I first called super to bring in the Exception class
    public DowFileReaderException(){
        super();
    }

    //Again, just following the same format, this allows me to write in what had happened in a particular instance as a
        //string
    public DowFileReaderException(String message){
        super(message);
    }

    //This brings in a throwable in order to have the ability to throw an exception
    //Again, just having the super method in there in order to make things easier for myself
    public DowFileReaderException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
