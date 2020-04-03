package edu.mcdaniel.java2206.lab6.components;

import edu.mcdaniel.java2206.lab6.exceptions.FileWriterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CombinedFileWriter {

    //Private assets
    private File file;
    private String fileName;
    private String ext;

    public CombinedFileWriter(){

    }

    public CombinedFileWriter(String fileName, String ext){
        // holding on to the variable names
        this.fileName = fileName;
        this.ext = ext;
        this.file = (new FileCreator()).createFileSafe(this.fileName,this.ext);
    }

    public CombinedFileWriter withNamedFile(){
        this.file = (new FileCreator()).createFileSafe(this.fileName, this.ext);
        return this;
    }

    public CombinedFileWriter validate(){
        if(this.file == null){
            throw new FileWriterException("FOUND NULL FILE!",null);
        }
        return this;
    }


    public boolean writeFileContents(List<String> fileContents) throws IOException {
        FileWriter fileWriter = new FileWriter(this.file);
        for(String line : fileContents){
            fileWriter.write(line);
        }

        fileWriter.close();

        return this.file.canRead();
    }

}

