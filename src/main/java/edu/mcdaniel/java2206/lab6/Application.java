package edu.mcdaniel.java2206.lab6;

import edu.mcdaniel.java2206.lab6.components.DowFileReader;
import edu.mcdaniel.java2206.lab6.components.InflationRateFileReader;
import edu.mcdaniel.java2206.lab6.exceptions.DowFileReaderException;
import edu.mcdaniel.java2206.lab6.exceptions.InflationRateFileReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Iterator;
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
    public Application(){
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

        } catch (NullPointerException | InflationRateFileReaderException npe){
            log.error(npe);
        }

        //While debugging my code, this was a way for me to see
        // how far the code was getting before it was getting hung up
        try{
            DowFileReader dowFileReader = new DowFileReader();
            log.error("We are here");
            dowFileReader.validate();
            log.error("We are now here");
            dowFileReader.setUp();
            log.error("We are almost there");
            dowFileReader.read();
            log.error("We are at the end");

            Map<Integer, Double> opens = dowFileReader.getDowOpens();
            Map<Integer, Double> highs = dowFileReader.getDowHighs();
            Map<Integer, Double> lows = dowFileReader.getDowLows();
            Map<Integer, Double> closes = dowFileReader.getDowClose();
            Map<Integer, Date> dates = dowFileReader.getDowDates();
            log.error(dates.size());

            Set<Map.Entry<Integer, Double>> entrySet = opens.entrySet();
            for (Map.Entry<Integer, Double> entry : entrySet) {
                log.error("The Open {}. The High {}. The Low {}. The Close {}. For {}",
                                entry.getValue(),
                                highs.get(entry.getKey()),
                                lows.get(entry.getKey()),
                                closes.get(entry.getKey()),
                                dates.get(entry.getKey()).toString()
                                );

            }
        } catch (DowFileReaderException dfre){
            log.error(dfre);
        }

    }


    //=============================================================================================
    // Minor Methods(s)
    //=============================================================================================

    //No minor methods made for this class


    //=============================================================================================
    // Getters and Setters
    //=============================================================================================

    //No private assets we want to expose in this class

}
