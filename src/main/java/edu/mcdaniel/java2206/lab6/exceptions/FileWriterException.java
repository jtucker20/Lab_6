package edu.mcdaniel.java2206.lab6.exceptions;

import java.io.IOException;

public class FileWriterException extends RuntimeException {

    public FileWriterException(String s, IOException ioe) {
        super(s, ioe);
    }
}
