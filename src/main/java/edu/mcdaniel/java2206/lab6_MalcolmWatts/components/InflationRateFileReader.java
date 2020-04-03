package edu.mcdaniel.java2206.lab6_MalcolmWatts.components;

import edu.mcdaniel.java2206.lab6_MalcolmWatts.exceptions.InflationRateFileReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will read a file.  We will manipulate the data later.
 */
public class InflationRateFileReader {

    //=============================================================================================
    // Private Assets
    //=============================================================================================

    /**
     * This provides access to logging.
     */
    private final Logger log = LogManager.getLogger(InflationRateFileReader.class);

    /**
     * File that will hold the link to the Inflation Rate Data
     */
    private File irFile;

    /**
     * Field that will hold the values of interest
     */
    private Map<Integer, Double> inflationRates;

    /**
     * Field that will hold the dates corresponding to the integer
     */
    private Map<Integer, Date> inflationDates;


    //=============================================================================================
    // Constructor(s)
    //=============================================================================================

    /**
     * This No Argument constructor Will use the internal file.
     */
    public InflationRateFileReader() throws NullPointerException {
        // If this is called, we will use the internal file
        this.irFile = new File(
                this.getClass().getClassLoader().getResource("InfRate.csv").getFile()
        );
        //NOTE!  YOU MUST CATCH FOR A BAD INITIALIZATION OF THIS FILE!! IT WILL THROW A
        //Null pointer exception if the file isn't found!
    }

    /*
     * This one argument constructor will use the provided file.
     */
    public InflationRateFileReader(File file){
        this.irFile = file;  //Assumes this is not null...
    }


    //=============================================================================================
    // Major Methods
    //=============================================================================================

    /**
     * This major method initializes the file.
     */
    public void setUp() throws InflationRateFileReaderException {
        if(!validate()){
           throw new InflationRateFileReaderException("Invalid File Setup.");
        }

        //Here we setup/instantiate the Map as a hash map to hold the data.
        this.inflationRates = new HashMap<>();
        this.inflationDates = new HashMap<>();
    }

    /**
     * Major method to read in the data
     */



    public void read() throws IOException {
        if(!validate()){
            //We validate that all parts are actually active
            if(this.irFile == null){
                throw new IOException("the Interest Rate File cannot be read!");
            }
            if(!this.irFile.canRead()){
                throw new IOException("The Interest Rate File cannot be read!");
            }
        }
        log.trace("File Validated. {}", this.irFile.getAbsolutePath());
        if(this.inflationRates == null){
            this.inflationRates = new HashMap<>();
        }
        if(this.inflationDates == null){
            this.inflationDates = new HashMap<>();
        }
        readLines();
    }

    /**
     * Line reader functionality
     */
    public void readLines() throws InflationRateFileReaderException, IOException {
        //This is a try with resources block.  Inside of it, you have auto-closeable things, like a buffered reader
        // You use this EVERY time there is a resource with auto-closeable abilities.
        try(FileReader fileReader = new FileReader(this.irFile); //Here we make the file reader
            BufferedReader reader = new BufferedReader(fileReader)){  //Here we make a buffered reader

            String line = "";  //This will hold the lines from the file reader
            int linePos = 0;  //This will hold the position the data was taken from.
            while((line = reader.readLine()) != null){  //This complicated logic takes a line from the reader
                // and puts it into line.  Then checks to see if the line was null.
                //The line reader will return a null when eof hits.


                try{
                    //read a line into the data stream
                    readAline(line, linePos); //called for every line

                } catch (InflationRateFileReaderException irfre) {
                    log.error("skipped a line! {}", linePos);
                    log.error(irfre);
                    //Here we increment to let us know we got to a new line.
                    linePos++;
                }
            }
        }
    }

    /**
     * Method to parse a single line
     */
    public void readAline(String line, int linePos) throws InflationRateFileReaderException {
        if(linePos < 0){
            throw new InflationRateFileReaderException("Bad Line Position: " + linePos);
        }
        if(linePos < 3){
            return;  // We don't want to read in the header lines!
        }
        String[] lineParts = line.split(","); // Here we split on commas as this file is comma
        // separated.

        //I am expecting that there will be 14 columns, All filled with data.
        if(lineParts.length == 14) {
            String year = lineParts[0]; //Since the year is at pos 0;
            String avg = lineParts[13]; //Since the average is at pos 13.

            //Now to check we have values we are expecting!
            if(year == null || year.isBlank() || year.isEmpty() || avg == null || avg.isBlank() || avg.isEmpty()){
                throw new InflationRateFileReaderException("Bad Data in line " + linePos + " Line Value " + line);
            }


            //Here we set the date
            Date date = new Date((Integer.parseInt(year) - 1900), Calendar.DECEMBER, 31);  // WE subtract 1900
            // for some stupid reason.
            this.inflationDates.put(linePos, date);
            //Here we set the double
            String cleanAvg = clean(avg);
            double value = Double.parseDouble(cleanAvg);
            this.inflationRates.put(linePos, value);

            log.info("We had date: {}, and rate: {}", date.toString(), value);

        } else if(lineParts.length == 0 ) {
            throw new InflationRateFileReaderException("Couldn't read line " + linePos);
        } else {
            log.error("Line " + linePos + " was " + lineParts.length + " long and couldn't be read, it's being skipped");
        }

    }


    //=============================================================================================
    // Minor Methods(s)
    //=============================================================================================

    /**
     * validation method.
     * @return true if valid.
     */
    public boolean validate(){
        return this.irFile != null && this.irFile.canRead();
    }

    /**
     * Percent from string remover
     */
    private String clean(String input){
        return input.substring(0, input.indexOf('%'));
    }

    //=============================================================================================
    // Getters and Setters
    //=============================================================================================

    /**
     * Just to get the File name.
     */
    public String getFileName(){
        return this.irFile.getName();
    }

    /**
     * Just to get the file itself
     */
    public File getFile(){
        return this.irFile;
    }

    public Map<Integer, Double> getInflationRates() {
        return inflationRates;
    }

    public void setInflationRates(Map<Integer, Double> inflationRates) {
        this.inflationRates = inflationRates;
    }

    public Map<Integer, Date> getInflationDates() {
        return inflationDates;
    }

    public void setInflationDates(Map<Integer, Date> inflationDates) {
        this.inflationDates = inflationDates;
    }
}
