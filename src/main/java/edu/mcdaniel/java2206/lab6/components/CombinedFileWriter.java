package edu.mcdaniel.java2206.lab6.components;

import edu.mcdaniel.java2206.lab6.exceptions.FileWriterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CombinedFileWriter {

    // Private Assets
    private File file;
    private String fileName;
    private String ext;

    //Constructor(s)
    public CombinedFileWriter(){
        //Must make a valid start point.
        this.fileName = "temp";
        this.ext = "txt";
    }

    public CombinedFileWriter(String fileName, String ext){
        // Holding onto the variable names
        this.fileName = fileName;
        this.ext = ext;

    }


    //Major Methods
    //Pattern "With"
    public CombinedFileWriter withNamedFile(){
        this.file = (new FileCreator()).createFileSafeRecursive(this.fileName, this.ext);
        return this;
    }

    public CombinedFileWriter validate(){
        //Check for null file.
        if(this.file == null){
            throw new FileWriterException("FOUND NULL FILE!", null);
        }

        return this;
    }
//
//    //Pattern "build"
//    public void buildVanilla(){
//        this.file = (new FileCreator()).createFileSafe(this.fileName, this.ext);
//    }
//
//    //Pattern "build/with"
//    public CombinedFileWriter build(){
//        this.file = (new FileCreator()).createFileSafe(this.fileName, this.ext);
//        return this;
//    }

    /**
     * Write out the file
     */
    public boolean writeFileContents(List<String> fileContents) throws IOException {
        //Opening a fileWritter for this file.
        FileWriter fileWriter = new FileWriter(this.file);
        for(String line : fileContents) {
            fileWriter.write(line);
        }
        fileWriter.close();

        //Returns true if file was written to "correctly".
        return this.file.canRead();
    }

}
