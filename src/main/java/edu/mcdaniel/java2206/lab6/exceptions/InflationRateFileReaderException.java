package edu.mcdaniel.java2206.lab6.exceptions;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class InflationRateFileReaderException extends Exception {

    public InflationRateFileReaderException(){
        super();
    }

    public InflationRateFileReaderException(String message){
        super(message);
    }

    public InflationRateFileReaderException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
