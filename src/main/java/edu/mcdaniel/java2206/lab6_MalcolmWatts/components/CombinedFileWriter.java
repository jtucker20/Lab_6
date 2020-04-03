/*package edu.mcdaniel.java2206.lab6_MalcolmWatts.components;
import java.io.File;
import java.util.List;

public class CombinedFileWriter {
    private File file;
    private String fileName;
    private String ext;

    public CombinedFileWriter(){

    }
    public CombinedFileWriter(String fileName, String ext){
        this.fileName = fileName;
        this.ext = ext;
    }


    public CombinedFileWriter withDefaultFile(){
        this.file = (new FileCreator()).createFileSafe(this.fileName, this.ext);
        return this;
    }
    public void build(){
        this.file = (new FileCreator()).createFileSafe(this.fileName, this.ext);
    }
    public CombinedFileWriter withNamedFile(){
        this.file = (new FileCreator()).createFileSafe(this.fileName, this.ext);
        return this;
    }
    public CombinedFileWriter validate(){
        if(this.file == null){
            throw new FileWriterException("FOUND NULL FILE");
        }
        return this;
    }


    public boolean writeFileContents(List<String> fileContents){
        CombinedFileWriter combinedFileWriter = new CombinedFileWriter(this.file);
        for(String line : fileContents){
            combinedFileWriter.write(line);
        }
    }
}
*/