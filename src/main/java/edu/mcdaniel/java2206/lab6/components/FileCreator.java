package edu.mcdaniel.java2206.lab6.components;

import edu.mcdaniel.java2206.lab6.exceptions.FileWriterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

public class FileCreator {
    //Private Assets
    private Logger log = LogManager.getLogger(FileCreator.class);

    public static final int MAX_ITERATIONS = 10;
    // Constructors
    public FileCreator(){ // This constructor is already provided!
        super();
    }

    // Major Methods.
    public File createFile(String fileName, String ext) throws IOException {
        File file = new File(fileName + "." + ext);
        if(file.createNewFile()){
            return file;
        } else {
            return null; // Since we don't want to overwrite a new file.
        }
    }












    /**
     * The purpose of this method is to create a file safely and not overwrite exiting files.
     * @param fileName the string of the file name
     * @param ext the three char extension of the file
     * @return
     */
    public File createFileSafeRecursive(String fileName, String ext){
        //Checking bad conditions
        if(fileName == null
                || fileName.isEmpty()
                || fileName.isBlank()
                || !Pattern.matches("^[a-zA-Z0-9]+$", fileName)){
            fileName = "temp";
        }
        // Have to check for null, empty, or blank. Also checking for length =3
        // Since that's the standard for file extensions.
        if(ext == null
                || ext.isEmpty()
                || ext.isBlank()
                || ext.length() != 3
                || !Pattern.matches("^[a-zA-Z0-9]+$", ext)){
            ext = "txt";
        }

        //Now We create the file.
        File file = null;
        try{
            //Try to make the new file.
            file = new File(fileName + "." + ext);

            //I need to know if it exists.
            //IF it exists, I DO NOT want to overwrite it.
            //TODO: LAB 7_8 Combo lab: Make this recursive.
            //A Recursive function is one that calls itself.
            if(file.exists()){
                file = recursivelySeekBlankFile(file, fileName, ext, 0);
            }
            //Since it's a new file, I need to create one.
            file.createNewFile();
            // We stop here because of the IOExceptions that could be thrown,  We want to
            // Break up as much as possible a long process into multiple try catches.
        } catch (IOException ioe){
            throw new FileWriterException("Failed to init new file" + fileName +"." + ext, ioe);
        }

        //Need to check if I can read and write to the file.
        if(file.canRead() && file.canWrite()){
            return file;
        }

        //Need to check if I can read.
        if(!file.canRead()){
            throw new FileWriterException("Failed to make a readable file at: "
                    + fileName + "." + ext, null);
        }
        //Nee to check if I can write.
        if(!file.canExecute()){
            throw new FileWriterException("Failed to make a readable file at: "
                    + fileName + "." + ext, null);
        }

        //Return null (nothing) when bad situations aren't caught.
        return null;
        //Make a Statement here.
    }




    private File recursivelySeekBlankFile(File file, String name, String ext, int n) {
        file = new File(name + n + "." + ext);
        log.info(name + n + "." + ext);
        // name = temp2
        // n = 4
        // ext = txt
        // fileName = temp24.txt
        if(file.exists() && n < MAX_ITERATIONS){
            file = recursivelySeekBlankFile(file, name, ext, n+1);
        }

        return file;
    }









    /**
     * The purpose of this method is to create a file safely and not overwrite exiting files.
     * @param fileName the string of the file name
     * @param ext the three char extension of the file
     * @return
     */
    public File createFileSafe(String fileName, String ext){
        //Checking bad conditions
        if(fileName == null
                || fileName.isEmpty()
                || fileName.isBlank()
                || !Pattern.matches("^[a-zA-Z0-9]+$", fileName)){
            fileName = "temp";
        }
        // Have to check for null, empty, or blank. Also checking for length =3
        // Since that's the standard for file extensions.
        if(ext == null
                || ext.isEmpty()
                || ext.isBlank()
                || ext.length() != 3
                || !Pattern.matches("^[a-zA-Z0-9]+$", ext)){
            ext = "txt";
        }

        //Now We create the file.
        File file = null;
        try{
            //Try to make the new file.
            file = new File(fileName + "." + ext);

            //I need to know if it exists.
            //IF it exists, I DO NOT want to overwrite it.
            //TODO: LAB 7_8 Combo lab: Make this recursive.
            //A Recursive function is one that calls itself.
            if(file.exists()){
                String randomEnding = fileName + (new Random()).nextInt(Integer.MAX_VALUE);
                //MAKE A NEW FILE THAT SHOULDN'T exist already.
                file = new File(randomEnding + "." + ext);
            }
            //Since it's a new file, I need to create one.
            file.createNewFile();
            // We stop here because of the IOExceptions that could be thrown,  We want to
            // Break up as much as possible a long process into multiple try catches.
        } catch (IOException ioe){
            throw new FileWriterException("Failed to init new file" + fileName +"." + ext, ioe);
        }

        //Need to check if I can read and write to the file.
        if(file.canRead() && file.canWrite()){
            return file;
        }

        //Need to check if I can read.
        if(!file.canRead()){
            throw new FileWriterException("Failed to make a readable file at: "
                    + fileName + "." + ext, null);
        }
        //Nee to check if I can write.
        if(!file.canExecute()){
            throw new FileWriterException("Failed to make a readable file at: "
                    + fileName + "." + ext, null);
        }

        //Return null (nothing) when bad situations aren't caught.
        return null;
    }

    // Minor Methods

    //Getters and Setters
}
