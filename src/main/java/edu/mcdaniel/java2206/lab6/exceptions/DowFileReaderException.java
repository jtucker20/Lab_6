package edu.mcdaniel.java2206.lab6.exceptions;

import edu.mcdaniel.java2206.lab6.components.DowFileReader;

public class DowFileReaderException extends Exception {
    //TODO: MAKE THIS ACTUALLY WORK!

    public DowFileReaderException(){
        super();
    }

    public DowFileReaderException(String message){
        super(message);
    }

    public DowFileReaderException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
