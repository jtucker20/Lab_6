package edu.mcdaniel.java2206.lab6.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InflationRateFileReaderException extends Exception {

    public InflationRateFileReaderException(){
        super();
    }

    public InflationRateFileReaderException(String message){
        super(message);
    }

    public InflationRateFileReaderException(String msg, Throwable throwable){
        super(msg, throwable);
        Logger logger = LogManager.getLogger(InflationRateFileReaderException.class);
        logger.error("The program failed to: {}", msg);
        logger.error(throwable);
    }
}
