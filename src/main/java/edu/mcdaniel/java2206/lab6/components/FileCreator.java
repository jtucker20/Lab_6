package edu.mcdaniel.java2206.lab6.components;

import edu.mcdaniel.java2206.lab6.exceptions.FileWriterException;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;


public class FileCreator {


    public File createFileSafe(String fileName, String ext) {
        if (fileName == null || fileName.isEmpty() || fileName.isBlank()||
                !Pattern.matches("^[a-zA-Z0-9]+$",fileName)) {
            fileName = "temp";
        }
        //Checking here for null, empty, and blank

        if(ext == null|| ext.isEmpty() || ext.isBlank()|| ext.length() !=3 ||
                !Pattern.matches("^[a-zA-Z0-9]+$", ext)){
            ext = "txt";
        }

        File file = null;
        try{
            file = new File(fileName + "." + ext);

            if(file.exists()){
                String randomEnding = fileName + (new Random()).nextInt(Integer.MAX_VALUE);
                file = new File(randomEnding + "." + ext);
            }
            file.createNewFile();
        } catch (IOException ioe){
            throw new FileWriterException("Failed to init new file" + fileName +"."+ext, ioe);
        }

        //Can the file be read and written?
        if(file.canRead() && file.canWrite()){
            return file;
        }
        //Can the file be read?
        if(!file.canRead()){
            throw new FileWriterException("Failed to make a readable file at: "
                    + fileName + "." + ext, null);
        }
        //Cam the file be written?
        if(!file.canExecute()){
            throw new FileWriterException("Failed to make a readable file at: "
            + fileName +"." + ext,null);
        }

        //Worst case, return null if nothing is caught
        return null;
    }



}
