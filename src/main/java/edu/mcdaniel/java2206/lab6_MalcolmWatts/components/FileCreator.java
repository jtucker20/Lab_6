/*package edu.mcdaniel.java2206.lab6_MalcolmWatts.components;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

public class FileCreator {

    public File createFile(String fileName, String ext) throws IOException{
        File file = new File(fileName + "." + ext);
        if (file.createNewFile()){
            return file;
        }
        else{
            return null;
        }
    }

    public File createFileSafe(String fileName, String ext){
        if(fileName == null
                || fileName.isEmpty()
                || fileName.isBlank()
                || !Pattern.matches("^[a-zA-Z0-9]+$", fileName)){
            fileName = "temp";
        }
        if(ext == null
                || ext.isEmpty()
                || ext.isBlank()
                || ext.length() != 3
                || !Pattern.matches("^[a-zA-Z0-9]+$", ext)){
            ext = "txt";
        }
        File file = null;
        try{
            file = new File(fileName + "." + ext);
            if(file.exists()){
                String randomEnding = fileName + (new Random().nextInt(Integer.MAX_VALUE));
                file = new File(randomEnding);
            }
            file.createNewFile();
        } catch (IOException ioe){
            throw new FileWriterException("failed to init new file" + fileName + "." + ext, ioe);
        }
        if(file.canRead() && file.canWrite()){
            return file;
        }
        return null;
    }
}
*/