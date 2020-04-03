package edu.mcdaniel.java2206.lab6_MalcolmWatts;

import edu.mcdaniel.java2206.lab6_MalcolmWatts.components.DowFileReader;
//import edu.mcdaniel.java2206.lab6_MalcolmWatts.components.CombinedFileWriter;
import edu.mcdaniel.java2206.lab6_MalcolmWatts.components.InflationRateFileReader;
import edu.mcdaniel.java2206.lab6_MalcolmWatts.exceptions.DowFileReaderException;
import edu.mcdaniel.java2206.lab6_MalcolmWatts.exceptions.InflationRateFileReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is designed to start/wrap your class.
 */
@SpringBootApplication
public class Application {

    //=============================================================================================
    // Private Assets
    //=============================================================================================

    /**
     * The logging mechanism of the class.
     */
    private static Logger log = LogManager.getLogger(Application.class);


    //=============================================================================================
    // Constructor(s)
    //=============================================================================================

    /**
     * The constructor for the Spring Boot application
     */
    public Application() {
        //This constructor is empty as no additional information need be provided.
        //This is an implemented No Argument Constructor.
    }


    //=============================================================================================
    // Major Methods
    //=============================================================================================

    //No major methods to implement


    //=============================================================================================
    // PSVM
    //=============================================================================================

    /**
     * This method actually accomplishes the running of the code we are seeking to write
     *
     * @param args the input from the command line.
     */
    public static void main(String[] args) {

        //===// Spring Application Hook //=======================================================//
        SpringApplication.run(Application.class, args);

        //===// User Defined Behavior //=========================================================//
        //Please start here and make the application work!
        try {
            InflationRateFileReader inflationRateFileReader = new InflationRateFileReader();
            inflationRateFileReader.validate();

            inflationRateFileReader.setUp();

            inflationRateFileReader.read();

            Map<Integer, Double> inflationRates = inflationRateFileReader.getInflationRates();
            Map<Integer, Date> inflationDates = inflationRateFileReader.getInflationDates();


            Set<Map.Entry<Integer, Double>> entrySet = inflationRates.entrySet();
            for (Map.Entry<Integer, Double> entry : entrySet) {
                log.info("The position {} is associated with an average inflation rate of {}% for year {}.",
                        entry.getKey(), entry.getValue(),
                        inflationDates.get(entry.getKey()));

            }

        } catch (NullPointerException | IOException | InflationRateFileReaderException npe) {
            log.error(npe);
        }

        try {
            DowFileReader dowFileReader = new DowFileReader();
            dowFileReader.validate();
            dowFileReader.setUp();
            dowFileReader.read();

            Map<Integer, Double> opens = dowFileReader.getDowOpens();
            Map<Integer, Double> highs = dowFileReader.getDowHighs();
            Map<Integer, Double> lows = dowFileReader.getDowLows();
            Map<Integer, Double> closes = dowFileReader.getDowClose();
            Map<Integer, Date> dates = dowFileReader.getDowDates();


            Set<Map.Entry<Integer, Double>> entrySet = opens.entrySet();
            for (Map.Entry<Integer, Double> entry : entrySet) {
                log.info("The Open {}. The High {}. The Low {}. The Close {}. For {}",
                        entry.getValue(),
                        highs.get(entry.getKey()),
                        lows.get(entry.getKey()),
                        closes.get(entry.getKey()),
                        dates.get(entry.getKey()).toString()
                );

            }
        } catch (DowFileReaderException dfre) {
            log.error(dfre);
        }

/*
        CombinedFileWriter combinedFileWriter = new CombinedFileWriter("temp1", "txt")
                .withNamedFile().validate();
        List<String> infoToFile.add("Hello World");

        boolean wrote = false;
        try{
            wrote = combinedFileWriter.writeFileContents(infoToFile);
        } catch(IOException ioe){
            log.error(new FileWriterException("Failed to Write.", ioe));
        }
        if(wrote){
            log.info("SUCCESS!");
        }


    }

*/
        //=============================================================================================
        // Minor Methods(s)
        //=============================================================================================

        //No minor methods made for this class


        //=============================================================================================
        // Getters and Setters
        //=============================================================================================

        //No private assets we want to expose in this class
    }
}